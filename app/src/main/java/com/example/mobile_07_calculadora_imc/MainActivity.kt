package com.example.mobile_07_calculadora_imc

import android.R.attr.contentDescription
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
        mutableStateOf(0)
    }
    var weightField by remember {
        mutableStateOf(0)
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
        Column( modifier = Modifier.fillMaxWidth()
            .padding(horizontal = 36.dp)) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = (-30.dp))
                    .height(300.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFE3FBFF) //0XFFA29898
                ),
                elevation = CardDefaults.cardElevation(4.dp),
//                shape = CircleShape,
//                border = BorderStroke(2.dp, color = Color.Black)
            ) {
                Text(text = "Seus dados", color = colorResource(R.color.app_col))
                TextField(
                    value = weightField,
                    onValueChange = {newInput ->
                        weightField = newInput
                    },
                    label = {
                        Text(text = "Altura")
                    }
                )
//                TextField()
//                Button() { }
            }
        }

        // Card-resultado
        //Row()
    }
}
