package com.jagdon.removal_jagdon.views.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jagdon.removal_jagdon.R
import com.jagdon.removal_jagdon.views.fragments.MenuFragment


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, MenuFragment())
                .commit()
        }
    }
}