package com.example.laptop

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.view.View
import android.widget.Toast
import com.example.laptop.adapter.LaptopAdapter
import com.example.laptop.databinding.ActivityMainBinding
import com.example.laptop.db.DbLaptops
import com.example.laptop.model.Laptop

//import com.example.laptop.db.DBhelper

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
      private lateinit var lisLaptop: ArrayList<Laptop>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val dbLaptops = DbLaptops(this)
        lisLaptop = dbLaptops.getLapto()

        val laptopAdapter = LaptopAdapter(this, lisLaptop )
        binding.lvLaptos.adapter = laptopAdapter
        binding.lvLaptos.setOnItemClickListener { adapterView, view, i, l ->

            //l es id     i es posición
           val intent = Intent(this,DetailsActivity::class.java)
            intent.putExtra("ID",l.toInt())
            startActivity(intent)
            finish()

        }



    }

    fun click(view: View) {
        //boton flotante
        startActivity(Intent(this, insertActivity::class.java))
        finish()

    }


    }



