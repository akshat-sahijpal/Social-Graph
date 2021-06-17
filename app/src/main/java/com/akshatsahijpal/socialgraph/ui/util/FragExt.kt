package com.akshatsahijpal.socialgraph.ui.util

import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

fun Fragment.snackBar(txt: String) {
   Snackbar.make(requireView(), txt, Snackbar.LENGTH_SHORT).show()
}