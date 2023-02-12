package com.handsshot.foronefortwo.ui

import android.content.Intent
import android.os.Bundle
import com.handsshot.foronefortwo.databinding.ActivityMainBinding
import com.handsshot.foronefortwo.locale.LocaleHelper

class MainActivity : BaseActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LocaleHelper.updateLocale(this)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
    }

    private fun initViews() {
        initObservers()
        binding.apply {
            btnSettings.setOnClickListener {
                vibrate()
                startActivity(Intent(this@MainActivity, SettingsActivity::class.java))
            }

            btnHandSelection.setOnClickListener {
                vibrate()
                startActivity(Intent(this@MainActivity, HandSelectionActivity::class.java))
            }

            btn1Player.setOnClickListener {
                vibrate()
                startActivity(Intent(this@MainActivity, SinglePlayerActivity::class.java))
            }

            btn2Player.setOnClickListener {
                vibrate()
                startActivity(Intent(this@MainActivity, DoublePlayerActivity::class.java))
            }
        }

    }

    private fun initObservers() {
        preferencesDataStoreVM.getSoundOn.observe(this) {
            if (it) {
                startService(svc)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if (LocaleHelper.LANGUAGE_CHANGED) {
            LocaleHelper.LANGUAGE_CHANGED = false
            recreate()
            startActivity(Intent(this@MainActivity, SettingsActivity::class.java))
        }
    }

}