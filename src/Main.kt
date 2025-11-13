import kotlin.random.Random

fun main(){
    val DAUS: String = "⚀ ⚁ ⚂ ⚃ ⚄ ⚅"
    val CARES_DAU: Array<String> = arrayOf("⚀", "⚁", "⚂", "⚃", "⚄", "⚅")

    var partides: Int?
    var tiradesPerPartida: Int?
    var partidesGuanyades = 0
    var nom: String?

    println(DAUS)
    println("👋 Benvingut/da al joc dels daus.")

    do {
        println("Com et dius?")
        nom = readLine()

        var nomEsValid = true
        if (nom.isNullOrEmpty()) {
            nomEsValid = false
        } else {
            var i = 0
            while (i < nom.length && nomEsValid) {
                if (!nom[i].isLetter()) {
                    nomEsValid = false
                }
                i++
            }
        }

        if (!nomEsValid) {
            println("⚠️ Ep! Això no sona a nom real. Fes servir només lletres, res de números ni robots! 🤨")
            nom = null
        }
    } while (nom == null)

    println("Molt bé $nom! Per guanyar, la suma dels punts dels teus daus ha de ser superior a la de la CPU 🤖")
    println(DAUS)

    // Llegim el número de partides que volem jugar
    do {
        println()
        println("📋 Quantes partides vols jugar? (de 1 a 3)")
        partides = readLine()?.toIntOrNull()

        if (partides != null && (partides < 1 || partides > 3)){
            partides = null
            println("⚠️ ERROR: Valor no acceptat!")
        }
    }while(partides == null)

    // Llegim el número de quantes tirades volem fer per cada partida
    do {
        println()
        println("🎲 Quantes tirades vols fer per cada partida? (de 1 a 6)")
        tiradesPerPartida = readLine()?.toIntOrNull()

        if (tiradesPerPartida != null && (tiradesPerPartida < 1 || tiradesPerPartida > 6)){
            tiradesPerPartida = null
            println("⚠️ ERROR: Valor no acceptat!")
        }
    }while(tiradesPerPartida == null)

    // Declarem la matriu
    var tiradesGuardades: Array<IntArray>

    // Inicialitzem la matriu de partides files i (tiradesPerPartida + 1) columnes
    tiradesGuardades = Array(partides){IntArray((tiradesPerPartida + 1)) }

    // Repetim tantes vegades com partides
    for(partida in 0 until partides) {
        var acumuladorCPU: Int = 0
        var tiradaActual: Int = 0
        var tiradaActualCPU: Int = 0

        println()
        println("--- PARTIDA ${partida + 1} ---")

        for (tirada in 0 until tiradesGuardades[partida].size - 1) {
            println()
            println("👉 $nom, tira el dau! (Intent ${tirada + 1})")
            /** Tirades persona **/
            tiradaActual = Random.nextInt(1, 6 + 1)
            println("✨ Has tret un ${CARES_DAU[tiradaActual-1]} !")

            // Guardem la tirada
            tiradesGuardades[partida][tirada] = tiradaActual

            // Acumulem el sumatori a l'última columna de la fila
            tiradesGuardades[partida][tiradesPerPartida] += tiradaActual

            /** Tirades CPU **/
            tiradaActualCPU = Random.nextInt(1, 6 + 1)
            acumuladorCPU += tiradaActualCPU
            println("🤖 CPU ha tret un ${CARES_DAU[tiradaActualCPU-1]} !")
        }

        println()
        println("🏁 Partida acabada!")
        println("👤 $nom, has aconseguit ${tiradesGuardades[partida][tiradesPerPartida]} punts")
        println("🤖 La CPU ha aconseguit $acumuladorCPU punts")

        if (tiradesGuardades[partida][tiradesPerPartida] > acumuladorCPU){
            println("🎉 Has guanyat $nom!")
            partidesGuanyades ++
        }else if (tiradesGuardades[partida][tiradesPerPartida] < acumuladorCPU){
            println("💀 Has perdut contra la màquina...")
        }else{
            println("🤝 Heu empatat!")
        }
    }
    println()
    println("📊 $nom, has guanyat el ${(partidesGuanyades.toDouble() / partides) * 100}% de les partides")
}