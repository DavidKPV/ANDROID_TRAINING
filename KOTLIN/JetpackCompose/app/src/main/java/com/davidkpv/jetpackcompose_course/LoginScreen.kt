package com.davidkpv.jetpackcompose_course

import androidx.compose.material.Text
import androidx.compose.runtime.Composable


@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}