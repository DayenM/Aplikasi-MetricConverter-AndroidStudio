package com.example.metricconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.metricconverter.models.UnitCategory
import com.example.metricconverter.screens.CategoriesScreen
import com.example.metricconverter.screens.converter.ConverterScreen
import com.example.metricconverter.ui.theme.DayenTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DayenTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var displayConverter by rememberSaveable {
                        mutableStateOf(false)
                    }
                    var category: UnitCategory by remember {
                        mutableStateOf(UnitCategory.Temperature())
                    }

                    if (displayConverter) {
                        ConverterScreen(
                            category = category,
                            onBackPressed = {
                                displayConverter = false
                            })
                    } else
                        CategoriesScreen(onCategoryClick = {
                            category = it
                            displayConverter = true
                        })
                }
            }
        }
    }

}