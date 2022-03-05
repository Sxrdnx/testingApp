package com.conacon.testcurse.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.conacon.testcurse.MainCoroutineRule
import com.conacon.testcurse.getOrAwaitValueTest
import com.conacon.testcurse.other.Constants
import com.conacon.testcurse.other.Status
import com.conacon.testcurse.repositories.FakeShoppingRepository
import com.google.common.truth.Truth.assertThat

import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ShoppingViewModelTest{
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get: Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewmodel: ShoppingViewModel

    @Before
    fun setUp(){
        viewmodel = ShoppingViewModel(FakeShoppingRepository())
    }

    @Test
    fun `insert shopping item with empty field,returns error`(){
        viewmodel.insertShoppingItem("name","","15")
        val value = viewmodel.insertShoppingItemStatus.getOrAwaitValueTest()
        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun `insert shopping item with too long name,returns error`(){
        val longName = buildString {
            for (i in 1..Constants.MAX_NAME_LENGTH + 1){
                append(1)
            }
        }
        viewmodel.insertShoppingItem(longName,"3","15")
        val value = viewmodel.insertShoppingItemStatus.getOrAwaitValueTest()
        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun `insert shopping item with too high amount,returns error`(){
        viewmodel.insertShoppingItem("name","999999999999999","15")
        val value = viewmodel.insertShoppingItemStatus.getOrAwaitValueTest()
        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun `insert shopping item with valid input,returns succes`(){
        viewmodel.insertShoppingItem("alex","10","46")
        val value = viewmodel.insertShoppingItemStatus.getOrAwaitValueTest()
        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.SUCCESS)
    }

}