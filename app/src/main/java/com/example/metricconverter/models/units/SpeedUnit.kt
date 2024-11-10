package com.example.metricconverter.models.units

sealed class SpeedUnit(
    override var value: Double,
    override val letter: String,
    override val label: String
) : BaseUnit {
    abstract fun toMetersPerSecond(): Double
    abstract fun toMilesPerHour(): Double
    abstract fun toKilometersPerHour(): Double
    abstract fun toFeetPerSecond(): Double

    class MetersPerSecond(value: Double) : SpeedUnit(value, "m/s", "Meters/Second") {
        override fun toMetersPerSecond(): Double = value
        override fun toMilesPerHour(): Double = value * 2.23694
        override fun toKilometersPerHour(): Double = value * 3.6
        override fun toFeetPerSecond(): Double = value * 3.28084
    }

    class MilesPerHour(value: Double) : SpeedUnit(value, "mph", "Miles/Hour") {
        override fun toMetersPerSecond(): Double = value * 0.44704
        override fun toMilesPerHour(): Double = value
        override fun toKilometersPerHour(): Double = value * 1.60934
        override fun toFeetPerSecond(): Double = value * 1.46667
    }

    class KilometersPerHour(value: Double) : SpeedUnit(value, "km/h", "Kilometers/Hour") {
        override fun toMetersPerSecond(): Double = value / 3.6
        override fun toMilesPerHour(): Double = value / 1.60934
        override fun toKilometersPerHour(): Double = value
        override fun toFeetPerSecond(): Double = value * 0.911344
    }

    class FeetPerSecond(value: Double) : SpeedUnit(value, "ft/s", "Feet/Second") {
        override fun toMetersPerSecond(): Double = value / 3.28084
        override fun toMilesPerHour(): Double = value / 1.46667
        override fun toKilometersPerHour(): Double = value * 1.09728
        override fun toFeetPerSecond(): Double = value
    }
}
