package fr.uparis.anesbouzouaouiexamen

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.irif.zielonka.exam2022session1gra.Plant
import fr.uparis.anesbouzouaouiexamen.databinding.ActivityOperationsBinding

class MainAdapter(val selectedLivres : MutableList<Long>, binding : ActivityOperationsBinding) : RecyclerView.Adapter<MainAdapter.VH>(){
        //la liste d'auteurs à afficher
        private var allPlants : List<Plant> = listOf()
        //private var checkedLivresIds : MutableList<Long> = mutableListOf()
        //la liste des ids (clés primaires) des auteurs marqués comme isChecked sur la liste
        //var checkedLivresIds : MutableList<Long> = mutableListOf()
        val auteurElem = binding.auteur
        val titreElem = binding.titre
        val emplacementElem = binding.emplacement
        class VH(itemView: View) : RecyclerView.ViewHolder(itemView){
            lateinit var author : Plant
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
            val v = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_layout, parent, false)

            val holder = VH( v )
            // Dans la suite on suppose que holder.author est la référence vers l'auteur affiché
            // par la View v.  C'est la fonction onBindViewHolder qui doit s'en charger.

            /* v.check est équivalent à  v.findViewById<CheckBox>(R.id.check) et donne la référence vers la CheckBox
             * Installer un listener qui met à jour la liste checkedIds en fonction
             * de l'état de la CheckBox (de la valeur de la propriété isChecked de la CheckBox)
             */
//            v.setOnClickListener( ) {
////                it as CheckBox    //it : le paramètre par défaut de lambda
////                if( it.isChecked ){
////                    selectedLivres.add ( holder.author )
////                }else{
////                    selectedLivres.remove( holder.author )
////                }
//                val indice = v
//                selectedLivres.add(holder.author.id)
//            }



            v.setOnClickListener {
                selectedLivres.clear()
                selectedLivres.add(holder.author.id)
                Log.d("Anes", "Index to remove : ${selectedLivres[0]}")
                Log.d("Anes", "Index to remove : ${selectedLivres[0]}")
                auteurElem.findViewById<TextView>(R.id.auteur).text = holder.author.auteur
                titreElem.findViewById<TextView>(R.id.titre).text = holder.author.titre
                emplacementElem.findViewById<TextView>(R.id.emplacement).text = holder.author.emplacement

            }
            return holder
        }

        override fun onBindViewHolder(holder: VH, position: Int) {

            /* mémoriser la référence vers l'auteur dans le holder */
            holder.author = allPlants[position]

            /* mettre à jour le nom et prenom de l'auteur affiché dans le RecuclerView */
            holder.itemView.findViewById<TextView>(R.id.auteur).text = holder.author.auteur
            holder.itemView.findViewById<TextView>(R.id.titre).text = holder.author.titre
            holder.itemView.findViewById<TextView>(R.id.emplacement).text = holder.author.emplacement

            holder.itemView.setBackgroundColor(
                if (position % 2 == 0)
                    Color.argb(30,0,220,0)
                else
                    Color.argb(30,0,0,220)
            )

            /* Mettre à jour l'état de la CheckBox.
             * la propriété  isChecked de la CheckBox est true si est seulement si
             * idLivre de l'auteur affiché est  dans la  liste checkedIds
             */
//            holder.itemView.findViewById<CheckBox>(R.id.check).isChecked = holder.author in selectedLivres
        }

        override fun getItemCount(): Int =  allPlants.size

        /* Remplacer la liste de tous les auteurs par une nouvelle liste */
        fun setLivres( authors: List<Plant> ){
            this.allPlants = authors
            notifyDataSetChanged()  /* demander que le RecyclerView affiche la nouvelle liste */
            Log.d( "Adapter ", "setListLivres $authors")
        }

}