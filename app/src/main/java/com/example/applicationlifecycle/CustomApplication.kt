package com.example.applicationlifecycle

import android.app.Application
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ProcessLifecycleOwner
import timber.log.Timber

class CustomApplication : Application() {

    val backgroundDetector = BackgroundDetector()

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())


        Timber.i("CustomApplication: onCreate: ")

        ProcessLifecycleOwner.get().lifecycle.addObserver(object : DefaultLifecycleObserver {
            override fun onStart(owner: LifecycleOwner) {
                super.onStart(owner)

                Timber.tag(TAG).e("ProcessLifecycleOwner: activity foreground")
            }

            override fun onStop(owner: LifecycleOwner) {
                super.onStop(owner)

                Timber.tag(TAG).e("ProcessLifecycleOwner: activity background")
            }
        })
    }


}