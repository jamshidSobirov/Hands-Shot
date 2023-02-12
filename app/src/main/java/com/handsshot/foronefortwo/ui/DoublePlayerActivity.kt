package com.handsshot.foronefortwo.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.handsshot.foronefortwo.databinding.ActivityDoublePlayerBinding
import com.handsshot.foronefortwo.model.*
import com.handsshot.foronefortwo.utils.*

class DoublePlayerActivity : BaseActivity() {
    lateinit var binding: ActivityDoublePlayerBinding
    private var userToHit = User.USER1
    private var alreadyHidden = false
    private var alreadyHit = false
    private var player1Score = 0
    private var player2Score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(0, 0)
        binding = ActivityDoublePlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
    }

    private fun initViews() {
        setUserImages()
        binding.apply {

            if (userToHit == User.USER1) {
                hitUser2.disable()
                hideUser1.disable()
            } else {
                hitUser1.disable()
                hideUser2.disable()
            }

            hitUser1.setOnClickListener {
                vibrate()
                alreadyHit = true
                if (alreadyHidden) {
                    hitUser1.disable()
                    hitUser2.enable()
                    hideUser1.enable()
                    hideUser2.disable()
                    userToHit = User.USER2
                } else {
                    player1Score++
                    user1Score.text = player1Score.toString()

                    if (player1Score == 5) {
                        EndGameDialog(this@DoublePlayerActivity, 1).show()
                    }
                }

                it.disable()
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
                    if (userToHit == User.USER1)
                        it.enable()
                }, 600)

            }

            hitUser2.setOnClickListener {
                vibrate()
                alreadyHit = true
                if (alreadyHidden) {
                    hitUser2.disable()
                    hideUser1.disable()
                    hitUser1.enable()
                    hideUser2.enable()
                    userToHit = User.USER1
                } else {
                    player2Score++
                    user2Score.text = player2Score.toString()

                    if (player2Score == 5) {
                        EndGameDialog(this@DoublePlayerActivity, 2).show()

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
                    if (userToHit == User.USER2)
                        it.enable()
                }, 600)
            }

            hideUser1.setOnClickListener {
                vibrate()
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
                    }, 900)
                }
            }

            hideUser2.setOnClickListener {
                vibrate()
                if (!alreadyHit) {
                    alreadyHidden = true
                    imRightHiddenHand.visible()
                    imRightHand.invisible()

                    Handler(Looper.myLooper()!!).postDelayed({
                        hitUser1.disable()
                    }, 400)

                    Handler(Looper.myLooper()!!).postDelayed({
                        alreadyHidden = false
                        imRightHand.visible()
                        imRightHiddenHand.invisible()

                    }, 700)

                    Handler(Looper.myLooper()!!).postDelayed({
                        if (userToHit == User.USER1) {
                            hitUser1.enable()
                        }
                    }, 900)
                }

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