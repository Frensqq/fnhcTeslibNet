package com.example.netlib.data.remote

import androidx.core.os.unregisterForAllProfilingResults
import com.example.netlib.domain.model.ApplicantStatuses.ApplicantStatusesListResponse
import com.example.netlib.domain.model.ApplicantStatuses.ApplicantStatusesRecord
import com.example.netlib.domain.model.Applicants.ApplicantsCreate
import com.example.netlib.domain.model.Applicants.ApplicantsListResponse
import com.example.netlib.domain.model.Applicants.ApplicantsRecord
import com.example.netlib.domain.model.Applicants.ApplicantsUpdate
import com.example.netlib.domain.model.CandidateCards.CandidateCardsCreate
import com.example.netlib.domain.model.CandidateCards.CandidateCardsListResponse
import com.example.netlib.domain.model.CandidateCards.CandidateCardsRecord
import com.example.netlib.domain.model.CandidateCards.CandidateCardsUpdate
import com.example.netlib.domain.model.CandidateCardsComments.CandidateCardsCommentsCreate
import com.example.netlib.domain.model.CandidateCardsComments.CandidateCardsCommentsListResponse
import com.example.netlib.domain.model.CandidateCardsComments.CandidateCardsCommentsRecord
import com.example.netlib.domain.model.CandidateCardsComments.CandidateCardsCommentsUpdate
import com.example.netlib.domain.model.CandidateStatuses.CandidateStatusesListResponse
import com.example.netlib.domain.model.Cities.CitiesListResponse
import com.example.netlib.domain.model.Department.DepartmentListResponse
import com.example.netlib.domain.model.FileUpload
import com.example.netlib.domain.model.Position.PositionListResponse
import com.example.netlib.domain.model.User.AuthWithPasswordRequest
import com.example.netlib.domain.model.User.UserAuthResponse
import com.example.netlib.domain.model.User.UserCreate
import com.example.netlib.domain.model.User.UserListResponse
import com.example.netlib.domain.model.User.UserRecord
import com.example.netlib.domain.model.User.UserUpdate
import com.example.netlib.domain.model.Vacancies.VacanciesCreate
import com.example.netlib.domain.model.Vacancies.VacanciesListResponse
import com.example.netlib.domain.model.Vacancies.VacanciesRecord
import com.example.netlib.domain.model.Vacancies.VacanciesUpdate
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.forms.MultiPartFormDataContent
import io.ktor.client.request.forms.formData
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.patch
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.encodeToJsonElement
import kotlinx.serialization.json.jsonObject
import java.io.File
import kotlin.collections.component1
import kotlin.collections.component2

class PBApi(private val client: HttpClient) {

    private val json = Json {
        explicitNulls = false
        encodeDefaults = true
    }


    private suspend inline fun<reified T> get(
        path: String,
        filter: String? = null
    ): T =
        client.get(path) {
            filter?.let { parameter("filter", it) }
        }.body()

    private suspend inline fun<reified T> post(
        path: String,
        data: Any
    ): T = client.post(path) {
            setBody(data)
            contentType(ContentType.Application.Json)
        }.body()

    private suspend inline fun<reified T> patch(
        path: String,
        data: Any
    ): T = client.patch(path) {
            setBody(data)
            contentType(ContentType.Application.Json)
        }.body()

    private suspend fun delete(
        path: String
    ){client.delete(path)}


    private inline fun <reified D> fields(data: D): Map<String, String> {
        val objectData = json.encodeToJsonElement(data).jsonObject

        return objectData.mapNotNull { (key, value) ->
            if (value == JsonNull) {
                null
            } else {
                key to if (value is JsonPrimitive) value.content else value.toString()
            }
        }.toMap()
    }

    private suspend inline fun <reified T, reified D> multipart(
        path: String,
        data: D,
        files: Map<String, List<FileUpload>>,
        patch: Boolean = false
    ): T {

        val body = MultiPartFormDataContent(
            formData {
                fields(data).forEach { (k, v) -> append(k, v) }

                files.forEach { (field, list) ->
                    list.forEach { file ->
                        append(
                            field,
                            file.byte,
                            Headers.build {
                                append(
                                    HttpHeaders.ContentDisposition,
                                    "filename=\"${file.name}\""
                                )
                                append(HttpHeaders.ContentType, file.mimeType)
                            }
                        )
                    }
                }
            }
        )
        return if (patch)
            client.patch(path) { setBody(body) }.body()
        else
            client.post(path) { setBody(body) }.body()
    }

    //dictionary
    suspend fun getDepartments(filter: String? = null): DepartmentListResponse =
        get("collections/departments/records", filter)

    suspend fun getCities(filter: String? = null): CitiesListResponse =
        get("collections/cities/records", filter)

    suspend fun getApplicantsStatuses(filter: String? = null): ApplicantStatusesListResponse =
        get("collections/applicant_statuses/records", filter)

    suspend fun getCandidateStatuses(filter: String? = null): CandidateStatusesListResponse =
        get("collections/candidate_statuses/records", filter)

    suspend fun getPositions(filter: String? = null): PositionListResponse =
        get("collections/positions/records",filter )

    //CandidateCardComments
    suspend fun getsCandidateCardComments(filter: String? = null): CandidateCardsCommentsListResponse =
        get("collections/candidate_card_comments/records", filter)

    suspend fun getCandidateCardComments(id: String): CandidateCardsCommentsRecord =
        get("collections/candidate_card_comments/records/$id")

    suspend fun postCandidateCardComments(data : CandidateCardsCommentsCreate): CandidateCardsCommentsRecord =
        post("collections/candidate_card_comments/records/", data)

    suspend fun patchCandidateCardComments(id: String, data : CandidateCardsCommentsUpdate): CandidateCardsCommentsRecord =
        patch("collections/candidate_card_comments/records/$id", data)

    //CandidateCard
    suspend fun getsCandidateCard(filter: String? = null): CandidateCardsListResponse =
        get("collections/candidate_cards/records", filter)

    suspend fun getCandidateCard(id: String): CandidateCardsRecord =
        get("collections/candidate_cards/records/$id")

    suspend fun postCandidateCard(data : CandidateCardsCreate): CandidateCardsRecord =
        post("collections/candidate_cards/records/", data)

    suspend fun patchCandidateCard(id: String, data : CandidateCardsUpdate): CandidateCardsRecord =
        patch("collections/candidate_cards/records/$id", data)

    //Vacancy
    suspend fun getVacancies(filter: String?): VacanciesListResponse =
        get("collections/vacancies/records", filter)

    suspend fun postVacancies(
        data: VacanciesCreate,
        files: List<FileUpload> = emptyList()
    ): VacanciesRecord =
        if (files.isEmpty())
            post("collections/vacancies/records", data)
        else
            multipart(
                "collections/vacancies/records",
                data,
                mapOf("files" to files))

    suspend fun getVacancy(id: String): VacanciesRecord =
        get("collections/vacancies/records/$id")

    suspend fun patchVacancies(
        id: String,
        data: VacanciesUpdate,
        files: List<FileUpload> = emptyList()
    ): VacanciesRecord =
        if (files.isEmpty())
            patch("collections/vacancies/records/$id", data)
        else
            multipart(
                "collections/vacancies/records/$id",
                data,
                mapOf("files+" to files),
                patch = true
            )

    suspend fun deleteVacancies(id: String) {
        delete("collections/vacancies/records/$id")
    }

    suspend fun getsUser(filter: String? = null): UserListResponse =
        get("collections/users/records", filter)

    suspend fun getUser(id: String): UserRecord =
        get("collections/users/records/$id")

    suspend fun postUser(data : UserCreate, fileUpload: FileUpload? = null): UserRecord =
        if (fileUpload == null){
            post("collections/users/records", data)
        } else multipart(
            "collections/users/records",
            data, mapOf("avatar" to listOf(fileUpload)),
        )

    suspend fun postUserAuth(data : AuthWithPasswordRequest): UserAuthResponse =
        post("collections/users/auth-with-password", data)

    suspend fun patchUser(id: String, data : UserUpdate, fileUpload: FileUpload? = null): UserRecord =
        if (fileUpload == null) {
            patch("collections/users/records/$id", data)
        } else multipart(
            "collections/users/records",
            data, mapOf("avatar" to listOf(fileUpload)), patch =  true,
        )

    suspend fun getsApplicants(filter: String? = null): ApplicantsListResponse=
        get("collections/applicants/records", filter)

    suspend fun getApplicants(id: String): ApplicantsRecord =
        get("collections/applicants/records/$id")

    suspend fun postApplicants(data : ApplicantsCreate): ApplicantsRecord =
        post("collections/applicants/records", data)

    suspend fun patchApplicants(id: String, data : ApplicantsUpdate): ApplicantsRecord =
        patch("collections/applicants/records/$id", data)


}