package com.dev.hms_admin

import android.content.Intent
import android.media.Image
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item.*

import java.lang.Exception


class MainActivity : AppCompatActivity() {

    var firebaseDatabase : FirebaseDatabase? = null
    var myRef : DatabaseReference? = null

    var array_nom: MutableList<String> = mutableListOf<String>()
    var array_email: MutableList<String> = mutableListOf<String>()
    var array_localisation: MutableList<String> = mutableListOf<String>()
    var array_prix: MutableList<String> = mutableListOf<String>()
    var array_rating: MutableList<String> = mutableListOf<String>()
    var array_numTel: MutableList<String> = mutableListOf<String>()
    var array_wilaya: MutableList<String> = mutableListOf<String>()
    var array_images: MutableList<String> = mutableListOf<String>()




    var mAuth : FirebaseAuth? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        firebaseDatabase = FirebaseDatabase.getInstance()
        myRef = firebaseDatabase!!.getReference()
        val newRef = firebaseDatabase!!.getReference("hotels")

        newRef.addValueEventListener(object : ValueEventListener{
            override fun onCancelled(error: DatabaseError) {
            }
            override fun onDataChange(snapshot: DataSnapshot) {
                    var i = 0

                for (snap in snapshot.children) {

                    val hm = snap.value as HashMap<String,String>
                    if(hm.size > 0){
                        val nomFB = hm["nom"].toString()
                        val numTelFB = hm["numero_telephone"].toString()
                        val localisationFB = hm["localisation"].toString()
                        val emailFB = hm["email"].toString()
                        val ratingFB = hm["Rating"].toString()
                        val prixFB = hm["prix_par_nuit"].toString()
                        val wilayaFB = hm["wilaya"].toString()
                        /*val typeChambreFB = hm["type_chambre"]*/
                        val im = hm["image"].toString()


                        array_nom.add(nomFB)
                        array_email.add(emailFB).toString()
                        array_localisation.add(localisationFB).toString()
                        array_numTel.add(numTelFB).toString()
                        array_rating.add(ratingFB).toString()
                        array_prix.add(prixFB).toString()
                        array_wilaya.add(wilayaFB).toString()
                       /* if (typeChambreFB != null) {
                            array_typechambre!!.add(typeChambreFB)
                        }*/
                        array_images.add(im!!).toString()


                    }

                    i++

                }

                var dis = array_nom.distinct()
                var size = dis.size


                var arrayList = ArrayList<Model>()

                try {

                    for(a in 0..size-1){
                        val nom = array_nom.get(a)
                        val wilaya = array_wilaya.get(a)
                        val localisation = array_localisation.get(a)
                        val rating = array_rating.get(a)
                        val prix = array_prix.get(a)
                        val numero = array_numTel.get(a)
                        val maill = array_email.get(a)
                        val img = array_images.get(a)

                        /*val m = picasso_image as Int*/


                        arrayList.add(Model(nom, wilaya, localisation, rating,prix,maill,numero,img))
                        arrayList.distinct()
                    }

                }catch (e: Exception){

                }
                println(arrayList.size)
                arrayList.distinct()
                val myAdapter = MyAdapter(arrayList,this)

                recyclerView.layoutManager = LinearLayoutManager(applicationContext)
                recyclerView.adapter = myAdapter

            }
        })



    }

    fun ajouterHotel(view:View){
        val intent = Intent(this, AjouterHotelActivity::class.java)
        startActivity(intent)
    }



}

