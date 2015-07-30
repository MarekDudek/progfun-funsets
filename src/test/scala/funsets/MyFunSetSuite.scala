package funsets

import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class MyFunSetSuite extends FunSuite {

  import funsets.FunSets._

  test("singleton") {
    val setWitOne = singletonSet(1)
    assert(setWitOne(1) === true)
  }
}