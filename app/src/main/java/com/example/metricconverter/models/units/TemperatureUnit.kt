package com.example.metricconverter.models.units

sealed class TemperatureUnit(
    override var value: Double,
    override val letter: String,
    override val label: String
) : BaseUnit {
    abstract fun toCelsius(): Double
    abstract fun toFahrenheit(): Double
    abstract fun toKelvin(): Double

    class Celsius(value: Double) : TemperatureUnit(value, "°C", "Celsius") {
        override fun toCelsius(): Double = value
        override fun toFahrenheit(): Double = value * 9 / 5 + 32
        override fun toKelvin(): Double = value + 273.15
    }

    class Fahrenheit(value: Double) : TemperatureUnit(value, "°F", "Fahrenheit") {
        override fun toCelsius(): Double = (value - 32) * 5 / 9
        override fun toFahrenheit(): Double = value
        override fun toKelvin(): Double = (value - 32) * 5 / 9 + 273.15
    }

    class Kelvin(value: Double) : TemperatureUnit(value, "K", "Kelvin") {
        override fun toCelsius(): Double = value - 273.15
        override fun toFahrenheit(): Double = (value - 273.15) * 9 / 5 + 32
        override fun toKelvin(): Double = value
    }
}
