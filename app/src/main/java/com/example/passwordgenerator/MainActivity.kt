package com.example.passwordgenerator

import android.graphics.drawable.shapes.ArcShape
import android.os.Bundle
import android.view.RoundedCorner
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.*
import androidx.compose.ui.unit.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.icons.Icons
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.example.passwordgenerator.ui.theme.black
import com.example.passwordgenerator.ui.theme.blackorange
import com.example.passwordgenerator.ui.theme.blackorange1
import com.example.passwordgenerator.ui.theme.orange

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
                Surface(modifier = Modifier.fillMaxSize(),color = orange){
                    Generator()
                }
            }
        }
}

@Composable
fun Generator(){
    var a by rememberSaveable {
    mutableStateOf(" ")
    }
    var field by remember {
        mutableStateOf(0)
    }
    val pg = PasswordGenerator(field)
    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.height(40.dp))
        Text(buildAnnotatedString {
            withStyle(style = SpanStyle(color = black, fontWeight = FontWeight.Medium, fontSize = 70.sp, fontFamily = FontFamily.Cursive)){
                append("Create")
            }
            withStyle(style = SpanStyle(color = blackorange, fontWeight = FontWeight.Medium, fontSize = 70.sp,fontFamily = FontFamily.Cursive)){
                append(" a Solid Password")
            }
        })
        Spacer(modifier = Modifier.padding(vertical = 20.dp))
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()){

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .clip(CircleShape)
                    .size(300.dp)
                    .background(color = Color.White)
            ) {
                Canvas(modifier = Modifier.size(250.dp)) {

                    drawArc(
                        color = blackorange1,
                        -90f,
                        360f,
                        useCenter = false,
                        style = Stroke(20.dp.toPx(), cap = StrokeCap.Round)
                    )
                    drawArc(
                        color = blackorange,
                        -90f,
                        (field*12).toFloat(),
                        useCenter = false,
                        style = Stroke(20.dp.toPx(), cap = StrokeCap.Round)
                    )
                }
                Column() {
                    Text(text = "CHARACTERS", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center, fontSize = 16.sp, fontWeight = FontWeight.Bold)

                    Text(text = field.toString(),color = Color.Black, fontSize = 75.sp, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth(), fontWeight = FontWeight.Bold)
                    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()){
                        Row{
                            IconButton(onClick = {if(field<30)field+=1 else field =30}) {
                                Icon(painter = painterResource(id =R.drawable.add), contentDescription = "", modifier = Modifier.size(25.dp))
                            }
                            IconButton(onClick = {if(field<=0) 0 else field-=1}) {
                                Icon(painter = painterResource(id =R.drawable.minus__1_), contentDescription = "", modifier = Modifier.size(25.dp))
                            }
                        }

                    }
                }


            }
        }


        Box(contentAlignment = Alignment.BottomCenter, modifier = Modifier.fillMaxSize()) {

            Card(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.7f), shape = RoundedCornerShape(20.dp)) {
                    Box(contentAlignment = Alignment.Center){
                        Column(modifier = Modifier.fillMaxWidth()) {
                            Text(text = "RANDOM PASSWORD", color = orange, fontSize = 10.sp, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth(), fontWeight = FontWeight.Bold)
                            Spacer(modifier = Modifier.height(10.dp))
                            SelectionContainer() {
                                Text(text = a,color = Color.Black, fontSize = 20.sp, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
                            }
                            Spacer(modifier = Modifier.height(10.dp))
                            IconButton(onClick = {a= pg.password()}, modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 50.dp)) {
                                    Image(painter = painterResource(id =R.drawable.sync), contentDescription = "sync", colorFilter = ColorFilter.tint(
                                    orange), modifier = Modifier
                                        .size(40.dp)
                                        .background(
                                            color = blackorange1,
                                            shape = RoundedCornerShape(30.dp)
                                        )
                                        .padding(10.dp))

                            }
                        }

                    }
            }

        }
    }
}

class PasswordGenerator(var characters: Int) {
    val characterSet = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
    val sb = StringBuilder(characters)
    fun password():String{
        var str:String = " "
        for (i in 0 until characters){
            val random: Int = (characterSet.indices).random()
            sb.append(characterSet[random])
        }
        str = sb.toString()
        sb.clear()
        return str
    }
}



