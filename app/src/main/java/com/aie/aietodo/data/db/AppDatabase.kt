package com.aie.aietodo.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.aie.aietodo.data.db.entity.Task
import com.aie.aietodo.data.db.entity.TaskDao
import com.aie.aietodo.utils.AppConstans
import androidx.annotation.NonNull



/**
 * This class created to handle
 */
@Database(entities = [Task::class], version = AppConstans.DATABASE_VERSION, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
     abstract fun getTaskDao(): TaskDao

    /**
     * companion object using to create static method for singleton
     * invoke - invoke operator using operator overloading
     */
    companion object{
        @Volatile //Immediately available to all
        private var instance: AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance?: synchronized(LOCK){
            instance?:buildDatabase(context).also{
                instance = it
            }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, AppConstans.DATABASE_NAME)
                //.addMigrations(MIGRATION_1_2)
                //.addCallback(addcallback)
                .build()

        /**
         * Database Migration logic
         */
        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE 'Task' ADD COLUMN 'createdBy' INTEGER DEFAULT 0")
            }
        }

        /**
         * This call will call just after database intilization
         */
        var addcallback = object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                //to do
            }
        }
    }


}