package com.eitcat.dschaphorst_p3_music.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "song_list")
data class Song(
    @PrimaryKey val trackID: Int,
    val artistName: String = "Invalid Name",
    val collectionName: String = "Invalid Name",
    val trackName: String = "Invalid Name",
    val artistViewUrl: String = "Invalid URL",
    val collectionViewUrl: String = "Invalid URL",
    val trackViewUrl: String = "Invalid URL",
    val previewUrl: String = "Invalid URL",
    val artworkUrl30: String = "Invalid URL",
    val artworkUrl60: String = "Invalid URL",
    val artworkUrl100: String = "Invalid URL",
)

fun List<SongData?>?
        .mapToSongList(): List<Song> =
    this?.map {
        Song(
            trackID = it?.trackId ?: 0,
            artistName = it?.artistName ?: "Invalid Name",
            collectionName = it?.collectionName ?: "Invalid Name",
            trackName = it?.trackName ?: "Invalid Name",
            artistViewUrl = it?.artistViewUrl ?: "Invalid URL",
            collectionViewUrl = it?.collectionViewUrl ?: "Invalid URL",
            trackViewUrl = it?.trackViewUrl ?: "Invalid URL",
            previewUrl = it?.previewUrl ?: "Invalid URL",
            artworkUrl30 = it?.artworkUrl30 ?: "Invalid URL",
            artworkUrl60 = it?.artworkUrl60 ?: "Invalid URL",
            artworkUrl100 = it?.artworkUrl100 ?: "Invalid URL"
        )
    } ?: emptyList()

fun SongData.mapToSong(): Song =
    Song(
        trackID = this.trackId ?: 0,
        artistName = this.artistName ?: "Invalid Name",
        collectionName = this.collectionName ?: "Invalid Name",
        trackName = this.trackName ?: "Invalid Name",
        artistViewUrl = this.artistViewUrl ?: "Invalid URL",
        collectionViewUrl = this.collectionViewUrl ?: "Invalid URL",
        trackViewUrl = this.trackViewUrl ?: "Invalid URL",
        previewUrl = this.previewUrl ?: "Invalid URL",
        artworkUrl30 = this.artworkUrl30 ?: "Invalid URL",
        artworkUrl60 = this.artworkUrl60 ?: "Invalid URL",
        artworkUrl100 = this.artworkUrl100 ?: "Invalid URL"
    )