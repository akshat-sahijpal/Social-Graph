package com.akshatsahijpal.socialgraph.ui.auth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.akshatsahijpal.socialgraph.R
import com.akshatsahijpal.socialgraph.ui.mainpage.MainPageActivity
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (FirebaseAuth.getInstance().currentUser != null) {
            Intent(applicationContext, MainPageActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }
    }
}