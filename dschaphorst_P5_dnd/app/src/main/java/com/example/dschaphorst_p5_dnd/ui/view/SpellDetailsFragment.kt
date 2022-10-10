package com.example.dschaphorst_p5_dnd.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.dschaphorst_p5_dnd.R
import com.example.dschaphorst_p5_dnd.databinding.FragmentHomeBinding
import com.example.dschaphorst_p5_dnd.databinding.FragmentSpellDetailsBinding
import com.example.dschaphorst_p5_dnd.ui.viewmodel.SpellsAdapter
import com.example.dschaphorst_p5_dnd.ui.viewmodel.SpellsViewModel

class SpellDetailsFragment : Fragment() {

    private var _binding: FragmentSpellDetailsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val spellsViewModel by lazy {
        ViewModelProvider(requireActivity())[SpellsViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSpellDetailsBinding.inflate(inflater, container, false)

        spellsViewModel.curSpell?.let {
            binding.spellInfoGroup.visibility = View.VISIBLE
            binding.spellName.text = it.name
            binding.spellLvl.text = it.lvl.toString()
            binding.spellSchool.text = it.school
            if (it.ritual) {
                binding.spellRitual.visibility = View.VISIBLE
                binding.ritual.visibility = View.VISIBLE
            } else {
                binding.spellRitual.visibility = View.GONE
                binding.ritual.visibility = View.GONE
            }
            if (it.range == ""){
                binding.range.visibility = View.GONE
                binding.spellRange.visibility = View.GONE
            } else {
                binding.range.visibility = View.GONE
                binding.spellRange.visibility = View.GONE
                binding.spellRange.text = it.range
            }
            val components = it.components + if (it.material != "") " (" + it.material + ")" else ""
            binding.spellComponents.text = components
            val duration = (if(it.concentration) "Concentration, " else "") + it.duration
            binding.spellDuration.text = duration
            binding.spellClasses.text = it.dndClass
            binding.spellDescription.text = it.desc
            if (it.higherLevel == "") {
                binding.highlevel.visibility = View.GONE
                binding.spellHighlvl.visibility = View.GONE
            } else {
                binding.highlevel.visibility = View.VISIBLE
                binding.spellHighlvl.visibility = View.VISIBLE
                binding.spellHighlvl.text = it.higherLevel
            }
        } ?: apply {
            binding.spellInfoGroup.visibility = View.GONE
            binding.spellName.text = "Invalid Spell"
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}