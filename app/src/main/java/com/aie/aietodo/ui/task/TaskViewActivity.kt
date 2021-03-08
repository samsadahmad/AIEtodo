package com.aie.aietodo.ui.task

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.aie.aietodo.R
import com.aie.aietodo.data.db.entity.Task
import com.aie.aietodo.databinding.ActivityTaskViewBinding
import com.aie.aietodo.ui.BaseActivity
import com.aie.aietodo.ui.task.viewmodel.TaskViewModel
import com.aie.aietodo.ui.task.viewmodel.TaskViewModelFactory
import com.aie.aietodo.utils.AppConstans
import com.aie.aietodo.utils.coroutines
import net.simplifiedcoding.mvvmsampleapp.util.toast
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class TaskViewActivity : BaseActivity(), KodeinAware {
    //Dependency injection with KodeinAware
    override val kodein by kodein()
    val taskfactory: TaskViewModelFactory by instance()
    private lateinit var viewModel: TaskViewModel
    var task:Task? = null
    lateinit var toolbar: Toolbar
    private lateinit var binding: ActivityTaskViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_view)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_task_view)
        binding.setLifecycleOwner(this)
        setToolbar()

        val id = intent.getLongExtra(AppConstans.ID, 0)
        viewModel = ViewModelProvider(this, taskfactory).get(TaskViewModel::class.java)

        if(id!=0L){
            coroutines.main{
                task = viewModel.getTaskById(id)
                binding.task = task
                supportActionBar!!.setTitle(task?.taskName)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_edit -> {
                var intent = Intent(this, AddEditTaskActivity::class.java)
                intent.putExtra(AppConstans.TYPE, AppConstans.EDIT)
                intent.putExtra(AppConstans.ID, task?.id)
                startActivity(intent)
                finish()
                return true
            }
            R.id.menu_delete -> {
                task?.let {
                    coroutines.main{
                        viewModel.deleteTask(task as Task)
                    }
                }
                finish()
                return true
            }
            else ->{ return super.onOptionsItemSelected(item)}
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.settings, menu)
        return true
    }

    private fun setToolbar() {
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        if (supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            supportActionBar!!.setDisplayShowHomeEnabled(true)
        }
        toolbar.setNavigationOnClickListener { finish() }
    }
}
