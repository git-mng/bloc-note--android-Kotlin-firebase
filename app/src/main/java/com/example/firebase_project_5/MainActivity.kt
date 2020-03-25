package com.example.firebase_project_5

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.add_note.*
import kotlinx.android.synthetic.main.add_note.view.*
import kotlinx.android.synthetic.main.delete_note.view.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {


    var myRef: DatabaseReference? = null    

    var mNoteList: ArrayList<noteClass>? = null      


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

// toolbar color

        toolbar.title ="BloC NoTe"
        toolbar.setTitleTextColor(Color.BLACK)




        var database = FirebaseDatabase.getInstance()

        myRef = database.getReference("NotesObeject")   




        mNoteList = ArrayList()



        add_new_note.setOnClickListener {
           

            showDialogShow()


        }


 note_list_view.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->


         var myNotes = mNoteList!!.get(position)

 

         var titleactivity = myNotes.title
         var nooteteActivity = myNotes.note

    


         var noteIntent = Intent(this, noteActivity::class.java)

         noteIntent.putExtra("title_key", titleactivity)   

         noteIntent.putExtra("note_key", nooteteActivity)


         startActivity(noteIntent)

 }

 note_list_view.onItemLongClickListener = AdapterView.OnItemLongClickListener { parent, view, position, id ->


     var alertBulder = AlertDialog.Builder(this)

            var view = layoutInflater.inflate(R.layout.delete_note, null)

            alertBulder.setView(view)

            var alertdialog = alertBulder.create()

            alertdialog.show()


            // 3amalia lmass7 dial title

            var myNoteforLonClick = mNoteList!![position]

            var titleminclasstitle = myNoteforLonClick.title
            var noteminclasstitle = myNoteforLonClick.note


            view.title_delete.setText(titleminclasstitle)
            view.note_delete.setText(noteminclasstitle)


   view.btn_update_note.setOnClickListener {



       var afterUpdate = noteClass(myNoteforLonClick.id!!, view.title_delete.text.toString(), view.note_delete.text.toString(), getCurrentDate())


        myRef!!.child(myNoteforLonClick.id!!).setValue(afterUpdate)


                                     
                alertdialog.dismiss()
            }



view.btn_delete_note.setOnClickListener {


       myRef!!.child(myNoteforLonClick.id!!).removeValue()

          alertdialog.dismiss()


     }



true                


 }



    }

    

override fun onStart() {
        super.onStart()



        myRef!!.addValueEventListener(object : ValueEventListener {

            override fun onCancelled(p0: DatabaseError) {


                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

override fun onDataChange(DataSnapshot: DataSnapshot) {


                

                    mNoteList?.clear()  


                for (nn in DataSnapshot.children) {             


                    var ddd = nn.getValue(noteClass::class.java)!!      

                    mNoteList!!.add(0, ddd)                             

                

                }


                

                var noteAdapter = NoteAdapter(applicationContext, mNoteList!!)



                note_list_view.adapter = noteAdapter


            }
        })


    }

fun showDialogShow() {

        var alertBuilder = AlertDialog.Builder(this)               

        var viewq =
            layoutInflater.inflate(R.layout.add_note, null)           



        alertBuilder.setView(viewq)                                            

        var alertDialog = alertBuilder.create()                  

        alertDialog.show()                                                 



    // had code insereto lwaste lighaybda min hna


  viewq.btnSaveListener.setOnClickListener {

            val titleoo = viewq.title_edit_text.text.toString()                         
            val notes = viewq.note_edit_text.text.toString()                         



            if (titleoo.isNotEmpty() && notes.isNotEmpty()) {


                var id: String = myRef!!.push().key!!

                // qua sopra aggiungo il fun

                var myNote = noteClass(id, titleoo, notes, getCurrentDate())

                        myRef!!.child(id).setValue(myNote)


         

            } else {


                Toast.makeText(this, "error non ti faccio un cazzo come gli altri programmer :: ->> ", Toast.LENGTH_LONG).show()

            }

      alertDialog.dismiss()
        }


    }


fun getCurrentDate(): String {


        val calendar = Calendar.getInstance()
        val mdformat = SimpleDateFormat("EEEE hh:mm:a")
        val strDate = mdformat.format(calendar.time)
        return strDate


    }

}
