package com.akshatsahijpal.socialgraph.ui.mainpage.fragment

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContract
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.akshatsahijpal.socialgraph.databinding.FragmentPostBinding
import com.akshatsahijpal.socialgraph.ui.mainpage.vm.PostCreationViewModel
import com.akshatsahijpal.socialgraph.util.EventObserver
import com.canhub.cropper.CropImage
import com.canhub.cropper.CropImageView
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

    private var ImageResultContract = object : ActivityResultContract<String, Uri>() {
        override fun createIntent(context: Context, input: String?): Intent {
            return CropImage.activity()
                .setAspectRatio(16, 9)
                .setGuidelines(CropImageView.Guidelines.ON)
                .getIntent(requireContext())

        }

        override fun parseResult(resultCode: Int, intent: Intent?): Uri? {
            return CropImage.getActivityResult(intent)?.uriContent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().registerForActivityResult(ImageResultContract) {
            it?.let {
                model.setURI(it)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listenToObserver()

    }

    private lateinit var finalUri: Uri
    private fun listenToObserver() {
        model.observeUriStatus.observe(viewLifecycleOwner) {
            finalUri = it
        }
        model.observerPostStatus.observe(
            viewLifecycleOwner, EventObserver(
                onError = {},
                onSuccess = {
                    findNavController().popBackStack()
                },
                onLoading = {}
            ))
    }
}