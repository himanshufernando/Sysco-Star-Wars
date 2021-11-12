package com.example.syscostarwars.repo

import com.example.syscostarwars.services.network.api.APIInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {


    @Singleton
    @Provides
    fun provideConversationRepository(aPIInterface: APIInterface): PlanetsRepo {
        return PlanetsRepo(
            client = aPIInterface
        )
    }


}