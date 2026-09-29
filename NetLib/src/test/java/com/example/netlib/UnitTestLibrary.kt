package com.example.netlib

import com.example.netlib.data.remote.PBApiService
import com.example.netlib.domain.model.User.AuthWithPasswordRequest
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class UnitTestLibrary {


    private val api = PBApiService.instance

    @Test
    fun getDepart() = runBlocking{
        val data = api.getDepartments()
        assertTrue(data.totalItems >= 1)
    }

    @Test
    fun getCities() = runBlocking{
        val data = api.getCities()
        assertTrue(data.totalItems >= 1)
    }

    @Test
    fun getPosition() = runBlocking{
        val data = api.getPositions()
        assertTrue(data.totalItems >= 1)
    }

    @Test
    fun getApplicants_Statuses() = runBlocking{
        val data = api.getApplicantsStatuses()
        assertTrue(data.totalItems >= 1)
    }

    @Test
    fun getCandidateStatuses() = runBlocking{
        val data = api.getCandidateStatuses()
        assertTrue(data.totalItems >= 1)
    }


 
}