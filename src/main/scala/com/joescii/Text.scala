package com.joescii

object f {
  private val floatingPointNumberPattern = """([-+]?(?:\d+)?(?:[.]\d*)?)""".r
  def unapply(s: String): Option[Float] = s match {
    case "" | "."  ⇒ None
    case floatingPointNumberPattern(x) ⇒ Some(x.toFloat)
    case _ ⇒ None
  }
}

object n {
  private val nonNegativeIntegerPattern = """(\d+)""".r
  def unapply(s: String): Option[Int] = s match {
    case nonNegativeIntegerPattern(x) ⇒ Some(x.toInt)
    case _ ⇒ None
  }
}

object Text {
  case class Text(x:Float, y:Float, siz:Float, orie:Int, orig:Int, text:String)
  private object TextExtractor {
    // KEY: T
    // PARAMETERS: x, y, siz, orie, orig, "str"
    // Example: T 55.7 65.3 50 1 1 LABEL
    def unapply(tokens:List[String]):Option[Text] = tokens match {
      case "T" :: f(x) :: f(y) :: f(siz) :: n(orie) :: n(orig) :: text =>
        Some(Text(x, y, siz, orie, orig, text mkString " "))
      case _ => None
    }
  }
}
