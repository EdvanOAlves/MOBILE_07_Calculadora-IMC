package com.example.mobile_07_calculadora_imc

import android.R.attr.contentDescription
import android.R.attr.label
import android.R.attr.onClick
import android.R.attr.singleLine
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontVariation.weight
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobile_07_calculadora_imc.ui.theme.MOBILE_07_CalculadoraIMCTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MOBILE_07_CalculadoraIMCTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    IMCScreen(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun IMCScreen(name: String, modifier: Modifier = Modifier) {
    var heightField by remember {
        mutableStateOf("")
    }
    var weightField by remember {
        mutableStateOf("")
    }
    var imcValue by remember {
        mutableStateOf("")
    }
    var imcMessage by remember {
        mutableStateOf("")
    }
    var cardCol by remember {
        mutableStateOf(Color(255, 255 ,255))
    }
        Column(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = colorResource(R.color.app_col))
                .height(160.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                modifier = Modifier
                    .width(96.dp)
                    .padding(16.dp),
                painter = painterResource(R.drawable.bmi),
                contentDescription = "IMC Icon",
            )
            Text(
                "Calculadora de IMC",
                fontSize = 24.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .offset(y = (-30.dp))
                .height(300.dp)
                .padding(horizontal = 36.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFE3FBFF) //0XFFA29898
            ),
            elevation = CardDefaults.cardElevation(4.dp),
//                shape = CircleShape,
//                border = BorderStroke(2.dp, color = Color.Black)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Seus dados",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(R.color.app_col)
                )
                //Campo de input altura
                OutlinedTextField(
                    value = heightField,
                    onValueChange = { newInput ->
                        heightField = newInput
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    label = {
                        Text(text = "Altura(cm)")
                    },
                    singleLine = true,
                    shape = RoundedCornerShape(16.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = colorResource(R.color.app_col),
                        focusedBorderColor = colorResource(R.color.app_col)
                    )
                )

                //Campo de input peso
                OutlinedTextField(
                    value = weightField,
                    onValueChange = { newInput ->
                        weightField = newInput
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    label = {
                        Text(text = "Peso(Kg)")
                    },
                    singleLine = true,
                    shape = RoundedCornerShape(16.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = colorResource(R.color.app_col),
                        focusedBorderColor = colorResource(R.color.app_col)
                    )
                )

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    onClick = {
                        val imc = getImc(weightField = weightField, heightField = heightField)
                        imcValue = String.format("%.1f", imc)
                        imcMessage = classifyImc(imc)
                        cardCol = colorImc(imc)


                    },
                    colors = ButtonDefaults.buttonColors(
                        colorResource(R.color.app_col)
                    )
                ) {
                    Text(
                        modifier = Modifier.padding(vertical = 6.dp),
                        color = Color.White,
                        text = "CALCULAR"
                    )
                }
            }
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(75.dp)
                .padding(horizontal = 36.dp),
            colors = CardDefaults.cardColors(
                containerColor = cardCol
            ),
            elevation = CardDefaults.cardElevation(4.dp),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                // IMC
                IMCText(text = imcValue.toString())
                IMCText(text = imcMessage)
            }

        }
    }
}


@Composable
fun IMCText(text:String) {
    Text(
        text = text,
        color = Color.White,
        fontSize = 24.sp,
        fontWeight = FontWeight.SemiBold
    )

}

fun getImc(weightField:String, heightField:String):Double{
    val weight = weightField.toDouble()
    val height = heightField.toDouble()/100
    return weight/(height*height)
}

fun classifyImc(imc:Double):String{
    val imcMessage: String = when(imc){
        in 0.0..<18.5 -> "Abaixo do peso."
        in 18.5..< 25.0 -> "Peso ideal."
        in 25.0..< 30.0 -> "Levemente acima do peso."
        in 18.5..< 35.0 -> "Obesidade grau I."
        in 18.5..< 40.0 -> "Obesidade grau II."
        else if (imc > 40.0) -> "Obesidade grau III."
        else -> "Alguma coisa deu errado"
    }

    return imcMessage
}


fun colorImc(imc:Double):Color{
    val errorCol = Color(255,255,255)
    val red = Color(232, 47, 47, 255)
    val green = Color(95, 182, 82, 255)
    val orange = Color(232, 101, 47, 255)

    val messageColor: Color = when(imc){
        in 0.0..<18.5 -> red
        in 18.5..< 25.0 -> green
        in 25.0..< 30.0 -> orange
        else if (imc > 18.5) -> red
        else -> errorCol
    }

    return messageColor
}