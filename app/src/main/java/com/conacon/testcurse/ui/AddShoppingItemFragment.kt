package com.conacon.testcurse.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.conacon.testcurse.R

class AddShoppingItemFragment: Fragment(R.layout.fragment_add_shopping_item) {

    lateinit var viewmodel : ShoppingViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewmodel = ViewModelProvider(requireActivity())[ShoppingViewModel::class.java]
    }

}