package com.example.netlib.data.repository

import com.example.netlib.data.remote.PBApi
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
import com.example.netlib.domain.model.Error
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
import com.example.netlib.domain.network.Network
import com.example.netlib.domain.repository.Repository
import io.ktor.client.plugins.ResponseException
import okio.IOException

class RepositoryImpl(
    private val network: Network,
    private val api: PBApi
):  Repository {

    private suspend fun<T> saveApiCall(apiCall: suspend() -> T): NetworkResult<T>{
        if(!network.isConnected()){
            return NetworkResult.NoInternet
        }

        return try {
            NetworkResult.Success(apiCall())
        }catch (e: IOException){
            NetworkResult.NoInternet
        }catch (e: ResponseException){
            NetworkResult.ErrorResponse(
                Error(
                     status = e.response.status.value,
                    massage = e.message?:"Unknown error"
                )
            )
        }catch (e: Exception){
            NetworkResult.ErrorResponse(
                Error(
                    status = -1,
                    massage = e.message?:"Unknown error"
                )
            )
        }
    }


    override suspend fun getDepartments(filter: String?): NetworkResult<DepartmentListResponse>
    =saveApiCall { api.getDepartments(filter) }

    override suspend fun getCities(filter: String?): NetworkResult<CitiesListResponse> = saveApiCall {
        api.getCities(filter)
    }

    override suspend fun getApplicantsStatuses(filter: String?): NetworkResult<ApplicantStatusesListResponse> = saveApiCall {
        api.getApplicantsStatuses(filter)
    }

    override suspend fun getCandidateStatuses(filter: String?): NetworkResult<CandidateStatusesListResponse> = saveApiCall {
        api.getCandidateStatuses(filter)
    }

    override suspend fun getPositions(filter: String?): NetworkResult<PositionListResponse> = saveApiCall {
        api.getPositions(filter)
    }

    override suspend fun getsCandidateCardComments(filter: String?): NetworkResult<CandidateCardsCommentsListResponse> = saveApiCall {
        api.getsCandidateCardComments(filter)
    }

    override suspend fun getCandidateCardComments(id: String): NetworkResult<CandidateCardsCommentsRecord> = saveApiCall {
        api.getCandidateCardComments(id)
    }

    override suspend fun postCandidateCardComments(data: CandidateCardsCommentsCreate): NetworkResult<CandidateCardsCommentsRecord> = saveApiCall {
        api.postCandidateCardComments(data)
    }

    override suspend fun patchCandidateCardComments(
        id: String,
        data: CandidateCardsCommentsUpdate
    ): NetworkResult<CandidateCardsCommentsRecord> = saveApiCall {
        api.patchCandidateCardComments(id,data)
    }

    override suspend fun getsCandidateCard(filter: String?): NetworkResult<CandidateCardsListResponse> = saveApiCall {
        api.getsCandidateCard(filter)
    }


    override suspend fun getCandidateCard(id: String): NetworkResult<CandidateCardsRecord> = saveApiCall {
        api.getCandidateCard(id)
    }

    override suspend fun postCandidateCard(data: CandidateCardsCreate): NetworkResult<CandidateCardsRecord> = saveApiCall {
        api.postCandidateCard(data)
    }

    override suspend fun patchCandidateCard(
        id: String,
        data: CandidateCardsUpdate
    ): NetworkResult<CandidateCardsRecord> = saveApiCall {
        api.patchCandidateCard(id,data)
    }

    override suspend fun getVacancies(filter: String?): NetworkResult<VacanciesListResponse> = saveApiCall {
        api.getVacancies(filter)
    }

    override suspend fun postVacancies(
        data: VacanciesCreate,
        files: List<FileUpload>
    ): NetworkResult<VacanciesRecord> = saveApiCall {
        api.postVacancies(data,files)
    }

    override suspend fun getVacancy(id: String): NetworkResult<VacanciesRecord> = saveApiCall {
        api.getVacancy(id)
    }

    override suspend fun patchVacancies(
        id: String,
        data: VacanciesUpdate,
        files: List<FileUpload>
    ): NetworkResult<VacanciesRecord> = saveApiCall {
        api.patchVacancies(id,data,files)
    }

    override suspend fun deleteVacancies(id: String) = saveApiCall {
        api.deleteVacancies(id)
    }

    override suspend fun getsUser(filter: String?): NetworkResult<UserListResponse> = saveApiCall {
        api.getsUser(filter)
    }

    override suspend fun getUser(id: String): NetworkResult<UserRecord> = saveApiCall {
        api.getUser(id)
    }

    override suspend fun postUser(
        data: UserCreate,
        fileUpload: FileUpload?
    ): NetworkResult<UserRecord> = saveApiCall {
        api.postUser(data,fileUpload)
    }

    override suspend fun postUserAuth(data: AuthWithPasswordRequest): NetworkResult<UserAuthResponse> = saveApiCall {
        api.postUserAuth(data)
    }

    override suspend fun patchUser(
        id: String,
        data: UserUpdate,
        fileUpload: FileUpload?
    ): NetworkResult<UserRecord> = saveApiCall {
        api.patchUser(id,data,fileUpload)
    }

    override suspend fun getsApplicants(filter: String?): NetworkResult<ApplicantsListResponse> = saveApiCall {
        api.getsApplicants(filter)
    }

    override suspend fun getApplicants(id: String): NetworkResult<ApplicantsRecord> = saveApiCall {
        api.getApplicants(id)
    }

    override suspend fun postApplicants(data: ApplicantsCreate): NetworkResult<ApplicantsRecord> = saveApiCall {
        api.postApplicants(data)
    }

    override suspend fun patchApplicants(
        id: String,
        data: ApplicantsUpdate
    ): NetworkResult<ApplicantsRecord> = saveApiCall {
        api.patchApplicants(id,data)
    }


}