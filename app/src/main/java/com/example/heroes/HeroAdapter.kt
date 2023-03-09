package com.example.heroes

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class HeroAdapter(val dataSet: List<Hero>) :
    RecyclerView.Adapter<HeroAdapter.ViewHolder>() {


    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewName: TextView
        val textViewDescription: TextView
        val textViewRanking: TextView
        val layout : ConstraintLayout

        init {
            // Define click listener for the ViewHolder's View
            textViewName = view.findViewById(R.id.textView_heroItem_name)
            textViewDescription = view.findViewById(R.id.textView_heroItem_description)
            textViewRanking = view.findViewById(R.id.textView_heroItem_rank)
            layout = view.findViewById(R.id.Layout_heroItem)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_hero, viewGroup, false)

        return ViewHolder(view)
    }


    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        val hero = dataSet[position]
        viewHolder.textViewName.text = hero.name
        viewHolder.textViewDescription.text = hero.description
        viewHolder.textViewRanking.text = hero.ranking.toString()
        viewHolder.layout.setOnClickListener {
            Toast.makeText(it.context, hero.name.toString(), Toast.LENGTH_SHORT).show()
            // 1. Create an intent object with the current activity
            //and the destination activity's class
            val detailIntent = Intent(it.context, HeroDetailActivity::class.java)
            //2 optionally add information to send with the intent
            //key-value pai rs just like with Bundles
            detailIntent.putExtra(HeroDetailActivity.EXTRA_HERO, hero)
//            //3a. launch the new activity using the intent
//          startActivity(registrationIntent)
            //3b. Launch the activity for a result using the variable from the register for result contract above
            it.context.startActivity(detailIntent)
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}
