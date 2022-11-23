package pl.hanusek.patient.service.domain.patient

import pl.hanusek.patient.service.domain.organization.Organization
import java.util.*
import javax.persistence.Convert
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "patient")
data class Patient private constructor(
    @Id
    val id: PatientId,
    @Convert(converter = FullNameAttributeConverter::class)
    val fullName: FullName,
    @Convert(converter = AddressAttributeConverter::class)
    val address: Address,
    val organizationId: Organization.OrganizationId
) {

    constructor(
        fullName: FullName,
        address: Address,
        organizationId: Organization.OrganizationId
    ) : this(
        id = PatientId(UUID.randomUUID().toString()),
        fullName = fullName,
        address = address,
        organizationId = organizationId
    )

    @JvmInline
    value class PatientId(
        val value: String
    )

    data class FullName(
        val firstName: String,
        val lastName: String
    )

    data class Address(
        val city: String,
        val postcode: String,
        val street: String?,
        val buildingNumber: String,
        val apartmentNumber: String?
    )
}