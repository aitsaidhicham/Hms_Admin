package com.dev.hms_admin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    var firebaseDatabase : FirebaseDatabase? = null
    var myRef : DatabaseReference? = null

    var array_nom : ArrayList<String>? = null
    var array_email : ArrayList<String>? = null
    var array_localisation: ArrayList<String>? = null
    var array_prix : ArrayList<String>? = null
    var array_rating : ArrayList<String>? = null
    var array_numTel : ArrayList<String>? = null
    var array_wilaya : ArrayList<String>? = null
    var array_typechambre : ArrayList<String>? = null


    var mAuth : FirebaseAuth? = null

    override fun onStart() {
        super.onStart()
        mAuth = FirebaseAuth.getInstance()
        var currentUser : FirebaseUser? = mAuth!!.currentUser
        if(currentUser == null){
            val intent = Intent(this, SignIn::class.java)
            startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAuth = FirebaseAuth.getInstance()
        firebaseDatabase = FirebaseDatabase.getInstance()
        myRef = firebaseDatabase!!.getReference()
        val newRef = firebaseDatabase!!.getReference("hotels")

        var size : Int ? = null

        newRef.addValueEventListener(object : ValueEventListener{
            override fun onCancelled(error: DatabaseError) {
            }
            override fun onDataChange(snapshot: DataSnapshot) {
                    var i = 0

                for (snap in snapshot.children) {
                    i++
                    val hm = snap.value as HashMap<String,String>
                    if(hm.size > 0){
                        val nomFB = hm["nom"]
                        val numTelFB = hm["numero_telephone"]
                        val localisationFB = hm["localisation"]
                        val emailFB = hm["email"]
                        val ratingFB = hm["Rating"]
                        val prixFB = hm["prix_par_nuit"]
                        val wilayaFB = hm["wilaya"]
                        /*val typeChambreFB = hm["type_chambre"]*/

                        if (nomFB != null) {
                            array_nom?.add(i,nomFB)
                        }
                        if (emailFB != null) {
                            array_email?.add(i,emailFB)
                        }
                        if (localisationFB != null) {
                            array_localisation?.add(i,localisationFB)
                        }
                        if (numTelFB != null) {
                            array_numTel?.add(i,numTelFB)
                        }
                        if (ratingFB != null) {
                            array_rating?.add(i,ratingFB)
                        }
                        if (prixFB != null) {
                            array_prix?.add(i,prixFB)
                        }
                        if (wilayaFB != null) {
                            array_wilaya?.add(i,wilayaFB)
                        }
                       /* if (typeChambreFB != null) {
                            array_typechambre!!.add(typeChambreFB)
                        }*/

                    }


                }

                size = array_localisation?.size

            }
        })



        val arrayList = ArrayList<Model>()


        for(a in 0..5){
            arrayList.add(a,Model(array_nom!!.get(a),
                array_wilaya!!.get(a), array_localisation!!.get(a), array_rating!!.get(a),R.drawable.accent_bg))
        }


        val myAdapter = MyAdapter(arrayList,this)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = myAdapter

    }

    fun ajouterHotel(view:View){
        val intent = Intent(this, AjouterHotelActivity::class.java)
        startActivity(intent)
    }



}