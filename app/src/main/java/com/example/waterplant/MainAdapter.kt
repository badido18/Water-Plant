package com.example.waterplant

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.waterplant.databinding.ActivityMainBinding

class MainAdapter
    (val selectedLivres : LiveData<List<Plant>>, binding : ActivityMainBinding)
    : RecyclerView.Adapter<MainAdapter.VH>(){

        private var allPlants : List<Plant> = listOf()
        class VH(itemView: View) : RecyclerView.ViewHolder(itemView){
            lateinit var plant : Plant
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
            val v = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.plant_item_layout, parent, false)
            val holder = VH( v )
            return holder
        }

        override fun onBindViewHolder(holder: VH, position: Int) {

            /* mémoriser la référence vers l'auteur dans le holder */
            holder.plant = allPlants[position]
//
//            /* mettre à jour le nom et prenom de l'auteur affiché dans le RecuclerView */
//            holder.itemView.findViewById<TextView>(R.id.plant_name_item).text = holder.plant.name
////            calcul de prochaine arrosage en utilisatnt lastArrosage et frequence arrosage :
//            val a = holder.plant.freqArosage
//            val b = holder.plant.lastArosage
//            val prochainArrosage = "prochainArrosage A calculer depuis a et b"
//            holder.itemView.findViewById<TextView>(R.id.date_time_prochain_arro_item).text = prochainArrosage
////

        }

        override fun getItemCount(): Int =  allPlants.size

        /* Remplacer la liste de tous les auteurs par une nouvelle liste */
        fun setPlants( plants: List<Plant> ){
            this.allPlants = plants
            notifyDataSetChanged()
        }

}