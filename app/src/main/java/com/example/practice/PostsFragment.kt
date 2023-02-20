package com.example.practice

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.practice.databinding.PostsPageBinding
import com.example.practice.overview.OverviewViewModel
import com.example.practice.overview.PostsAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView

class PostsFragment: androidx.fragment.app.Fragment(),PostsAdapter.PostsAdapterInterface {

    private var _binding: PostsPageBinding? = null
    private val binding get() = _binding
    private val navController = NavController

    private val viewModel: OverviewViewModel by activityViewModels()

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = PostsPageBinding.inflate(inflater,container,false)
        val root = binding?.root

        val view = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        view.visibility = View.VISIBLE

        binding?.lifecycleOwner = this

        binding?.viewModel = viewModel

        binding?.postsList?.adapter = PostsAdapter(this)


//        binding?.refreshButton?.setOnClickListener {
//            binding?.postsList?.adapter?.notifyDataSetChanged()
//        }

        binding?.refreshButton?.setOnClickListener {
            val newFragment = PostsFragment()
            requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, newFragment)
                .commit()
        }



        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onImageClicked(imgSrcUrl: String) {
       // findNavController().navigate(R.id.action_mainFragment_to_singlePostFragment)
        findNavController().navigate(MainFragmentDirections.actionMainFragmentToSinglePostFragment(imgSrcUrl))
    }



}