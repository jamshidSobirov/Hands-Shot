package com.handsshot.foronefortwo.ui

import android.app.Activity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.handsshot.foronefortwo.databinding.ActivitySinglePlayerBinding
import com.handsshot.foronefortwo.model.*
import com.handsshot.foronefortwo.utils.*


class SinglePlayerActivity : BaseActivity() {
    lateinit var binding: ActivitySinglePlayerBinding
    private var userToHit = User.USER1
    private var alreadyHidden = false
    private var alreadyHit = false
    private var player1Score = 0
    private var player2Score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(0, 0)
        binding = ActivitySinglePlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
    }

    private fun initViews() {
        setUserImages()
        binding.apply {

            if (userToHit == User.USER1) {
                hitUser2.disable()
            } else {
                hideUser2.disable()
            }

            roboticHit()


            hitUser2.setOnClickListener {
                makeHitSound()
                vibrate()
                alreadyHit = true
                if (alreadyHidden) {
                    hitUser2.disable()
                    hideUser2.enable()
                    userToHit = User.USER1
                } else {
                    player2Score++
                    user2Score.text = player2Score.toString()

                    if (player2Score == 5) {
                        if (!(this@SinglePlayerActivity as Activity).isFinishing) {
                            EndGameDialog(this@SinglePlayerActivity, 4).show()
                        }

                    } else {
                        Handler(Looper.myLooper()!!).postDelayed({
                            roboticHide()
                        }, 800)
                    }
                }

                it.disable()
                imRightHandHitter.visible()
                imRightHand.invisible()
                Handler(Looper.myLooper()!!).postDelayed({
                    imHitBoomLeft.visible()
                }, 50)

                Handler(Looper.myLooper()!!).postDelayed({
                    imRightHand.visible()
                    imHitBoomLeft.invisible()
                    imRightHandHitter.invisible()

                    alreadyHit = false
                    if (userToHit == User.USER2) it.enable()
                }, 600)
            }

            hideUser2.setOnClickListener {
                vibrate()
                if (!alreadyHit) {
                    alreadyHidden = true
                    imRightHiddenHand.visible()
                    imRightHand.invisible()

                    Handler(Looper.myLooper()!!).postDelayed({
                        alreadyHidden = false
                        imRightHand.visible()
                        imRightHiddenHand.invisible()

                    }, 700)

                }

            }

        }
    }

    private fun roboticHit() {
        Handler(Looper.myLooper()!!).postDelayed({
            binding.apply {
                makeHitSound()
                alreadyHit = true
                if (alreadyHidden) {
                    hitUser2.enable()
                    hideUser2.disable()
                    userToHit = User.USER2

                    Handler(Looper.myLooper()!!).postDelayed({
                        roboticHide()
                    }, 800)

                } else {
                    player1Score++
                    user1Score.text = player1Score.toString()

                    if (player1Score == 5) {
                        if (!(this@SinglePlayerActivity as Activity).isFinishing) {
                            EndGameDialog(this@SinglePlayerActivity, 3).show()
                        }
                    } else {
                        roboticHit()
                    }

                }

                imLeftHandHitter.visible()
                imLeftHand.invisible()
                Handler(Looper.myLooper()!!).postDelayed({
                    imHitBoomRight.visible()
                }, 50)

                Handler(Looper.myLooper()!!).postDelayed({
                    imLeftHand.visible()
                    imLeftHandHitter.invisible()
                    imHitBoomRight.invisible()

                    alreadyHit = false

                }, 600)
            }
        }, 2800)


    }

    private fun roboticHide() {
        binding.apply {
            if (!alreadyHit) {

                alreadyHidden = true

                imLeftHiddenHand.visible()
                imLeftHand.invisible()

                Handler(Looper.myLooper()!!).postDelayed({
                    hitUser2.disable()
                }, 400)


                Handler(Looper.myLooper()!!).postDelayed({
                    imLeftHand.visible()
                    imLeftHiddenHand.invisible()
                    alreadyHidden = false

                }, 700)

                Handler(Looper.myLooper()!!).postDelayed({
                    if (userToHit == User.USER2) {
                        hitUser2.enable()
                    }

                    if (player2Score < 5 && userToHit == User.USER2) {
                        Handler(Looper.myLooper()!!).postDelayed({
                            roboticHide()
                        }, 500)
                    } else {
                        Handler(Looper.myLooper()!!).postDelayed({
                            roboticHit()
                        }, 1500)
                    }
                }, 900)
            }
        }

    }

    private fun setUserImages() {
        preferencesDataStoreVM.getUser1.observe(this) {
            binding.apply {
                imLeftHand.setImageResource(LeftShortHands.values()[it].res)
                imLeftHiddenHand.setImageResource(LeftShortHands.values()[it].res)
                imLeftHandHitter.setImageResource(LeftHitterHands.values()[it].res)
            }
        }

        preferencesDataStoreVM.getUser2.observe(this) {
            binding.apply {
                imRightHand.setImageResource(RightShortHands.values()[it].res)
                imRightHiddenHand.setImageResource(RightShortHands.values()[it].res)
                imRightHandHitter.setImageResource(RightHitterHands.values()[it].res)
            }
        }

    }

    override fun onPause() {
        super.onPause()
        overridePendingTransition(0, 0)
    }
}