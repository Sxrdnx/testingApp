package com.conacon.testcurse.di

import android.content.Context
import androidx.room.Room
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.conacon.testcurse.R
import com.conacon.testcurse.data.local.ShoppinDao
import com.conacon.testcurse.data.local.ShoppingIteDatabase
import com.conacon.testcurse.data.remote.PixaBayAPI
import com.conacon.testcurse.other.Constants.BASE_URL
import com.conacon.testcurse.other.Constants.DATABASE_NAME
import com.conacon.testcurse.repositories.DefaultShoppingRepository
import com.conacon.testcurse.repositories.ShoppingRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
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
    fun provideDefaultShoppingRepository(
        dao: ShoppinDao,
        api: PixaBayAPI
    )= DefaultShoppingRepository(dao,api) as ShoppingRepository

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

    @Singleton
    @Provides
    fun provideGlideInstance(
        @ApplicationContext context: Context
    ) = Glide.with(context).setDefaultRequestOptions(
        RequestOptions()
            .placeholder(R.drawable.ic_image)
            .error(R.drawable.ic_image)
    )

}