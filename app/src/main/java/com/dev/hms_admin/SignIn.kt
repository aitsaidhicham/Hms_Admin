package com.dev.hms_admin

import android.R.attr.password
import android.R.attr.start
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.activity_sign_in.*
import kotlinx.android.synthetic.main.activity_sign_in.view.*


class SignIn : AppCompatActivity() {

    var mAuth: FirebaseAuth? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        mAuth = FirebaseAuth.getInstance()

    }

        fun signInn(view: View) {

            val email = email.text.toString()
            val password = pass.text.toString()

            mAuth!!.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {

                        Toast.makeText(this, "Authentication success !!", Toast.LENGTH_SHORT).show()
                        val userRecord = FirebaseAuth.getInstance().uid
                        if (userRecord == "BdCeNyLsxfe1PrEVO1ReoGOrhyC2") {
                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                            Toast.makeText(this, "Connecté en tant que admin", Toast.LENGTH_SHORT)
                                .show()
                        } else {
                            val intent = Intent(this, HotelActivity::class.java)
                            startActivity(intent)
                            Toast.makeText(this, "connecté", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(this, "erreur !!", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }
