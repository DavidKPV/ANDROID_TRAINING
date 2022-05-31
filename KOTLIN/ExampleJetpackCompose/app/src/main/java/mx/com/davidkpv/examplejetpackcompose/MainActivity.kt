package mx.com.davidkpv.examplejetpackcompose

import android.graphics.Color
import android.os.Bundle
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.LinearLayout
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import mx.com.bancoazteca.azteca360.sdk.ui.views.AztecaPayCardView

// JETPACK COMPOSE DE BASA EN PROGRAMACIÓN DECLARATIVA

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // ELEMENTOS QUE CONTENDRÁ LA APP
        setContent {
            aztecaCard()
        }
    }
}

// LA SIGUIENTE ETIQUETA INDICA QUE SERÁ UN ELEMENTO GRÁFICO
@Composable
fun myImage(){
    Row {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "image",
        )
        myTexts()
    }
}

@Composable
fun myText(text : String){
    Text(text)
}

@Composable
fun myTexts(){
    Column() {
        myText(text = "Pruebas")
        myText(text = "Columnas")
        myText(text = "Con Texto")
    }
}

// HOW TO ADD COMPOSABLE VIEW AZTECA CARD
@Composable
fun aztecaCard() {
    AndroidView(factory = { ctx ->
        AztecaPayCardView(ctx).apply {
            layoutParams = LinearLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT)
            setButtonColor(Color.BLUE)
            setBannerAzteca(false)
        }
    })
}

// FUNCIONES DE PREVISUALIZACIÓN PARA EVITAR LANZAR EL EMULADOR CONSTANTEMENTE
@Preview
@Composable
fun previewText(){
    aztecaCard()
}