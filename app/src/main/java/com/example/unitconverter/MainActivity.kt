package com.example.unitconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unitconverter.ui.theme.UnitConverterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UnitConverter()
                }
            }
        }
    }
}



@Composable
fun UnitConverter(){

    var inputValue by remember {mutableStateOf("")}
    var outputValue by remember {mutableStateOf("")}
    var inputUnit by remember {mutableStateOf("Centimeters")}
    var outputUnit by remember {mutableStateOf("Meters")}
    var iExpanded by remember {mutableStateOf(false)}
    var oExpanded by remember {mutableStateOf(false)}
    val conversionFactor = remember {mutableStateOf(0.01)}
    var buttonText by remember { mutableStateOf("Select") }

    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Unit Converter")

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            modifier = Modifier.width(320.dp),
            value = inputValue,
            onValueChange = {
                            inputValue= it
            },
            label = {
                Text(text = "Enter Value")
        })

        Spacer(modifier = Modifier.height(16.dp))

        Row {
            //Input Box
            Box {
                Button(
                    modifier = Modifier.width(150.dp),
                    onClick = { iExpanded = true}) {
                    Text(text = buttonText)
                    Icon(Icons.Default.ArrowDropDown,
                        contentDescription = "Arrow Down")
                }
                DropdownMenu(expanded = iExpanded, onDismissRequest = { iExpanded = false }) {
                    DropdownMenuItem(
                        text = { Text(text = "Centimeters")},
                        onClick = {
                            iExpanded = false
                            inputUnit = "Centimeters"
                            buttonText = "Centimeters"
                            conversionFactor.value = 0.01
                        })
                    DropdownMenuItem(
                        text = { Text(text = "Meters")},
                        onClick = {
                            iExpanded = false
                            inputUnit = "Meters"
                            buttonText = "Meters"
                        })
                    DropdownMenuItem(
                        text = { Text(text = "Feet")},
                        onClick = {
                            iExpanded = false
                            inputUnit = "Feet"
                            buttonText = "Feet"
                        })
                    DropdownMenuItem(
                        text = { Text(text = "Millimeters")},
                        onClick = {
                            iExpanded = false
                            inputUnit = "Millimeters"
                            buttonText = "Millimeters"
                        })
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            //Output Box
            Box {
                    Button(
                        modifier = Modifier.width(150.dp),
                        onClick = { oExpanded = true }) {
                        Text(text = "Select")
                        Icon(Icons.Default.ArrowDropDown,
                            contentDescription = "Arrow Down"
                        )
                    }
                    DropdownMenu(expanded = oExpanded, onDismissRequest = { oExpanded = false }) {
                        DropdownMenuItem(
                            text = { Text(text = "Centimeters")},
                            onClick = { /*TODO*/ })
                        DropdownMenuItem(
                            text = { Text(text = "Meters")},
                            onClick = { /*TODO*/ })
                        DropdownMenuItem(
                            text = { Text(text = "Feet")},
                            onClick = { /*TODO*/ })
                        DropdownMenuItem(
                            text = { Text(text = "Millimeters")},
                            onClick = { /*TODO*/ })
                    }
                }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Result")
    }
}





@Preview(showBackground = true)
@Composable
fun UnitConverterPreview(){
    UnitConverter()
}