package com.example.dschaphorst_p5_dnd.ui.view

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dschaphorst_p5_dnd.R
import com.example.dschaphorst_p5_dnd.databinding.FragmentHomeBinding
import com.example.dschaphorst_p5_dnd.ui.viewmodel.SpellsAdapter
import com.example.dschaphorst_p5_dnd.ui.viewmodel.SpellsViewModel
import com.example.dschaphorst_p5_dnd.ui.viewmodel.adapter.SpellComparator
import com.example.dschaphorst_p5_dnd.util.UIState
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

private const val TAG = "HomeFragment"

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val spellsViewModel by lazy {
        ViewModelProvider(requireActivity())[SpellsViewModel::class.java]
    }

    private val spellsAdapter by lazy {
        SpellsAdapter(SpellComparator) { spell ->
            spellsViewModel.curSpell = spell
            binding.root.findNavController().navigate(R.id.action_navigation_home_to_spellDetailsFragment)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.homeRecycle.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = spellsAdapter
        }
        viewLifecycleOwner.lifecycleScope.launch {
            spellsViewModel.flowPager.collectLatest {
                spellsAdapter.submitData(it)
            }
        }

        spellsViewModel.spellsState.observe(viewLifecycleOwner) { state ->
            when(state) {
                is UIState.LOADING -> {
                    binding.loadingSpinner.visibility = View.VISIBLE
                    binding.homeRecycle.visibility = View.GONE
                }
                is UIState.SUCCESS -> {
                    binding.loadingSpinner.visibility = View.GONE
                    binding.homeRecycle.visibility = View.VISIBLE
                    spellsAdapter.setSpellData(state.spells)
                }
                is UIState.ERROR -> {
                    binding.loadingSpinner.visibility = View.GONE
                    binding.homeRecycle.visibility = View.GONE
                    AlertDialog.Builder(requireActivity())
                        .setTitle("Error Loading Spells")
                        .setMessage(state.e.localizedMessage)
                        .setNegativeButton("DISMISS") { dialog, _ ->
                            dialog.dismiss()
                        }
                        .setPositiveButton("Retry") { dialog, _ ->
                            spellsViewModel.pullSpellsData()
                            dialog.dismiss()
                        }
                        .create()
                        .show()
                }
            }
        }

        spellsViewModel.pullSpellsData()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}