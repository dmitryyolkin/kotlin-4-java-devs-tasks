package com.yolkin.fromjava.sample

// no need to create class
// there can be functions specified on top level
fun main(args: Array<String>) {
    val v = if (args.isNotEmpty()) args[0]  else "World";
    println("Hello, $v, ${foo()}");
}

fun foo() : String {
    println("print foo")
    return "foo"
}