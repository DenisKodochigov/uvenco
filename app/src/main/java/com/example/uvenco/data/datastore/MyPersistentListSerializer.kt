package com.example.uvenco.data.datastore

import com.example.uvenco.entity.ListCoffee
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.toPersistentList
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializer
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.serialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@OptIn(ExperimentalSerializationApi::class)
@Serializer(forClass = PersistentList::class)
class MyPersistentListSerializer(
    private val serializer: KSerializer<ListCoffee>,
) : KSerializer<PersistentList<ListCoffee>> {
    private class PersistentListDescriptor :
        SerialDescriptor by serialDescriptor<List<ListCoffee>>() {
        @ExperimentalSerializationApi
        override val serialName: String = "kotlinx.serialization.immutable.persistentList"
    }

    override val descriptor: SerialDescriptor = PersistentListDescriptor()

    override fun serialize(encoder: Encoder, value: PersistentList<ListCoffee>) {
        return ListSerializer(serializer).serialize(encoder, value)
    }

    override fun deserialize(decoder: Decoder): PersistentList<ListCoffee> {
        return ListSerializer(serializer).deserialize(decoder).toPersistentList()
    }

}