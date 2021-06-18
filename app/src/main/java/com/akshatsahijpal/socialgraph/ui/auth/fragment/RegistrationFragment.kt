package com.akshatsahijpal.socialgraph.ui.auth.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.akshatsahijpal.socialgraph.R
import com.akshatsahijpal.socialgraph.databinding.FragmentRegistrationBinding
import com.akshatsahijpal.socialgraph.ui.auth.vm.LoginViewModel
import com.akshatsahijpal.socialgraph.ui.mainpage.MainPageActivity
import com.akshatsahijpal.socialgraph.ui.util.snackBar
import com.akshatsahijpal.socialgraph.util.EventObserver
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegistrationFragment : Fragment() {
    private lateinit var _binding: FragmentRegistrationBinding
    private lateinit var navController: NavController
    private val model by viewModels<LoginViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        return _binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        listenToObserver()
        _binding.apply {
            LoginButton.setOnClickListener {
                model.registerUser(
                    email = NameIdEd.editText?.text.toString(),
                    username = UserNameIdEd.editText?.text.toString(),
                    password = PassIdEd.editText?.text.toString()
                )

                hideKeyboard()
            }
        }
        _binding.switchBet.setOnClickListener {
            if (navController.previousBackStackEntry != null) {
                navController.popBackStack()
            } else {
                navController.navigate(R.id.action_registrationFragment_to_loginFragment)
            }
        }
    }

    private fun hideKeyboard() {
        val view = requireActivity().currentFocus
        if (view != null) {
            val imm =
                requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    private fun listenToObserver() {
        model.observerRegister.observe(
            viewLifecycleOwner, EventObserver(
                onError = {
                    _binding.registerProgressBar.isVisible = false
                    _binding.LoginButton.isEnabled = true
                    _binding.NameIdEd.isEnabled = true
                    _binding.UserNameIdEd.isEnabled = true
                    _binding.PassIdEd.isEnabled = true
                    snackBar(it)
                },
                onLoading = {
                    _binding.registerProgressBar.isVisible = true
                    _binding.LoginButton.isEnabled = false
                    _binding.NameIdEd.isEnabled = false
                    _binding.UserNameIdEd.isEnabled = false
                    _binding.PassIdEd.isEnabled = false
                },
                onSuccess = {
                    _binding.registerProgressBar.isVisible = false
                    snackBar("Success!!!")
                    Intent(requireContext(), MainPageActivity::class.java).also {
                        startActivity(it)
                        requireActivity().finish()
                    }
                })
        )
    }
}