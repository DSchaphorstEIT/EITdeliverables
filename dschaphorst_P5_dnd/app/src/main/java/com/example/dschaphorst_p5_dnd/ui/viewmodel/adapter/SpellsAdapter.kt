package com.example.dschaphorst_p5_dnd.ui.viewmodel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dschaphorst_p5_dnd.databinding.SpellCardBinding
import com.example.dschaphorst_p5_dnd.data.model.SpellsData
import com.example.dschaphorst_p5_dnd.data.model.domain.Spell

class SpellsAdapter(
    private val spellsDataSet: MutableList<Spell> = mutableListOf(),
    private val onSpellClickHandler: (Spell) -> Unit
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        SpellViewHolder(SpellCardBinding.inflate(LayoutInflater.from(parent.context),
            parent, false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as SpellViewHolder).bind(spellsDataSet[position], onSpellClickHandler)
    }

    override fun getItemCount(): Int = spellsDataSet.size

    fun setSpellData(spells: List<Spell>) {
        spellsDataSet.clear()
        spells.forEach{
            spellsDataSet.add(it)
        }
        notifyDataSetChanged()
    }
}

class SpellViewHolder(private val binding: SpellCardBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(spell: Spell, onSpellClickHandler: (Spell) -> Unit) {
        binding.spellName.text = spell.name
        binding.spellSchool.text = spell.school
        if (spell.concentration){
            val dur = "Concentration, " + spell.duration
            binding.spellDuration.text = dur
        } else {
            binding.spellDuration.visibility = View.GONE
        }
        if (spell.ritual){
            val ritual = "Ritual"
            binding.spellAction.text = ritual
        } else if (spell.castingTime != "1 action") {
            binding.spellAction.text = spell.castingTime.split(",").first()
        } else {
            binding.spellAction.visibility = View.GONE
        }
        binding.spellComponents.text = spell.components
        binding.spellLevel.text = spell.level
        binding.root.setOnClickListener { onSpellClickHandler.invoke(spell) }
    }
}