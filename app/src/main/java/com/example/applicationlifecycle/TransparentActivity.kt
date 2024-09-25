package com.example.applicationlifecycle

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.applicationlifecycle.databinding.ActivityTransparentBinding
import timber.log.Timber

class TransparentActivity : AppCompatActivity() {

    private lateinit var backgroundDetector: BackgroundDetector
    private lateinit var binding: ActivityTransparentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val app = this.applicationContext as CustomApplication
        backgroundDetector = app.backgroundDetector

        enableEdgeToEdge()
        binding = ActivityTransparentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        Timber.tag(TAG).e("onCreate : TransparentActivity")
    }

    override fun onDestroy() {
        super.onDestroy()

        Timber.tag(TAG).e("onDestroy: TransparentActivity")
    }

    override fun onStart() {
        super.onStart()

        Timber.tag(TAG).e("onStart  : TransparentActivity")

        backgroundDetector.activityStarted()
    }

    override fun onStop() {
        super.onStop()

        Timber.tag(TAG).e("onStop   : TransparentActivity")

        backgroundDetector.activityStarted()
    }

    override fun onResume() {
        super.onResume()

        Timber.tag(TAG).e("onResume : TransparentActivity")
    }

    override fun onPause() {
        super.onPause()

        Timber.tag(TAG).e("onPause  : TransparentActivity")
    }
}