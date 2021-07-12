package com.hakansarac.photoalbum.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.hakansarac.photoalbum.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentMain = MainFragment()
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container,fragmentMain).commit()
    }
}