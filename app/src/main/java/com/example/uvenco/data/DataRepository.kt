package com.example.uvenco.data

import com.example.uvenco.entity.Coffee
import com.example.uvenco.entity.TypeCoffee
import javax.inject.Inject
import javax.inject.Singleton
import javax.sql.DataSource

@Singleton
class DataRepository @Inject constructor(){
    private val listCoffee = mutableListOf(
        Coffee ( name = "Капучино эконом 1", size = 0.33, price = 199.0,),
        Coffee ( name = "Капучино эконом 2", size = 0.33, price = 199.0,),
        Coffee ( name = "Капучино эконом 3", size = 0.33, price = 0.0, ),
        Coffee ( name = "Капучино эконом 4", size = 0.33, price = 199.0, ),
        Coffee ( name = "Капучино эконом 5", size = 0.33, price = 199.0, ),
        Coffee ( name = "Капучино эконом 6" , size = 0.33, price = 199.0, ),
        Coffee ( name = "Капучино эконом 7", size = 0.33, price = 199.0, ),
        Coffee ( name = "Капучино эконом 8", size = 0.33, price = 199.0, ),
        Coffee ( name = "Капучино эконом 9", size = 0.33, price = 199.0, ),
        Coffee ( name = "Капучино эконом 10", size = 0.33, price = 199.0, ),
        Coffee ( name = "Капучино эконом 11", size = 0.33, price = 199.0, ),
    )

    fun getTypesCoffee(): List<Coffee>{
        return listCoffee
    }
    fun onSave(id: Int, coffee: Coffee): List<Coffee>{
        listCoffee[id] = coffee.copy()
        return listCoffee
    }
}