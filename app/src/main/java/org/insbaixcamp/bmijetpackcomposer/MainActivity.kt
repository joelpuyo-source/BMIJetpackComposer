package org.insbaixcamp.bmijetpackcomposer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.insbaixcamp.bmijetpackcomposer.ui.theme.BMIJetpackComposerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BMIJetpackComposerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    BMIScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}


@Composable
fun BMIScreen(modifier: Modifier = Modifier) {
    var nom: String by remember { mutableStateOf("") }
    var pes: Int by remember { mutableIntStateOf(80) }
    var altura: Int by remember { mutableIntStateOf(180) }
    var bmi: Float by remember { mutableFloatStateOf(0f) }

    Column(
        modifier = modifier
            .padding(all = 16.dp)
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
    ) {
        Text(
            text = "Calculadora BMI",
            modifier = Modifier.padding(bottom = 16.dp),
            fontSize = 30.sp,
            color = Color.Blue
        )
        TextField(
            value = nom,
            onValueChange = { nom = it },
            label = { Text("Nom") },
            placeholder = { Text("Escriu el teu nom") }
        )

        Text(text = "Pes")
        Row() {
            Text(text = "$pes kg")
            Button(
                onClick = { pes++ }) {
                Text(text = "+")
            }
            Button(
                onClick = { pes-- }) {
                Text(text = "-")
            }
        }

        Text(text = "Altura")
        Text(text = "$altura cm")
        Slider(
            value = altura.toFloat(),
            onValueChange = { altura = it.toInt() },
            valueRange = 100f..250f,
        )
        Button(
            onClick = {
                bmi = pes.toFloat() / ((altura.toFloat() / 100) * (altura.toFloat() / 100))
            }
        ) {
            Text(text = "Calcular BMI")
        }
        if (bmi != 0f) {
            Text(text = "El teu BMI és $bmi")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BMIJetpackComposerTheme {
        BMIScreen()
    }
}