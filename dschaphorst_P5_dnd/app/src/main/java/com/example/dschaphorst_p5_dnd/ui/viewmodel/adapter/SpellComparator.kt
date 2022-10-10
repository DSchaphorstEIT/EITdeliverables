package com.example.dschaphorst_p5_dnd.ui.viewmodel.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.dschaphorst_p5_dnd.data.model.domain.Spell

object SpellComparator : DiffUtil.ItemCallback<Spell>() {
    override fun areItemsTheSame(oldItem: Spell, newItem: Spell): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Spell, newItem: Spell): Boolean {
        return oldItem == newItem
    }

}