package recfun
import common._
import scala.annotation.tailrec

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
  }

  /**
   * Exercise 1
   */
  //  def pascal(c: Int, r: Int): Int = ???
  def pascal(c: Int, r: Int): Int = {
    def factorial(x: Int, accumulator: Int): Int = if (x == 0) accumulator else factorial(x - 1, x * accumulator)
    factorial(r, 1) / (factorial(r - c, 1) * factorial(c, 1))
  }

  /**
   * Exercise 2
   */
  //def balance(chars: List[Char]): Boolean = ???

  def balancetrail(chars: List[Char]): Boolean = {
    @tailrec def balanced(chars: List[Char], count: Int): Boolean =
      chars match {
        case Nil => count == 0
        case '(' :: tail => balanced(tail, count + 1)
        case ')' :: tail => count > 0 && balanced(tail, count - 1)
        case _ :: tail => balanced(tail, count)
      }
    balanced(chars, 0)
  }

  def balance(chars: List[Char]): Boolean = {
    def f(s: List[Char], acc: List[Char]): List[Char] = {
      if (s.isEmpty) acc
      else if (s.head == '(') f(s.tail, s.head :: acc)
      else if (s.head == ')') {
        if (acc.isEmpty) f(s.tail, '(' :: acc)
        else f(s.tail, acc.tail)
      } else f(s.tail, acc)
    }
    f(chars, List()).isEmpty
  }

  /**
   * Exercise 3
   */
  //  def countChange(money: Int, coins: List[Int]): Int = ???
  def countChange(money: Int, coins: List[Int]): Int = {
    if (money == 0) 1
    else if (money < 0) 0
    else if (coins.isEmpty) 0
    else countChange(money, coins.tail) + countChange(money - coins.head, coins)
  }
}
