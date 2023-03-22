package com.davidkpv.jetpackcompose_course

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.davidkpv.jetpackcompose_course.ui.theme.JetpackCompose_CourseTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackCompose_CourseTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    // State hosting - Es un patrón de diseño para eliminar los estados de los composables
                    /*var myText by rememberSaveable { mutableStateOf("David ama a Ivonne") }

                    MyTextField(name = myText) {myText = it}*/

                    MyProgressAdvanced()
                }
            }
        }
    }
}

@Preview(
    showSystemUi = true,
    showBackground = true
)
@Composable
fun MyStateExample() {

    var counter by rememberSaveable { mutableStateOf(0) }

    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { counter += 1 }) {
            Text(text = "Pulsar")
        }
        Text(text = "He sido pulsado ${counter} veces")
    }
}


@Composable
fun MyComplexLayout() {
    Column(Modifier.fillMaxSize()) {
        Box(
            Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(Color.Cyan), contentAlignment = Alignment.Center
        ) {
            Text(text = "Ejemplo 1")
        }
        MySpacer(size = 30)
        Row(
            Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            Box(
                Modifier
                    .weight(1f)
                    .background(Color.Red)
                    .fillMaxHeight(), contentAlignment = Alignment.Center
            ) {
                Text(text = "Ejemplo 2")
            }
            Box(
                Modifier
                    .weight(1f)
                    .background(Color.Green)
                    .fillMaxHeight(), contentAlignment = Alignment.Center
            ) {
                Text(text = "Ejemplo 3")
            }
        }
        MySpacer(size = 20)
        Box(
            Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(Color.Magenta), contentAlignment = Alignment.BottomCenter
        ) {
            Text(text = "Ejemplo 4")
        }
    }
}

@Composable
fun MySpacer(size: Int) {
    Spacer(modifier = Modifier.height(size.dp))
}


/*@Preview(
    name = "Preview Base", // Etiqueta de nombre para el elemento Composbale
    heightDp = 50, // Le asignamos una altura por defecto
    widthDp = 200, // Le asignamos un ancho por defecto
    showBackground = true, // Le indicamos que quiero un fondo blanco de elemeto
    showSystemUi = true, // Le indicamos que deseo ver cómo se verá en un dispositivo móvil
    apiLevel = 29, // Le indicamos que quiero ver el comportamiento del elemento dentro de la API 29
    device = Devices.NEXUS_5 // Le indicamos que tipo de dispositivo quiero emular la UI
)*/
@Composable
fun MyColumn() {
    Row(
        Modifier
            .fillMaxSize()
            .horizontalScroll(rememberScrollState())
    ) {
        Text(
            text = "Ejemplo 1", modifier = Modifier
                .background(Color.Red)
                .weight(1f)
        )
        Text(
            text = "Ejemplo 2", modifier = Modifier
                .background(Color.Black)
                .weight(1f)
        )
        Text(
            text = "Ejemplo 3", modifier = Modifier
                .background(Color.Cyan)
                .weight(1f)
        )
        Text(
            text = "Ejemplo 4", modifier = Modifier
                .background(Color.Blue)
                .weight(1f)
        )
        Text(
            text = "Ejemplo 4", modifier = Modifier
                .background(Color.Blue)
                .width(100.dp)
        )
        Text(
            text = "Ejemplo 4", modifier = Modifier
                .background(Color.Blue)
                .width(100.dp)
        )
        Text(
            text = "Ejemplo 4", modifier = Modifier
                .background(Color.Blue)
                .width(100.dp)
        )
        Text(
            text = "Ejemplo 4", modifier = Modifier
                .background(Color.Blue)
                .width(100.dp)
        )
        Text(
            text = "Ejemplo 4", modifier = Modifier
                .background(Color.Blue)
                .width(100.dp)
        )
    }
}

@Composable
fun MyBox() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Box(
            modifier = Modifier
                .width(50.dp)
                .height(50.dp)
                .background(Color.Cyan)
                .verticalScroll(rememberScrollState())
        ) {
            Text(text = "Esto es un ejemplo")
        }
    }
}

