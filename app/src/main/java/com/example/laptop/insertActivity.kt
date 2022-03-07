package com.example.laptop

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.laptop.databinding.ActivityInsertBinding
import com.example.laptop.db.DbLaptops

class insertActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInsertBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInsertBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    fun click(view: View) {
       val dbLaptops = DbLaptops(this)

        with(binding){

            if (!tietMarca.text.toString().isEmpty() && !tietTipo.text.toString().isEmpty() && !tietDeveloper.text.toString().isEmpty()){
                val id = dbLaptops.insertLapto(tietMarca.text.toString(),tietTipo.text.toString(),tietDeveloper.text.toString())

            if (id > 0) {//se inserta el registro

              Toast.makeText(this@insertActivity,"Se guardo el registro", Toast.LENGTH_LONG).show()
                tietMarca.setText("")
                tietTipo.setText("")
                tietDeveloper.setText("")
                tietMarca.requestFocus()

            } else {
                Toast.makeText(this@insertActivity,"error al guardar el registro", Toast.LENGTH_LONG).show()
            }
            }else{
            Toast.makeText(this@insertActivity,"Favor de llenar los campos", Toast.LENGTH_LONG).show()

            }
            }

        }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this,MainActivity::class.java))
        finish()
    }


}