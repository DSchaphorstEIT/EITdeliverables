package com.eitcat.dschaphorst_p4_movies.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.eitcat.dschaphorst_p4_movies.databinding.FragmentSoonBinding
import com.eitcat.dschaphorst_p4_movies.ui.viewmodel.MovieViewModel

class SoonFragment : Fragment() {

    private var _binding: FragmentSoonBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val movieViewModel by lazy {
        ViewModelProvider(requireActivity())[MovieViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSoonBinding.inflate(inflater, container, false)

        val textView: TextView = binding.textNotifications
        movieViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}