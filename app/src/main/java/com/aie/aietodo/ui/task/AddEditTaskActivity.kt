package com.aie.aietodo.ui.task

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.aie.aietodo.R
import com.aie.aietodo.data.db.entity.Task
import com.aie.aietodo.databinding.ActivityAddEditBinding
import com.aie.aietodo.databinding.ActivityTaskViewBinding
import com.aie.aietodo.ui.BaseActivity
import com.aie.aietodo.ui.task.viewmodel.TaskViewModel
import com.aie.aietodo.ui.task.viewmodel.TaskViewModelFactory
import com.aie.aietodo.utils.AppConstans
import com.aie.aietodo.utils.coroutines
import kotlinx.android.synthetic.main.activity_add_edit.*
import net.simplifiedcoding.mvvmsampleapp.util.toast
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance
import java.util.*

class AddEditTaskActivity : BaseActivity(), KodeinAware {
    //Dependency injection with KodeinAware
    override val kodein by kodein()
    val taskfactory: TaskViewModelFactory by instance()
    private lateinit var viewModel: TaskViewModel
    private lateinit var binding: ActivityAddEditBinding
    var task:Task? = null
    var isEditing = false // Add or Edit flag
    var id: Long = 0;
    var type: String ? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_edit)
        binding.setLifecycleOwner(this)


        viewModel = ViewModelProvider(this, taskfactory).get(TaskViewModel::class.java)

        // Get the intent and check if it was editing existing contact or create a new one
        val mIntent = intent
        if (mIntent != null) {
            type = mIntent.getStringExtra(AppConstans.TYPE)
            id = mIntent.getLongExtra(AppConstans.ID, 0L)
            //Checking this activity for Add/Edit task
            if (type == AppConstans.EDIT) {
                isEditing = true
                binding.task = task
                //This coroutine block fecthing task by id and stting title
                coroutines.main{
                    task = viewModel.getTaskById(id)
                    binding.task = task
                    setTitle(getResources().getString(R.string.edit_title));
                }
            } else {
                setTitle(getResources().getString(R.string.add_title))
            }
        }
    }

    /**
     * @method : onDatePicker
     * @desc : This method will launch datepicker dialog
     * @param : View
     */
    fun onDatePicker(view: View){
        //Calender instance
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        //task_date.setText("" + dayOfMonth + "/" + month + "/" + year)
        val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            // Display Selected date in TextView
            task_date.setText("" + dayOfMonth + "/" + month + "/" + year)
            task_date.setError(null)
        }, year, month, day)
        //Setting minmum date is today date to avoid past date selection
        dpd.getDatePicker().setMinDate(c.getTimeInMillis());
        dpd.show()
    }

    /**
     * @method : onSaveBtnClick
     * @desc : This method will validate data and save/update data into DB
     * @param : View
     */
    fun onSaveBtnClick(view: View){
        val taskName = task_name.getText().toString().trim()
        val taskDate = task_date.getText().toString().trim()
        val taskCreator = task_creator.getText().toString().trim()
        if (isDataValid(taskName, taskDate, taskCreator)) {
            if (isEditing) {
                //This block will update task in DB
                val task = Task(id, taskName, taskCreator, taskDate)
                coroutines.main{
                    viewModel.updateTask(task)
                    toast(resources.getString(R.string.task_saved_updated))
                }
                finish()
            } else {
                //This block will check duplicate task and insert into DB
                val task = Task(taskName, taskCreator, taskDate)
                coroutines.main{
                    val upsertId = viewModel.saveTask(task)
                    if(upsertId==-1L){
                        task_name.setError(resources.getString(R.string.error_duplicate))
                        toast(resources.getString(R.string.error_duplicate))
                        return@main
                    }else{
                        toast(resources.getString(R.string.task_saved_updated))
                        finish()
                    }
                }
            }
        }
    }

    /**
     * @method : onCancelBtnClick
     * @desc : This method will finish the current activity
     * @param : View
     */
    fun onCancelBtnClick(view: View){
        finish()
    }

    /**
     * @method : isDataValid
     * @desc : This method will validate task data
     * @param : View
     */
    private fun isDataValid(
        taskName: String,
        taskDate: String,
        taskCreator: String
    ): Boolean {
        if (taskName.isEmpty()) {
            task_name.setError(resources.getString(R.string.error_blank))
            return false
        }
        if (taskDate.isEmpty()) {
            task_date.setError(resources.getString(R.string.error_blank))
            return false
        }
        if (taskCreator.isEmpty()) {
            task_creator.setError(resources.getString(R.string.error_blank))
            return false
        }
        return true
    }
}
