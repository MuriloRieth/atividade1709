package model

sealed class PackageState {
    open fun onReceive(): PackageState = this
    open fun onStartProcessing(): PackageState = this
    open fun onShip(): PackageState = this
    open fun onDeliver(): PackageState = this
    open fun onTrackUpdate(info: String): PackageState = this
    open fun onReportIssue(issue: String): PackageState = this

    object Created : PackageState() {
        override fun onReceive() = Received
    }

    object Received : PackageState() {
        override fun onStartProcessing() = Processing
    }

    object Processing : PackageState() {
        override fun onShip() = InTransit
    }

    object InTransit : PackageState() {
        override fun onTrackUpdate(info: String) = InTransit // stays same but could record
        override fun onDeliver() = Delivered
        override fun onReportIssue(issue: String) = IssueReported(issue)
    }

    data class IssueReported(val issue: String) : PackageState() {
        override fun onStartProcessing() = Processing // reprocess after issue
    }

    object Delivered : PackageState()
}
