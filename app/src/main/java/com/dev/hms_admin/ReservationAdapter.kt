package com.dev.hms_admin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.item_reservation.view.*

/*
class ReservationAdapter(var reservation: ArrayList<Reservation>, val context: ValueEventListener) : RecyclerView.Adapter<ReservationAdapter.MyViewHolder>() {

    class MyViewHolder(item_reservation : View) : RecyclerView.ViewHolder(item_reservation){

        fun bindItems(reservation: Reservation){
            itemView.nomClient.text = reservation.nomClient


        }


    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        // create a new view
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_reservation, parent, false)

        return MyViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.bindItems = reservation[position]
    }
    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = reservation.size

}*/
