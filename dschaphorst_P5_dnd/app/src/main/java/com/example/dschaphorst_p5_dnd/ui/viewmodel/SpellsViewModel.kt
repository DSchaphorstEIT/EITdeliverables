package com.example.dschaphorst_p5_dnd.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.dschaphorst_p5_dnd.data.api.Open5eRepository
import com.example.dschaphorst_p5_dnd.data.model.domain.Spell
import com.example.dschaphorst_p5_dnd.data.model.domain.mapToSpellList
import com.example.dschaphorst_p5_dnd.ui.viewmodel.adapter.SpellsPagingSource
import com.example.dschaphorst_p5_dnd.util.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "SpellsViewModel"

@HiltViewModel
class SpellsViewModel @Inject constructor(
    private val ioDispatcher: CoroutineDispatcher,
    private val repository: Open5eRepository
) : ViewModel() {
    private val _spellsState: MutableLiveData<UIState> = MutableLiveData(UIState.LOADING)
    val spellsState: LiveData<UIState> get() = _spellsState
    var curSpell: Spell? = null
    val flowPager = Pager(
        PagingConfig(pageSize = 30)
    ) {
        SpellsPagingSource(repository)
    }.flow.cachedIn(viewModelScope)

    fun pullSpellsData() {
        viewModelScope.launch(ioDispatcher) {
            val flowHolder: Flow<UIState> = flow {
                emit(UIState.LOADING)
                try {
                    emit(UIState.SUCCESS(repository.getSpells().results.mapToSpellList()))
                } catch (e: Exception) {
                    emit(UIState.ERROR(e))
                    Log.e(TAG, "Caught Error: ${e.localizedMessage}")
                }
            }
            flowHolder.collect { _spellsState.postValue(it) }
        }
    }

    private val _text = MutableLiveData<String>().apply {
        value = "This is Fragment has not been implemented yet."
    }
    val text: LiveData<String> = _text
}