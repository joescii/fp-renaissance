package com.joescii

object PrimeScala {
  def isPrime(n:Int) = (2 to (n-1))
      .forall(i => n % i != 0)

  def first(n:Int) = Stream.from(2)
    .filter(isPrime _)
    .take(n)

  def first(n:Int, p:Int=>Boolean) = Stream.from(2)
    .filter(p)
    .take(n)

  def first2(n:Int) = first(n, isPrime _)
}
