package com.example.metricconverter.models.units

sealed class TimeUnit(
    override var value: Double,
    override val letter: String,
    override val label: String
) : BaseUnit {
    abstract fun toSeconds(): Double
    abstract fun toMinutes(): Double
    abstract fun toHours(): Double


    class Second(value: Double) : TimeUnit(value, "s", "Seconds") {
        override fun toSeconds(): Double = value
        override fun toMinutes(): Double = value / 60
        override fun toHours(): Double = value / 3600
    }

    class Minute(value: Double) : TimeUnit(value, "min", "Minutes") {
        override fun toSeconds(): Double = value * 60
        override fun toMinutes(): Double = value
        override fun toHours(): Double = value / 60
    }

    class Hour(value: Double) : TimeUnit(value, "h", "Hours") {
        override fun toSeconds(): Double = value * 3600
        override fun toMinutes(): Double = value * 60
        override fun toHours(): Double = value
    }
}