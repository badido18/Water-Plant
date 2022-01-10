package com.example.waterplant.adapters

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.waterplant.FichePlantActivity
import com.example.waterplant.entities.Plant
import com.example.waterplant.databinding.PlantItemLayoutBinding
import com.example.waterplant.utils.ImageManager
import com.example.waterplant.utils.TimeManager
import com.example.waterplant.utils.TimeManager.Companion.NEVER
import java.io.Serializable

class MainAdapter(
    context: Context,
    var query : String = ""
)
    : RecyclerView.Adapter<MainAdapter.ViewHolder>(){

        private val ctx:Context by lazy { context }
        private var allPlants : List<Plant> = listOf()
        private val TM by lazy { TimeManager() }
        private val IM by lazy { ImageManager() }
        inner class ViewHolder(val binding: PlantItemLayoutBinding) : RecyclerView.ViewHolder(binding.root)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val v = PlantItemLayoutBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
            return ViewHolder( v )
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.binding.name.setText(allPlants[position].name)
            val nextArro = TM.nextDateToString(allPlants[position].lastArosage,allPlants[position].freqArosage)
            val nextNutri = TM.nextDateToString(allPlants[position].lastNutriment,allPlants[position].freqNutriment)
            try {
                holder.binding.plantImageItem.setImageBitmap(IM.getBitmap(allPlants[position].image!!))
            }
            catch (e:Exception){
                Log.d("exception","no iamge - put default")
            }
            if (allPlants[position].freqArosage == NEVER && allPlants[position].freqNutriment == NEVER)
                    holder.binding.nextArosage.setText("non specifie")
            else
                if(nextArro <  nextNutri)
                    if (allPlants[position].freqArosage == NEVER)
                        holder.binding.nextArosage.setText(nextNutri)
                    else
                        holder.binding.nextArosage.setText(nextArro)
                else
                    if (allPlants[position].freqNutriment == NEVER)
                        holder.binding.nextArosage.setText(nextArro)
                    else
                        holder.binding.nextArosage.setText(nextNutri)
            holder.binding.itemLayout.setOnClickListener {
                Intent(ctx, FichePlantActivity::class.java).also {
                    it.putExtra("plant_id",allPlants[position])
                    ctx.startActivity(it)
                }
            }
        }

        override fun getItemCount(): Int =  allPlants.size

        fun setPlants(plants: List<Plant> ){
            allPlants = plants
            notifyDataSetChanged()
        }

        @JvmName("setQuery1")
        fun setQuery(newQuery: String) {
            query = newQuery
        }

}