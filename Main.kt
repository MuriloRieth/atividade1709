import events.*
import model.Package

fun main() {
    val pkg = Package()
    println("Id do pacote: ${pkg.id}")

    // Simulação de fluxo sem condicionais
    val workflow = listOf(
        Receive,
        StartProcessing,
        Ship,
        TrackUpdate("Centro de distribuição - SP"),
        TrackUpdate("Rota para cidade destino"),
        Deliver
    )

    workflow.forEach { event ->
        pkg.apply(event)
    }

    println("Estado final: ${pkg.getState()}")
    println("Histórico (últimas entradas):")
    pkg.getHistory().takeLast(10).forEach { println("${it.timestamp}: ${it.message}") }
}
