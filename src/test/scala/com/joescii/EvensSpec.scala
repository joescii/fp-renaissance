package com.joescii

import org.scalatest.{ShouldMatchers, FlatSpec}
import scala.collection.JavaConverters._

class EvensSpec extends FlatSpec with ShouldMatchers {
  "The EvensJava7 first25" should "be correct" in {
    val evens = EvensJava7.first25().asScala
    evens shouldEqual (2 to 50 by 2)
  }
}
