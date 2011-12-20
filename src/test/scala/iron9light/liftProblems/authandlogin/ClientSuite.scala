package iron9light.liftProblems.authandlogin

import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith
import org.scalatest.FunSuite
import dispatch._
import net.liftweb.common.Loggable

/**
 * @author il
 * @version 12/20/11 10:06 PM
 */

@RunWith(classOf[JUnitRunner])
class ClientSuite extends FunSuite with Loggable {
  test("") {
    val http = new Http
    val url = :/("localhost", 8080) / "api" / "x" as_! ("aa", "bb")
    val handler = url >- (x => logger info x)
    http(handler)
  }
}