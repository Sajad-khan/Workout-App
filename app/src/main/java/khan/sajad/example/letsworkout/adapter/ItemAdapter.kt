package khan.sajad.example.letsworkout.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import khan.sajad.example.letsworkout.databinding.ItemHistoryRowBinding

class ItemAdapter(private val dataset: ArrayList<String>): RecyclerView.Adapter<ItemAdapter.ItemViewHolder>()  {

    class ItemViewHolder(binding: ItemHistoryRowBinding) : RecyclerView.ViewHolder(binding.root) {
        // Holds the TextView that will add each item to
        val llHistoryItemMain = binding.llHistoryItemMain
        val tvItem = binding.tvItem
        val tvPosition = binding.tvPosition
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder{
        return ItemViewHolder(ItemHistoryRowBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val date: String = dataset[position]

        holder.tvPosition.text = (position + 1).toString()
        holder.tvItem.text = date

        // Updating the background color according to the odd/even positions in list.
        if (position % 2 == 0) {
            holder.llHistoryItemMain.setBackgroundColor(
                Color.parseColor("#EBEBEB")
            )
        } else {
            holder.llHistoryItemMain.setBackgroundColor(
                Color.parseColor("#FFFFFF")
            )
        }
    }
    override fun getItemCount(): Int {
        return dataset.size
    }

}