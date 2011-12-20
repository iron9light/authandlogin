package iron9light.liftProblems.authandlogin

import net.liftweb.http.rest.RestHelper
import net.liftweb.http.PlainTextResponse
import net.liftweb.common.Loggable

/**
 * @author il
 * @version 12/20/11 9:33 PM
 */

object AuthServer extends RestHelper with Loggable {
  serve {
    case "api" :: _ Get _ => {
      val x = User.currentUser.toString
      logger info x
      PlainTextResponse(x)
    }
  }
}