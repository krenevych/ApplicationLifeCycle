package com.example.applicationlifecycle

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import timber.log.Timber

class SecondActivity : AppCompatActivity() {
    lateinit var app: CustomApplication
    lateinit var backgroundDetector: BackgroundDetector

    override fun onCreate(savedInstanceState: Bundle?) {

        app = this.applicationContext as CustomApplication

        super.onCreate(savedInstanceState)

        backgroundDetector = app.backgroundDetector

        Timber.tag(TAG).e("onCreate : SecondActivity")

        enableEdgeToEdge()
        setContentView(R.layout.activity_second)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val button: Button = findViewById(R.id.button_to_thrird_activity)

        button.setOnClickListener {
            val intent = Intent(this, TransparentActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        Timber.tag(TAG).e("onStart  : SecondActivity")
//        backgroundDetector.activityStarted()
    }

    override fun onStop() {
        super.onStop()
        Timber.tag(TAG).e("onStop   : SecondActivity")
//        backgroundDetector.activityStoped()
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.tag(TAG).e("onDestroy: SecondActivity")
    }

    override fun onResume() {
        super.onResume()
        Timber.tag(TAG).e("onResume : SecondActivity")
    }

    override fun onPause() {
        super.onPause()
        Timber.tag(TAG).e("onPause  : SecondActivity")
    }
}