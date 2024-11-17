package com.aliric.traininggeorgeyman

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Converter(navController: NavController) {

    var rates = mapOf(
        "EUR" to mapOf("USD" to 1.1, "GBP" to 0.85, "AUG" to 1.5),
        "USD" to mapOf("EUR" to 0.9, "GBP" to 0.77, "AUG" to 1.36),
        "GBP" to mapOf("EUR" to 1.18, "USD" to 1.3, "AUG" to 1.75),
        "AUG" to mapOf("EUR" to 0.67, "USD" to 0.73, "GBP" to 0.57)
    )
    val fromCurrency= remember {
        mutableStateOf("EUR")
    }
    val toCurrency= remember {
        mutableStateOf("USD")
    }

    val textFieldValue = remember {
        mutableStateOf("")
    }

    val dropDownStatus = remember {
        mutableStateOf(false)
    }

    val currencyList = stringArrayResource(id = R.array.currencyList)

    val itemPosition = remember {
        mutableStateOf(0)
    }

    val dropDownStatus2= remember {
        mutableStateOf(false)
    }
    val itemPosition2= remember {
        mutableStateOf(0)
    }


    val buttonStatus= remember {
        mutableStateOf(true)
    }

    val myContext= LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Currency Converter",
                        fontSize = 25.sp
                    )
                },
                actions = {
                    Button(onClick = {
                        navController.popBackStack()
                    }

                    ) {
                        Text(text = "Calculator")
                    }

                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorResource(id = R.color.teal_700),
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
            )
        }, content = {


            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
                    .padding(top = 100.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(50.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 40.dp)
                        .padding(end = 40.dp)
                        .weight(3f),

                )

                {
                    Text(
                        text = "Enter Amount",
                        fontSize = 20.sp,
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    TextField(
                        value = textFieldValue.value,
                        onValueChange = {
                            textFieldValue.value = it
                        },
                        label = { Text(text = "Amount")},
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White,
                            cursorColor = Color.White,
                            focusedIndicatorColor = Color.White,


                            ),
                        modifier = Modifier
                            .width(400.dp)
                            .border(
                                border = BorderStroke(
                                    1.dp,
                                    color = colorResource(id = R.color.black)
                                )
                            ),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        shape = RoundedCornerShape(5.dp),
                        textStyle = TextStyle(fontSize = 23.sp)


                    )


                    Spacer(modifier = Modifier.height(30.dp))

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 40.dp),
                        horizontalAlignment = Alignment.Start

                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(end = 100.dp),

                            verticalAlignment = Alignment.CenterVertically,


                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "From",
                                fontSize = 20.sp,
                            )
                            Text(
                                text = "To",
                                fontSize = 20.sp,
                            )
                        }

                        Spacer(modifier = Modifier.height(20.dp))


                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(end = 55.dp),
                            verticalAlignment = Alignment.CenterVertically,

                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Box {
                                Row(
                                    modifier = Modifier
                                        .border(
                                            BorderStroke(
                                                1.dp,
                                                color = colorResource(id = R.color.black)
                                            )
                                        )
                                        .size(60.dp, 25.dp)


                                        .clickable {
                                            dropDownStatus.value = true
                                        },
                                ) {

                                    Text(text = currencyList[itemPosition.value],

                                        fontSize = 20.sp,
                                        modifier = Modifier
                                            .clickable {
                                            dropDownStatus.value = true
                                        })
                                    Image(
                                        Icons.Filled.ArrowDropDown,
                                        contentDescription = "DropDownItems"
                                    )

                                    DropdownMenu(
                                        expanded = dropDownStatus.value,
                                        onDismissRequest = {
                                            dropDownStatus.value = false
                                        }
                                    )
                                    {

                                        currencyList.forEachIndexed { position, country ->
                                            DropdownMenuItem(
                                                text = {
                                                    Text(
                                                        text = country,
                                                        fontSize = 20.sp
                                                    )

                                                },
                                                onClick = {
                                                    dropDownStatus.value = false
                                                    itemPosition.value = position

                                                })
                                        }

                                    }
                                }
                            }
                            Icon(Icons.Filled.ArrowForward, contentDescription ="" )
                            Box {
                                Row(
                                    modifier = Modifier
                                        .border(
                                            BorderStroke(
                                                1.dp,
                                                color = colorResource(id = R.color.black)
                                            )
                                        )
                                        .size(60.dp, 25.dp)

                                        .clickable {
                                            dropDownStatus2.value = true
                                        },
                                ) {

                                    Text(text = currencyList.reversed()[itemPosition2.value],
                                        fontSize = 20.sp,
                                        modifier = Modifier.clickable {
                                            dropDownStatus2.value = true
                                        })
                                    Image(
                                        Icons.Filled.ArrowDropDown,
                                        contentDescription = "DropDownItems"
                                    )

                                    DropdownMenu(
                                        expanded = dropDownStatus2.value,
                                        onDismissRequest = {
                                            dropDownStatus2.value = false
                                        }
                                    )
                                    {

                                        currencyList.reversed().forEachIndexed { position, country ->
                                            DropdownMenuItem(
                                                text = {
                                                    Text(
                                                        text = country,
                                                        fontSize = 20.sp
                                                    )

                                                },
                                                onClick = {
                                                    dropDownStatus2.value = false
                                                    itemPosition2.value = position
                                                    rates.map { it.key }
                                                })
                                        }

                                    }
                                }
                            }

                        }


                    }
                    Spacer(modifier = Modifier.height(40.dp))
                    
                    Text(
                        text = "1000 USD=73,704 INR",
                        fontSize = 20.sp)
                    
                    Spacer(modifier = Modifier.height(50.dp))
                    
                    Button(onClick = {

                       if (textFieldValue.value.isNotEmpty()){
                           if (buttonStatus.value){
                               textFieldValue.value= (textFieldValue.value )
                           }
                       }
                        
                    }, modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                        colors = ButtonDefaults.buttonColors(
                            contentColor = Color.White,
                            containerColor = colorResource(id = R.color.teal_700)
                        ),
                        shape = RoundedCornerShape(10.dp)
                    )
                    {
                        Text(
                            text = "Get Exchange Rate",
                            fontSize = 20.sp
                        )
                    }
                }
            }
        })
}