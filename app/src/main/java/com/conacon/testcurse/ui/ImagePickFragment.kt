package com.conacon.testcurse.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.conacon.testcurse.R
import com.conacon.testcurse.adapters.ImageAdapter
import com.conacon.testcurse.databinding.FragmentImagePickBinding
import com.conacon.testcurse.other.Constants.GRID_SPAN_COUNT
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ImagePickFragment @Inject constructor(
     val imageAdapter: ImageAdapter
): Fragment(R.layout.fragment_image_pick) {
    private lateinit var binding: FragmentImagePickBinding
     lateinit var viewModel : ShoppingViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(requireActivity())[ShoppingViewModel::class.java]
        binding = FragmentImagePickBinding.inflate(inflater,container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpAdapter()
        imageAdapter.setOnItemClickListener {
            findNavController().popBackStack()
            viewModel.setCurImageUrl(it)
        }
    }


    private fun setUpAdapter(){
        binding.rvImages.adapter = imageAdapter
        binding.rvImages.layoutManager = GridLayoutManager(requireContext(),GRID_SPAN_COUNT)
    }
}