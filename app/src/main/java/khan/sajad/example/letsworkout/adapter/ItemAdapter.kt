package khan.sajad.example.letsworkout.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import khan.sajad.example.letsworkout.R
import khan.sajad.example.letsworkout.model.Exercise

class ItemAdapter(val context: Context,
                  private val dataset: ArrayList<Exercise>): RecyclerView.Adapter<ItemAdapter.ItemViewHolder>()  {

    class ItemViewHolder(private val view: View): RecyclerView.ViewHolder(view){
        val exerciseNoTextView: TextView = view.findViewById(R.id.tv_exerciseNo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder{
        return ItemViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_item, parent, false))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]
        holder.exerciseNoTextView.text = item.exerciseNo.toString()
    }
    override fun getItemCount(): Int {
        return dataset.size
    }

}