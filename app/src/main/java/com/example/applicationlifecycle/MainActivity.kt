package com.example.applicationlifecycle

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    lateinit var backgroundDetector: BackgroundDetector

    override fun onCreate(savedInstanceState: Bundle?) {

        val app = this.applicationContext as CustomApplication

        super.onCreate(savedInstanceState)

        Timber.tag(TAG).e("onCreate : MainActivity")

        backgroundDetector = app.backgroundDetector

        enableEdgeToEdge()
        setContentView(R.layout.activity_main)


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val button: Button = findViewById(R.id.buttonSecondActivity)

        button.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        Timber.tag(TAG).e("onStart  : MainActivity")

        backgroundDetector.activityStarted()
    }

    override fun onStop() {
        super.onStop()
        Timber.tag(TAG).e("onStop   : MainActivity")

        backgroundDetector.activityStopped()
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.tag(TAG).e("onDestroy: MainActivity")
    }

    override fun onResume() {
        super.onResume()

        Timber.tag(TAG).e("onResume : MainActivity")
    }

    override fun onPause() {
        super.onPause()

        Timber.tag(TAG).e("onPause  : MainActivity")
    }

    override fun onTopResumedActivityChanged(isTopResumedActivity: Boolean) {
        super.onTopResumedActivityChanged(isTopResumedActivity)

        Timber.tag(TAG).e("onTopResumedActivityChanged  : MainActivity isTopResumedActivity=$isTopResumedActivity")
    }


}