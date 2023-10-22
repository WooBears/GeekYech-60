package com.example.practice1bottomnav
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.practice1bottomnav.databinding.ActivityMainBinding
import com.example.practice1bottomnav.ui.data.local.Pref
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val pref by lazy {
        Pref(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navigationGraph

        val navController = findNavController(R.id.nav_host_fragment_container)

        if (!pref.isShow())
            navController.navigate(R.id.onBoardingFragment)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.homeFragment,
                R.id.dashboardFragment,
                R.id.notificationFragment,
                R.id.profileFragment,
                R.id.taskFragment
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        navController.addOnDestinationChangedListener{_, destination, _ ->
            if (destination.id == R.id.onBoardingFragment){
                navView.isVisible = false
                supportActionBar?.hide()
            }else {
                navView.isVisible = true
                supportActionBar?.show()
            }
        }
    }
}