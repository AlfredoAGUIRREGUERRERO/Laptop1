package com.example.laptop

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.Toast
import com.example.laptop.databinding.ActivityDetailsBinding
import com.example.laptop.db.DbLaptops
import com.example.laptop.model.Laptop

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding

    private lateinit var dbLaptops: DbLaptops
     var laptop: Laptop? = null
    var id = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null){
            val bundle = intent.extras
            if (bundle != null){
                id = bundle.getInt("ID")

            }

        }else{
            id = savedInstanceState.getSerializable("ID") as Int

        }

        dbLaptops = DbLaptops(this)
        laptop = dbLaptops.getLaptop(id)
        if (laptop != null){
          with(binding){
              tietMarca.setText(laptop?.marca)
              tietTipo.setText(laptop?.Tipo)
              tietDeveloper.setText(laptop?.developer)

              // para que no abra el teclado
              tietMarca.inputType = InputType.TYPE_NULL
              tietTipo.inputType = InputType.TYPE_NULL
              tietDeveloper.inputType = InputType.TYPE_NULL



          }
    }
    }

    fun click(view: View) {

        when(view.id){
            R.id.btnEdit ->{
              val intent = Intent(this, EditActivity::class.java)
              intent.putExtra("ID",id)
              startActivity(intent)
              finish()
            }
            R.id.btnDelete ->{
              AlertDialog.Builder(this)
                  .setTitle("Confirmación")
                  .setMessage("Deseas eliminar la marca ${laptop?.marca}?")
                  .setPositiveButton("Sí",DialogInterface.OnClickListener { dialogInterface, i ->
                      if (dbLaptops.deleteLaptop(id)){
                          Toast.makeText(this, "Registro eliminado",Toast.LENGTH_LONG).show()
                          startActivity(Intent(this,MainActivity::class.java))
                          finish()

                      }

                  })
                  .setNegativeButton("No",DialogInterface.OnClickListener { dialogInterface, i ->

                  })
                  .show()
            }

        }

    }
        override fun onBackPressed() {
            super.onBackPressed()
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
