package com.example.aaaaa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_lesson.*

class lessonActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson)

        supportActionBar?.title = "Details"

        var title = intent?.extras?.getString("title")
        var content = intent?.extras?.getString("content")

        title_lesson.text = title
        content_lesson.text = content
    }
}
