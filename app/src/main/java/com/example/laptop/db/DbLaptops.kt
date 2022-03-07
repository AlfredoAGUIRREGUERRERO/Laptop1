package com.example.laptop.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import com.example.laptop.model.Laptop
import java.lang.Exception

class DbLaptops(context: Context?) : DBHelper(context) {
    // codigo del crud

    val context = context

    fun insertLapto(marca: String, tipo: String, developer: String):Long {
        val dbHelper =DBHelper(context)
        val db = dbHelper.writableDatabase
        var id: Long = 0

       try {
          val values = ContentValues()

           values.put("marca",marca)
           values.put("tipo",tipo)
           values.put("developer",developer)

           id =db.insert(TABLE_LAPTOPS,null,values)


       }catch (e:Exception){

    }finally {
        db.close()
    }
        return id
}

    fun getLapto(): ArrayList<Laptop> {
        val dbHelper =DBHelper(context)
        val db = dbHelper.writableDatabase

        var listLaptop = ArrayList<Laptop>()
        var laptopTmp: Laptop? = null
        var cursorLaptop: Cursor? = null

        cursorLaptop = db.rawQuery("SELECT * FROM $TABLE_LAPTOPS", null)

        if (cursorLaptop.moveToFirst()){
            do {
                laptopTmp = Laptop(cursorLaptop.getInt(0),cursorLaptop.getString(1),cursorLaptop.getString(2),cursorLaptop.getString(3))
                listLaptop.add(laptopTmp)
            }while (cursorLaptop.moveToNext())
        }
        cursorLaptop.close()
        return listLaptop
    }

    fun getLaptop(id: Int): Laptop?{
        val dbHelper =DBHelper(context)
        val db = dbHelper.writableDatabase

        var laptop: Laptop? = null
        var cursorLaptop: Cursor? = null

        cursorLaptop = db.rawQuery("SELECT * FROM $TABLE_LAPTOPS WHERE id = $id LIMIT 1", null)

        if (cursorLaptop.moveToFirst()){
            laptop = Laptop(cursorLaptop.getInt(0),cursorLaptop.getString(1),cursorLaptop.getString(2),cursorLaptop.getString(3))

        }
        cursorLaptop.close()
        return laptop
    }
   fun updateLaptop(id: Int,marca: String, tipo: String, developer: String):Boolean{

       var banderaCorrecto = false
       val dbHelper = DBHelper(context)
       val db = dbHelper.writableDatabase

       try {
           db.execSQL("UPDATE $TABLE_LAPTOPS SET marca= '$marca',tipo= '$tipo', developer ='$developer' WHERE id = $id")
           banderaCorrecto = true

       }catch (e: Exception){

       }finally {
           db.close()
       }
       return banderaCorrecto
   }
    fun deleteLaptop(id: Int): Boolean{
        var banderaCorrecto = true
        val dbHelper = DBHelper(context)
        val db = dbHelper.writableDatabase

        try {
            db.execSQL("DELETE FROM $TABLE_LAPTOPS WHERE id = $id ")
            banderaCorrecto = true

        }catch (e:Exception){

        }finally {
            db.close()
        }
        return banderaCorrecto

        return banderaCorrecto
    }

}