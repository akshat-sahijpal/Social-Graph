package com.akshatsahijpal.socialgraph.ui.mainpage.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.akshatsahijpal.socialgraph.databinding.FragmentPostBinding

class PostFragment : Fragment() {
    private lateinit var _binding: FragmentPostBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPostBinding.inflate(inflater, container, false)
        return _binding.root
    }
}