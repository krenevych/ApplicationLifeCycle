package com.example.applicationlifecycle

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.example.applicationlifecycle.databinding.ActivityMainBinding
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private lateinit var backgroundDetector: BackgroundDetector
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        lifecycle.addObserver(object : LifecycleEventObserver {
            override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
                Timber.tag(TAG).e("onStateChanged: $event")
            }

        })

        lifecycle.addObserver(object : DefaultLifecycleObserver {
            override fun onCreate(owner: LifecycleOwner) {
                super.onCreate(owner)
                Timber.e("onCreate: $owner")
            }

            override fun onDestroy(owner: LifecycleOwner) {
                super.onDestroy(owner)
                Timber.e("onDestroy: $owner")
            }

            override fun onPause(owner: LifecycleOwner) {
                super.onPause(owner)
                Timber.e("onPause: $owner")
            }

            override fun onResume(owner: LifecycleOwner) {
                super.onResume(owner)
                Timber.e("onResume: $owner")
            }

            override fun onStart(owner: LifecycleOwner) {
                super.onStart(owner)
                Timber.e("onStart: $owner")

            }

            override fun onStop(owner: LifecycleOwner) {
                super.onStop(owner)
                Timber.e("onStop: $owner")
            }
        })

        val app = this.applicationContext as CustomApplication

        super.onCreate(savedInstanceState)


        Timber.tag(TAG).e("onCreate : MainActivity")

        backgroundDetector = app.backgroundDetector

        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.buttonSecondActivity.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        Timber.tag(TAG).e("onStart  : MainActivity")

        backgroundDetector.activityStarted()

//        thread(start = true) {
//
//            for (i in 1..100) {
//                Handler(Looper.getMainLooper()).post{
//                    Timber.tag(TAG).e("Thread is running: $i")
//                }
//
//                Thread.sleep(1000)
//            }
//
//        }
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