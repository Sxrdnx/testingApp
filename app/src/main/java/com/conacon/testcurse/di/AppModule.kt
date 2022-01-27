package com.conacon.testcurse.di

import android.content.Context
import androidx.room.Room
import com.conacon.testcurse.data.local.ShoppingIteDatabase
import com.conacon.testcurse.data.remote.PixaBayAPI
import com.conacon.testcurse.other.Constants.BASE_URL
import com.conacon.testcurse.other.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.internal.managers.ApplicationComponentManager
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponentManager::class)
/**
 * la anotacion Install significa que se asegurara que el tiempo de vida de las dependencias
 * sera tan larga como nuestra applicacion
 */

object AppModule {
    @Singleton
    @Provides
    fun provideShoppinItemDatabase(
        @ApplicationContext context: Context
        ) = Room.databaseBuilder(context,ShoppingIteDatabase::class.java,DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideShoppingDao(
        database: ShoppingIteDatabase
    ) = database.shoppingDao()


    @Singleton
    @Provides
    fun providePixaBayApi(): PixaBayAPI{
        return  Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(PixaBayAPI::class.java)

    }
}