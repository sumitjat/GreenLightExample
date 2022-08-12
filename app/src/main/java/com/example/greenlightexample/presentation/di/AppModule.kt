package com.example.greenlightexample.presentation.di

import android.content.Context
import android.location.Location
import androidx.room.Room
import com.example.greenlightexample.data.ApiService
import com.example.greenlightexample.data.ResponseRepository
import com.example.greenlightexample.data.db.AppDatabase
import com.example.greenlightexample.data.db.LocationDao
import com.example.greenlightexample.data.models.ResponseData
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)

@Module
class AppModule {

    @Provides
    fun providesBaseUrl() = "https://run.mocky.io/"

    @Provides
    @Singleton
    fun getRetrofit(baseUrl: String): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    @Provides
    fun providesApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }


    @Provides
    @Singleton
    fun providesDataBase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "green_light_database"
        ).fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun provideChannelDao(appDatabase: AppDatabase): LocationDao {
        return appDatabase.locationDao()
    }


    @Provides
    @Singleton
    fun provides(apiService: ApiService, locationDao: LocationDao): ResponseRepository {
        return ResponseRepository(apiService, locationDao)
    }

}