package com.aie.aietodo.ui.task

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.aie.aietodo.R
import com.aie.aietodo.ui.BaseActivity
import com.aie.aietodo.ui.task.adapter.TaskAdapter
import com.aie.aietodo.ui.task.viewmodel.TaskViewModel
import com.aie.aietodo.ui.task.viewmodel.TaskViewModelFactory
import com.aie.aietodo.utils.AppConstans
import com.aie.aietodo.utils.coroutines
import kotlinx.android.synthetic.main.activity_task.*
import net.simplifiedcoding.mvvmsampleapp.util.hide
import net.simplifiedcoding.mvvmsampleapp.util.show
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class TaskActivity : BaseActivity(), KodeinAware {

    //Dependency injection with KodeinAware
    override val kodein by kodein()
    val taskfactory: TaskViewModelFactory by instance()

    private lateinit var viewModel: TaskViewModel
    private lateinit var taskAdapter: TaskAdapter;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task)
        bindUI()
    }

    /**
     * @method : bindUI
     * @desc : This method is created bind UI and will populate tasks in view
     */
    private fun bindUI() {
        taskAdapter = TaskAdapter(this)
        viewModel = ViewModelProvider(this, taskfactory).get(TaskViewModel::class.java)
        //Apply Scope function using to set recycler view params
        recycler_View.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = taskAdapter
        }

        //This Coroutine block will get & listen LiveData changes
        coroutines.main{
            viewModel.tasks.await().observe(this, Observer {
                if(it!=null)  {
                    taskAdapter.addTask(it)
                    if (it.isEmpty()) tv_task_empty.show() else tv_task_empty.hide()
                }else{
                    tv_task_empty.show()
                }
            })
        }
    }

    /**
     * @method : onAddBtnClick
     * @desc : This method will launch AddEditTaskActivity Activity with type("Add/Edit")
     * @param : View
     */
    fun onAddBtnClick(view: View){
        var intent = Intent(this, AddEditTaskActivity::class.java)
        intent.putExtra(AppConstans.TYPE, AppConstans.ADD)
        startActivity(intent)
    }
}
