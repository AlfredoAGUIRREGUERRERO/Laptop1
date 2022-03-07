package com.example.laptop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.Toast
import com.example.laptop.databinding.ActivityEditBinding
import com.example.laptop.db.DbLaptops
import com.example.laptop.model.Laptop

class EditActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditBinding

    private lateinit var dbLaptops: DbLaptops
    var laptop: Laptop? = null
    var id: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            val bundle = intent.extras
            if (bundle != null) {
                id = bundle.getInt("ID")

            }

        } else {
            id = savedInstanceState.getSerializable("ID") as Int

        }

        dbLaptops = DbLaptops(this)
        laptop = dbLaptops.getLaptop(id)
        if (laptop != null) {
            with(binding) {
                tietMarca.setText(laptop?.marca)
                tietTipo.setText(laptop?.Tipo)
                tietDeveloper.setText(laptop?.developer)


            }
        }
    }

    fun click(view: View) {
        with(binding) {
            if (!tietMarca.text.toString().isEmpty() && !tietTipo.text.toString()
                    .isEmpty() && !tietDeveloper.text.toString().isEmpty()) {
                if (dbLaptops.updateLaptop(
                        id,
                        tietMarca.text.toString(),
                        tietTipo.text.toString(),
                        tietDeveloper.text.toString()
                    )
                )
                    Toast.makeText(
                        this@EditActivity,
                        "Registro actualizado correctamente",
                        Toast.LENGTH_LONG).show()
                val intent = Intent(this@EditActivity, DetailsActivity::class.java)
                intent.putExtra("ID", id)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(
                    this@EditActivity,
                    "Error al actualizar el registro",
                    Toast.LENGTH_LONG).show()
            }
        }

    }

        }












