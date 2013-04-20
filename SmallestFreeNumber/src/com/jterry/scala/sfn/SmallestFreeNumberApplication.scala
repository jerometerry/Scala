/** 
 * Jerome Terry
 * April 20, 2013
 * 
 * Port of smallestFreeNumber.hs Haskell application
 */
package com.jterry.scala.sfn

/**
 * SmallestFreeNumberApplication is a simple application to test the Smallest Free Number
 * algorithm found in Pearls of Functional Algorithm Design, Richard Bird, 2010
 */
object SmallestFreeNumberApplication extends App {
  /**
   * Gets the smallest free number from a list of natural numbers       
   * @param  xs The list of natural numbers, sorted or unsorted
   * @return The computed smallest free number
   */
  def getMinFree (xs : List[Int]) : Int = {
    return getMinFree(xs, 0, xs.length)
  }
  
  /**
   * Gets the smallest free number from the given list of natural numbers
   * 
   * <p>
   * 
   * For example, given the list [0, 1, 2, 4, 5, 6], the smallest free value is 3.
   * The call stacks is as follows:
   * 
   * <p>
   * 
   * Initial call
   * getMinFree' [0, 1, 2, 4, 5, 6] 0 6
   * start = 0, len = 6, pv = 0+1+(6/2)=4
   * left = [0, 1, 2], right = [4,5,6]
   * leftLen = 3, rightLen = 6 - 3 = 3
   * rightStart = 0 + 3 = 3
   * inRight = 3 == 4 = False
   *
   * <p>
   * 
   * 1st recursive call
   * getMinFree' [0,1,2] 0 3
   * start = 0, len = 3, pv = 0+1+(3/2) = 2
   * left = [0,1], right = [2]
   * leftLen = 2, rightLen = 3 - 2 = 1
   * rightStart = 0 + 2 = 2
   * inRight = 2 == 2 = True
   *
   * <p>
   * 
   * 2nd recursive call
   * getMinFree' [2] 2 1
   * start = 2, len = 1, pv = 2+1+(1/2) = 3
   * left = [2], right = []
   * leftLen = 1, rightLen = 1 - 1 = 0
   * rightStart = 2 + 1 = 3
   * inRight = 3 == 3 = True
   * 
   * <p>
   * 
   * 3rd recursive call
   * getMinFree' [] 3 0
   * start = 3, len = 0, pv = 3+1+(0/2) = 4
   * since len = 0, smallest free number = start = 3
   * 
   * @param  xs The list of natural numbers, sorted or unsorted
   * @param  start The value associated with index 0 in xs
   * @param  len The number of elements contained in xs
   * @return The smallest free number in the given list of natural numbers
   */
  def getMinFree (xs : List[Int], start : Int, len : Int) : Int = {
    return len match {
      case 0 => start
      case _ => getMinFree(xs, computePartition(start, len), start, len) 
    } 
  }
  
  /**
   * Helper method that partitions xs by the partition value, then invokes
   * the getMinFree method passing in both the left and right partitions
   * 
   * @param  xs The list of natural numbers to search
   * @param  pv The partition value to split the xs on
   * @param  start The value associated with the first element of xs
   * @param  len The number of elements in xs
   * @return The smallest free number
   */
  def getMinFree (xs : List[Int], pv : Int, start : Int, len : Int) : Int = { 
    return partitionList(xs, pv) match {
      case (left, right) => getMinFree(left, right, pv, start, len)
	  case _             => -1
	}
  }
  
  /**
   * Helper method that determines which partition to search, then searches that partition
   * @param  left The left partition containing values < pv
   * @param  right The right partition containing values >= pv
   * @param  pv The partition value to split the xs on
   * @param  start The value associated with the first element of xs
   * @param  len The number of elements in xs
   * @return The smallest free number
   */
  def getMinFree (left: List[Int], right: List[Int], pv : Int, start : Int, len: Int) : Int = {
    return start + left.length - pv == 0 match {
      case true => getMinFree(right, pv, right.length)
      case _    => getMinFree(left, start, left.length)
    }
  }
  
  /**
   * Computes the partition value for use with getMinFreePrime
   * @param  start The start value
   * @param  len The length
   * @return The partition value
   */
  def computePartition (start : Int, len : Int) : Int = {
    return start + 1 + len / 2
  }
	
  /**
   * Partitions a list of integers into 2 lists, one list containing values less than the partition start
   * and one list containing values greater than or equal to the partition start
   * @param  xs The list of numbers to partition
   * @param  pv The value to partition the list with
   * @return A tuple containing two lists, one list with all values less than the partition value
   * and the other list all values greater than or equal to the partition value
   */
  def partitionList (xs : List[Int], pv : Int) : (List[Int], List[Int]) = {
    return xs.partition(_ < pv);
  }
  
  /**
   * Generate lists of natural numbers, and test the getMinFree mehtod on each list
   * @param n The upper bound for natural numbers in all generated lists
   */
  def generateAndTestLists (n : Int) = {
    for (i <- List.range(0, n+1)) {
      testGetMinFree(generateList(n+1, i), i)
    }
  }
  
  /**
   * Calls getMinFree on the given list of natural numbers, and outputs the expected 
   * smallest free number, followed by either Ok or Failed depending on if the computed
   * smallest free number matches the expected value.
   */
  def testGetMinFree(xs : List[Int], e : Int) = {
    getMinFree(xs) - e == 0 match {
      case true => println(e.toString() + " Ok")
      case _    => println(e.toString() + " Failed")
    }
  } 
  
  /**
   * Generates a list of natural numbers from 0 to n, excluding one number in the range
   * @param  n The upper bound of the list
   * @param  e The natural number to exclude from the list
   * @return A list of all natural numbers from 0 to n, excluding e
   */
  def generateList (n : Int, e : Int) : List[Int] = {
    return List.range(0,e) ::: List.range(e+1,n)
  }
  
  override def main(args: Array[String]): Unit = {
    generateAndTestLists(100)
  }
}