/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.animatedVectorResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.createBitmap
import com.example.androiddevchallenge.activity.DetailsActivity
import com.example.androiddevchallenge.data.DemoDataProvider
import com.example.androiddevchallenge.data.Puppy
import com.example.androiddevchallenge.ui.theme.MyTheme
import java.time.Duration

class MainActivity : AppCompatActivity() {
    val instance by lazy { this }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                MyApp()
            }
        }
    }


    // Start building your app here!
    @Composable
    fun MyApp() {
        Surface(color = MaterialTheme.colors.background) {
            PetList(DemoDataProvider.puppyList)
        }
    }

    @Preview("Light Theme", widthDp = 360, heightDp = 640)
    @Composable
    fun LightPreview() {
        MyTheme {
            MyApp()
        }
    }

    @Preview("Dark Theme", widthDp = 360, heightDp = 640)
    @Composable
    fun DarkPreview() {
        MyTheme(darkTheme = true) {
            MyApp()
        }
    }

    @Composable
    fun PetList(pets:List<Puppy>){
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            contentPadding = PaddingValues(horizontal = 5.dp,vertical = 10.dp)
        ){
            items(items=pets){
                    pet -> PetRow(pet = pet)
            }
        }
    }

    @Composable
    fun PetRow(pet:Puppy){
        val image = ImageBitmap.imageResource(id = pet.images[0])
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = { onItemClick(pet) })
        ){
            val imageModifier = Modifier
                .width(100.dp)
                .wrapContentHeight()
                .padding(end = 10.dp)
            Image(bitmap = image,
                contentDescription = pet.name,
                modifier = imageModifier)
            Column() {
                Text(text = "name: ${pet.name}")
                Text(text = "age: ${pet.age}")
                Text(text = "breed: ${pet.breed}")
            }
        }
    }

    private fun onItemClick(pet:Puppy){
        val intent = Intent(this,DetailsActivity::class.java)
        intent.putExtra("PetInfoId",pet.id)
        startActivity(intent)
    }
}

