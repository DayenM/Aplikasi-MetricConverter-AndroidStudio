package com.example.metricconverter.models.units

/**
 * Antarmuka yang konsisten untuk kelas unit dalam kategori yang berbeda
 */
sealed interface BaseUnit {
    var value: Double
    val letter: String
    val label: String
}