package com.example.applicationlifecycle

import timber.log.Timber

class BackgroundDetector {

    private var startedActivityNum = 0

    fun activityStarted() {
        startedActivityNum++
        if (startedActivityNum == 1){
            Timber.e("activity foreground")
        }
    }

    fun activityStoped() {
        startedActivityNum--
        if (startedActivityNum == 0){
            Timber.e("activity background")
        }
    }

}