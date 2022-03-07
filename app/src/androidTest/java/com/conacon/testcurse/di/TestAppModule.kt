package com.conacon.testcurse.di

import android.content.Context
import androidx.room.Room
import com.conacon.testcurse.data.local.ShoppingIteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named




@Module
@InstallIn(SingletonComponent::class)
object TestAppModule {
    /**
     * este constructor (inMemoriaDatabase) implica que la db se generara en ram y no sera una db real
     * solo sera asi para nuestro caso de uso
     */
    @Provides
    @Named("test_db")
    fun provideInMemoryDb(@ApplicationContext context: Context)=
        Room.inMemoryDatabaseBuilder(
            context,
            ShoppingIteDatabase::class.java).allowMainThreadQueries()
            .build()



}