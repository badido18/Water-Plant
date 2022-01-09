package com.example.waterplant.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.waterplant.DecalerActivity
import com.example.waterplant.entities.Plant
import com.example.waterplant.databinding.ArrosageItemLayoutBinding
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter

class ArrosageAdapter(
    context: Context
) : RecyclerView.Adapter<ArrosageAdapter.ViewHolder>() {
    private val ctx: Context by lazy { context }

    private var allArrosages: List<Plant> = listOf()

    inner class ViewHolder(val binding: ArrosageItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = ArrosageItemLayoutBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.binding.name.text = allArrosages[position].name

//        val today = "2020-01-12"
//        val type = if (sameDay(allArrosages[position].lastArosage, today))
        val type = if (true)
            "Arrossage avec nutriments"
        else
            "Arrossage classique"

        holder.binding.type.text = type

        holder.binding.decaler.setOnClickListener {
            Intent(ctx, DecalerActivity::class.java).also {
                it.putExtra("oneArrosage", allArrosages[position])
                ctx.startActivity(it)
            }
        }
    }



    override fun getItemCount(): Int = allArrosages.size

    @RequiresApi(Build.VERSION_CODES.O)
    fun setArrosages(arrosages: List<Plant>) {


        var tmp: MutableList<Plant> = mutableListOf()


        val today = LocalDate.now()

        for (p in arrosages) {
            val a_lastArosage =
                LocalDate.parse(p.lastArosage, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
            val a_lastNutriment =
                LocalDate.parse(p.lastNutriment, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
            val period_lastArosage = Period.between(a_lastArosage, today)
            val diff_lastArosage = period_lastArosage.days


            val period_lastNutriment = Period.between(a_lastNutriment, today)
            val diff_lastNutriment = period_lastNutriment.days

            if (diff_lastArosage >= p.freqArosage || diff_lastNutriment >= p.freqNutriment)
                tmp.add(p)
        }

        allArrosages = tmp
        notifyDataSetChanged()
    }

}