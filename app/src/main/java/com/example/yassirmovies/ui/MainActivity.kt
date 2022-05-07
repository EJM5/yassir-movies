package com.example.yassirmovies.ui

import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.Fragment
import com.example.yassirmovies.R
import com.example.yassirmovies.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity: AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()
        customizeSplashScreen()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.movieConfigLiveData.observe(this, {
            if (!it.images.posterSizes.isNullOrEmpty()) {
                viewModel.imageBaseUrl = it.images.baseUrl + it.images.posterSizes[0]
                viewModel.imageBaseUrl = viewModel.imageBaseUrl.replace("http:", "https:")
            }
            if (supportFragmentManager.fragments.isEmpty()) {
                launchListFragment()
            }
            viewModel.isInitialSetupReady = true
        })

        viewModel.getMovieConfig()
    }

    // Remove splash screen once initial data is retrieved.
    private fun customizeSplashScreen() {
        val content: View = findViewById(android.R.id.content)
        content.viewTreeObserver.addOnPreDrawListener(
            object : ViewTreeObserver.OnPreDrawListener {
                override fun onPreDraw(): Boolean {
                    return if (viewModel.isInitialSetupReady) {
                        content.viewTreeObserver.removeOnPreDrawListener(this)
                        true
                    } else {
                        false
                    }
                }
            }
        )
    }

    // Very simple replace based navigation. No stack handling.
    private fun loadFragment(fragment: Fragment, tag: String) {
        // Find if the requested fragment already exists. If so, don't do anything
        val existingFragment = supportFragmentManager.findFragmentByTag(tag)
        if (existingFragment?.isVisible == true) return

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_frame, fragment, tag)
            .commit()
    }

    private fun launchListFragment() {
        loadFragment(MovieListFragment(), MovieListFragment.tag)
    }
}