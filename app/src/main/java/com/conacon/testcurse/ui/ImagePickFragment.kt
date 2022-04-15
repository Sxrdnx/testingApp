package com.conacon.testcurse.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.conacon.testcurse.R
import com.conacon.testcurse.adapters.ImageAdapter
import javax.inject.Inject


class ImagePickFragment @Inject constructor(
    private val imageAdapter: ImageAdapter
): Fragment(R.layout.fragment_image_pick) {
    val viewModel : ShoppingViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}