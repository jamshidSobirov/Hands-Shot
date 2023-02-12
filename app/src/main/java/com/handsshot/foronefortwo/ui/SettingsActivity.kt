package com.handsshot.foronefortwo.ui

import android.os.Bundle
import com.handsshot.foronefortwo.R
import com.handsshot.foronefortwo.databinding.ActivitySettingsBinding
import com.handsshot.foronefortwo.locale.LocaleHelper

class SettingsActivity : BaseActivity() {
    lateinit var binding: ActivitySettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(0, 0)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
    }

    private fun initViews() {
        binding.apply {
            setUpObservers()

            btnMenu.setOnClickListener {
                vibrate()
                this@SettingsActivity.finish()
            }

            rgSound.setOnCheckedChangeListener { _, i ->
                if (i == R.id.rbSoundOn) {
                    preferencesDataStoreVM.setSoundOn(true)
                    startService(svc)
                } else {
                    preferencesDataStoreVM.setSoundOn(false)
                    stopService(svc)
                }
            }

            rgVibration.setOnCheckedChangeListener { _, i ->
                if (i == R.id.rbVibrationOn) {
                    preferencesDataStoreVM.setVibrationOn(true)
                } else {
                    preferencesDataStoreVM.setVibrationOn(false)
                }
            }


            rbEng.setOnClickListener {
                vibrate()
                if (LocaleHelper.getLocale(this@SettingsActivity) == "ru") {
                    LocaleHelper.setLocale(this@SettingsActivity, "en")
                    preferencesDataStoreVM.setLanguage("en")
                    LocaleHelper.LANGUAGE_CHANGED = true
                    finish()
                }
            }

            rbRus.setOnClickListener {
                vibrate()
                if (LocaleHelper.getLocale(this@SettingsActivity) == "en") {
                    LocaleHelper.setLocale(this@SettingsActivity, "ru")
                    preferencesDataStoreVM.setLanguage("ru")
                    LocaleHelper.LANGUAGE_CHANGED = true
                    finish()
                }
            }
        }

    }

    private fun setUpObservers() {
        preferencesDataStoreVM.getLanguage.observe(this) {
            if (it == "ru") {
                binding.rbRus.isChecked = true
                binding.rbEng.isChecked = false

            } else {
                binding.rbEng.isChecked = true
                binding.rbRus.isChecked = false
            }
        }

        preferencesDataStoreVM.getVibrationOn.observe(this) {
            if (!it) {
                binding.rgVibration.check(R.id.rbVibrationOff)
            }
        }

        preferencesDataStoreVM.getSoundOn.observe(this) {
            if (!it) {
                binding.rgSound.check(R.id.rbSoundOff)
            }
        }
    }

    override fun onPause() {
        super.onPause()
        overridePendingTransition(0, 0)
    }
}