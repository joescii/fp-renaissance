package com.joescii

import org.scalatest.{ShouldMatchers, FlatSpec}
import scala.collection.JavaConverters._

class EvensSpec extends FlatSpec with ShouldMatchers {
  "The EvensJava7 first(25)" should "be equal to Scala's '2 to 50 by 2'" in {
    val evens = EvensJava7.evensFirst(25).asScala
    evens shouldEqual (2 to 50 by 2)
  }

  "The EvensJava8 first(25)" should "be equal to Scala's '2 to 50 by 2'" in {
    val evens = EvensJava8.evensFirst(25).toArray().toList
    evens shouldEqual (2 to 50 by 2)
  }
}