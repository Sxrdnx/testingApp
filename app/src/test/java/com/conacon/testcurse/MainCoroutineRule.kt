package com.conacon.testcurse

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.rules.TestWatcher
import org.junit.runner.Description

/**
 * Clase nesesaria para generar un dispacher que corra en el test ya que
 * usar viewmodelScope.launch necesita lifecycle para su coroutina
 */

class MainCoroutineRule @OptIn(ExperimentalCoroutinesApi::class) constructor(
    private val dispacher: CoroutineDispatcher = StandardTestDispatcher()
): TestWatcher() {
    override fun starting(description: Description?) {
        super.starting(description)
        Dispatchers.setMain(dispacher)
    }

    override fun finished(description: Description?) {
        super.finished(description)
        Dispatchers.resetMain()
    }

}