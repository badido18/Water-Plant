package com.example.waterplant.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.waterplant.DecalerActivity
import com.example.waterplant.entities.Plant
import com.example.waterplant.databinding.ArrosageItemLayoutBinding
import com.example.waterplant.utils.ImageManager
import com.example.waterplant.utils.TimeManager
import com.example.waterplant.viewmodels.ArrosageViewModel
import java.io.Serializable
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter

class ArrosageAdapter(
    context: Context,
    model: ArrosageViewModel
) : RecyclerView.Adapter<ArrosageAdapter.ViewHolder>() {
    private val ctx: Context by lazy { context }

    private val TM = TimeManager()

    private val IM  = ImageManager()

    private val model = model

    private var allArrosages: List<Plant> = listOf()

    private var allArrosagesClassique: List<Plant> = listOf()
    private var allArrosagesNutriments: List<Plant> = listOf()

    inner class ViewHolder(val binding: ArrosageItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = ArrosageItemLayoutBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val p = allArrosages[position]
        holder.binding.name.text = p.name

        val a_lastArosage = TM.stringToDate(p.lastArosage)
        val diff_lastArosage = TM.diffDays(a_lastArosage, TM.today())

        try {
            holder.binding.plantImageItem.setImageBitmap(IM.getBitmap(allArrosages[position].image!!))
        }
        catch (e:Exception){
            Log.d("exception","no iamge - put default")
        }

        lateinit var type: String

        if (diff_lastArosage >= p.freqArosage) {
            type = "Arrossage classique"
            holder.binding.type.setTextColor(Color.parseColor("#FF00897B"))
        } else {
            type = "Arrossage avec nutriments"
            holder.binding.type.setTextColor(Color.parseColor("#FFFF4081"))
        }

        holder.binding.type.text = type

        holder.binding.decaler.setOnClickListener {
            Intent(ctx, DecalerActivity::class.java).also {
                it.putExtra("oneArrosage", allArrosages[position])
                it.putExtra("type", type)
                ctx.startActivity(it)
            }
        }

        holder.binding.effectuer.setOnClickListener {
            Log.d("ZOZO", "holder.binding.effectuer")
            Log.d("ZOZO", "NEGTH AVANT ${allArrosages.size}")
//            allArrosages = allArrosages.filterIndexed { index, plant ->  index != position}
            if (type == "Arrossage classique") {
                p.lastArosage = TM.todayToString()
            } else {
                p.lastNutriment = TM.todayToString()
            }

            model.updatePlant(p)
            Log.d("ZOZO", "NEGTH APRES ${allArrosages.size}")
        }

    }


    override fun getItemCount(): Int = allArrosages.size

    @RequiresApi(Build.VERSION_CODES.O)
    fun setArrosages(arrosages: List<Plant>) {


        var tmp: MutableList<Plant> = mutableListOf()

        for (p in arrosages) {
            val a_lastArosage = TM.stringToDate(p.lastArosage)
            val a_lastNutriment = TM.stringToDate(p.lastNutriment)

            val diff_lastArosage = TM.diffDays(a_lastArosage, TM.today())
            val diff_lastNutriment = TM.diffDays(a_lastNutriment, TM.today())

            if (diff_lastArosage >= p.freqArosage || diff_lastNutriment >= p.freqNutriment)
                tmp.add(p)
        }

        allArrosages = tmp
        notifyDataSetChanged()
    }

}