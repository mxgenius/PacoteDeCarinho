package mxgenius.my.pacotedecarinho.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import mxgenius.my.pacotedecarinho.data.local.ClothingDatabase
import mxgenius.my.pacotedecarinho.data.repository.LocalDataSourceImpl
import mxgenius.my.pacotedecarinho.domain.repository.LocalDataSource
import mxgenius.my.pacotedecarinho.util.Constants.CLOTHING_DATABASE
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ) : ClothingDatabase{
        return Room.databaseBuilder(
            context,
            ClothingDatabase::class.java,
            CLOTHING_DATABASE
        ).build()
    }

    @Provides
    @Singleton
    fun provideLocalDataSource(
        database: ClothingDatabase
    ): LocalDataSource {
        return LocalDataSourceImpl(
            clothingDatabase = database
        )
    }
}