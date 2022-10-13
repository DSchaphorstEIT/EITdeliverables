package com.example.dschaphorst_p5_dnd.ui.viewmodel.adapter

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.dschaphorst_p5_dnd.data.api.Open5eRepository
import com.example.dschaphorst_p5_dnd.data.model.domain.Spell
import com.example.dschaphorst_p5_dnd.data.model.domain.mapToSpellList
import com.example.dschaphorst_p5_dnd.util.NullResponseFromServer
import javax.inject.Inject

/**
 * The paging source used to update the data for each consecutive page from the Open5eRepository API
 */
class SpellsPagingSource @Inject constructor(
    private val repository: Open5eRepository
) : PagingSource<Int, Spell>() {
    override fun getRefreshKey(state: PagingState<Int, Spell>): Int? {
        return state.anchorPosition?.let {
            val anchorPage = state.closestPageToPosition(it)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Spell> {
        try {
            val nextPageNumber = params.key ?: 1
            val spellData = repository.getSpells(nextPageNumber)
            return LoadResult.Page(
                data = spellData.results.mapToSpellList(),
                prevKey = null,
                nextKey = (nextPageNumber + 1)
            )
        } catch (e: Exception) {
            throw NullResponseFromServer("Paging Error when talking to server.")
        }
    }

}