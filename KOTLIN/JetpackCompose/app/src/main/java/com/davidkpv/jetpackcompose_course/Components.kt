package com.davidkpv.jetpackcompose_course

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DragIndicator
import androidx.compose.material.icons.rounded.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun MyProgressAdvanced() {

    var progressState by rememberSaveable{ mutableStateOf(0.0f) }

    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LinearProgressIndicator(progressState)
        
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Button(onClick = { progressState += 0.10f }, Modifier.padding(end = 20.dp, top = 25.dp)) {
                Text(text = "Incrementar")
            }
            Button(onClick = { progressState -= 0.10f }, Modifier.padding(start = 20.dp, top = 25.dp)) {
                Text(text = "Reducir")
            }
        }
    }
}

@Composable
fun MyProgress() {

    var progressState by rememberSaveable { mutableStateOf(false) }

    Column(
        Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (progressState) {
            CircularProgressIndicator(color = Color.Green, strokeWidth = 10.dp)
            LinearProgressIndicator(
                modifier = Modifier.padding(top = 32.dp),
                color = Color.Blue,
                backgroundColor = Color.Green
            )
        }

        Button(onClick = { progressState = !progressState }, Modifier.padding(top = 20.dp)) {
            if (progressState) {
                Text(text = "Desactivar progreso")
            } else {
                Text(text = "Activar progreso")
            }
        }
    }
}

@Composable
fun MyIcon() {
    Icon(
        imageVector = Icons.Rounded.DragIndicator,
        contentDescription = "Icon",
        tint = Color.Green
    )
}

@Composable
fun MyImageAdvanced() {
    Image(
        painter = painterResource(id = R.drawable.ic_launcher_background),
        contentDescription = "Image Advanced",
        alpha = 0.5f,   // Aplica transparencia
        modifier = Modifier
            .clip(CircleShape)
            .border(5.dp, Color.Green, CircleShape)  // Aplica un recorte a la imagen
    )
}

@Composable
fun MyImage() {
    Image(
        painter = painterResource(id = R.drawable.ic_launcher_background),
        contentDescription = "imagen",
        alpha = 0.5f // Transparencia a la imagen
    )
}

@Composable
fun MyButtonExample() {

    var stateButton by rememberSaveable { mutableStateOf(true) }

    Column(
        Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Button(
            onClick = { stateButton = !stateButton },
            enabled = stateButton,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Green,
                contentColor = Color.Blue
            ),
            border = BorderStroke(2.dp, Color.Red)
        ) {
            Text(text = "Mi Primer Botón")
        }

        OutlinedButton(
            onClick = { stateButton = false },
            enabled = stateButton,
            modifier = Modifier.padding(8.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Green,
                contentColor = Color.Black,
                disabledBackgroundColor = Color.Red,
                disabledContentColor = Color.Green
            )
        ) {
            Text(text = "Outlined Button")
        }

        TextButton(onClick = { }) {
            Text(text = "Text Button")
        }
    }
}

@Composable
fun MyTextFieldOutlined() {

    var myState by rememberSaveable { mutableStateOf("") }

    Column() {
        OutlinedTextField(
            value = myState,
            onValueChange = { myState = it },
            modifier = Modifier.padding(all = 20.dp),
            label = {
                Text(
                    text = "Introduce tu nombre :O"
                )
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Green,
                unfocusedBorderColor = Color.Red
            )
        )
    }
}

@Composable
fun MyTextFieldAdvanced() {

    var myState by rememberSaveable { mutableStateOf("") }

    TextField(
        value = myState,
        onValueChange = {
            myState = if (it.contains("a")) {
                it.replace("a", "")
            } else {
                it
            }
        },
        label = {
            Text(text = "Introduce tu nombre :D")
        })
}

// Composable aplicando el state hosting para eliminar la variable de estado del composable
@Composable
fun MyTextField(name: String, onValueChanged: (String) -> Unit) {
    TextField(value = name, onValueChange = { onValueChanged(it) })
}

@Composable
fun MyText() {
    Column(Modifier.fillMaxSize()) {
        Text(text = "Este es un ejemplo")
        Text(text = "Este es un ejemplo", color = Color.Blue)
        Text(text = "Este es un ejemplo", fontWeight = FontWeight.Bold)
        Text(text = "Este es un ejemplo", fontWeight = FontWeight.Light)
        Text(text = "Este es un ejemplo", style = TextStyle(fontFamily = FontFamily.Cursive))
        Text(
            text = "Este es un ejemplo",
            textDecoration = TextDecoration.LineThrough
        )
        Text(
            text = "Este es un ejemplo",
            style = TextStyle(textDecoration = TextDecoration.Underline)
        )
        Text(
            text = "Este es un ejemplo", style = TextStyle(
                textDecoration = TextDecoration.combine(
                    listOf(TextDecoration.Underline, TextDecoration.LineThrough)
                )
            )
        )
        Text(text = "Este es un ejemplo", fontSize = 30.sp)
    }
}