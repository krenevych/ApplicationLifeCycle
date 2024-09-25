package com.example.applicationlifecycle

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.applicationlifecycle.databinding.ActivitySecondBinding
import timber.log.Timber
import kotlin.concurrent.thread

class SecondActivity : AppCompatActivity(), BackgroundDetector.Listener {
    private lateinit var backgroundDetector: BackgroundDetector
    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        val app = this.applicationContext as CustomApplication

        super.onCreate(savedInstanceState)

        backgroundDetector = app.backgroundDetector

        Timber.tag(TAG).e("onCreate : SecondActivity")

        enableEdgeToEdge()
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.buttonToThrirdActivity.setOnClickListener {
            val intent = Intent(this, TransparentActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        Timber.tag(TAG).e("onStart  : SecondActivity")
        backgroundDetector.registerListener(this)
        backgroundDetector.activityStarted()

        thread(start = true) {
            for (i in 1..100) {
                Handler(Looper.getMainLooper()).post{
                    Timber.tag(TAG).e("Thread is running: $i")
                }

                Thread.sleep(1000)
            }
        }

    }

    override fun onStop() {
        super.onStop()
        Timber.tag(TAG).e("onStop   : SecondActivity")
        backgroundDetector.activityStopped()
        backgroundDetector.unregisterListener(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.tag(TAG).e("onDestroy: SecondActivity")
    }

    override fun onResume() {
        super.onResume()
        Timber.tag(TAG).e("onResume : SecondActivity")

        binding.progressBar.visibility = View.VISIBLE
    }

    override fun onPause() {
        super.onPause()
        Timber.tag(TAG).e("onPause  : SecondActivity")

        binding.progressBar.visibility = View.GONE
    }

//    override fun onTopResumedActivityChanged(isTopResumedActivity: Boolean) {
//        super.onTopResumedActivityChanged(isTopResumedActivity)
//
//        progress.visibility = if (isTopResumedActivity) View.VISIBLE else View.GONE
//    }

    override fun onBackground() {
        Timber.tag(TAG).e("onBackground")
    }

    override fun onForeground() {
        Timber.tag(TAG).e("onForeground")
        Toast.makeText(this, "onForeground", Toast.LENGTH_SHORT).show()
    }
}