package com.handsshot.foronefortwo.service

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import com.handsshot.foronefortwo.R

class BackgroundSoundService : Service() {
    var player: MediaPlayer? = null
    override fun onBind(arg0: Intent): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        player = MediaPlayer.create(this, R.raw.main_theme)
        player!!.isLooping = true // Set looping
        player!!.setVolume(100f, 100f)
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        player!!.start()
        return START_STICKY
    }

    override fun onStart(intent: Intent, startId: Int) {
        // TO DO
    }

    fun onStop() {}
    fun onPause() {}
    override fun onDestroy() {
        player!!.stop()
        player!!.release()
    }

    override fun onLowMemory() {}


}
