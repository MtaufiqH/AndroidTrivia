package com.example.androidtrivia

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.TaskStackBuilder
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.androidtrivia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var drawer: DrawerLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(
            this, R.layout.activity_main)

        drawer = binding.navDrawerLayout

        // nav controller
        val navController = this.findNavController(R.id.myNavHostFragment)


        // add navDrawer
        NavigationUI.setupActionBarWithNavController(this, navController,drawer)

        // add up button
        NavigationUI.setupActionBarWithNavController(this, navController)


    }



    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.myNavHostFragment)
        return NavigationUI.navigateUp(navController,drawer)
//        return navController.navigateUp()
    }


}