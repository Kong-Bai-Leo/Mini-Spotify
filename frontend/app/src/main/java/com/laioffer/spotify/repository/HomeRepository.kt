package com.laioffer.spotify.repository

import android.util.Log
import com.laioffer.spotify.datamodel.Section
import com.laioffer.spotify.network.NetworkApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HomeRepository @Inject constructor(private val networkApi: NetworkApi){
    suspend fun getHomeSection(): List<Section> = withContext(Dispatchers.IO) {
        delay(3000)
            val responseCall = networkApi.getHomeFeed()
            val response = responseCall.execute()
            val sections = response.body()
            Log.d("Network", response.toString())
            sections ?: listOf()
        }
    }
