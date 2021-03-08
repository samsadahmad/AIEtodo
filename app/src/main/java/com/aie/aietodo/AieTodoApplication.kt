package com.aie.aietodo

import android.app.Application
import com.aie.aietodo.data.db.AppDatabase
import com.aie.aietodo.data.repository.TaskRepository
import com.aie.aietodo.ui.task.viewmodel.TaskViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import android.net.ConnectivityManager
import android.content.IntentFilter
import android.os.Build
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import com.aie.aietodo.broadcast.NetworkChangeReceiver
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T

/**
 * It's application which's using for Dependency injection intilization
 * KodeinAware - DI lib for kotlin
 */
class AieTodoApplication: Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@AieTodoApplication))

        bind() from singleton { AppDatabase(instance()) }
        bind() from singleton { TaskViewModelFactory(instance()) }
        bind() from singleton { TaskRepository(instance()) }
    }
}
