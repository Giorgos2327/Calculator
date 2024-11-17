package com.aliric.traininggeorgeyman

import android.telephony.CellInfoCdma
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import org.mozilla.javascript.Context
import org.mozilla.javascript.Scriptable
import java.lang.reflect.Modifier



val buttonList= listOf(
    "C","(",")","/",
    "7","8","9","*",
    "4","5","6","-",
    "1","2","3","+",
    "AC","0",".","="
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Calculator(navController: NavController,viewModel: CalculatorViewModel) {

    val equationText=viewModel.equationText.observeAsState()

    val resultText=viewModel.resultText.observeAsState()



        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = "Calculator",
                            fontSize = 25.sp
                        )

                    }, actions = {
                        Button(onClick = {
                            navController.navigate("SecondPage")
                        }

                        ) {
                            Text(text = "Converter")
                        }



                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = colorResource(id = R.color.teal_700),
                        titleContentColor = Color.White,
                        navigationIconContentColor = Color.White
                    )

                )


            },
            content = {
                Box(modifier = androidx.compose.ui.Modifier) {
                    Column(
                        modifier = androidx.compose.ui.Modifier
                            .fillMaxSize()
                            .padding(it),
                        horizontalAlignment = Alignment.End

                    ) {
                        Text(
                            text = equationText.value?:"",
                            style = TextStyle(
                                fontSize = 30.sp,
                                textAlign = TextAlign.End

                            ),
                            maxLines = 5,
                            overflow = TextOverflow.Ellipsis
                        )

                        Spacer(modifier = androidx.compose.ui.Modifier.height(70.dp))

                        Text(
                            text = resultText.value?:"",
                            style = TextStyle(
                                fontSize = 50.sp,
                                textAlign = TextAlign.End
                            ),
                            maxLines = 2
                        )

                        Spacer(modifier = androidx.compose.ui.Modifier.height(50.dp))

                        LazyVerticalGrid(
                            columns = GridCells.Fixed(4)
                        ) {
                            
                            items(buttonList) {
                                CalculatorButtons(btn = it, onClick = {
                                    viewModel.onButtonClick(it)
                                })
                               

                            }


                        }

                    }
                }
            }
        )
}
@Composable
fun CalculatorButtons(btn:String,onClick:()->Unit){

    Box(modifier = androidx.compose.ui.Modifier.padding(8.dp)){
         FloatingActionButton(
             onClick = onClick,
             modifier = androidx.compose.ui.Modifier.size(80.dp),
             contentColor = Color.White,
             shape = CircleShape,
             containerColor = getColor(btn)

         ) {
             Text(text = btn, fontSize = 20.sp, fontWeight = FontWeight.Bold)
         }
    }
}
fun getColor(btn: String):Color{

    if (btn=="C" || btn=="AC")
        return Color(0xFFF44336)
    if (btn=="("|| btn==")")
        return Color.Gray
    if (btn=="/" || btn=="*" || btn=="+" || btn=="-" || btn=="=")
        return Color(0xFFFF9800)
    return Color(0xFF00C8C9)

}