package com.example.uvenco.service

import com.example.uvenco.entity.TickTime
import com.example.uvenco.ui.lg
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

object TimeProvider {
    private lateinit var scope: CoroutineScope
    private val currentTickTime: MutableStateFlow<TickTime> =  MutableStateFlow(TickTime())
    private fun engineWatcher() {
        scope = CoroutineScope(Dispatchers.Default)
        scope.launch {
            while (true) {
                delay(1000L)
                currentTickTime.value = TickTime(
                    time = DateTimeFormatter
                        .ofPattern("HH:mm")
                        .withZone(ZoneOffset.systemDefault())
                        .format(Instant.now()),
                    temperature = (850..950).random()/10.0
                )
            }
        }
    }
    fun startEngine(){
        engineWatcher()
    }
    fun stopEngine(){
        scope.cancel()
    }
    fun getTickTime(): MutableStateFlow<TickTime> = currentTickTime
}