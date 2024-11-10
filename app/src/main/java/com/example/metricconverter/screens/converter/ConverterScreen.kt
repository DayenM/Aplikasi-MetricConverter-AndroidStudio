package com.example.metricconverter.screens.converter

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.metricconverter.R
import com.example.metricconverter.models.UnitCategory
import com.example.metricconverter.ui.theme.*
import com.example.metricconverter.utils.viewModelFactory

@Composable
fun ConverterScreen(
    modifier: Modifier = Modifier,
    category: UnitCategory,
    onBackPressed: () -> Unit,
) {
    val viewModel = viewModel<ConverterViewModel>(
        key = category.title,
        factory = viewModelFactory { ConverterViewModel(category) }
    )

    var isSourceValueEmpty by remember { mutableStateOf(true) }
    var inputText by remember { mutableStateOf("") }
    val targetUnitLetter = viewModel.targetUnit.collectAsStateWithLifecycle().value.letter
    val result by viewModel.calculationResult.collectAsStateWithLifecycle()
    val resultText = if (isSourceValueEmpty) "" else "$result $targetUnitLetter"
    val unitList by viewModel.unitList.collectAsStateWithLifecycle()
    val categoryTitle by viewModel.categoryTitle.collectAsStateWithLifecycle()

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(LightGrey50)
    ) {
        // Top Bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = onBackPressed,
                modifier = Modifier
                    .clip(CircleShape)
                    .background(LightGrey100)
                    .size(40.dp)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = LightGrey
                )
            }

            Text(
                text = categoryTitle,
                color = LightGrey100,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 24.sp,
                modifier = Modifier.weight(1f)
            )
        }

        // Main Content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 100.dp)
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            // Conversion Input Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = LightGrey25),
                shape = RoundedCornerShape(24.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(24.dp)
                ) {
                    // Input Section
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = "NILAI YANG AKAN DIKONVERI",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = LightGrey75
                        )
                        TextField(
                            value = inputText,
                            onValueChange = {
                                inputText = it
                                isSourceValueEmpty = it.isBlank()
                                viewModel.onSourceValueChanged(it)
                            },
                            placeholder = { Text(text = "Masukkan Nilai", color = LightGrey75) },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            singleLine = true,
                            colors = TextFieldDefaults.colors(
                                focusedTextColor = LightGrey100,
                                unfocusedTextColor = LightGrey100,
                                focusedContainerColor = Color.Transparent,
                                unfocusedContainerColor = Color.Transparent,
                                focusedIndicatorColor = LightGrey75,
                                unfocusedIndicatorColor = LightGrey75
                            ),
                            modifier = Modifier.fillMaxWidth()
                        )
                    }

                    // Unit Selection
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // From Unit
                        Column(
                            horizontalAlignment = Alignment.Start,
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                text = "Dari Satuan",
                                fontSize = 14.sp,
                                color = LightGrey75,
                                modifier = Modifier.padding(bottom = 8.dp)
                            )
                            UnitSelectionButton(
                                unitsList = unitList,
                                onUnitChanged = { viewModel.onSourceUnitChanged(it) },
                                initialSelectedUnit = unitList.first()
                            )
                        }

                        // Arrow Icon
                        Icon(
                            painter = painterResource(id = R.drawable.ic_swap),
                            contentDescription = "Swap",
                            tint = LightGrey75,
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                                .size(32.dp)
                        )


                        // To Unit
                        Column(
                            horizontalAlignment = Alignment.End,
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                text = "Ke Satuan",
                                fontSize = 14.sp,
                                color = LightGrey75,
                                modifier = Modifier.padding(bottom = 8.dp)
                            )
                            UnitSelectionButton(
                                unitsList = unitList,
                                onUnitChanged = { viewModel.onTargetUnitChanged(it) },
                                initialSelectedUnit = unitList.last()
                            )
                        }
                    }
                }
            }

            // Result Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = LightGrey25),
                shape = RoundedCornerShape(24.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "HASIL KONVERSI",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = LightGrey100
                    )
                    Text(
                        text = resultText,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = LightGrey100,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}

@Composable
private fun UnitSelectionButton(
    unitsList: List<String>,
    initialSelectedUnit: String,
    onUnitChanged: (String) -> Unit
) {
    var isMenuExpanded by remember { mutableStateOf(false) }
    var selectedUnit by remember { mutableStateOf(initialSelectedUnit) }

    Button(
        onClick = { isMenuExpanded = true },
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = LightGrey75,
            contentColor = LightGrey
        ),
        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Text(
            text = selectedUnit,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )
        Spacer(modifier = Modifier.width(4.dp))
        Icon(
            imageVector = Icons.Default.KeyboardArrowDown,
            contentDescription = null,
            tint = LightGrey,
            modifier = Modifier.size(16.dp)
        )
    }

    if (isMenuExpanded) {
        AlertDialog(
            onDismissRequest = { isMenuExpanded = false },
            containerColor = LightGrey50,
            title = {
                Text(
                    text = "Pilih Satuan",
                    color = LightGrey100,
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                LazyColumn {
                    items(unitsList) { unit ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    selectedUnit = unit
                                    onUnitChanged(unit)
                                    isMenuExpanded = false
                                }
                                .padding(vertical = 12.dp, horizontal = 16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = selectedUnit == unit,
                                onClick = null,
                                colors = RadioButtonDefaults.colors(
                                    selectedColor = LightGrey100,
                                    unselectedColor = LightGrey75
                                )
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = unit,
                                color = LightGrey100,
                                fontSize = 16.sp
                            )
                        }
                    }
                }
            },
            confirmButton = {}
        )
    }
}