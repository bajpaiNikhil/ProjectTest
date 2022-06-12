package com.example.projecttest


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.navigation.fragment.NavHostFragment
import com.example.projecttest.productList.ui.ProductListFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var bottomNavigationView: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//
//        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
//        val navController = findNavController(R.id.fragment)
//        val appBarConfiguration = AppBarConfiguration(setOf(R.id.firstFragment,R.id.secondFragment,R.id.thirdFragment))
//
//        setupActionBarWithNavController(navController,appBarConfiguration)
//
//        bottomNavigationView.setupWithNavController(navController)
//
//        Log.d("mainActivity" , "item is clicked $appBarConfiguration")




        bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment) as NavHostFragment

        bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.firstFragment ->{

                    navHostFragment.navController.navigate(R.id.homeFragment)
                    Log.d("mainActivity" , "item is clicked1 ${it.itemId}")
                    true
                }
                R.id.secondFragment ->{
                    navHostFragment.navController.navigate(R.id.editFragment)
                    Log.d("mainActivity" , "item is clicked2 ${it.itemId}")
                    true
                }
                R.id.thirdFragment ->{
                    navHostFragment.navController.navigate(R.id.settingFragment)
                    Log.d("mainActivity" , "item is clicked3 ${it.itemId}")
                    true
                }
                else -> {
                    Log.d("mainActivity" , "item is clicked4 ${it.itemId}")
                    false
                }

            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menu?.add( 0 ,0,0,"Add")
        return true

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.title){
            "Add" ->{

                bottomNavigationView.visibility = View.INVISIBLE
                val fragmentTransition = supportFragmentManager.beginTransaction()
                fragmentTransition.replace(R.id.mainActivity, ProductListFragment()).commit()

            }
        }
        return super.onOptionsItemSelected(item)
    }
}