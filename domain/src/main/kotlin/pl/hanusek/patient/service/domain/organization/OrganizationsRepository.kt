package pl.hanusek.patient.service.domain.organization

interface OrganizationsRepository {

    fun getOrCreateIfNotExists(organizationName: Organization.OrganizationName): Organization
    fun findById(value: String): Organization?
}
