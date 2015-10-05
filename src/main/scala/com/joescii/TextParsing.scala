package com.joescii

import scala.util.Try

object f {
  def unapply(s: String): Option[Float] = try {
    Some(s.toFloat)
  } catch {
    case _ => None
  }
}

object n {
  def unapply(s: String): Option[Int] = Try(s.toInt).toOption
}

trait DrawingObject
case class Circle(x:Float, y:Float, r:Float) extends DrawingObject
case class Line(x1:Float, y1:Float, x2:Float, y2:Float) extends DrawingObject
case class Text(x:Float, y:Float, orientation:Int, text:String) extends DrawingObject
case class Unknown(line:String) extends DrawingObject

// KEY: L
// PARAMETERS: x1, y1, x2, y2
// EXAMPLE: L 55.7 65.3 -50 -60.3
private object LineExtractor {
  def unapply(tokens:List[String]):Option[Line] = tokens match {
    case "L" :: f(x1) :: f(y1) :: f(x2) :: f(y2) :: _ =>
      Some(Line(x1, y1, x2, y2))
    case _ => None
  }
}

// KEY: C
// PARAMETERS: x, y, radius
// EXAMPLE: C 10 12.2 5.5
private object CircleExtractor {
  def unapply(tokens:List[String]):Option[Circle] = tokens match {
    case "C" :: f(x) :: f(y) :: f(r) :: _ =>
      Some(Circle(x, y, r))
    case _ =>
      None
  }
}

// KEY: T
// PARAMETERS: x, y, orientation, text
//   orientation: 0 => L to R; 1 => Top to Bottom
// EXAMPLE: T -5 23.2 0 My text label
private object TextExtractor {
  def unapply(tokens:List[String]):Option[Text] = tokens match {
    case "T" :: f(x) :: f(y) :: n(orie) :: text =>
      Some(Text(x, y, orie, text mkString " "))
    case _ =>
      None
  }
}

object Serializer {
  def parse(file:String):List[DrawingObject] =
    file.lines              // Split into lines
      .map(_.trim)          // Remove leading/trailing whitespace
      .filter(!_.isEmpty)   // Remove empty lines
      .map { line =>
        line.split("""\s+""").toList match {
          case LineExtractor(line) => line
          case CircleExtractor(circle) => circle
          case TextExtractor(text) => text
          case unknown => Unknown(line)
        }
      }.toList
}