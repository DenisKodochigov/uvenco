package com.example.uvenco.data.datastore

import androidx.datastore.core.Serializer
import com.example.uvenco.entity.Coffee
import com.example.uvenco.entity.Const.DEFAULT_LIST_COFFEE
import com.example.uvenco.entity.ListCoffee
import kotlinx.collections.immutable.persistentListOf
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

object ListCoffeeSerializer : Serializer<ListCoffee>{
    override val defaultValue: ListCoffee
        get() = ListCoffee( list = DEFAULT_LIST_COFFEE)

    override suspend fun readFrom(input: InputStream): ListCoffee {
        return try {
            Json.decodeFromString(
                deserializer = ListCoffee.serializer(),
                string = input.readBytes().decodeToString()
            )
        } catch (e: SerializationException){
            e.printStackTrace()
            defaultValue
        }
    }

    override suspend fun writeTo(t: ListCoffee, output: OutputStream) {
        output.write(
            Json.encodeToString(
                serializer = ListCoffee.serializer(),
                value = t
            ).encodeToByteArray()
        )
    }
}