package com.example.dschaphorst_p5_dnd.ui.viewmodel.adapter

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.dschaphorst_p5_dnd.data.model.domain.Spell
import javax.inject.Inject

class SpellPagingSource @Inject constructor(

) : PagingSource<Int, Spell> {
    override fun getRefreshKey(state: PagingState<Int, Spell>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Spell> {

    }

}