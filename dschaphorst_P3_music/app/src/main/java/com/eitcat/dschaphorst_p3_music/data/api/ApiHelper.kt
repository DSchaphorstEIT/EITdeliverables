package com.eitcat.dschaphorst_p3_music.data.api

class ApiHelper(private val apiService: ApiService) {

    fun getMusic() = apiService.getMusic()
}