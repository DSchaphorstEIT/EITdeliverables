package com.eitcat.dschaphorst_p4_movies

import android.app.TaskStackBuilder
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.eitcat.dschaphorst_p4_movies.databinding.ActivityMainBinding
import com.eitcat.dschaphorst_p4_movies.ui.viewmodel.MovieViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_now, R.id.navigation_soon
            )
        )
        navController.addOnDestinationChangedListener{ _, destination, _ ->
            if (destination.id != R.id.detailsFragment){
                ViewModelProvider(this)[MovieViewModel::class.java].movieHistList = emptyList()
            } else {
                ViewModelProvider(this)[MovieViewModel::class.java].videoHistList = emptyList()
            }
        }
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
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
}

