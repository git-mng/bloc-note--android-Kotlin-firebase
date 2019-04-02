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


    var myRef: DatabaseReference? = null    // dertha hna yban liya lta7 ba3d a9wass per usalo e chiamrlo lii

    var mNoteList: ArrayList<noteClass>? = null      // eccolo arraylist del noteclass per chiamare i suoi variabile


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

// toolbar color

        toolbar.title ="BloC NoTe"
        toolbar.setTitleTextColor(Color.BLACK)




        var database = FirebaseDatabase.getInstance()

        myRef = database.getReference("NotesObeject")   // inchaell object dakhel root


        // ora devo fare inizialiasent lil array list lilfo9


        mNoteList = ArrayList()



        add_new_note.setOnClickListener {
            // hadi function dial lcode min nkliki y7al liya fin naktab

            showDialogShow()


        }













        // start

        // hna fin ghatftah new activity lifiha information note


 note_list_view.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->







         // wala chaye howa njib constructor fin kaydozo les datas

         var myNotes = mNoteList!!.get(position)

         // poi njib var dial noteclass lihuya asslan kant dawazt l mNoteList

         var titleactivity = myNotes.title
         var nooteteActivity = myNotes.note

         // daba 3amaliya monadat 3la activit


         var noteIntent = Intent(this, noteActivity::class.java)

         noteIntent.putExtra("title_key", titleactivity)   // irsal ma3lomat min hna

         noteIntent.putExtra("note_key", nooteteActivity)


         startActivity(noteIntent)









 }


        //End












        // start

        // questa function la uso per remove and update informazione in myapp note
        // qui la parte del lon click dove devo clickare per un tempo per aprire un attivita



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



       /*
                // qui chimao il id del key  intendo il key quello objectto non quello il secondo
                //  qundi faccio cosi


                // id dial noteclass ghanjibo min myNoteforLonClick lideja jaybha quindi nakho lvar dialha poi nzidha id


                var childReference = myRef!!.child(myNoteforLonClick.id!!)


                // qui ho chimato variabile da noteClss


                var actionremovetitle = view.title_delete.text.toString()
                var actiondeleteNote = view.note_delete.text.toString()


                // hna ghanjib les autre varialble min noteClass

                var afterUpdate = noteClass(myNoteforLonClick.id!!, actionremovetitle, actiondeleteNote, getCurrentDate())



                childReference?.setValue(afterUpdate)

*/

       // ora scrivo un codice bereve


       var afterUpdate = noteClass(myNoteforLonClick.id!!, view.title_delete.text.toString(), view.note_delete.text.toString(), getCurrentDate())


        myRef!!.child(myNoteforLonClick.id!!).setValue(afterUpdate)


                                      // cancella o nasconde il form dopo che finisco di scrivere
                alertdialog.dismiss()
            }



view.btn_delete_note.setOnClickListener {


       myRef!!.child(myNoteforLonClick.id!!).removeValue()

          alertdialog.dismiss()


     }



true                // dima katkon m3a listener


 }







    }

    // end





    // daba devo leggere dalla fatabase firebase  read


override fun onStart() {
        super.onStart()



        myRef!!.addValueEventListener(object : ValueEventListener {

            override fun onCancelled(p0: DatabaseError) {


                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

override fun onDataChange(DataSnapshot: DataSnapshot) {


                // for (n in dataget.children)

                    mNoteList?.clear()    // hadi ista3metlha bach mayb9ach creee liya coppie zaydein dial note likanktab ok // questo serve per cancellare il vecchio note in list


                for (nn in DataSnapshot.children) {                    // mnin tjib dawra jib liya m3ak children likaynin ldakhel lawlidat :)


                    var ddd = nn.getValue(noteClass::class.java)!!        // poi chiamo il nome del file class che ho cresato a parte

                    // dopo che lo creato mi serve arraylist per dentro noteclass varialbile
                    mNoteList!!.add(0, ddd)                             // had arraylist ghassni passarlo al listView

                    // hna zedt 0 ma3nata ana note lighatkhol jida ghatblassa fi index zero  tbna hiya lawla


                    // val value = dataSnapshot.getValue(String::class.java)


                }


                // hna nacreee var min noteadapter

                var noteAdapter = NoteAdapter(applicationContext, mNoteList!!)


                // ora prendo id listview  fin ghaytem irssal l9iyam min ba3d

                note_list_view.adapter = noteAdapter


            }
        })


    }


    // hadchi kamel lilta7t per write scriver













fun showDialogShow() {

        var alertBuilder = AlertDialog.Builder(this)               // hna jabt dalla dial alertdialog

        var viewq =
            layoutInflater.inflate(R.layout.add_note, null)           // hna jebt layout  lifih template lghayban liya



        alertBuilder.setView(viewq)                                            // hna fin ghayjib layout dakhel alertdialog

        var alertDialog = alertBuilder.create()                   // hna bach 3teto amr creat // had creat khasso hta howa alertdialog ::jabto lih tahowa


        alertDialog.show()                                                      // hna 3teto amr show()



    // had code insereto lwaste lighaybda min hna


  viewq.btnSaveListener.setOnClickListener {

            val titleoo = viewq.title_edit_text.text.toString()                          // hna jebt id editText
            val notes = viewq.note_edit_text.text.toString()                             // hna jebt id editText


            // hadi tari9a 3adiya
            // myRef.child("tiltles").setValue(title)
            // myRef.child("notess").setValue(notes)

            // tari9a mota9adema   // hna irsal bayant


            if (titleoo.isNotEmpty() && notes.isNotEmpty()) {


                var id: String = myRef!!.push().key!!

                // qua sopra aggiungo il fun

                var myNote = noteClass(id, titleoo, notes, getCurrentDate())

                        myRef!!.child(id).setValue(myNote)


               // myRef!!.child("kora").setValue(myNote)                                // olala ghaydkol ojbect tani fin ndakhel les autres single string


            } else {


                Toast.makeText(this, "error non ti faccio un cazzo come gli altri programmer :: ->> ", Toast.LENGTH_LONG).show()

            }

      alertDialog.dismiss()
        }


        // ghaussali hna  poi tattima dial code lilfo9 ghatssali hnaya


    }












fun getCurrentDate(): String {


        val calendar = Calendar.getInstance()
        val mdformat = SimpleDateFormat("EEEE hh:mm:a")
        val strDate = mdformat.format(calendar.time)
        return strDate


    }





    //






}
