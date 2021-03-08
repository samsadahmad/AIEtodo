package com.aie.aietodo.ui.task.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aie.aietodo.R
import com.aie.aietodo.data.db.entity.Task
import com.aie.aietodo.ui.task.TaskViewActivity
import com.aie.aietodo.utils.AppConstans
import kotlinx.android.synthetic.main.task_item.view.*

/**
 * @class : TaskAdapter
 * @desc : This class created for task recycler view adapter
 */
class TaskAdapter(val context: Context) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {
    private var tasks: List<Task> = ArrayList<Task>();

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.task_item, parent, false)
        return TaskViewHolder(view)
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = tasks.get(position)
        holder.taskName.text = task.taskName
        holder.taskCreator.text = task.taskCreator
        holder.taskDate.text = ""+task.taskDate

        holder.itemView.setOnClickListener {
            val intent = Intent(context, TaskViewActivity::class.java)
            intent.putExtra(AppConstans.ID, task.id)
            context.startActivity(intent)
        }
    }

    public fun addTask(tasks: List<Task>){
        this.tasks = tasks;
        notifyDataSetChanged()
    }

    class TaskViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var taskName: TextView = view.tv_task
        var taskCreator: TextView = view.tv_creator
        var taskDate: TextView = view.tv_date
    }
}