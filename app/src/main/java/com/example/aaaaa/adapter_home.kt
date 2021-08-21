package com.example.aaaaa

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.realm.Realm
import io.realm.RealmConfiguration
import kotlinx.android.synthetic.main.home_items.view.*

class adapter_home() : RecyclerView.Adapter<adapter_home.ViewHolderIndex>() {

    var config = RealmConfiguration.Builder().name("database").build()
    var realm = Realm.getInstance(config)
    val readData = realm.where(database::class.java).findAll()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): adapter_home.ViewHolderIndex {
        var myViewInflater = LayoutInflater.from(parent.context).inflate(R.layout.home_items,parent,false)
        return ViewHolderIndex(myViewInflater)
    }

    override fun getItemCount(): Int {
        return readData.size
    }

    override fun onBindViewHolder(holder: adapter_home.ViewHolderIndex, position: Int) {
        val allDataaa = readData!![position]
        holder.bind(allDataaa!!)
    }

    class ViewHolderIndex(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(myData : database){
            val name = itemView.textTitle as TextView
            name.text = myData.name


            itemView.setOnClickListener {
                var intent = Intent(itemView.context,lessonActivity::class.java)
                intent.putExtra("title",myData.name)
                intent.putExtra("content",myData.body)
                // لو كان عندي صور
                // intent.putExtra("imagee",myData.image_url)
                itemView.context.startActivity(intent)
            }
        }
    }
}