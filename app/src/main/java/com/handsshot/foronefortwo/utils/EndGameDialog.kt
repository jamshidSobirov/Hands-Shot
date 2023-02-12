package com.handsshot.foronefortwo.utils

import android.app.Activity
import android.app.Dialog
import android.graphics.Color
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.TextView
import com.google.android.material.button.MaterialButton
import com.handsshot.foronefortwo.R
import com.handsshot.foronefortwo.ui.BaseActivity


class EndGameDialog(var cntx: Activity, var winner: Int) : Dialog(cntx) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.end_game_dialog)


        findViewById<MaterialButton>(R.id.btnMenu).setOnClickListener {
            (cntx as BaseActivity).vibrate()
            this.dismiss()
            cntx.finish()
        }
        this.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT
        )
        this.setCancelable(false)
        this.window?.setBackgroundDrawableResource(android.R.color.transparent);

        when (winner) {
            1 -> findViewById<TextView>(R.id.endGameText).setText(R.string.victory_player1)
            2 -> findViewById<TextView>(R.id.endGameText).setText(R.string.victory_player2)
            3 -> {
                findViewById<TextView>(R.id.endGameText).setText(R.string.victory_bot)
                findViewById<TextView>(R.id.endGameText).setTextColor(Color.parseColor("#8A0000"))

            }
            4 -> findViewById<TextView>(R.id.endGameText).setText(R.string.you_win)
        }
    }
}