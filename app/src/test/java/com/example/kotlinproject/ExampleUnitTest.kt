package com.example.kotlinproject

import com.example.kotlinproject.data.database.FavoritesEntity
import com.example.kotlinproject.data.database.ItemsEntity
import com.example.kotlinproject.data.database.dao.ItemsDao
import com.example.kotlinproject.data.items.ItemsRepositoryImpl
import com.example.kotlinproject.data.model.ItemsResponse
import com.example.kotlinproject.data.service.ApiService
import com.example.kotlinproject.data.service.ApiServiceSecond
import com.example.kotlinproject.domain.items.ItemsRepository
import com.example.kotlinproject.domain.model.ItemsModel
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotSame
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.createTestCoroutineScope
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import retrofit2.Response

class ExampleUnitTest {

    lateinit var itemsRepository: ItemsRepository

    @Mock
    lateinit var apiService: ApiService

    @Mock
    lateinit var apiServiceSecond: ApiServiceSecond

    @Mock
    lateinit var itemsDao: ItemsDao

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        itemsRepository = ItemsRepositoryImpl(apiService, apiServiceSecond, itemsDao)
    }

    @Test
    fun `getData request successful`() {
        val response = Response.success(ItemsResponse(listOf()))
        createTestCoroutineScope(TestCoroutineScope().testScheduler).launch {
            Mockito.`when`(apiService.getData()).thenReturn(response)
            itemsRepository.getData()

            verify(apiService, only()).getData()
        }
    }

    @Test
    fun `showData gave from database success`(){
        val itemsEntity = listOf( ItemsEntity(0,"", "", false))
        runBlocking {
            `when`(itemsDao.getItemsEntities()).thenReturn(itemsEntity)
            itemsRepository.showData()

            verify(itemsDao, times(1)).getItemsEntities()
        }
    }

    @Test
    fun `deleteItemByDescription success`(){
        runBlocking {
            doNothing().`when`(itemsDao).deleteItemEntityByDescription("description")
            itemsRepository.deleteItemByDescription("description")

            verify(itemsDao, times(1)).deleteItemEntityByDescription("description")
        }
    }

    @Test
    fun `getFavorites success`(){
        val favEntity = listOf<FavoritesEntity>()
        runBlocking {
            `when`(itemsDao.getFavoritesEntities()).thenReturn(favEntity)
            itemsRepository.getFavorites()

            verify(itemsDao, times(1)).getFavoritesEntities()
            assertEquals(favEntity, itemsDao.getFavoritesEntities())
            assertNotSame(itemsDao.getItemsEntities(), itemsDao.getFavoritesEntities())
        }
    }

    @Test(expected = Exception::class)
    fun `getData request error`() {
        createTestCoroutineScope(TestCoroutineScope().testScheduler).launch {
            Mockito.`when`(apiService.getData()).thenThrow(Exception())
            itemsRepository.getData()

            verify(apiService, only()).getData()
        }
    }

    @Test(expected = Exception::class)
    fun `showData gave from database error`(){
        runBlocking {
            `when`(itemsDao.getItemsEntities()).thenThrow(Exception())
            itemsRepository.showData()

            verify(itemsDao, times(1)).getItemsEntities()
        }
    }
}