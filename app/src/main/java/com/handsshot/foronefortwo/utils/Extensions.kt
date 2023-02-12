package com.handsshot.foronefortwo.utils

import android.content.Context.VIBRATOR_SERVICE
import android.graphics.Color
import android.os.Vibrator
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE

fun View.visible() {
    this.visibility = VISIBLE
}

fun View.invisible() {
    this.visibility = INVISIBLE
}

fun View.enable() {
    this.isEnabled = true
    this.setBackgroundColor(Color.parseColor("#227E36"))
}

fun View.disable() {
    this.isEnabled = false
    this.setBackgroundColor(Color.GRAY)
}

