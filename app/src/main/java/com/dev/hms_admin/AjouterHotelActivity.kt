package com.dev.hms_admin

import android.Manifest
import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_ajouter_hotel.*
import java.net.URI
import java.util.*


class AjouterHotelActivity : AppCompatActivity() {
    var firebaseStorage : FirebaseStorage? = null
    var myStorageRef : StorageReference? = null
    var image :Uri? = null
    var firebaseDatabase : FirebaseDatabase? = null
    var myRef : DatabaseReference? = null
    val REQUEST_CODE = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ajouter_hotel)
        firebaseDatabase = FirebaseDatabase.getInstance()
        myRef = firebaseDatabase!!.getReference()
        firebaseStorage = FirebaseStorage.getInstance()
        myStorageRef = firebaseStorage!!.getReference()

    }

    fun ajouterHotelbdd(view : View){

        val nom_hotel = nomHotel.text.toString()
        val wilaya_hotel = wilayaHotel.text.toString()
        val prix_nuit = prixNuit.text.toString()
        val rating = stars.text.toString()
        val email = email.text.toString()
        val numTel = phone.text.toString()
        val localisation = localisation.text.toString()
        var chambre_solo = false
        var chambre_duo = false
        var chambre_triple = false
        var chambre_studio = false

        if(solo.isChecked()){
            chambre_solo = true
        }
        if (duo.isChecked()){
            chambre_duo = true
        }
        if (triple.isChecked()){
            chambre_triple = true
        }
        if (studio.isChecked){
            chambre_studio = true
        }

        myRef!!.child("hotels").child(nom_hotel).child("nom").setValue(nom_hotel)
        myRef!!.child("hotels").child(nom_hotel).child("wilaya").setValue(wilaya_hotel)
        myRef!!.child("hotels").child(nom_hotel).child("prix_par_nuit").setValue(prix_nuit)
        myRef!!.child("hotels").child(nom_hotel).child("Rating").setValue(rating)
        myRef!!.child("hotels").child(nom_hotel).child("email").setValue(email)
        myRef!!.child("hotels").child(nom_hotel).child("numero_telephone").setValue(numTel)
        myRef!!.child("hotels").child(nom_hotel).child("localisation").setValue(localisation)
        myRef!!.child("hotels").child(nom_hotel).child("type_chambre").child("solo").setValue(chambre_solo)
        myRef!!.child("hotels").child(nom_hotel).child("type_chambre").child("duo").setValue(chambre_duo)
        myRef!!.child("hotels").child(nom_hotel).child("type_chambre").child("triple").setValue(chambre_triple)
        myRef!!.child("hotels").child(nom_hotel).child("type_chambre").child("studio").setValue(chambre_studio)


    }

    fun pickImage(view : View){
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE){
           image = data!!.data

        }
    }

}