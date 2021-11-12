package com.example.syscostarwars.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.syscostarwars.repo.PlanetsRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import com.example.syscostarwars.data.Result

@ExperimentalCoroutinesApi
@HiltViewModel
class PlanetsViewModel @Inject  constructor(private val planetsRepo: PlanetsRepo) : ViewModel(){



    val userSelectedPlanet: MutableLiveData<Result> by lazy { MutableLiveData<Result>() }
    var currentPlanetResult: Flow<PagingData<Result>>? = null
    fun getAllPlanetFromServer(): Flow<PagingData<Result>> {
        val lastResult = currentPlanetResult
        return if (lastResult != null) {
            lastResult
        } else {
            val newResult: Flow<PagingData<Result>> = planetsRepo.getAllPlanetFromServer().cachedIn(viewModelScope)
            currentPlanetResult = newResult
            newResult
        }
    }
    
}