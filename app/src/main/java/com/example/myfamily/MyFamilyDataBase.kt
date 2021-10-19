package com.example.myfamily

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import java.security.AccessControlContext


@Database(entities = [ContactModel::class], version = 1, exportSchema = false)
public abstract class MyFamilyDataBase : RoomDatabase() {

    abstract fun contactDao(): ContactsDao


    companion object {
        @Volatile
        private var INSTANCE: MyFamilyDataBase? = null
        fun getDatabase(context: Context): MyFamilyDataBase {
            INSTANCE?.let {
                return it
            }

          return  synchronized(MyFamilyDataBase::class.java){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MyFamilyDataBase::class.java,
                    "my_family_db"
                ).build()

                INSTANCE  = instance
              instance
            }



        }
    }
}