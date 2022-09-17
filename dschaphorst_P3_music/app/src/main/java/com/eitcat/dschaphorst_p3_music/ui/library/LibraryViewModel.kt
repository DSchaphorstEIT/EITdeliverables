package com.eitcat.dschaphorst_p3_music.ui.library

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LibraryViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is library Fragment, but it was unable to be implemented in the given time."
    }
    val text: LiveData<String> = _text
}