package com.joescii

import org.scalatest.{ShouldMatchers, WordSpec}

class ParseSpec extends WordSpec with ShouldMatchers {
  "Integers" should {
    "introduce pattern matching" in {
      val i = 2
      i match {
        case 1 => // if i == 1
        case 2 => // if i == 2
        case n if n % 2 == 0 => // if i is even, sets n <- i
        case other => // otherwise, sets other <- 1
      }
    }
  }

  "Cons operator" should {
    "further demonstrate pattern matching" in {
      val none  = Nil
      val one   = 3 :: Nil
      val three = 1 :: 2 :: 3 :: Nil
      def test(l:List[Int]) = l match {
        case Nil =>
          // Matches none
        case last :: Nil =>
          // Matches one, sets last <- 3
        case head :: tail =>
          // Matches three, sets head <- 1, tail <- 2 :: 3 :: Nil
      }
    }
  }

  "f" should {
    "match a positive integer" in {
      f.unapply("10") shouldEqual Some(10f)
    }
    "match a positive float with a decimal" in {
      f.unapply("3.14") shouldEqual Some(3.14f)
    }
    "match a negative float" in {
      f.unapply("-22.7") shouldEqual Some(-22.7f)
    }
    "not match alpha string" in {
      f.unapply("bob") shouldEqual None
    }
    "demonstrate custom pattern match" in {
      val str = "12.3"
      val num = str match {
        case f(x) => x
        case _ => 0
      }
      num shouldEqual 12.3f
    }
    "demonstrate custom pattern match failure" in {
      val str = "eleven"
      val num = str match {
        case f(x) => x
        case _ => 0
      }
      num shouldEqual 0f
    }
  }

  "n" should {
    "match a positive integer" in {
      n.unapply("10") shouldEqual Some(10)
    }
    "match a negative integer" in {
      n.unapply("-42") shouldEqual Some(-42)
    }
    "match zero" in {
      n.unapply("0") shouldEqual Some(0)
    }
    "not match alpha string" in {
      n.unapply("jimmy") shouldEqual None
    }
    "demonstrate custom pattern match" in {
      val str = "15"
      val num = str match {
        case n(x) => x
        case _ => 0
      }
      num shouldEqual 15
    }
    "demonstrate custom pattern match failure" in {
      val str = "15.01"
      val num = str match {
        case n(x) => x
        case _ => 0
      }
      num shouldEqual 0
    }
  }

  "The parser" should {
    "work" in {
      val file =
        """L 55.7 65.3 -50 -60.3
          |C 10 12.2 5.5
          |T -5 23.2 0 My text label
          |Junk
        """.stripMargin

      val expected = List(
        Line(55.7f, 65.3f, -50f, -60.3f),
        Circle(10f, 12.2f, 5.5f),
        Text(-5f, 23.2f, 0, "My text label"),
        Unknown("Junk")
      )

      Serializer.parse(file) shouldEqual expected
    }
  }
}
