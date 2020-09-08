package com.dev.hms_admin


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.item_reservation.view.*


class ReservationAdapter(var reservation: MutableList<Reservation>, private val onresClickListener: OnresClickListener) : RecyclerView.Adapter<ReservationAdapter.MyViewHolder>() {


    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {


        fun bindItems(reservation: Reservation){
            itemView.nomClient.text = "email : "+reservation.emailClient
            itemView.dateDebut.text = "Date début : " + reservation.dateDebut
            itemView.dateFin.text = "Date fin : " + reservation.dateFin
            itemView.Duree.text = "Durée : " + reservation.Duree
            itemView.prix.text = "prix : " + reservation.prix

        }



    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {



        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_reservation, parent, false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.itemView.setOnClickListener {
            /*val context = holder.itemView.context
            val intent = Intent(context,Ccp::class.java)
            startActivity(intent)*/
            onresClickListener.onresItemClicked(position)
        }
        holder.bindItems(reservation[position])
    }
    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = reservation.size


}