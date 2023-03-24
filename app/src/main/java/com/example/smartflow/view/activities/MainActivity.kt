package com.example.smartflow.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.smartflow.R
import com.example.smartflow.databinding.ActivityMainBinding
import com.example.smartflow.viewmodel.SmartFlowViewModel
/***
 * Created by collins Ihezie
 * on 24/03/2023
 */

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}