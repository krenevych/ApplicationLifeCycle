package com.example.applicationlifecycle

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
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

        ProcessLifecycleOwner.get().lifecycle.addObserver(object : LifecycleEventObserver{
            override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
                Timber.tag("RRRR").e("onStateChanged: $event")
                Timber.tag("RRRR").e("currentState: ${source.lifecycle.currentState}")

                source.lifecycle.currentState
            }
        })

        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                Timber.tag(TAG).e("onActivityCreated: $activity")
            }

            override fun onActivityStarted(activity: Activity) {
                Timber.tag(TAG).e("onActivityStarted: $activity")
            }

            override fun onActivityResumed(activity: Activity) {
                Timber.tag(TAG).e("onActivityResumed: $activity")
            }

            override fun onActivityPaused(activity: Activity) {
                Timber.tag(TAG).e("onActivityPaused: $activity")
            }

            override fun onActivityStopped(activity: Activity) {
                Timber.tag(TAG).e("onActivityStopped: $activity")
            }

            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
                Timber.tag(TAG).e("onActivitySaveInstanceState: $activity")
            }

            override fun onActivityDestroyed(activity: Activity) {
                Timber.tag(TAG).e("onActivityDestroyed: $activity")
            }

        })

    }



}