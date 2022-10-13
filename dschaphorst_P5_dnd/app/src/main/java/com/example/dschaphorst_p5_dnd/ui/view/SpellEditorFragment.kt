package com.example.dschaphorst_p5_dnd.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.dschaphorst_p5_dnd.R
import com.example.dschaphorst_p5_dnd.data.model.domain.Spell
import com.example.dschaphorst_p5_dnd.databinding.FragmentSpellDetailsBinding
import com.example.dschaphorst_p5_dnd.databinding.FragmentSpellEditorBinding
import com.example.dschaphorst_p5_dnd.ui.viewmodel.SpellsViewModel
import com.example.dschaphorst_p5_dnd.util.Classes5e
import com.example.dschaphorst_p5_dnd.util.Schools5e
import com.example.dschaphorst_p5_dnd.util.SpellValidator

class SpellEditorFragment : Fragment() {

    private var _binding: FragmentSpellEditorBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val spellsViewModel by lazy {
        ViewModelProvider(requireActivity())[SpellsViewModel::class.java]
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSpellEditorBinding.inflate(inflater, container, false)

        binding.spellSchoolSpinner.adapter = ArrayAdapter(
            requireActivity(), android.R.layout.simple_spinner_item, Schools5e.values()
        )

        binding.spellClassesSpinner.adapter = ArrayAdapter(
            requireActivity(), android.R.layout.simple_spinner_item, Classes5e.values()
        )

        binding.materials.setOnClickListener {
            if (binding.materials.isChecked) binding.componentsEdit.visibility = View.VISIBLE
            else binding.componentsEdit.visibility = View.GONE
        }
        binding.saveSpell.setOnClickListener {
            if (SpellValidator.isValid(binding)) {
                val lvlInt = Integer.parseInt(binding.spellLevel.text.toString())
                spellsViewModel.curSpell = Spell(
                    name = binding.spellName.text.toString(),
                    lvl = lvlInt,
                    level = SpellValidator.levelOrdinal(lvlInt),
                    school = binding.spellSchoolSpinner.selectedItem.toString(),
                    ritual = binding.spellRitual.isChecked,
                    castingTime = binding.spellCasting.text.toString(),
                    range = binding.spellRange.text.toString(),
                    components = SpellValidator.componentsString(
                        binding.verbal.isChecked, binding.somatic.isChecked, binding.materials.isChecked
                    ),
                    material = if (binding.materials.isChecked) binding.materials.text.toString() else "",
                    duration = binding.spellDuration.text.toString(),
                    concentration = binding.spellConcentration.isChecked,
                    dndClass = binding.spellClassesSpinner.selectedItem.toString(),
                    desc = binding.spellDescription.text.toString(),
                    higherLevel = binding.spellHighlvl.text.toString()
                    )
                Toast.makeText(requireActivity(), "Created Spell Successfully!", Toast.LENGTH_LONG).show()
                findNavController().popBackStack()
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}