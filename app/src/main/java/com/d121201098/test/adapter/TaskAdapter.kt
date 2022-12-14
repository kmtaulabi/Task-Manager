package com.d121201098.test.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.d121201098.test.R
import com.d121201098.test.fragments.HistoryFragmentDirections
import com.d121201098.test.fragments.HomeFragmentDirections
import com.d121201098.test.model.Task

class TaskAdapter:RecyclerView.Adapter<TaskAdapter.TaskAdapterViewHolder>() {
    private var listTask = emptyList<Task>()

    class TaskAdapterViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val judul:TextView = itemView.findViewById(R.id.judul_tugas)
        val isi:TextView = itemView.findViewById(R.id.isi_tugas)
        val kategori:TextView = itemView.findViewById(R.id.kategori_tugas)
        val klik:CardView = itemView.findViewById(R.id.card)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskAdapterViewHolder {
        return TaskAdapterViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.adapter_task,parent,false))
    }
    override fun onBindViewHolder(holder: TaskAdapterViewHolder, position: Int) {
        val currentTask = listTask[position]
        holder.judul.text = currentTask.judul
        holder.isi.text  = currentTask.isi
        holder.kategori.text = currentTask.kategori

        if (currentTask.status == "Belum Selesai"){

            holder.klik.setOnClickListener{
                holder.itemView.findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment(currentTask))
            }

        }else{
            holder.klik.setOnClickListener{
                holder.itemView.findNavController().navigate(HistoryFragmentDirections.actionHistoryFragmentToDetailFragment(currentTask))
            }
        }
    }
    override fun getItemCount(): Int {
        return listTask.size
    }
    fun setData(task:List<Task>){
        this.listTask = task
        notifyDataSetChanged()
    }
}