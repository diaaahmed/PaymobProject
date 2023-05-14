package com.esh7enly.paymobproject


import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.esh7enly.paymobproject.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity()
{
    private val ui by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(ui.root)

        ui.Button1.setOnClickListener { getToken() }
       // ui.Button1.setOnClickListener { getTokenKiosk() }
       // ui.Button1.setOnClickListener { startActivity(Intent(this,NewActivity::class.java))}

        mainViewModel.kioskCode.observe(this)
        {
            ui.kioskCode.text = it
        }

        mainViewModel.move.observe(this)
        {
            if(it == true)
            {
                //startActivity(Intent(this,IframeActivity::class.java))
                startActivity(Intent(this,PayScreenActivity::class.java))
            }
        }
    }

    private fun getToken() {
        mainViewModel.getToken()
    }

    private fun getTokenKiosk() {
        mainViewModel.getTokenKiosk()
    }
}