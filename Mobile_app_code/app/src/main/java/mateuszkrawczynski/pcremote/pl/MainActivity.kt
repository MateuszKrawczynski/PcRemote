package mateuszkrawczynski.pcremote.pl

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import mateuszkrawczynski.pcremote.pl.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button.setOnClickListener(){
            val ip = binding.ip.getText().toString()
            val intent = Intent(applicationContext,gui::class.java)
            intent.putExtra("ip",ip)
            startActivity(intent)
        }
    }
}