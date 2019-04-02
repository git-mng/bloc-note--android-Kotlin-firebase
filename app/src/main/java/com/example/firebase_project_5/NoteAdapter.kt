package com.example.firebase_project_5

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.note_layout.view.*


// hna creet constructor dial arrayadapter
// + poi 3tetto context + arraylist  poi fi ArrayAdapter 3tetto nome dial noteclass fin andi les variabile fi constructor dialo 3tetto nass les infos
// ra9m 0 zero 3tetolo bach manliltich ch7al min datti ghanktab di agenda  m// momkin na3teha 3 mais maykench nfoout 3 dial mawa3id fi agenda diali


 // context derto hna lta7t  bach nasta3mlo fi layoutinflater  guarda giu amoo :)))))))))

class NoteAdapter(context:Context, noteList:ArrayList<noteClass>) : ArrayAdapter<noteClass>(context,0, noteList) {


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        //return super.getView(position, convertView, parent)


         // hna jebt layout LIGAHDI YBn fih les informations



        var layoutView =  LayoutInflater.from(context).inflate(R.layout.note_layout, parent, false)

        var note:noteClass = getItem(position)

        layoutView.titleTextView.text = note.title
        layoutView.timeTextview.text = note.timestamp.toString()



         return layoutView





    }


}