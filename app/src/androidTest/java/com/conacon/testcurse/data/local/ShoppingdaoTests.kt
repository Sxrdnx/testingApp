package com.conacon.testcurse.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.conacon.testcurse.getOrAwaitValue
import com.google.common.truth.Truth.assertThat

import kotlinx.coroutines.runBlocking

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * RunWith-la anotacion se usa pues junit es una libreria para testear codigo java y kotlin pero
 * aqui no es solo java o kotlin si no que estamos en un entorno de android
 * small esto indica a junit que nuestro test es unitario (no de integracion [mediumtest] ni de ui
 * [largetests])
 */


@RunWith(AndroidJUnit4::class)
@SmallTest
class ShoppingDaoTests {

    @get: Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: ShoppingIteDatabase
    private  lateinit var  shoppinDao: ShoppinDao
    @Before
    fun setUp(){
        /**
         * este constructor (inMemoriaDatabase) implica que la db se generara en ram y no sera una db real
         * solo sera asi para nuestro caso de uso
         */
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            ShoppingIteDatabase::class.java
        ).allowMainThreadQueries().build()
        shoppinDao = database.shoppingDao()
    }


    @After
    fun tearDown(){
        database.close()
    }


    @Test
    fun insertShoppingItem()= runBlocking {
        val item = ShoppingItem("name",1,2f,"url", id = 1)
        shoppinDao.insertShoppingItem(item)

        val allShoppingItems = shoppinDao.observeAllShoppingItems().getOrAwaitValue()

        assertThat(allShoppingItems).contains(item)

    }

    @Test
    fun deleteShoppingItem()= runBlocking {
        val item = ShoppingItem("name",1,2f,"url", id = 1)
        shoppinDao.insertShoppingItem(item)
        shoppinDao.deleteShoppingItem(item)

        val allShoppingItems = shoppinDao.observeAllShoppingItems().getOrAwaitValue()
        assertThat(allShoppingItems).doesNotContain(item)

    }


    @Test
    fun observeTotalPriceSum()= runBlocking{
        val item1 = ShoppingItem("name",4,6f,"url", id = 1)
        val item2 = ShoppingItem("name",6,4.4f,"url", id = 2)
        val item3 = ShoppingItem("name",0,2f,"url", id = 3)
        shoppinDao.insertShoppingItem(item1)
        shoppinDao.insertShoppingItem(item2)
        shoppinDao.insertShoppingItem(item3)
        val total = shoppinDao.observeTotalPrice().getOrAwaitValue()
        assertThat(total).isEqualTo(4 * 6f + 6 * 4.4f)
    }
}