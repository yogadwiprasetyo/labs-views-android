package yprsty.android.playground

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import yprsty.android.playground.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnDoTask.setOnClickListener {
            lifecycleScope.launch(Dispatchers.Main) {
                binding.waitingScreen.frame.isVisible = true
                delay(3000)
                binding.waitingScreen.frame.isVisible = false
            }
        }

        supportFragmentManager.setFragmentResultListener("", this) { _, bundle ->
            val result = bundle.getString("bundleKey")
        }

    }
}