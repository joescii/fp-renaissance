package com.joescii

object PrimeScala {
  def isPrime(n:Int) = (2 to (n-1))
      .forall(i => n % i != 0)

  def first(n:Int) = Stream.from(2)
    .filter(isPrime(_))
    .take(n)
}
