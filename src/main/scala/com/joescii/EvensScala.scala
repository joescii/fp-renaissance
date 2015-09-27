package com.joescii

object EvensScala {
  def firstNEvens(n:Int) = (1 to n) map (_ * 2)

  def firstNEvensPar(n:Int) = (1 to n).par map (_ * 2)
}
