package com.example.syscostarwars.ui.fragment.planets

import androidx.paging.PagingSource
import com.example.syscostarwars.services.network.api.APIInterface
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import com.example.syscostarwars.data.Result
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.BDDMockito.given

import org.mockito.Mock
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class PlanetsPagingSourceTest : TestCase(){

    @Mock
    lateinit var service: APIInterface

    lateinit var planetsPagingSource: PlanetsPagingSource



    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        planetsPagingSource = PlanetsPagingSource(service)
    }


    @Test
    fun reviewspaging() = runBlockingTest  {

        val error = RuntimeException("404", Throwable())
        given(service.getPlanets(any())).willThrow(error)
        val expectedResult = PagingSource.LoadResult.Error<Int, Result>(error)
        assertEquals(
            expectedResult, planetsPagingSource.load(
                PagingSource.LoadParams.Refresh(
                    key = 0,
                    loadSize = 1,
                    placeholdersEnabled = false
                )
            )
        )
    }
}