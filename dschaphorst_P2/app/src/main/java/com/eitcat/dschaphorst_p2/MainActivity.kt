package com.eitcat.dschaphorst_p2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import com.eitcat.dschaphorst_p2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        //setSupportActionBar(binding.toolbar)
//
//        val navController = binding.toolbar.findNavController()
//        val appBarConfig = AppBarConfiguration(navController.graph)
//        binding.toolbar.setupWithNavController(navController, appBarConfig)

        binding.btnAdd.setOnClickListener{
            binding.navHostFragment.findNavController().navigate(R.id.action_eventsDisplay_to_eventsEditor)
        }
    }
}