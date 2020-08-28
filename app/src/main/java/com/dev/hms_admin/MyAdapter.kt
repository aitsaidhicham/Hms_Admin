package com.dev.hms_admin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item.view.*

open class MyAdapter (var arrayList: ArrayList<Model>,val context : Context) :
    RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        fun bindItems(model : Model){
            itemView.place_name.text = model.nom
            itemView.wilaya.text = model.wilaya
            itemView.etoile.text = model.rating
            itemView.localisation.text = model.localisation
            itemView.place_image.setImageResource(model.image)



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