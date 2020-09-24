package com.curso.practicapruebafinal

import com.curso.practicapruebafinal.model.Repository
import com.curso.practicapruebafinal.model.remote.RetrofitClient
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    private val service = Repository()
    @Test
    fun addition_isCorrect() {
       service.getBreedsFromServer()
    }
}