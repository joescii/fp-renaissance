package com.joescii

import scala.collection.JavaConverters._
import org.scalatest.{ShouldMatchers, WordSpec}

class PrimeSpec extends WordSpec with ShouldMatchers {
  val primes = List(2, 3, 5, 7, 11, 13, 17, 19, 23, 29)
  val composites = List(4, 6, 8, 9, 10, 12, 14, 15, 16, 18, 20, 21, 22, 24, 25, 26, 27, 28)

  "PrimeJava.isPrime" should {
    "find some primes" in {
      primes.foreach(p => PrimeJava.isPrime(p) shouldBe true)
    }

    "deny some composites" in {
      composites.foreach(c => PrimeJava.isPrime(c) shouldBe false)
    }
  }

  "PrimeJava.first" should {
    "find the first 10 primes" in {
      PrimeJava.first(10).asScala shouldEqual primes
    }
  }

  "PrimeScala.isPrime" should {
    "find some primes" in {
      primes.foreach(p => PrimeScala.isPrime(p) shouldBe true)
    }

    "deny some composites" in {
      composites.foreach(c => PrimeScala.isPrime(c) shouldBe false)
    }
  }

  "PrimeScala.first" should {
    "find the first 10 primes" in {
      PrimeScala.first(10) shouldEqual primes
    }
  }
}
