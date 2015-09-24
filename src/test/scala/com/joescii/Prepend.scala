package com.joescii

import org.scalatest.FunSuite

class Prepend extends FunSuite {
  test("Prepend produces new instance") {
    val oneTo3  = List(1, 2, 3)
    val zeroTo3 = 0 :: oneTo3

    assert(oneTo3.size  == 3)
    assert(zeroTo3.size == 4)
    assert(oneTo3 != zeroTo3)
  }
}
