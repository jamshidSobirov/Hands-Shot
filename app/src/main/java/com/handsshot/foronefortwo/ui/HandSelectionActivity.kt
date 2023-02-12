package com.handsshot.foronefortwo.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import com.google.android.material.checkbox.MaterialCheckBox
import com.handsshot.foronefortwo.R
import com.handsshot.foronefortwo.databinding.ActivityHandSelectionBinding
import com.handsshot.foronefortwo.model.Hand
import com.handsshot.foronefortwo.model.User

class HandSelectionActivity : BaseActivity() {

    private lateinit var binding: ActivityHandSelectionBinding
    private lateinit var hands: List<Hand>
    private var whoseTurn = User.USER1
    private var handUser1: Hand? = null
    private var handUser2: Hand? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        overridePendingTransition(0, 0)
        super.onCreate(savedInstanceState)
        binding = ActivityHandSelectionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
    }

    private fun initViews() {
        hands = getAllHands()

        for (hand in hands) {
            hand.apply {
                button.setOnClickListener {
                    vibrate()
                    if (hand == handUser1 || hand == handUser2) {
                        Toast.makeText(
                            applicationContext,
                            "Choose another one!",
                            Toast.LENGTH_SHORT
                        ).show()
                        button.isChecked = true
                    } else {
                        if (whoseTurn == User.USER1) {
                            handUser1 = this
                            button.background = AppCompatResources.getDrawable(
                                this@HandSelectionActivity,
                                backgroundUser1Res
                            )
                            whoseTurn = User.USER2
                        } else {
                            handUser2 = this
                            button.background = AppCompatResources.getDrawable(
                                this@HandSelectionActivity,
                                backgroundUser2Res
                            )
                            whoseTurn = User.USER1
                        }
                        unCheckAllOtherButtons()
                    }

                }

            }
        }

        binding.btnMenu.setOnClickListener {
            vibrate()
            preferencesDataStoreVM.setUser1(handUser1?.pos ?: 0)
            preferencesDataStoreVM.setUser2(handUser2?.pos ?: 9)
            this.finish()
        }
    }

    override fun onBackPressed() {
        preferencesDataStoreVM.setUser1(handUser1?.pos ?: 0)
        preferencesDataStoreVM.setUser2(handUser2?.pos ?: 9)
        this.finish()
        super.onBackPressed()
    }

    private fun unCheckAllOtherButtons() {
        for (hand in hands) {

            if (!(hand == handUser1 || hand == handUser2))
                hand.button.isChecked = false

        }
    }

    override fun onPause() {
        super.onPause()
        overridePendingTransition(0, 0)
    }

    private fun getAllHands(): List<Hand> {
        val hands = ArrayList<Hand>()
        for (i in 0..9) {
            hands.add(Hand(getButtons()[i], getButtonUser1Res()[i], getButtonUser2Res()[i], i))
        }
        return hands
    }

    private fun getButtons(): ArrayList<MaterialCheckBox> {
        val buttons: ArrayList<MaterialCheckBox> = ArrayList()
        buttons.add(binding.rbHand1)
        buttons.add(binding.rbHand2)
        buttons.add(binding.rbHand3)
        buttons.add(binding.rbHand4)
        buttons.add(binding.rbHand5)
        buttons.add(binding.rbHand6)
        buttons.add(binding.rbHand7)
        buttons.add(binding.rbHand8)
        buttons.add(binding.rbHand9)
        buttons.add(binding.rbHand10)
        return buttons
    }

    private fun getButtonUser2Res(): ArrayList<Int> {
        val buttonUser2Res = ArrayList<Int>()
        buttonUser2Res.add(R.drawable.hand_green_1)
        buttonUser2Res.add(R.drawable.hand_green_2)
        buttonUser2Res.add(R.drawable.hand_green_3)
        buttonUser2Res.add(R.drawable.hand_green_4)
        buttonUser2Res.add(R.drawable.hand_green_5)
        buttonUser2Res.add(R.drawable.hand_green_6)
        buttonUser2Res.add(R.drawable.hand_green_7)
        buttonUser2Res.add(R.drawable.hand_green_8)
        buttonUser2Res.add(R.drawable.hand_green_9)
        buttonUser2Res.add(R.drawable.hand_green_10)
        return buttonUser2Res
    }

    private fun getButtonUser1Res(): ArrayList<Int> {
        val buttonUser1Res = ArrayList<Int>()
        buttonUser1Res.add(R.drawable.hand_red_1)
        buttonUser1Res.add(R.drawable.hand_red_2)
        buttonUser1Res.add(R.drawable.hand_red_3)
        buttonUser1Res.add(R.drawable.hand_red_4)
        buttonUser1Res.add(R.drawable.hand_red_5)
        buttonUser1Res.add(R.drawable.hand_red_6)
        buttonUser1Res.add(R.drawable.hand_red_7)
        buttonUser1Res.add(R.drawable.hand_red_8)
        buttonUser1Res.add(R.drawable.hand_red_9)
        buttonUser1Res.add(R.drawable.hand_red_10)
        return buttonUser1Res
    }
}