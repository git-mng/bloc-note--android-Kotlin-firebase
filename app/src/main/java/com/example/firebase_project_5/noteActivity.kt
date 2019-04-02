package com.example.firebase_project_5

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_note.*

class noteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)



        var til =   intent.extras.getString("title_key")
        var not =   intent.extras.getString("note_key")


      title_text_view.text = til
        note_text_view.text = not


    }
}
