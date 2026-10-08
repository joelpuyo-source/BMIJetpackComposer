package org.insbaixcamp.bmijetpackcomposer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
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
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFF121212)
                ) {
                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        containerColor = Color(0xFF121212)
                    ) { innerPadding ->
                        BMIScreen(
                            modifier = Modifier.padding(innerPadding)
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun BMIScreen(modifier: Modifier = Modifier) {
    var nombre: String by remember { mutableStateOf("") }
    var peso: Int by remember { mutableIntStateOf(80) }
    var altura: Int by remember { mutableIntStateOf(180) }
    var imc: Float by remember { mutableFloatStateOf(0f) }

    val p5Red = Color(0xFFE60012)
    val p5Black = Color(0xFF1A1A1A)
    val p5Yellow = Color(0xFFFFEE00)
    val p5White = Color.White

    Column(
        modifier = modifier
            .background(Color(0xFF121212))
            .padding(all = 16.dp)
            .verticalScroll(rememberScrollState())
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = CutCornerShape(topStart = 16.dp, bottomEnd = 16.dp),
            colors = CardDefaults.cardColors(containerColor = p5Red)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "★ CONTROL DE SALUD - LADRONES FANTASMA ★",
                    color = p5Yellow,
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Panel de IMC",
                    color = p5White,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 18.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "Calculadora IMC",
            modifier = Modifier.padding(bottom = 8.dp),
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = p5Yellow
        )
        
        TextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") },
            placeholder = { Text("Escribe tu nombre") },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = p5Black,
                unfocusedContainerColor = p5Black,
                disabledContainerColor = p5Black,
                focusedIndicatorColor = p5Red,
                unfocusedIndicatorColor = Color.DarkGray,
                focusedLabelColor = p5Yellow,
                unfocusedLabelColor = Color.Gray,
                focusedTextColor = p5White,
                unfocusedTextColor = p5White,
                cursorColor = p5Yellow
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Text(text = "Peso", color = p5White, fontWeight = FontWeight.Bold)
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(text = "$peso kg", color = p5Yellow, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Button(
                onClick = { peso++ },
                colors = ButtonDefaults.buttonColors(
                    containerColor = p5Red,
                    contentColor = p5Yellow
                )
            ) {
                Text(text = "+", fontWeight = FontWeight.Bold)
            }
            Button(
                onClick = { peso-- },
                colors = ButtonDefaults.buttonColors(
                    containerColor = p5Red,
                    contentColor = p5Yellow
                )
            ) {
                Text(text = "-", fontWeight = FontWeight.Bold)
            }
        }

        Text(text = "Altura", color = p5White, fontWeight = FontWeight.Bold)
        Text(text = "$altura cm", color = p5Yellow, fontSize = 18.sp, fontWeight = FontWeight.Bold)
        Slider(
            value = altura.toFloat(),
            onValueChange = { altura = it.toInt() },
            valueRange = 100f..250f,
            colors = SliderDefaults.colors(
                thumbColor = p5Yellow,
                activeTrackColor = p5Red,
                inactiveTrackColor = Color.DarkGray
            )
        )
        Button(
            onClick = {
                imc = peso.toFloat() / ((altura.toFloat() / 100) * (altura.toFloat() / 100))
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = p5Red,
                contentColor = p5Yellow
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Calcular IMC", fontWeight = FontWeight.Bold, fontSize = 16.sp)
        }
        if (imc != 0f) {
            Text(
                text = "Tu IMC es $imc",
                color = p5Yellow,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(containerColor = p5Black),
            border = BorderStroke(2.dp, p5Red)
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = "🐱",
                    fontSize = 32.sp
                )
                Column {
                    Text(
                        text = "Morgana (Mona)",
                        color = p5Yellow,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = if (nombre.isNotBlank())
                            "¡Todo un acierto, $nombre! ¡Mantén tu físico listo para el Metaverso!"
                        else
                            "¡Eh! ¡Introduce tu nombre y estadísticas, Ladrón Fantasma! ¡Comprobemos tu estado!",
                        color = p5White,
                        fontSize = 13.sp
                    )
                }
            }
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
