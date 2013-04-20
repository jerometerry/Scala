package com.jterry.scala.sfn

object SmallestFreeNumberApplication extends App {
  println("Hello World")
  
  var list = Array(0, 1, 2, 4, 5, 6)
  var sfn = getMinFree(list)
  println(sfn.toString())
	
  def getMinFree (xs : Array[Int]) : Int = {
    return getMinFreePrime(xs, 0, xs.length)
  }
	
  def getMinFreePrime (xs : Array[Int], start : Int, len : Int) : Int = {
    return len match {
      case 0 => start
      case _ => getMinFreeByPartition(xs, computePartition(start, len), start, len) 
    } 
  }
  
  def getMinFreeByPartition (xs : Array[Int], pv : Int, start : Int, len : Int) : Int = { 
    return partitionList(xs, pv) match {
      case (left, right) => getMinFreeByParts(left, right, start, len, pv)
	  case _             => -1
	}
  }
  
  def getMinFreeByParts(left: Array[Int], right: Array[Int], start : Int, len: Int, pv : Int) : Int = {
    return start + left.length - pv == 0 match {
      case true => getMinFreePrime(right, pv, right.length)
      case _    => getMinFreePrime(left, start, left.length)
    }
    
  }
	
  def computePartition (start : Int, len : Int) : Int = {
    return start + 1 + len / 2
  }
	
  def partitionList (xs : Array[Int], pv : Int) : (Array[Int], Array[Int]) = {
    return xs.partition(_ < pv);
  }
}