package com.example.waterplant

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.core.app.NotificationCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.waterplant.adapters.MainAdapter
import com.example.waterplant.viewmodels.MainViewModel
import com.example.waterplant.databinding.ActivityMainBinding
import com.example.waterplant.entities.Plant
import com.example.waterplant.room.PlantItem

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val model by lazy { ViewModelProvider(this).get(MainViewModel::class.java) }
    private val adapter by lazy { MainAdapter(this) }
    private var showSearch = false
    private lateinit var notification: Notification


    override fun onResume() {
        super.onResume()
        model.loadPlants()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        model.loadPlants()

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        model.plants.observe(this) {
            adapter.setPlants(it)
        }

        binding.addButton.setOnClickListener {
            var intent: Intent = Intent(this, CreatePlantActivity::class.java)
            startActivity(intent)
        }

        binding.search.setOnClickListener {
            if (showSearch) {
                binding.searchText.setVisibility(View.GONE)
                this.showSearch = false
            } else {
                binding.searchText.setVisibility(View.VISIBLE)
                this.showSearch = true
            }
        }

        binding.searchText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val query: String = s.toString()
                model.partialNomPlants(query)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        createNotificationChannel()
        notification = createNotification()

        val onBootReceiver: ComponentName =
            ComponentName(getApplication().getPackageName(), AutoStart::class.java.name)

        if (getPackageManager().getComponentEnabledSetting(onBootReceiver)
            != PackageManager.COMPONENT_ENABLED_STATE_ENABLED
        )
            getPackageManager().setComponentEnabledSetting(
                onBootReceiver, PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP
            )

    }


    fun testerArrosage(v: View) {
        Intent(this, ArrosageActivity::class.java).also {
            startActivity(it)
        }
    }

    fun initialiser(view: View) {
        val names = resources.getStringArray(R.array.name)
        val latinNames = resources.getStringArray(R.array.latinName)
        val freqArosages = resources.getIntArray(R.array.freqArosage)
        val lastArosages = resources.getStringArray(R.array.lastArosage)

        val freqNutriments = resources.getIntArray(R.array.freqNutriment)
        val lastNutriments = resources.getStringArray(R.array.lastNutriment)

        for (i in names.indices) {
            model.addPlant(
                PlantItem(
                    names[i],
                    latinNames[i],
                    freqArosages[i],
                    lastArosages[i],
                    freqNutriments[i],
                    lastNutriments[i]
                )
            )
        }
    }

    fun supprimerTous(view: View) {
        model.deleteAllPlants()
    }


    companion object { /* définir les constantes */
        const val notId = 9
        const val CHANNEL_ID = "message urgent"
    }

    fun createNotificationChannel() {
        val name = "channel_name"
        val description = "description"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(CHANNEL_ID, name, importance)
        channel.description = description
        // récupérer NotificationManager et enregistrer le channel
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)

    }


    fun createNotification(): Notification {
        val goToArrosage = Intent(this, ArrosageActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            this, 1, goToArrosage,
            PendingIntent.FLAG_IMMUTABLE
        )

        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("ARROSAGE DES PLANTES")
            .setContentText("ici plantes a arroser aujourd'hui")
            .setContentIntent(pendingIntent)
//            .setAutoCancel(true)
            .setSmallIcon(R.drawable.small_up)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setLargeIcon(
                BitmapFactory.decodeResource(
                    resources,
                    R.drawable.ic_watering_plants
                )
            )
            .build()
    }

    fun triggerNotif(view: View) {
        val notifManger = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notifManger.notify(notId, notification)
    }


}