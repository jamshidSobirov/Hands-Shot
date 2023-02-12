package com.handsshot.foronefortwo.preferences_datastore

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PreferencesDataStoreVM(private val appContext: Application) : AndroidViewModel(appContext) {


    fun setSoundOn(soundOn: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            appContext.writeBool("SoundOn", soundOn)
        }
    }

    val getSoundOn: LiveData<Boolean> = appContext.readBool("SoundOn").asLiveData()


    fun setVibrationOn(vibrationOn: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            appContext.writeBool("VibrationOn", vibrationOn)
        }
    }

    val getVibrationOn = appContext.readBool("VibrationOn").asLiveData()


    fun setLanguage(lang: String) {
        viewModelScope.launch(Dispatchers.IO) {
            appContext.writeString("Language", lang)
        }
    }

    val getLanguage = appContext.readString("Language").asLiveData()

    fun setUser1(pos: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            appContext.writeInt("User1HandPos", pos)
        }
    }

    val getUser1 = appContext.readInt("User1HandPos").asLiveData()

    fun setUser2(pos: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            appContext.writeInt("User2HandPos", pos)
        }
    }

    val getUser2 = appContext.readInt("User2HandPos").asLiveData()

}