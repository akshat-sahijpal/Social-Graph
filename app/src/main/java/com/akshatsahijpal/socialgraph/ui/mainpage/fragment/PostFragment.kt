package com.akshatsahijpal.socialgraph.ui.mainpage.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.akshatsahijpal.socialgraph.databinding.FragmentPostBinding
import com.akshatsahijpal.socialgraph.ui.mainpage.vm.PostCreationViewModel
import com.akshatsahijpal.socialgraph.util.EventObserver
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostFragment : Fragment() {
    private lateinit var _binding: FragmentPostBinding
    private val model by viewModels<PostCreationViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPostBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listenToObserver()

    }

    private fun listenToObserver() {
        model.observerPostStatus.observe(viewLifecycleOwner, EventObserver(
            onError = {},
            onSuccess = {
                findNavController().popBackStack()
            },
            onLoading = {}
        ))
    }
}