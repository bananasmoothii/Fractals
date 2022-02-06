package fr.bananasmoothii.fractals

import java.math.BigDecimal
import java.math.MathContext

class BigComplex(var real: BigDecimal, var imaginary: BigDecimal) {
    constructor(real: Double, imaginary: Double): this(BigDecimal.valueOf(real), BigDecimal.valueOf(imaginary))

    constructor(real: Int, imaginary: Int): this(real.toDouble(), imaginary.toDouble())

    operator fun plus(other: BigComplex) = BigComplex(real.add(other.real), imaginary.add(other.imaginary))

    operator fun minus(other: BigComplex) = BigComplex(real.subtract(other.real), imaginary.subtract(other.imaginary))

    operator fun times(other: BigComplex) = BigComplex(
        real.multiply(other.real).subtract(imaginary.multiply(other.imaginary)),
        real.multiply(other.imaginary).add(imaginary.multiply(other.real))
    )

    operator fun div(other: BigComplex): BigComplex {
        val div = other.real.pow(2).add(other.imaginary.pow(2))
        return BigComplex(
            real.multiply(other.real).add(imaginary.multiply(other.imaginary)).divide(div, mathContext),
            imaginary.multiply(other.real).subtract(real.multiply(other.imaginary)).divide(div, mathContext)
        )
    }

    fun squared() = BigComplex(
        real.pow(2).subtract(imaginary.pow(2)),
        real.multiply(imaginary).multiply(decimalTWO)
    )

    fun abs() = real.pow(2).add(imaginary.pow(2)).sqrt(mathContext)

    companion object {
        var mathContext = MathContext(10)

        val ZERO = BigComplex(0, 0)

        val ONE = BigComplex(1, 0)

        private val decimalTWO = BigDecimal.valueOf(2)
    }
}