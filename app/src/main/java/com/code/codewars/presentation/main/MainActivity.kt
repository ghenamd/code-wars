package com.code.codewars.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.code.codewars.R
import com.code.codewars.data.remote.ChallengeDto
import com.code.codewars.databinding.ActivityMainBinding
import com.code.codewars.utils.Common.CHALLENGE_DTO
import com.code.codewars.utils.Common.CHALLENGE_DTO_LIST

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.main_graph) as NavHostFragment
        navController = navHostFragment.findNavController()
        setSupportActionBar(findViewById(R.id.main_toolbar))
        setupActionBarWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}