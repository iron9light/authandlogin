package iron9light.liftProblems.authandlogin

import net.liftweb.http.rest.RestHelper
import net.liftweb.common.Loggable
import net.liftweb.http.{RequestVar, PlainTextResponse}

/**
 * @author il
 * @version 12/20/11 9:33 PM
 */

object AuthServer extends RestHelper with Loggable {
  object user extends RequestVar[Option[User]](None)
  
  serve {
    case "api" :: _ Get _ => {
      val x = User.currentUser.toString
      logger info x
      PlainTextResponse(x)
    }

    case "api2" :: _ Get _ => {
      val x = user.is.toString
      logger info x
      PlainTextResponse(x)
    }
  }
}