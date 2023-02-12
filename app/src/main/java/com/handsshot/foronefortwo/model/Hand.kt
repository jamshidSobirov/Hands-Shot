package com.handsshot.foronefortwo.model

import com.google.android.material.checkbox.MaterialCheckBox

data class Hand(
    var button: MaterialCheckBox,
    var backgroundUser1Res: Int = 0,
    var backgroundUser2Res: Int = 0,
    var pos: Int
)

//data class Hand(
//    var button: MaterialCheckBox,
//    var user: User,
//    var isChecked: Boolean = false,
//)