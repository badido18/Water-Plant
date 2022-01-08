package com.example.waterplant.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.waterplant.entities.Plant
import com.example.waterplant.databinding.PlantItemLayoutBinding
class MainAdapter
    (val selectedLivres : LiveData<List<Plant>>)
    : RecyclerView.Adapter<MainAdapter.ViewHolder>(){

        private var allPlants : List<Plant> = listOf()

        inner class ViewHolder(val binding: PlantItemLayoutBinding) : RecyclerView.ViewHolder(binding.root)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val v = PlantItemLayoutBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
            return ViewHolder( v )
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.binding.name.setText(allPlants[position].name)
            holder.binding.nextArosage.setText(allPlants[position].nextArosage)
        }

        override fun getItemCount(): Int =  allPlants.size

        fun setPlants(plants: List<Plant> ){
            allPlants = plants
            notifyDataSetChanged()
        }

}