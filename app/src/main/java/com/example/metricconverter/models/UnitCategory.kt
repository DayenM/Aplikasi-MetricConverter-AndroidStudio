package com.example.metricconverter.models

import androidx.annotation.DrawableRes
import com.example.metricconverter.R
import com.example.metricconverter.models.units.*
import com.example.metricconverter.utils.*

/**
 * Kategori Unit yang digunakan untuk berpindah antar tampilan.
 * Berisi informasi seperti judul, ikon, dan daftar unit yang tersedia untuk setiap kategori.
 */

sealed class UnitCategory(
    val title: String,
    @DrawableRes val icon: Int,
    val units: List<BaseUnit>,
) {
    class Temperature :
        UnitCategory(
            icon = R.drawable.icon_temperature,
            title = TEMPERATURE,
            units = listOf(
                TemperatureUnit.Celsius(0.0),
                TemperatureUnit.Fahrenheit(0.0),
                TemperatureUnit.Kelvin(0.0),
            ),
        )

    class Weight :
        UnitCategory(
            icon = R.drawable.icon_weight,
            title = WEIGHT,
            units = listOf(
                WeightUnit.Kilogram(0.0),
                WeightUnit.Pound(0.0),
                WeightUnit.Gram(0.0),
                WeightUnit.Ounce(0.0),
            ),
        )

    class Volume :
        UnitCategory(
            icon = R.drawable.icon_volume,
            title = VOLUME,
            units = listOf(
                VolumeUnit.Gallon(0.0),
                VolumeUnit.Liter(0.0),
                VolumeUnit.Milliliter(0.0),
                VolumeUnit.CubicMeter(0.0),
            ),
        )

    class Length :
        UnitCategory(
            icon = R.drawable.icon_length,
            title = LENGTH,
            units = listOf(
                LengthUnit.Inch(0.0),
                LengthUnit.Meter(0.0),
                LengthUnit.Kilometer(0.0),
                LengthUnit.Centimeter(0.0),
                LengthUnit.Millimeter(0.0),
                LengthUnit.Foot(0.0),
            ),
        )

    class Speed :
        UnitCategory(
            icon = R.drawable.icon_speed,
            title = SPEED,
            units = listOf(
                SpeedUnit.MetersPerSecond(0.0),
                SpeedUnit.MilesPerHour(0.0),
                SpeedUnit.KilometersPerHour(0.0),
                SpeedUnit.FeetPerSecond(0.0),
            ),
        )

    class Time :
        UnitCategory(
            icon = R.drawable.icon_time,
            title = TIME,
            units = listOf(
                TimeUnit.Hour(0.0),
                TimeUnit.Minute(0.0),
                TimeUnit.Second(0.0),
            ),
        )
}
