package com.conacon.testcurse.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import androidx.fragment.app.viewModels

import androidx.navigation.fragment.findNavController
import com.conacon.testcurse.R
import com.conacon.testcurse.databinding.FragmentShoppingBinding
import dagger.hilt.android.AndroidEntryPoint


class ShoppingFragment: Fragment(R.layout.fragment_shopping) {
    val viewModel : ShoppingViewModel by viewModels()
    private lateinit var binding: FragmentShoppingBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShoppingBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fabAddShoppingItem.setOnClickListener {
            findNavController().navigate(
                ShoppingFragmentDirections.actionShoppingFragmentToAddShoppingItemFragment()
            )
        }
    }

}