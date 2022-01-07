package com.example.waterplant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.waterplant.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
//    get MainViewModel et MainAdapter
    val model by lazy{ ViewModelProvider(this).get(MainViewModel::class.java) }
    val adapter by lazy{  MainAdapter(model.selectedLivres, binding) }
    val TAG = "Operationsds"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate( layoutInflater )
        setContentView(binding.root)
        // setContentView(R.layout.activity_add_authors)
        //nom = findViewById<EditText>(R.id.nom)
        //prenom = findViewById<EditText>(R.id.prenom)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        /* quand plants change mettre à jour la liste d'auteurs dans adapter */
        model.plants.observe(this){
            Log.d(TAG, "nouvelle liste auteurs")
            adapter.setLivres( it )
        }
    }

    /*
    //callback function pour le bouton "ajouter auteur"
    fun addLivre( v: View){
        val auteur_ = binding.auteur.text.toString().trim()
        val titre_ = binding.titre.text.toString().trim()
        val emplacement_ = binding.emplacement.text.toString().trim()
        if( auteur_ == "" && titre_ == "" && emplacement_ =="" ){
            afficherDialog("auteur et titre et emplacement ne peuvent pas être vides")
            return
        }
        model.addLivre(auteur_, titre_, emplacement_)
        binding.auteur.text.clear()
        binding.titre.text.clear()
        binding.emplacement.text.clear()
    }

    //calback function pour le bouton "supprimer auteurs"
    fun removeOneLivre( v: View){
        if( model.selectedLivres.isEmpty() ){
            afficherDialog("aucun author sélectionné")
            return
        }
        model.removeOneLivre()
        binding.auteur.text.clear()
        binding.titre.text.clear()
        binding.emplacement.text.clear()
    }
*/

    fun afficherDialog( s: String ){
        AlertDialog.Builder(this)
            .setMessage( s )
            .setPositiveButton("OK"){ d, _ -> d.dismiss() }
            .show()
        return
    }


}