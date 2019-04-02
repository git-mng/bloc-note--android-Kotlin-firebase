package com.example.firebase_project_5

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.note_layout.view.*






class NoteAdapter(context:Context, noteList:ArrayList<noteClass>) : ArrayAdapter<noteClass>(context,0, noteList) {


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        //return super.getView(position, convertView, parent)





        var layoutView =  LayoutInflater.from(context).inflate(R.layout.note_layout, parent, false)

        var note:noteClass = getItem(position)

        layoutView.titleTextView.text = note.title
        layoutView.timeTextview.text = note.timestamp.toString()



         return layoutView





    }


}
