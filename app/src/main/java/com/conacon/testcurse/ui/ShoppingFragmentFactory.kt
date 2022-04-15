package com.conacon.testcurse.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.conacon.testcurse.adapters.ImageAdapter

import javax.inject.Inject

class ShoppingFragmentFactory @Inject constructor(
   private val imageAdapter: ImageAdapter
) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return  when (className){
            ImagePickFragment::class.java.name ->{ImagePickFragment(imageAdapter)}
            else -> super.instantiate(classLoader, className)
        }

        
    }

}