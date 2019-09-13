package com.mprzybylak.jackson.api

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

import com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME
import com.mprzybylak.reactivecars.utils.logger

@RestController(value = "documents/")
class DocumentsEndpoint {

    @PostMapping
    fun saveDocuments(@RequestBody requestApi: DocumentsApi): DocumentsApi =
        requestApi
            .also { logger.info("request & response: $requestApi") }

    companion object {
        val logger by logger()
    }
}

data class DocumentsApi(
    val documents: List<StatementGroup>
)

@JsonTypeInfo(use = NAME, include = PROPERTY)
@JsonSubTypes(value = [
    JsonSubTypes.Type(value = PersonGroup::class, name = "PersonGroup"),
    JsonSubTypes.Type(value = DocumentGroup::class, name = "DocumentGroup"),
    JsonSubTypes.Type(value = AgreementsGroup::class, name = "AgreementsGroup")
]
)
interface StatementGroup {
    val metadata: Map<String, String>
    val documents : List<String>
}

data class PersonGroup(override val metadata: Map<String, String>, override val documents: List<String>) : StatementGroup
data class DocumentGroup(override val metadata: Map<String, String>, override val documents: List<String>) : StatementGroup
data class AgreementsGroup(override val metadata: Map<String, String>, override val documents: List<String>) : StatementGroup