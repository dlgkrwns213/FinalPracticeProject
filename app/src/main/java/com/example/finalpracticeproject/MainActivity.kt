package com.example.finalpracticeproject

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.room.Room
import com.example.finalpracticeproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tapBtn1.setOnClickListener {
            val navController = findNavController(R.id.nav_host)
            if (navController.currentDestination?.id != R.id.mainFragment) {
                val navOptions = NavOptions.Builder()
                    .setLaunchSingleTop(true)  // 이미 존재하면 새로 생성하지 않음
                    .setPopUpTo(R.id.mainFragment, false)  // MainFragment를 팝하지 않음
                    .build()
                navController.navigate(R.id.mainFragment, null, navOptions)
            }
        }
        binding.tapBtn2.setOnClickListener {
            val navController = findNavController(R.id.nav_host)
            if (navController.currentDestination?.id != R.id.recordFragment) {
                val navOptions = NavOptions.Builder()
                    .setLaunchSingleTop(true)  // 이미 존재하면 새로 생성하지 않음
                    .setPopUpTo(R.id.recordFragment, false)  // MainFragment를 팝하지 않음
                    .build()
                navController.navigate(R.id.recordFragment, null, navOptions)
            }
        }
        binding.tapBtn3.setOnClickListener {
            findNavController(R.id.nav_host).navigate(R.id.saveFragment)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }


}