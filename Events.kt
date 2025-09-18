package events

import model.PackageState
import java.time.Instant

sealed interface Event {
    fun applyTo(state: PackageState): PackageState
    fun description(): String
}

object Receive : Event {
    override fun applyTo(state: PackageState) = state.onReceive()
    override fun description() = "Receive"
}

object StartProcessing : Event {
    override fun applyTo(state: PackageState) = state.onStartProcessing()
    override fun description() = "StartProcessing"
}

object Ship : Event {
    override fun applyTo(state: PackageState) = state.onShip()
    override fun description() = "Ship"
}

data class TrackUpdate(val info: String) : Event {
    override fun applyTo(state: PackageState) = state.onTrackUpdate(info)
    override fun description() = "TrackUpdate($info)"
}

object Deliver : Event {
    override fun applyTo(state: PackageState) = state.onDeliver()
    override fun description() = "Deliver"
}

data class ReportIssue(val issue: String) : Event {
    override fun applyTo(state: PackageState) = state.onReportIssue(issue)
    override fun description() = "ReportIssue($issue)"
}
