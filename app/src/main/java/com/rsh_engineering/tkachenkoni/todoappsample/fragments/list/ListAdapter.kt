package com.rsh_engineering.tkachenkoni.todoappsample.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.rsh_engineering.tkachenkoni.todoappsample.R
import com.rsh_engineering.tkachenkoni.todoappsample.data.entity.RecordToDo
import com.rsh_engineering.tkachenkoni.todoappsample.data.models.Priority
import kotlinx.android.synthetic.main.row_layout.view.*

/**
 * Created by Nikolay Tkachenko on 25, September, 2020
 *
 **/
class ListAdapter: RecyclerView.Adapter<ListAdapter.ItemRecordViewHolder>() {

    var recordsList = emptyList<RecordToDo>()

    class ItemRecordViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemRecordViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_layout, parent, false)

        return ItemRecordViewHolder(view)
    }

    override fun getItemCount(): Int {
        return recordsList.size
    }

    override fun onBindViewHolder(holder: ItemRecordViewHolder, position: Int) {
        holder.itemView.title_txt.text = recordsList[position].title
        holder.itemView.descr_txt.text = recordsList[position].description

        holder.itemView.row_background.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(recordsList[position])
            //holder.itemView.findNavController().navigate(R.id.action_listFragment_to_updateFragment)
            holder.itemView.findNavController().navigate(action)
        }

        val priority = recordsList[position].priority
        when(priority){
            Priority.HIGH -> holder.itemView.priority_indicator.setCardBackgroundColor(ContextCompat.getColor(holder.itemView.context,
            R.color.red))
            Priority.MEDIUM -> holder.itemView.priority_indicator.setCardBackgroundColor(ContextCompat.getColor(holder.itemView.context,
                R.color.yellow))
            Priority.LOW -> holder.itemView.priority_indicator.setCardBackgroundColor(ContextCompat.getColor(holder.itemView.context,
                R.color.green))
        }
    }

    fun setData(recordsToDo: List<RecordToDo>){
        this.recordsList = recordsToDo
        notifyDataSetChanged()
    }

}