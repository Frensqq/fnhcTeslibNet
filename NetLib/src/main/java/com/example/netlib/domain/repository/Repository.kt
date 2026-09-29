package com.example.netlib.domain.repository

import com.example.netlib.domain.model.ApplicantStatuses.ApplicantStatusesListResponse
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
import com.example.netlib.domain.model.NetworkResult
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

interface Repository {


    //dictionary
    suspend fun getDepartments(filter: String? = null): NetworkResult<DepartmentListResponse>

    suspend fun getCities(filter: String? = null): NetworkResult<CitiesListResponse>

    suspend fun getApplicantsStatuses(filter: String? = null): NetworkResult<ApplicantStatusesListResponse>

    suspend fun getCandidateStatuses(filter: String? = null): NetworkResult<CandidateStatusesListResponse>

    suspend fun getPositions(filter: String? = null): NetworkResult<PositionListResponse>

    //CandidateCardComments
    suspend fun getsCandidateCardComments(filter: String? = null): NetworkResult<CandidateCardsCommentsListResponse>

    suspend fun getCandidateCardComments(id: String): NetworkResult<CandidateCardsCommentsRecord>

    suspend fun postCandidateCardComments(data : CandidateCardsCommentsCreate): NetworkResult<CandidateCardsCommentsRecord>

    suspend fun patchCandidateCardComments(id: String, data : CandidateCardsCommentsUpdate): NetworkResult<CandidateCardsCommentsRecord>

    //CandidateCard
    suspend fun getsCandidateCard(filter: String? = null): NetworkResult<CandidateCardsListResponse>

    suspend fun getCandidateCard(id: String): NetworkResult<CandidateCardsRecord>

    suspend fun postCandidateCard(data : CandidateCardsCreate): NetworkResult<CandidateCardsRecord>

    suspend fun patchCandidateCard(id: String, data : CandidateCardsUpdate): NetworkResult<CandidateCardsRecord>

    //Vacancy
    suspend fun getVacancies(filter: String?): NetworkResult<VacanciesListResponse>

    suspend fun postVacancies(
        data: VacanciesCreate,
        files: List<FileUpload> = emptyList()
    ): NetworkResult<VacanciesRecord>

    suspend fun getVacancy(id: String): NetworkResult<VacanciesRecord>


    suspend fun patchVacancies(
        id: String,
        data: VacanciesUpdate,
        files: List<FileUpload> = emptyList()
    ): NetworkResult<VacanciesRecord>

    suspend fun deleteVacancies(id: String) : NetworkResult<Unit>

    suspend fun getsUser(filter: String? = null): NetworkResult<UserListResponse>

    suspend fun getUser(id: String): NetworkResult<UserRecord>

    suspend fun postUser(data : UserCreate, fileUpload: FileUpload? = null): NetworkResult<UserRecord>

    suspend fun postUserAuth(data : AuthWithPasswordRequest): NetworkResult<UserAuthResponse>

    suspend fun patchUser(id: String, data : UserUpdate, fileUpload: FileUpload? = null): NetworkResult<UserRecord>

    suspend fun getsApplicants(filter: String? = null): NetworkResult<ApplicantsListResponse>

    suspend fun getApplicants(id: String): NetworkResult<ApplicantsRecord>

    suspend fun postApplicants(data : ApplicantsCreate): NetworkResult<ApplicantsRecord>

    suspend fun patchApplicants(id: String, data : ApplicantsUpdate): NetworkResult<ApplicantsRecord>

}