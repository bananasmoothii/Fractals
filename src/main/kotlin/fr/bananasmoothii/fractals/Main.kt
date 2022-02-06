package fr.bananasmoothii.fractals

import java.awt.Color
import java.awt.image.BufferedImage
import java.io.File
import java.io.IOException
import java.lang.Math.pow
import javax.imageio.ImageIO

@Throws(IOException::class)
fun main(args: Array<String>) {
    ImageIO.write(generateImage(16000, 16000, 60), "png", File("Fractal.png"))
}

fun generateImage(width: Int, height: Int, factor: Int): BufferedImage {
    val image = BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)
    for (x in 0 until width) {
        for (y in 0 until height) {
            image.setRGB(x, y, valueToColor(getValuePoint(coordsToComplex(x, y, width, height), factor)))
        }
    }
    return image
}

fun coordsToComplex(x: Int, y: Int, imgWidth: Int, imgHeight: Int): Complex {
    return Complex(x.toDouble() / imgWidth * 4 - 2, y.toDouble() / imgHeight * 4 - 2)
}

fun f(z: Complex, c: Complex): Complex = z.squared() + c

fun getValuePoint(coords: Complex, factor: Int): Double {
    var z: Complex = Complex.ZERO
    repeat(factor) {
        z = f(z, coords)
    }
    return z.abs()
}

val WHITE = Color.WHITE.rgb
val BLACK = Color.BLACK.rgb

fun valueToColor(value: Double): Int {
    if (value.isNaN() || value.isInfinite()) return Color.BLUE.rgb
    return Color((255 / pow(value + 1, 2.0)).toInt(), 0, 0).rgb
}
