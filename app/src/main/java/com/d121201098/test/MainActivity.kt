package com.d121201098.test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.d121201098.test.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAdd.setOnClickListener {
            val intent = Intent(this,AddActivity::class.java)
            startActivity(intent)
        }

        val navControll = findNavController(R.id.fragment)
        binding.bottomNavigationView.setupWithNavController(navControll)

        navControll.addOnDestinationChangedListener{_,dest,_->
            if(dest.id==R.id.detailFragment){
                binding.btnAdd.visibility = View.GONE
                binding.bottomNavigationView.visibility = View.GONE
            }else{
                binding.btnAdd.visibility = View.VISIBLE
                binding.bottomNavigationView.visibility = View.VISIBLE
            }
        }
    }
}