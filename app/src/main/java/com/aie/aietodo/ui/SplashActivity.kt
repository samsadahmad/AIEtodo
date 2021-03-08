package com.aie.aietodo.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.aie.aietodo.R
import com.aie.aietodo.ui.task.TaskActivity

/**
 * @class : SplashActivity
 * @desc : This is launcher activity of application to splash screen
 */
class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        /**
         * A handler postDelayed method is used wheen there is a need to
         * perform a particular event after a specific number of times(say 2 sec here)
         * After 2 secs SplashActivity automatically start TaskActivity
         */
        Handler().postDelayed(Runnable {
            startActivity(Intent(SplashActivity@this, TaskActivity::class.java))
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
            finish()
        }, 2000)
    }
}
