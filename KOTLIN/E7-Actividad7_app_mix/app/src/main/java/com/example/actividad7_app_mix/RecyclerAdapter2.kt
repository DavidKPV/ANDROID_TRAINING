package com.example.actividad7_app_mix

import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

class RecyclerAdapter2: RecyclerView.Adapter<RecyclerAdapter2.ViewHolder>(){

    private val titles = arrayOf(
        "El curso de Kotlin avanzado es muy interesante",
        "Intents implícitos y explícitos",
        "Linear, Table, Constraint y Relative Layouts son utilizados para crear UI en Android",
        "Adaptadores son utilizados para utilizar en recycler view",
        "Android tiene la misma arquitectura desde hace mucho tiempo",
        "Actualmente Android es uno de los S.O. más utilizados",
        "Las corrutinas son más eficientes que los hilos y los AsyncTask",
        "Jetpack compoese es otra manera de crear UI en Android",
        "Los principios SOLID son muy utilizados en el desarrollo de software",
        "En Android existen las Activities y los Fragments")


    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context)
        .inflate(R.layout.card_layout_sentences, viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.itemTitle.text = titles[i]
    }

    override fun getItemCount(): Int {
        return titles.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var itemTitle: TextView

        init {
            itemTitle = itemView.findViewById(R.id.itemTitle)

            itemView.setOnClickListener { v: View ->
                var position: Int = getAdapterPosition()
                Snackbar.make(v, "Click detected on item $position",
                Snackbar.LENGTH_LONG).setAction("Action", null).show()
            }
        }
    }

}
