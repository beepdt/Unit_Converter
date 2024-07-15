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
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import com.example.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt

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
    var inputUnit by remember {mutableStateOf("Meters")}
    var outputUnit by remember {mutableStateOf("Meters")}
    var iExpanded by remember {mutableStateOf(false)}
    var oExpanded by remember {mutableStateOf(false)}
    val conversionFactor = remember {mutableStateOf(1.0)}
    val oConversionFactor = remember {mutableStateOf(1.0)}
    var inputButtonText by remember { mutableStateOf("Select") }
    var outputButtonText by remember { mutableStateOf("Select") }

    fun conversionUnits(){
        //elvis operator ?:
        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
        val result = (inputValueDouble * conversionFactor.value * 100.0/oConversionFactor.value).roundToInt()/100.0
        outputValue = result.toString()
    }

    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Unit Converter",
            style = TextStyle(fontSize = 8.em, fontWeight = FontWeight.Medium)
        )

        Spacer(modifier = Modifier.height(16.dp))

        //entering the value for conversion
        OutlinedTextField(
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.width(320.dp) ,
            value = inputValue,
            onValueChange = {
                inputValue= it
                conversionUnits()
            },
            label = {
                Text(text = "Enter Value")
        })

        Spacer(modifier = Modifier.height(16.dp))

        Row {
            //Input Box
            Box {
                Button(
                    modifier = Modifier.width(144.dp),
                    onClick = { iExpanded = true}) {
                    Text(text = inputUnit)
                    Icon(Icons.Default.ArrowDropDown,
                        contentDescription = "Arrow Down")
                }
                DropdownMenu(expanded = iExpanded, onDismissRequest = { iExpanded = false }) {
                    DropdownMenuItem(
                        text = { Text(text = "Centimeters")},
                        onClick = {
                            iExpanded = false
                            inputUnit = "Centimeters"
                            inputButtonText = "Centimeters"
                            conversionFactor.value = 0.01
                            conversionUnits()
                        })
                    DropdownMenuItem(
                        text = { Text(text = "Meters")},
                        onClick = {
                            iExpanded = false
                            inputUnit = "Meters"
                            inputButtonText = "Meters"
                            conversionFactor.value = 1.0
                            conversionUnits()
                        })
                    DropdownMenuItem(
                        text = { Text(text = "Feet")},
                        onClick = {
                            iExpanded = false
                            inputUnit = "Feet"
                            inputButtonText = "Feet"
                            conversionFactor.value = 0.3048
                            conversionUnits()
                        })
                    DropdownMenuItem(
                        text = { Text(text = "Millimeters")},
                        onClick = {
                            iExpanded = false
                            inputUnit = "Millimeters"
                            inputButtonText = "Millimeters"
                            conversionFactor.value = 0.001
                            conversionUnits()
                        })
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            //Output Box
            Box {
                    Button(
                        modifier = Modifier.width(144.dp),
                        onClick = { oExpanded = true }) {
                        Text(text = outputUnit)
                        Icon(Icons.Default.ArrowDropDown,
                            contentDescription = "Arrow Down"
                        )
                    }
                    DropdownMenu(expanded = oExpanded, onDismissRequest = { oExpanded = false }) {
                        DropdownMenuItem(
                            text = { Text(text = "Centimeters")},
                            onClick = {
                                oExpanded = false
                                outputUnit = "Centimeters"
                                outputButtonText = "Centimeters"
                                oConversionFactor.value = 0.01
                                conversionUnits()
                            })
                        DropdownMenuItem(
                            text = { Text(text = "Meters")},
                            onClick = {
                                oExpanded = false
                                outputUnit = "Meters"
                                outputButtonText = "Meters"
                                oConversionFactor.value = 1.0
                                conversionUnits()
                            })
                        DropdownMenuItem(
                            text = { Text(text = "Feet")},
                            onClick = {
                                oExpanded = false
                                outputUnit = "Feet"
                                outputButtonText = "Feet"
                                oConversionFactor.value = 0.3048
                                conversionUnits()
                            })
                        DropdownMenuItem(
                            text = { Text(text = "Millimeters")},
                            onClick = {
                                oExpanded = false
                                outputUnit = "Millimeters"
                                outputButtonText = "Millimeters"
                                oConversionFactor.value = 0.001
                                conversionUnits()
                            })
                    }
                }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Result :")
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "$outputValue $outputUnit",
            style = TextStyle(fontSize = 6.em)
        )
    }
}





@Preview(showBackground = true)
@Composable
fun UnitConverterPreview(){
    UnitConverter()
}