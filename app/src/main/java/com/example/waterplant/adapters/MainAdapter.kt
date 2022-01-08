package com.example.waterplant.adapters

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.waterplant.CreatePlantActivity
import com.example.waterplant.FichePlantActivity
import com.example.waterplant.entities.Plant
import com.example.waterplant.databinding.PlantItemLayoutBinding
import kotlin.time.Duration.Companion.hours

class MainAdapter()
    : RecyclerView.Adapter<MainAdapter.ViewHolder>(){

        private val context:Context by lazy {context}

        private var allPlants : List<Plant> = listOf()

        var ctx:Context?  = null

        inner class ViewHolder(val binding: PlantItemLayoutBinding) : RecyclerView.ViewHolder(binding.root)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            ctx = parent.context
            val v = PlantItemLayoutBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
            return ViewHolder( v )
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.binding.name.setText(allPlants[position].name)
            holder.binding.nextArosage.setText(allPlants[position].nextArosage)
            holder.binding.itemLayout.setOnClickListener {
                val context = null
                Intent(ctx, FichePlantActivity::class.java).also {
                    it.putExtra("plant_id",allPlants[position].id)
                    startActivity(it as Context,it,null)
                }

            }
        }

        override fun getItemCount(): Int =  allPlants.size

        fun setPlants(plants: List<Plant> ){
            allPlants = plants
            notifyDataSetChanged()
        }

}