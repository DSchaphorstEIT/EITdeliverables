package com.eitcat.dschaphorst_p3_music

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.eitcat.dschaphorst_p3_music.databinding.ActivityMainBinding
import com.eitcat.dschaphorst_p3_music.ui.home.HomeViewModel
import com.google.android.exoplayer2.ExoPlayer

/**
 * This is the initial [MainActivity] that is used to hold all fragments used. Should only
 * have code that is used for the core and navigation.
 *
 */
class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    /**
     * When activity is created, set the navigation and appbar properties.
     *
     * @param savedInstanceState
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_search, R.id.nav_library
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        binding.playerView.player = ViewModelProvider(this)[HomeViewModel::class.java].player
    }

    /**
     * Enable navigation back up from fragments.
     *
     * @return Navigate up destination.
     */
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    /**
     * Must release the [ExoPlayer] when activity is destroyed.
     */
    override fun onDestroy() {
        super.onDestroy()
        ViewModelProvider(this)[HomeViewModel::class.java].player.release()
    }
}