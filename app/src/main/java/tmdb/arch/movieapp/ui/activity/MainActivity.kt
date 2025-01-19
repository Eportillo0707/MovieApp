package tmdb.arch.movieapp.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import tmdb.arch.movieapp.databinding.ActivityMainBinding
import viewBinding

class MainActivity : AppCompatActivity() {
    private val binding by viewBinding(ActivityMainBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}
