package com.jterry.scala.sfn

object SmallestFreeNumberApplication extends App {
  generateAndTestLists(101)
  
  def generateAndTestLists (n : Int) = {
    for (i <- List.range(0, n)) {
      testGetMinFree(generateList(i, n), i)
    }
  }
  
  def testGetMinFree(xs : List[Int], e : Int) = {
    getMinFree(xs) - e == 0 match {
      case true => println(e.toString() + " Ok")
      case _    => println(e.toString() + " Failed")
    }
  } 
  
  def generateList (e : Int, n : Int) : List[Int] = {
    return List.range(0,e) ::: List.range(e+1,n)
  }
  
  def getMinFree (xs : List[Int]) : Int = {
    return getMinFreePrime(xs, 0, xs.length)
  }
	
  def getMinFreePrime (xs : List[Int], start : Int, len : Int) : Int = {
    return len match {
      case 0 => start
      case _ => getMinFreeByPartition(xs, computePartition(start, len), start, len) 
    } 
  }
  
  def getMinFreeByPartition (xs : List[Int], pv : Int, start : Int, len : Int) : Int = { 
    return partitionList(xs, pv) match {
      case (left, right) => getMinFreeByParts(left, right, start, len, pv)
	  case _             => -1
	}
  }
  
  def getMinFreeByParts(left: List[Int], right: List[Int], start : Int, len: Int, pv : Int) : Int = {
    return start + left.length - pv == 0 match {
      case true => getMinFreePrime(right, pv, right.length)
      case _    => getMinFreePrime(left, start, left.length)
    }
  }
	
  def computePartition (start : Int, len : Int) : Int = {
    return start + 1 + len / 2
  }
	
  def partitionList (xs : List[Int], pv : Int) : (List[Int], List[Int]) = {
    return xs.partition(_ < pv);
  }
}