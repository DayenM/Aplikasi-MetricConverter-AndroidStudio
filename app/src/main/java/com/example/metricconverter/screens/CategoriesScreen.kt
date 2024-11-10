package com.example.metricconverter.screens

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.metricconverter.models.UnitCategory
import com.example.metricconverter.ui.theme.*

val categoryList = listOf(
    UnitCategory.Weight(),
    UnitCategory.Volume(),
    UnitCategory.Temperature(),
    UnitCategory.Length(),
    UnitCategory.Speed(),
    UnitCategory.Time()
)

@Composable
fun CategoriesScreen(
    modifier: Modifier = Modifier,
    onCategoryClick: (UnitCategory) -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(LightGrey50)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Header Section
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
                colors = CardDefaults.cardColors(
                    containerColor = LightGrey100
                ),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "Metric Converter",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = LightGrey
                    )
                    Text(
                        text = "Pilih kategori metrik yang ingin Anda konversi",
                        fontSize = 14.sp,
                        color = LightGrey25,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }

            // Grid Categories
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.weight(1f)
            ) {
                items(items = categoryList) { category ->
                    CategoryItemCard(
                        category = category,
                        onClick = { onCategoryClick(category) }
                    )
                }
            }
        }

        // Footer
        Text(
            text = "by Dayen Manoppo",
            fontSize = 12.sp,
            color = LightGrey100.copy(alpha = 1f),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 8.dp)
        )
    }
}

@Composable
fun CategoryItemCard(
    modifier: Modifier = Modifier,
    category: UnitCategory,
    onClick: () -> Unit,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = LightGrey25
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Untuk Perbesar Icon
            Image(
                painter = painterResource(id = category.icon),
                contentDescription = category.title,
                modifier = Modifier
                    .size(72.dp)
                    .padding(4.dp),
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Category Title
            Text(
                text = category.title.lowercase()
                    .replaceFirstChar { it.uppercase() },
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = LightGrey100,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_NO)
@Composable
private fun CategoriesPreview() {
    CategoriesScreen(onCategoryClick = {})
}