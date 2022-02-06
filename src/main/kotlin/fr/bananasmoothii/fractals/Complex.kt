package fr.bananasmoothii.fractals

import kotlin.math.sqrt

class Complex(var real: Double, var imaginary: Double) {
    constructor(real: Int, imaginary: Int): this(real.toDouble(), imaginary.toDouble())

    operator fun plus(other: Complex) = Complex(
        real + other.real,
        imaginary + other.imaginary
    )

    operator fun minus(other: Complex) = Complex(
        real - other.real,
        imaginary - other.imaginary
    )

    operator fun times(other: Complex) = Complex(
        real * other.real - imaginary * other.imaginary,
        real * other.imaginary + imaginary * other.real
    )

    operator fun div(other: Complex): Complex {
        val div = other.real.squared() + other.imaginary.squared()
        return Complex(
            (real * other.real + imaginary * other.imaginary) / div,
            (real * other.imaginary - imaginary * other.real) / div
        )
    }

    fun squared() = Complex(
        real.squared() - imaginary.squared(),
        2 * real * imaginary
    )

    fun abs() = sqrt(real.squared() + imaginary.squared())

    override fun toString() = real.toString() + " + " + imaginary + 'i'

    companion object {

        val ZERO = Complex(0, 0)

        fun Double.squared() = this * this

    }
}