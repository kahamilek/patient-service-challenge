package pl.hanusek.patient.service.infrastructure

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import pl.hanusek.patient.service.domain.organization.Organization
import pl.hanusek.patient.service.domain.organization.OrganizationsRepository

@Repository
internal class OrganizationsDbRepository(
    private val organizationsJpaRepository: OrganizationsJpaRepository
) : OrganizationsRepository {

    override fun getOrCreateIfNotExists(organizationName: Organization.OrganizationName): Organization {
        val newOrganization = Organization(organizationName)
        return organizationsJpaRepository.insertNewIfNotExists(
            newOrganization.id.value,
            newOrganization.name.formattedName
        ) ?: organizationsJpaRepository.findByName(newOrganization.name.formattedName)!!
    }

    override fun findById(id: String): Organization? {
        return organizationsJpaRepository.findById(id)
            .orElse(null)
    }


}

internal interface OrganizationsJpaRepository : JpaRepository<Organization, String> {
    @Query(
        nativeQuery = true,
        value = """
            INSERT INTO ${Organization.TABLE_NAME} (id, name)
            VALUES (:id, :name)
            ON CONFLICT DO NOTHING
            RETURNING *
        """
    )
    fun insertNewIfNotExists(id: String, name: String): Organization?
    fun findByName(formattedName: String): Organization?
}
