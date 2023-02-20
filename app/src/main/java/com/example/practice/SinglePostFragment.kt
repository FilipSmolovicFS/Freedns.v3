package com.example.practice

import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.practice.databinding.SinglePostLayoutBinding
import com.example.practice.overview.OverviewViewModel

class SinglePostFragment: Fragment() {

    private var binding : SinglePostLayoutBinding? = null

    private val args: SinglePostFragmentArgs by navArgs()

    private val viewModel: OverviewViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = SinglePostLayoutBinding.inflate(inflater,container,false)
        val root = binding?.root

        binding?.backButton?.setOnClickListener{
            findNavController().navigate(R.id.action_singlePostFragment_to_mainFragment)
        }

        var imageURI: Uri = Uri.parse(args.photoUri)

        binding?.postToolbar?.inflateMenu(R.menu.photo_menu)

        binding?.postPhoto?.setImageURI(imageURI)

        return root
    }

}