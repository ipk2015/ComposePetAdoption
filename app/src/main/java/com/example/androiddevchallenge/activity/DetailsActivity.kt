package com.example.androiddevchallenge.activity

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.androiddevchallenge.data.DemoDataProvider
import com.example.androiddevchallenge.data.Puppy
import com.example.androiddevchallenge.ui.theme.MyTheme

class DetailsActivity : AppCompatActivity() {
    lateinit var pet:Puppy
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val petId = intent.getIntExtra("PetInfoId",0);
        pet = DemoDataProvider.puppyList.find { pet -> pet.id == petId }!!
        setContent {
            MyTheme {
                DetailsActivityScreen()
            }
        }
    }

    // Start building your app here!
    @Composable
    fun DetailsActivityScreen() {
        Surface(color = MaterialTheme.colors.background) {
            Text(text = pet.name)
        }
    }

    @Preview("Light Theme", widthDp = 360, heightDp = 640)
    @Composable
    fun LightPreview() {
        MyTheme {
            DetailsActivityScreen()
        }
    }
}

