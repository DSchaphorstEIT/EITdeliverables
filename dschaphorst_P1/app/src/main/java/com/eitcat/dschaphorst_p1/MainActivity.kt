package com.eitcat.dschaphorst_p1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.eitcat.dschaphorst_p1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()

        binding.btnMain.setOnClickListener{
            Intent(baseContext, CreateAccount::class.java).apply {
                startActivity(this)
            }
        }
    }
}