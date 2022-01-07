package com.example.waterplant

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData


class MainViewModel(application: Application) : AndroidViewModel(application) {
    val dao = BDPlants.getDatabase(application).monDao()

    val plants : LiveData<List<Plant>> = dao.getPlants()

    /* la liste d'auteurs sélectionés */
    val selectedLivres: MutableList<Long> = mutableListOf()
//    var idSelectedLivre : Int = 0

    val TAG = "MainViewModel"
//    nom, titre, emplacement
//    fun addLivre(auteur: String, titre: String, emplacement: String) {
//        Thread {
//            dao.insererLivre(PlantItem(auteur = auteur.trim(), titre = titre.trim(), emplacement = emplacement.trim() ))
//        }.start()
//    }
//
//    /* supprimer les auteurs qui se trouvent sur la liste selectedLivres */
//    fun removeOneLivre() {
//        Thread {
//            dao.deleteLivre( IdPlant(selectedLivres[0] ))
//            selectedLivres.clear()
//        }.start()
//    }
}