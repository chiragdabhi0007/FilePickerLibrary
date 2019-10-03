package com.tops.filepicker

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity

import androidx.appcompat.widget.Toolbar

/**
 * Created by chirag on 02/10/19.
 */

abstract class BaseFilePickerActivity : AppCompatActivity() {

    protected fun onCreate(savedInstanceState: Bundle?, @LayoutRes layout: Int) {
        super.onCreate(savedInstanceState)
        setTheme(PickerManager.theme)
        setContentView(layout)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
      //  setSupportActionBar(toolbar)
     //   supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //set orientation
        requestedOrientation = PickerManager.orientation
        initView()
    }

    protected abstract fun initView()
}
