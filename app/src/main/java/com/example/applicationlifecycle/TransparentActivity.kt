package com.example.applicationlifecycle

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import timber.log.Timber

class TransparentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_transparent)
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
    }

    override fun onStop() {
        super.onStop()

        Timber.tag(TAG).e("onStop   : TransparentActivity")
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