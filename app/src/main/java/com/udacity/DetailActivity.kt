package com.udacity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.databinding.DataBindingUtil
//import com.udacity.MainActivity.Companion.FILE_NAME
//import com.udacity.MainActivity.Companion.STATUS
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.content_detail.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setSupportActionBar(toolbar)

        //myFragment.transitionToEnd()


        file_name_text.text = intent.getStringExtra(FILE_NAME)
        status_text.text = intent.getStringExtra(STATUS)

        ok_button.setOnClickListener {
            // Dismiss Mode
            finish()
        }
    }
}
