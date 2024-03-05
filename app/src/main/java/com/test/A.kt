package com.test

import com.google.android.material.bottomsheet.BottomSheetDialog

open class A {
    val a = 0
}

class B : A() {
    val b = 1
}

class Test<T> {
    private var item: T? = null
    fun get(): T? = item
    fun set(t: T) {
        item = t
    }
}

fun main() {
    var test1: Test<out A> = Test<B>()
    var test2: Test<in B> = Test<A>()
    val testA = Test<A>()
    val testB = Test<B>()
    test1 = testA
    test2 = testB
}