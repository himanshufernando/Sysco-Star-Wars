package com.example.syscostarwars.repo

import NETWORK_PAGE_SIZE
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.syscostarwars.services.network.api.APIInterface
import kotlinx.coroutines.flow.Flow
import com.example.syscostarwars.data.Result
import com.example.syscostarwars.ui.fragment.planets.PlanetsPagingSource

class PlanetsRepo(private var client: APIInterface) {


    fun getAllPlanetFromServer(): Flow<PagingData<Result>> {
        return Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE, enablePlaceholders = false),
            pagingSourceFactory = { PlanetsPagingSource(client) }
        ).flow
    }




}