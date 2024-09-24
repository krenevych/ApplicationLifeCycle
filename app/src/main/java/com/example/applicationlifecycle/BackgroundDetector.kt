package com.example.applicationlifecycle

import timber.log.Timber

class BackgroundDetector {

    interface Listener {
        fun onBackground()
        fun onForeground()
    }

    private var startedActivityNum = 0
    private val listeners = mutableListOf<Listener>()

    fun registerListener(listener: Listener){
        Timber.tag(TAG).e("registerListener  : $this")
        listeners.add(listener)
    }

    fun unregisterListener(listener: Listener){
        Timber.tag(TAG).e("unregisterListener: $this")
        listeners.remove(listener)
    }

    fun activityStarted() {
        startedActivityNum++
        if (startedActivityNum == 1){
            Timber.tag(TAG).e("activity foreground")

            listeners.map { it.onForeground() }
        }
    }

    fun activityStopped() {
        startedActivityNum--
        if (startedActivityNum == 0){
            Timber.tag(TAG).e("activity background")
            listeners.map { it.onBackground() }
        }
    }

}