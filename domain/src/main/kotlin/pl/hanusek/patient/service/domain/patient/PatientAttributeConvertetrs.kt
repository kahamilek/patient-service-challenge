package pl.hanusek.patient.service.domain.patient

import pl.hanusek.patient.service.shared.Jackson
import javax.persistence.AttributeConverter

internal object FullNameAttributeConverter: AttributeConverter<Patient.FullName, String> {
    override fun convertToDatabaseColumn(attribute: Patient.FullName): String {
        return mapper.writeValueAsString(attribute)
    }

    override fun convertToEntityAttribute(dbData: String): Patient.FullName {
        return mapper.readValue(dbData, Patient.FullName::class.java)
    }
}

internal object AddressAttributeConverter: AttributeConverter<Patient.Address, String> {
    override fun convertToDatabaseColumn(attribute: Patient.Address): String {
        return mapper.writeValueAsString(attribute)
    }

    override fun convertToEntityAttribute(dbData: String): Patient.Address {
        return mapper.readValue(dbData, Patient.Address::class.java)
    }
}
private val mapper = Jackson.mapper
