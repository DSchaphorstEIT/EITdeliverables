package com.eitcat.dschaphorst_p3_music.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "song_list")
data class Song(
    @PrimaryKey val trackID: Int,
    val artistName: String = "",
    val collectionName: String = "",
    val trackName: String = "",
    val artistViewUrl: String = "",
    val collectionViewUrl: String = "",
    val trackViewUrl: String = "",
    val previewUrl: String = "",
    val artworkUrl30: String = "",
    val artworkUrl60: String = "",
    val artworkUrl100: String = "",
)

fun List<MusicNetworkData>
        .mapToSongList(): List<Song> =
    this.map {
        Song(
            trackID = it.trackId,
            artistName = it.artistName,
            collectionName = it.collectionName,
            trackName = it.trackName,
            artistViewUrl = it.artistViewUrl,
            collectionViewUrl = it.collectionViewUrl,
            trackViewUrl = it.trackViewUrl,
            previewUrl = it.previewUrl,
            artworkUrl30 = it.artworkUrl30,
            artworkUrl60 = it.artworkUrl60,
            artworkUrl100 = it.artworkUrl100
        )
    }

fun MusicNetworkData.mapToSong(): Song =
    Song(
        trackID = this.trackId,
        artistName = this.artistName,
        collectionName = this.collectionName,
        trackName = this.trackName,
        artistViewUrl = this.artistViewUrl,
        collectionViewUrl = this.collectionViewUrl,
        trackViewUrl = this.trackViewUrl,
        previewUrl = this.previewUrl,
        artworkUrl30 = this.artworkUrl30,
        artworkUrl60 = this.artworkUrl60,
        artworkUrl100 = this.artworkUrl100
    )