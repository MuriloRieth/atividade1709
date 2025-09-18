package model

import events.Event
import java.time.Instant
import java.util.UUID

data class PacketLog(val timestamp: Instant, val message: String)

class Package(val id: String = UUID.randomUUID().toString()) {
    private var state: PackageState = PackageState.Created
    private val history = mutableListOf<PacketLog>()

    fun apply(event: Event) {
        log("Applying event: ${event.description()}")
        state = event.applyTo(state)
        log("New state: ${state::class.simpleName}")
    }

    private fun log(message: String) {
        history += PacketLog(Instant.now(), message)
    }

    fun getState() = state
    fun getHistory() = history.toList()
}

