package com.example.metricconverter.screens.converter

import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.metricconverter.models.units.LengthUnit
import com.example.metricconverter.models.units.SpeedUnit
import com.example.metricconverter.models.units.TemperatureUnit
import com.example.metricconverter.models.units.TimeUnit
import com.example.metricconverter.models.UnitCategory
import com.example.metricconverter.models.units.VolumeUnit
import com.example.metricconverter.models.units.WeightUnit
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class ConverterViewModel(private val category: UnitCategory) : ViewModel() {

    var sourceUnit = MutableStateFlow(category.units.first())
        private set

    var targetUnit = MutableStateFlow(category.units.last())
        private set

    var unitList = MutableStateFlow(category.units.map { it.label })
        private set
    var categoryTitle = MutableStateFlow(category.title)
        private set
    var categoryIcon = MutableStateFlow(category.icon)
        private set

    private val sourceValue = MutableStateFlow(sourceUnit.value.value)

    private val _calculationResult = MutableStateFlow(sourceValue.value)
    val calculationResult =
        combine(sourceValue, sourceUnit, targetUnit) { sourceValue, sourceUnit, targetUnit ->
            sourceUnit.value = sourceValue
            val result = when (sourceUnit) {
                is SpeedUnit -> {
                    when (targetUnit as SpeedUnit) {
                        is SpeedUnit.MetersPerSecond -> sourceUnit.toMetersPerSecond()
                        is SpeedUnit.MilesPerHour -> sourceUnit.toMilesPerHour()
                        is SpeedUnit.KilometersPerHour -> sourceUnit.toKilometersPerHour()
                        is SpeedUnit.FeetPerSecond -> sourceUnit.toFeetPerSecond()
                    }
                }

                is TemperatureUnit -> {
                    when (targetUnit as TemperatureUnit) {
                        is TemperatureUnit.Celsius -> sourceUnit.toCelsius()
                        is TemperatureUnit.Fahrenheit -> sourceUnit.toFahrenheit()
                        is TemperatureUnit.Kelvin -> sourceUnit.toKelvin()
                    }
                }

                is VolumeUnit -> {
                    when (targetUnit as VolumeUnit) {
                        is VolumeUnit.Liter -> sourceUnit.toLiters()
                        is VolumeUnit.Gallon -> sourceUnit.toGallons()
                        is VolumeUnit.Milliliter -> sourceUnit.toMilliliters()
                        is VolumeUnit.CubicMeter -> sourceUnit.toCubicMeters()
                    }
                }

                is WeightUnit -> {
                    when (targetUnit as WeightUnit) {
                        is WeightUnit.Kilogram -> sourceUnit.toKilograms()
                        is WeightUnit.Pound -> sourceUnit.toPounds()
                        is WeightUnit.Gram -> sourceUnit.toGrams()
                        is WeightUnit.Ounce -> sourceUnit.toOunces()
                    }
                }

                is TimeUnit -> {
                    when (targetUnit as TimeUnit) {
                        is TimeUnit.Second -> sourceUnit.toSeconds()
                        is TimeUnit.Minute -> sourceUnit.toMinutes()
                        is TimeUnit.Hour -> sourceUnit.toHours()
                    }
                }

                is LengthUnit -> {
                    when (targetUnit as LengthUnit) {
                        is LengthUnit.Meter -> sourceUnit.toMeters()
                        is LengthUnit.Kilometer -> sourceUnit.toKilometers()
                        is LengthUnit.Inch -> sourceUnit.toInches()
                        is LengthUnit.Centimeter -> sourceUnit.toCentimeters()
                        is LengthUnit.Millimeter -> sourceUnit.toMillimeters()
                        is LengthUnit.Foot -> sourceUnit.toFeet()
                    }
                }
            }
            formatResult(result)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = _calculationResult.value
        )

    private fun formatResult(value: Double): String {
        return if (value % 1.0 == 0.0) {
            value.toInt().toString()
        } else {
            value.toString()
        }
    }

    fun onSourceValueChanged(text: String) {
        val value = if (text.isDigitsOnly() && text.isNotBlank()) text.toDouble() else 0.0
        sourceValue.value = value
    }

    fun onSourceUnitChanged(unitLabel: String) {
        val unit = category.units.first {
            it.label == unitLabel
        }
        sourceUnit.update { unit }
    }

    fun onTargetUnitChanged(unitLabel: String) {
        val unit = category.units.first {
            it.label == unitLabel
        }
        targetUnit.update { unit }
    }
}
