package com.eitcat.dschaphorst_p3_music.data.api

import com.eitcat.dschaphorst_p3_music.data.model.Song
import io.reactivex.Single

interface ApiService {

    fun getMusic(): Single<List<Song>>
}

class ApiServiceImpl: ApiService {
    override fun getMusic(): Single<List<Song>> {
        TODO("Network Call to Pull Song Data")
    }

}