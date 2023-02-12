package com.handsshot.foronefortwo.ui

import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Vibrator
import androidx.appcompat.app.AppCompatActivity
import com.handsshot.foronefortwo.R
import com.handsshot.foronefortwo.locale.LocaleHelper
import com.handsshot.foronefortwo.preferences_datastore.PreferencesDataStoreVM
import com.handsshot.foronefortwo.service.BackgroundSoundService


open class BaseActivity : AppCompatActivity() {
    lateinit var svc: Intent
    lateinit var preferencesDataStoreVM: PreferencesDataStoreVM
    private var vibrationOn: Boolean = false
    private var soundOn: Boolean = false
    private lateinit var mp: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        svc = Intent(this, BackgroundSoundService::class.java)
        preferencesDataStoreVM = PreferencesDataStoreVM(application)
        super.onCreate(savedInstanceState)
        initViews()
    }

    private fun initViews() {
        preferencesDataStoreVM.getVibrationOn.observe(this) {
            vibrationOn = it
        }

        preferencesDataStoreVM.getSoundOn.observe(this) {
            soundOn = it
        }
        mp = MediaPlayer.create(this, R.raw.hit)
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(LocaleHelper.updateLocale(newBase))
    }

    fun vibrate() {
        if (vibrationOn) {
            val vibe = getSystemService(VIBRATOR_SERVICE) as Vibrator
            vibe.vibrate(50)
        }
    }

    fun makeHitSound() {
        if (soundOn) {
            mp.setVolume(100f, 100f)
            mp.start()
        }
    }


}