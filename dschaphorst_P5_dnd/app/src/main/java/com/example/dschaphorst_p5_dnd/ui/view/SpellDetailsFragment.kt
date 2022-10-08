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

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}