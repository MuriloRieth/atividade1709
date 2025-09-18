# package-tracker-no-if

Proposta: pequena POC de sistema de rastreio/monitoramento de pacotes sem uso de condicionais (if/when), usando Kotlin e State Pattern.

Como executar:
1. `kotlinc -d out src/main/kotlin/**/*.kt` (ou use Gradle/Maven)
2. `java -cp out MainKt`

Estrutura:
- `model/`: estados e entidade Package
- `events/`: eventos que afetam o pacote
- `Main.kt`: exemplo de fluxo

Princípio de design: polimorfismo para transitions, eventos aplicando-se ao estado atual.
