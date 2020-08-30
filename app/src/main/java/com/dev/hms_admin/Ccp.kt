package com.dev.hms_admin


import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.google.firebase.database.*
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_ccp.*


class Ccp : AppCompatActivity() {


    var myRef : DatabaseReference? = null
    var received : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ccp)
        val intent = intent
        received = intent.getStringExtra("URL")
        Picasso.get().load(received).into(im)



    }



    fun acc(view : View){
        var database : FirebaseDatabase = FirebaseDatabase.getInstance()
        myRef = database.getReference("reservation")
        myRef!!.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                for (snap in dataSnapshot.children){

                    val u = snap.value as HashMap<String,String>
                    if (received == u["URL"] ){
                        myRef!!.child(snap.key!!).child("etat").setValue("confirmé")
                        val intent = Intent(this@Ccp,HotelActivity::class.java)
                        startActivity(intent)
                    }
                }


            }
            override fun onCancelled(error: DatabaseError) {

            }
        })


    }

    fun ref(view:View){
        var database : FirebaseDatabase = FirebaseDatabase.getInstance()
        myRef = database.getReference("reservation")
        myRef!!.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                 for (snap in dataSnapshot.children){

                    val u = snap.value as HashMap<String,String>
                    if (received == u["URL"] ){
                        myRef!!.child(snap.key!!).child("etat").setValue("refusé")
                        val intent = Intent(this@Ccp,HotelActivity::class.java)
                        startActivity(intent)
                    }
                }
            }
            override fun onCancelled(error: DatabaseError) {

            }
        })

    }
}