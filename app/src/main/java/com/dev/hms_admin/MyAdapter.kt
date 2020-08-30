package com.dev.hms_admin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item.view.*
import java.lang.Exception

open class MyAdapter(var arrayList: ArrayList<Model>, val context: ValueEventListener) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        fun bindItems(model : Model){


            itemView.place_name.text = model.nom
            itemView.wilaya.text = model.wilaya
            itemView.etoile.text = model.rating
            itemView.localisation.text = model.localisation
            itemView.prix.text = model.prix
            var link = model.image.toString()

            Picasso.get().load(link).into(itemView.place_image)
            /*itemView.place_image.setImageURI(link)*/



        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item,parent,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
       return arrayList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(arrayList[position])
    }
}