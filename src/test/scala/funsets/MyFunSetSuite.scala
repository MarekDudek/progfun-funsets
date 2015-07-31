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

    def divisibleBySeven =
      (x: Int) => x % 7 == 0

    def notDivisibleBySeven(x: Int) =
      !divisibleBySeven(x)
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

  test("forall") {
    new TestSets {
      assert(forall(s123, x => true))
      assert(!forall(s123, x => false))
      assert(forall(s123, notDivisibleBySeven))
      assert(!forall(s123, odd))
    }
  }

  test("exists") {
    new TestSets {
      assert(exists(s123, x => true))
      assert(!exists(s123, x => false))
      assert(exists(s123, odd))
      assert(!exists(s123, divisibleBySeven))
    }
  }

  test("map") {
    new TestSets {
      val tripled = map(s12, x => x * 3)
      assert(contains(tripled, 3))
      assert(contains(tripled, 6))
    }
  }
}