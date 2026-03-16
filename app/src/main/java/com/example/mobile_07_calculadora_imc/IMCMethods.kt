package com.example.mobile_07_calculadora_imc

fun getImc(weightField: String, heightField: String): Double {
    val weight = weightField.toDouble()
    val height = heightField.toDouble() / 100
    return weight / (height * height)
}

fun classifyImc(imc: Double): String {
    val imcMessage: String = when (imc) {
        in 0.0..<18.5 -> "Abaixo do peso."
        in 18.5..<25.0 -> "Peso ideal."
        in 25.0..<30.0 -> "Levemente acima do peso."
        in 18.5..<35.0 -> "Obesidade grau I."
        in 18.5..<40.0 -> "Obesidade grau II."
        else if (imc > 40.0) -> "Obesidade grau III."
        else -> "Alguma coisa deu errado"
    }

    return imcMessage
}