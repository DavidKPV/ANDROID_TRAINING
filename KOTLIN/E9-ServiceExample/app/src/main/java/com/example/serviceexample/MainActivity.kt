package com.example.serviceexample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.app.JobIntentService.enqueueWork
import com.example.serviceexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val SERVICE_ID = 1001
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun buttonClick(view: View) {
        //enqueueWork(this, MyJobIntentService::class.java, SERVICE_ID, intent)

        intent = Intent(this, MyService::class.java)
        startService(intent)
    }

}