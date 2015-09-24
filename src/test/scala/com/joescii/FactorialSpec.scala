package com.joescii

import org.scalatest.{ShouldMatchers, FlatSpec}

class FactorialSpec extends FlatSpec with ShouldMatchers {
  "Factorial.factorial(5)" should "equal 120" in {
    Factorial.factorial(5) shouldBe 120
  }
}
