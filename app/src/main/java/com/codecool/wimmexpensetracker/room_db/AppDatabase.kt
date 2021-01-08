package com.codecool.wimmexpensetracker.room_db

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.codecool.wimmexpensetracker.data.CategoryColor

@Database(entities = [Category::class,Expense::class], version = 2)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase(){
    abstract fun CategoryDao() : CategoryDao
    abstract fun ExpenseDao() : ExpenseDao

    companion object {
        private var instance : AppDatabase? = null
        fun getDatabase(context: Context?) : AppDatabase?{
            if ( instance == null) {
                context?.let{
                    val appDatabase = Room.databaseBuilder(it,AppDatabase::class.java, "database-name")
                        .build()
                    instance = appDatabase
                }
            }

            return instance
        }



        public fun createExpenses(db : AppDatabase){
            db.ExpenseDao().insertExpense(
                Expense("euid1","Electricity bill (2020.12)", 2020,12,1,30.00f,"uid2")
            )
            db.ExpenseDao().insertExpense(
                Expense("euid2","Fuel (10l)", 2020,12,4,15.40f,"uid5")
            )
            db.ExpenseDao().insertExpense(
                Expense("euid3","Rent (2021.1)", 2021,1,2,120.50f,"uid1")
            )
            db.ExpenseDao().insertExpense(
                Expense("euid4","Insurance (2021.1)", 2021,1,1,30.00f,"uid4")
            )
            db.ExpenseDao().insertExpense(
                Expense("euid5","Netflix (2020.11)", 2020,11,14,10.00f,"uid6")
            )
            db.ExpenseDao().insertExpense(
                Expense("euid6","Android course", 2020,10,5,5.00f,"uid6")
            )
        }

        public fun createCategories(db : AppDatabase){
            db.CategoryDao().insertCategory(
                Category("uid1","Rent",CategoryColor.RED)
            )

            db.CategoryDao().insertCategory(
                Category("uid2","Rates and Taxes",CategoryColor.YELLOW)
            )

            db.CategoryDao().insertCategory(
                Category("uid3","Repairs",CategoryColor.BLUE)
            )

            db.CategoryDao().insertCategory(
                Category("uid4","Insurance",CategoryColor.PINK)
            )

            db.CategoryDao().insertCategory(
                Category("uid5","Power and Fuel",CategoryColor.GREEN)
            )

            db.CategoryDao().insertCategory(
                Category("uid6", "Hobby & Chilling", CategoryColor.GREEN)
            )
        }
    }
}