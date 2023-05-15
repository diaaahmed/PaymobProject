package com.esh7enly.paymobproject.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.esh7enly.paymobproject.RequestInterceptor
import com.fcm.data.remote.ApiInterface
import com.fcm.data.repo.ServicesRepoImpl
import com.fcm.domain.repo.ServicesRepo
import com.fcm.domain.usecase.GetServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

const val BASE_URL = "https://accept.paymobsolutions.com/api/"
@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    fun provideUseCase(repo: ServicesRepo):GetServices{
        return GetServices(repo)
    }

    @Provides
    fun provideRepo(apiInterface: ApiInterface):ServicesRepo {
        return ServicesRepoImpl(apiInterface)
    }

    @Provides
    @Singleton
    fun provideOkhttp(@ApplicationContext context: Context):OkHttpClient{

         val logging = HttpLoggingInterceptor()
         logging.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            .addInterceptor(ChuckerInterceptor(context))
            .addInterceptor(logging)
            .addNetworkInterceptor(RequestInterceptor())
            .connectTimeout(20,TimeUnit.MINUTES)
            .readTimeout(20,TimeUnit.MINUTES)
            .writeTimeout(20,TimeUnit.MINUTES)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiInterface {
        return retrofit.create(ApiInterface::class.java)
    }


}