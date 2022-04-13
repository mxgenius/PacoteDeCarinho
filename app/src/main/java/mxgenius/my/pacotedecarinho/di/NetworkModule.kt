package mxgenius.my.pacotedecarinho.di

import androidx.paging.ExperimentalPagingApi
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import mxgenius.my.pacotedecarinho.data.local.ClothingDatabase
import mxgenius.my.pacotedecarinho.data.remote.ClothingApi
import mxgenius.my.pacotedecarinho.data.repository.RemoteDataSourceImpl
import mxgenius.my.pacotedecarinho.domain.repository.RemoteDataSource
import mxgenius.my.pacotedecarinho.util.Constants.BASE_URL
import okhttp3.MediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@ExperimentalSerializationApi
@ExperimentalPagingApi
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .build()
    }



    @Provides
    @Singleton
    fun provideRetrofitInstance(okHttpClient: OkHttpClient): Retrofit {
        val contentType = MediaType.get("application/json")
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(Json.asConverterFactory(contentType))
            .build()
    }

    @Provides
    @Singleton
    fun provideClothingApi(retrofit: Retrofit): ClothingApi {
        return retrofit.create(ClothingApi::class.java)
    }



    @Provides
    @Singleton
    fun provideRemoteDataSource(
        clothingApi: ClothingApi,
        clothingDatabase: ClothingDatabase
    ): RemoteDataSource {
        return RemoteDataSourceImpl(
            clothingApi = clothingApi,
            clothingDatabase = clothingDatabase
        )
    }
}