package com.schmitt.mlbweatherapp.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import com.schmitt.mlbweatherapp.MainActivity

val mainScreenVM = MainScreenVM()

@Composable
fun MainScreen(modifier: Modifier = Modifier) {

    val activity = LocalContext.current as MainActivity

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
        .fillMaxSize()) {
        Text(
            text = "Enter your zip Code",
            modifier = modifier
        )
        ZipCodeInput(
            textValue = mainScreenVM.zipCode
        )
        ContinueButton({
            activity.processGeoLocation(mainScreenVM.zipCode);
        })
    }

}

@Composable
fun ContinueButton(onClick: () -> Unit) {
    FilledTonalButton(onClick = { onClick() }) {
        Text("Continue")
    }
}

@Composable
fun ZipCodeInput(
    textValue: String
) {
    var textFieldValue by remember {
        mutableStateOf(TextFieldValue(text = textValue))
    }
    TextField(
        value = textFieldValue,
        onValueChange = { newValue ->
            textFieldValue = newValue
            mainScreenVM.zipCode = newValue.text
        }
    )
}