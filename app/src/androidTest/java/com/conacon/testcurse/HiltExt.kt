package com.conacon.testcurse

import android.os.Bundle
import androidx.annotation.StyleRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory

inline fun <reified T: Fragment> launchFragmentInHiltContainer(
    fragmentArgs: Bundle? = null,
    @StyleRes themeResId: Int = R.style.Theme_AppCompat,
    fragmentFactory: FragmentFactory? = null,
    crossinline action: T.()-> Unit = {}
){


}