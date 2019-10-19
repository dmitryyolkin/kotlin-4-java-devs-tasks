package com.yolkin.fromjava.coursera.week4.rationals

import java.math.BigInteger

data class Rational(var numerator: BigInteger, var denominator: BigInteger) : Comparable<Rational> {

    init {
        if (denominator == BigInteger.ZERO) {
            throw IllegalArgumentException("denominator is zero")
        }
        val gcd = numerator.gcd(denominator)
        numerator /= gcd
        denominator /= gcd

        if (denominator < BigInteger.ZERO) {
            numerator = -numerator
            denominator = -denominator
        }
    }

    override fun toString(): String {
        return if (denominator == BigInteger.ONE) "$numerator" else "$numerator/$denominator"
    }

    override fun compareTo(other: Rational): Int {
        val a = numerator
        val b = denominator
        val c = other.numerator
        val d = other.denominator

        // Compute ad-bc
        val y = a * d - b * c
        return if (y > BigInteger.ZERO) 1 else if (y < BigInteger.ZERO) -1 else 0
    }
}

operator fun Rational.plus(other: Rational): Rational {
    val a = numerator
    val b = denominator
    val c = other.numerator
    val d = other.denominator
    val e = b * d

    return Rational(a * (e / b) + c * (e / d), e)
}

operator fun Rational.minus(other: Rational): Rational {
    val a = numerator
    val b = denominator
    val c = other.numerator
    val d = other.denominator
    val e = b * d

    return Rational(a * (e / b) - c * (e / d), e)
}

operator fun Rational.times(other: Rational): Rational {
    val a = numerator
    val b = denominator
    val c = other.numerator
    val d = other.denominator

    return Rational(a * c, b * d)
}

operator fun Rational.div(other: Rational): Rational {
    val a = numerator
    val b = denominator
    val c = other.numerator
    val d = other.denominator

    return Rational(a * d, b * c)
}

operator fun Rational.unaryMinus(): Rational {
    return Rational(-numerator, denominator)
}


infix fun Number.divBy(other: Number): Rational {
    val numerator = Rational(BigInteger.valueOf(this.toLong()), BigInteger.ONE)
    val denominator = Rational(BigInteger.valueOf(other.toLong()), BigInteger.ONE)
    return numerator / denominator
}

fun String.toRational(): Rational {
    val split: List<String> = split("/")
    return Rational(
            BigInteger(split[0]),
            if (split.size == 2) {
                BigInteger(split[1])
            } else {
                BigInteger.ONE
            }
    )
}

fun main() {
    val half = 1 divBy 2
    val third = 1 divBy 3

    val sum: Rational = half + third
    println(5 divBy 6 == sum)

    val difference: Rational = half - third
    println(1 divBy 6 == difference)

    val product: Rational = half * third
    println(1 divBy 6 == product)

    val quotient: Rational = half / third
    println(3 divBy 2 == quotient)

    val negation: Rational = -half
    println(-1 divBy 2 == negation)

    println((2 divBy 1).toString() == "2")
    println((-2 divBy 4).toString() == "-1/2")
    println("117/1098".toRational().toString() == "13/122")

    val twoThirds = 2 divBy 3
    println(half < twoThirds)

    println(half in third..twoThirds)

    println(2000000000L divBy 4000000000L == 1 divBy 2)

    println("912016490186296920119201192141970416029".toBigInteger() divBy
            "1824032980372593840238402384283940832058".toBigInteger() == 1 divBy 2)
}