package funsets

import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class MyFunSetSuite extends FunSuite {

  import funsets.FunSets._

  trait TestSets {
    val s1 = singletonSet(1)
    val s2 = singletonSet(2)
    val s3 = singletonSet(3)

    val s12 = union(s1, s2)
    val s23 = union(s2, s3)

    val s123 = union(s12, s23)

    def odd(n: Int) = 
      n % 2 != 0
  }

  test("singleton") {
    new TestSets {
      assert(s1(1))
      assert(!s1(2))
      assert(!s1(3))
    }
  }

  test("intersection") {
    new TestSets {
      val intersection = intersect(s12, s23)
      assert(contains(intersection, 2))
      assert(!contains(intersection, 1))
      assert(!contains(intersection, 3))
    }
  }

  test("difference") {
    new TestSets {
      val difference = diff(s12, s23)
      assert(contains(difference, 1))
      assert(!contains(difference, 2))
      assert(!contains(difference, 3))
    }
  }

  test("filter") {
    new TestSets {
      val onlyOdd = filter(s123, odd)
      assert(contains(onlyOdd, 1))
      assert(!contains(onlyOdd, 2))
      assert(contains(onlyOdd, 3))
    }
  }
}