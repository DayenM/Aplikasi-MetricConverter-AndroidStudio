package com.example.metricconverter.models.units

sealed class WeightUnit(
    override var value: Double,
    override val letter: String,
    override val label: String
) : BaseUnit {
    abstract fun toKilograms(): Double
    abstract fun toPounds(): Double
    abstract fun toGrams(): Double
    abstract fun toOunces(): Double

    class Kilogram(value: Double) : WeightUnit(value, "kg", "Kilograms") {
        override fun toKilograms(): Double = value
        override fun toPounds(): Double = value / 0.453592
        override fun toGrams(): Double = value * 1000
        override fun toOunces(): Double = value * 35.274
    }

    class Pound(value: Double) : WeightUnit(value, "lb", "Pounds") {
        override fun toKilograms(): Double = value * 0.453592
        override fun toPounds(): Double = value
        override fun toGrams(): Double = value * 453.592
        override fun toOunces(): Double = value * 16
    }

    class Gram(value: Double) : WeightUnit(value, "g", "Grams") {
        override fun toKilograms(): Double = value / 1000
        override fun toPounds(): Double = value / 453.592
        override fun toGrams(): Double = value
        override fun toOunces(): Double = value / 28.3495
    }

    class Ounce(value: Double) : WeightUnit(value, "oz", "Ounces") {
        override fun toKilograms(): Double = value / 35.274
        override fun toPounds(): Double = value / 16
        override fun toGrams(): Double = value * 28.3495
        override fun toOunces(): Double = value
    }
}
