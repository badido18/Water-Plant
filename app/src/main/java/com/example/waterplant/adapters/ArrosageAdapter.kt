package com.example.waterplant.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.waterplant.FichePlantActivity
import com.example.waterplant.entities.Plant
import com.example.waterplant.databinding.PlantItemLayoutBinding
import java.io.Serializable

class ArrosageAdapter(
    context: Context
)
    : RecyclerView.Adapter<ArrosageAdapter.ViewHolder>(){
    private val ctx:Context by lazy { context }
    private var allArrosages : List<Plant> = listOf()

    inner class ViewHolder(val binding: PlantItemLayoutBinding) : RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = PlantItemLayoutBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder( v )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.name.setText(allArrosages[position].name)
        holder.binding.nextArosage.setText(allArrosages[position].lastArosage)
        holder.binding.itemLayout.setOnClickListener {
            Intent(ctx, FichePlantActivity::class.java).also {
                it.putExtra("plant_id",allArrosages[position] as Serializable)
                ctx.startActivity(it)
            }

        }
    }

    override fun getItemCount(): Int =  allArrosages.size

    fun setPlants(plants: List<Plant> ){
        allArrosages = plants
        notifyDataSetChanged()
    }

}