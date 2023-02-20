package com.example.practice

import android.os.Build.VERSION_CODES.P
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.practice.databinding.MainFragmentBinding
import com.example.practice.overview.PostsAdapter
import com.example.practice.overview.SettingsFragment

class MainFragment : Fragment() {

    private var binding: MainFragmentBinding? = null
    private var lastSelectedFragmentId: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = MainFragmentBinding.inflate(inflater, container, false)
        val root = binding?.root

        replaceFragment(lastSelectedFragmentId)
        binding!!.bottomNavigationView.setOnItemSelectedListener {
            lastSelectedFragmentId = it.itemId
            when (it.itemId) {
                R.id.profile -> replaceFragment(R.id.profile)
                R.id.posts -> replaceFragment(R.id.posts )
                R.id.settings -> replaceFragment( R.id.settings)
                else -> {}
            }
            true
        }

        return root
    }

    private fun replaceFragment(index: Int?) {
        var fragment: Fragment? = null
        if (index == null) fragment = PostsFragment()
        else {
            when (index) {
                R.id.posts -> {
                    fragment = PostsFragment()
                }
                R.id.settings -> {
                    fragment = SettingsFragment()
                }
                R.id.profile -> {
                    fragment = ProfileFragment()
                }
            }
        }
        val fragmentManager = parentFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment!!)
        fragmentTransaction.commit()
    }

}