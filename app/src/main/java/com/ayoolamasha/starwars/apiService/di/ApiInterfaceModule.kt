package com.ayoolamasha.starwars.apiService.di

import com.ayoolamasha.starwars.apiService.searchApiService.SearchApiServiceHelper
import com.ayoolamasha.starwars.apiService.searchApiService.SearchApiServiceHelperImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@[Module InstallIn(SingletonComponent::class)]
abstract class ApiInterfaceModule {

    @Binds
    abstract fun providesSearchApiServiceHelper(searchApiServiceHelperImpl: SearchApiServiceHelperImpl): SearchApiServiceHelper
}