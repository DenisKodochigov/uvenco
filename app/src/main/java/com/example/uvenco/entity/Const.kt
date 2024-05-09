package com.example.uvenco.entity

import com.example.uvenco.navigation.CatalogDestination
import kotlinx.collections.immutable.persistentListOf


object Const {
    const val DATA_STORE_FILE_NAME = "list-coffee.json"
    val DEFAULT_SCREEN = CatalogDestination
    const val DURATION_SCREEN = 800
    const val DELAY_SCREEN = 200
    val DEFAULT_LIST_COFFEE = persistentListOf(
        Coffee ( id = 0, name = "Капучино эконом", size = 0.33, price = 199,),
        Coffee ( id = 1, name = "Капучино эконом", size = 0.33, price = 199,),
        Coffee ( id = 2, name = "Капучино эконом", size = 0.33, price = 0, ),
        Coffee ( id = 3, name = "Капучино эконом", size = 0.33, price = 199, ),
        Coffee ( id = 4, name = "Капучино эконом", size = 0.33, price = 199, ),
        Coffee ( id = 5, name = "Капучино эконом", size = 0.33, price = 199, ),
        Coffee ( id = 6, name = "Капучино эконом", size = 0.33, price = 199, ),
        Coffee ( id = 7, name = "Капучино эконом", size = 0.33, price = 199, ),
        Coffee ( id = 8, name = "Капучино эконом", size = 0.33, price = 199, ),
        Coffee ( id = 9, name = "Капучино эконом", size = 0.33, price = 199, ),
        Coffee ( id = 10, name = "Капучино эконом", size = 0.33, price = 199, ),
        Coffee ( id = 11, name = "Капучино эконом", size = 0.33, price = 199, ),
        Coffee ( id = 12, name = "Капучино эконом", size = 0.33, price = 199, ),
        Coffee ( id = 13, name = "Капучино эконом", size = 0.33, price = 199, ),
        Coffee ( id = 14, name = "Капучино эконом", size = 0.33, price = 199, ),
        Coffee ( id = 15, name = "Капучино эконом", size = 0.33, price = 199, ),
    )
}