package com.ayoolamasha.starwars.di


import com.ayoolamasha.starwars.BuildConfig
import com.ayoolamasha.starwars.network.NetworkService
import com.ayoolamasha.starwars.network.middleware.ConnectivityMiddleware
import com.ayoolamasha.starwars.network.middleware.MiddlewareProvider
import com.ayoolamasha.starwars.network.middleware.MiddlewareProviderImpl
import com.ayoolamasha.starwars.network.model.ResponseMessage
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @[Provides Singleton]
    fun provideRetrofit(networkFactory: NetworkService): Retrofit =
        networkFactory.createRetrofit(
            url = BuildConfig.BASE_URL,
            isDebug = BuildConfig.DEBUG
        )

    @[Provides Singleton]
    fun provideResponseJsonAdapter(moshi: Moshi): JsonAdapter<ResponseMessage> {
        val typeT = Types.newParameterizedType(ResponseMessage::class.java,  String::class.java)
        return moshi.adapter(typeT)
    }


    @[Provides Singleton]
    fun providesMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }


    @Provides
    @Singleton
    fun bindMiddlewareProvider(
        connectivityMiddlewareImpl:
        ConnectivityMiddleware
    ): MiddlewareProvider =
        MiddlewareProviderImpl.Builder()
            .add(middleware = connectivityMiddlewareImpl)
            .build()
}