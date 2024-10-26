package com.schmitt.mlbweatherapp

import com.schmitt.mlbweatherapp.repo.ZipRepo
import org.junit.Assert.assertEquals
import org.junit.Test

class ZipRepoUnitTest {
    @Test
    fun zipRepoTest() {
        val zipRepo = ZipRepo()

        zipRepo.addDataToCache("11111", "Test Data")
        val repoResponse = zipRepo.getDataFromCache("11111");

        assertEquals("Test Data", repoResponse)
    }
}