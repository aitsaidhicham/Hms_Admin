package com.dev.hms_admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_hotel.*
import java.lang.Exception

class HotelActivity : AppCompatActivity(), OnresClickListener {

    var mAuth: FirebaseAuth? = null
    var firebaseDatabase: FirebaseDatabase? = null
    var res: MutableList<Reservation> = mutableListOf<Reservation>()
    var arrayNom: MutableList<String> = mutableListOf<String>()
    var arrayDateDebut: MutableList<String> = mutableListOf<String>()
    var arrayDateFin: MutableList<String> = mutableListOf<String>()
    var arrayDuree: MutableList<String> = mutableListOf<String>()
    var arrayPrix: MutableList<String> = mutableListOf<String>()
    var arrayimage: MutableList<String> = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hotel)

        mAuth = FirebaseAuth.getInstance()
        firebaseDatabase = FirebaseDatabase.getInstance()
        var myRef = firebaseDatabase!!.getReference("reservation")


        myRef.addValueEventListener(object : ValueEventListener {


            override fun onCancelled(error: DatabaseError) {
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {

                    mAuth = FirebaseAuth.getInstance()
                    var i = 0

                    for (snap in dataSnapshot.children) {
                        val map = snap.value as HashMap<String, String>
                        var mail = map["emailhotel"]
                        var etat = map["etat"]
                        val currentuser = mAuth!!.currentUser!!.email
                        println("CUREEEEENTTT USERRRR1111111" + currentuser)
                        println("MAILLLLL1111111" + mail)
                        println("etatttttttttttt111111" + etat)
                        if (currentuser!!.toLowerCase().equals(mail!!.toLowerCase()) && etat.equals("en attente")) {
                            println("CUREEEEENTTT USERRRR" + currentuser)
                            println("etatttttttttttt" + etat)
                            arrayNom.add(map["email"].toString())
                            arrayDateDebut.add(map["datedebut"].toString())
                            arrayDateFin.add(map["datefin"].toString())
                            arrayDuree.add(map["dure"].toString())
                            arrayPrix.add(map["prix"].toString())
                            arrayimage.add(map["URL"].toString())

                        }
                    }
                try{
                        res.add(Reservation(arrayNom.get(i),arrayDateDebut.get(i),arrayDateFin.get(i),arrayDuree.get(i),arrayPrix.get(i),arrayimage.get(i)))

                        i++
                    }
                catch (e: Exception) {
                }
               try {
                   res!!
                   val reservationAdapter = ReservationAdapter(res,this@HotelActivity)
                   recyclerReservations.layoutManager = LinearLayoutManager(applicationContext)
                   recyclerReservations.adapter = reservationAdapter

               }catch (e : Exception){

               }
            }
        })


    }

   override fun onresItemClicked(position: Int) {

       val url = arrayimage.get(position)

       val intent = Intent(this, Ccp::class.java)
       intent.putExtra("URL",url)
       startActivity(intent)

    }

    fun dis(view:View){

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Deconnexion")
        builder.setMessage("Se deconnecter ?")


        builder.setPositiveButton(android.R.string.yes) { dialog, which ->
            try {
                mAuth!!.signOut()
            }catch (e : Exception){

            }
            val intent = Intent(this, SignIn::class.java)
            startActivity(intent)
        }

        builder.setNegativeButton(android.R.string.no) { dialog, which ->

        }
        builder.show()

    }


}
