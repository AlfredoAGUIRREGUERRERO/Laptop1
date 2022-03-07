package com.example.laptop.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.laptop.databinding.ListItemBinding
import com.example.laptop.model.Laptop

class LaptopAdapter(context: Context,listlaptop: ArrayList<Laptop>):BaseAdapter() {
    private val listLaptop = listlaptop
    private val layoutInflater = LayoutInflater.from(context)
    override fun getCount(): Int {
       return listLaptop.size
    }

    override fun getItem(p0: Int): Any {
       return listLaptop[p0]

    }

    override fun getItemId(p0: Int): Long {
      return listLaptop[p0].id.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
     val binding = ListItemBinding.inflate(layoutInflater)
     with(binding){
         tvTitle.text = listLaptop[p0].marca
         tvGenre.text = listLaptop[p0].Tipo
         tvDeveloper.text = listLaptop[p0].developer
     }
        return binding.root
    }
}