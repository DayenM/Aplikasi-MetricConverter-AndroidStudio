package com.example.metricconverter.models.units

sealed class VolumeUnit(
    override var value: Double,
    override val letter: String,
    override val label: String
) : BaseUnit {
    abstract fun toLiters(): Double
    abstract fun toGallons(): Double
    abstract fun toMilliliters(): Double
    abstract fun toCubicMeters(): Double

    class Liter(value: Double) : VolumeUnit(value, "L", "Liters") {
        override fun toLiters(): Double = value
        override fun toGallons(): Double = value * 0.264172
        override fun toMilliliters(): Double = value * 1000
        override fun toCubicMeters(): Double = value / 1000
    }

    class Gallon(value: Double) : VolumeUnit(value, "gal", "Gallons") {
        override fun toLiters(): Double = value * 3.78541
        override fun toGallons(): Double = value
        override fun toMilliliters(): Double = value * 3785.41
        override fun toCubicMeters(): Double = value * 0.00378541
    }

    class Milliliter(value: Double) : VolumeUnit(value, "mL", "Milliliters") {
        override fun toLiters(): Double = value / 1000
        override fun toGallons(): Double = value / 3785.41
        override fun toMilliliters(): Double = value
        override fun toCubicMeters(): Double = value / 1_000_000
    }

    class CubicMeter(value: Double) : VolumeUnit(value, "m³", "Cubic Meters") {
        override fun toLiters(): Double = value * 1000
        override fun toGallons(): Double = value * 264.172
        override fun toMilliliters(): Double = value * 1_000_000
        override fun toCubicMeters(): Double = value
    }
}
