package com.example.dschaphorst_p5_dnd.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.dschaphorst_p5_dnd.R
import com.example.dschaphorst_p5_dnd.databinding.FragmentCustomSpellsBinding
import com.example.dschaphorst_p5_dnd.databinding.FragmentDashboardBinding
import com.example.dschaphorst_p5_dnd.ui.viewmodel.SpellsViewModel

class CustomSpellsFragment : Fragment() {

    private var _binding: FragmentCustomSpellsBinding? = null

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
    ): View {

        _binding = FragmentCustomSpellsBinding.inflate(inflater, container, false)

        binding.createSpell.setOnClickListener {
            binding.root.findNavController().navigate(R.id.action_customSpellsFragment_to_spellEditorFragment)
        }

        binding.viewSpell.setOnClickListener {
            binding.root.findNavController().navigate(R.id.action_customSpellsFragment_to_spellDetailsFragment)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}