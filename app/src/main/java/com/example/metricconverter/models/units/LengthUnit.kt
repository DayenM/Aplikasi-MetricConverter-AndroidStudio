package com.example.metricconverter.models.units

sealed class LengthUnit(
    override var value: Double,
    override val letter: String,
    override val label: String
) : BaseUnit {
    abstract fun toMeters(): Double
    abstract fun toKilometers(): Double
    abstract fun toInches(): Double
    abstract fun toCentimeters(): Double
    abstract fun toMillimeters(): Double
    abstract fun toFeet(): Double

    class Meter(value: Double) : LengthUnit(value, "m", "Meters") {
        override fun toMeters(): Double = value
        override fun toKilometers(): Double = value / 1000
        override fun toInches(): Double = value / 0.0254
        override fun toCentimeters(): Double = value * 100
        override fun toMillimeters(): Double = value * 1000
        override fun toFeet(): Double = value / 0.3048
    }

    class Kilometer(value: Double) : LengthUnit(value, "km", "Kilometers") {
        override fun toMeters(): Double = value * 1000
        override fun toKilometers(): Double = value
        override fun toInches(): Double = value * 1000 / 0.0254
        override fun toCentimeters(): Double = value * 100000
        override fun toMillimeters(): Double = value * 1_000_000
        override fun toFeet(): Double = value * 1000 / 0.3048
    }

    class Inch(value: Double) : LengthUnit(value, "in", "Inches") {
        override fun toMeters(): Double = value * 0.0254
        override fun toKilometers(): Double = value * 0.0254 / 1000
        override fun toInches(): Double = value
        override fun toCentimeters(): Double = value * 2.54
        override fun toMillimeters(): Double = value * 25.4
        override fun toFeet(): Double = value / 12
    }

    class Centimeter(value: Double) : LengthUnit(value, "cm", "Centimeters") {
        override fun toMeters(): Double = value / 100
        override fun toKilometers(): Double = value / 100000
        override fun toInches(): Double = value / 2.54
        override fun toCentimeters(): Double = value
        override fun toMillimeters(): Double = value * 10
        override fun toFeet(): Double = value / 30.48
    }

    class Millimeter(value: Double) : LengthUnit(value, "mm", "Millimeters") {
        override fun toMeters(): Double = value / 1000
        override fun toKilometers(): Double = value / 1_000_000
        override fun toInches(): Double = value / 25.4
        override fun toCentimeters(): Double = value / 10
        override fun toMillimeters(): Double = value
        override fun toFeet(): Double = value / 304.8
    }

    class Foot(value: Double) : LengthUnit(value, "ft", "Feet") {
        override fun toMeters(): Double = value * 0.3048
        override fun toKilometers(): Double = value * 0.3048 / 1000
        override fun toInches(): Double = value * 12
        override fun toCentimeters(): Double = value * 30.48
        override fun toMillimeters(): Double = value * 304.8
        override fun toFeet(): Double = value
    }
}
