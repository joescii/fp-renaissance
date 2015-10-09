package com.joescii

import org.scalatest.{ShouldMatchers, WordSpec}

class ParseSpec extends WordSpec with ShouldMatchers {
  "Integers" should {
    "introduce pattern matching" in {
      def ints(i:Int):String = i match {
        case 1 => "one"
        case 2 => "two"
        case n if n % 2 == 0 => s"$n is even"
        case other => s"$other is whatever"
      }

      ints(1) shouldEqual "one"
      ints(2) shouldEqual "two"
      ints(3) shouldEqual "3 is whatever"
      ints(4) shouldEqual "4 is even"
    }
  }

  "Cons operator" should {
    "further demonstrate pattern matching" in {
      val none  = Nil
      val one   = 3 :: Nil
      val three = 1 :: 2 :: 3 :: Nil

      def lists(l:List[Int]) = l match {
        case Nil => "list is empty"
        case last :: Nil => s"$last is alone"
        case head :: tail => s"$head and ${tail.size} others"
      }

      lists(none) shouldEqual "list is empty"
      lists(one) shouldEqual "3 is alone"
      lists(three) shouldEqual "1 and 2 others"
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
      z.unapply("10") shouldEqual Some(10)
    }
    "match a negative integer" in {
      z.unapply("-42") shouldEqual Some(-42)
    }
    "match zero" in {
      z.unapply("0") shouldEqual Some(0)
    }
    "not match alpha string" in {
      z.unapply("jimmy") shouldEqual None
    }
    "demonstrate custom pattern match" in {
      val str = "15"
      val num = str match {
        case z(x) => x
        case _ => 0
      }
      num shouldEqual 15
    }
    "demonstrate custom pattern match failure" in {
      val str = "15.01"
      val num = str match {
        case z(x) => x
        case _ => 0
      }
      num shouldEqual 0
    }
  }

  "The parser" should {
    "work" in {
      val file = """
          L 55.7 65.3 -50 -60.3
          C 10 12.2 5.5
          T -5 23.2 0 My text label
          Junk
        """

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
