package bootstrap.liftweb

import net.liftweb.db.{DefaultConnectionIdentifier, StandardDBVendor, DB}
import net.liftweb.util.Props
import net.liftweb.mapper.Schemifier
import net.liftweb.http.{Req, LiftRules}
import iron9light.liftProblems.authandlogin.{AuthServer, User}
import net.liftweb.http.auth.{userRoles, HttpBasicAuthentication, AuthRole}
import net.liftweb.common.{Loggable, Full}

/**
 * @author il
 * @version 12/20/11 9:32 PM
 */

class Boot extends  Loggable {
  def boot {
    if (!DB.jndiJdbcConnAvailable_?) {
      val vendor = new StandardDBVendor(
        Props.get("db.driver") openOr "org.h2.Driver",
        Props.get("db.url") openOr "jdbc:h2:mem:lift_proto.db;AUTO_SERVER=TRUE",
        Props.get("db.user"),
        Props.get("db.password"))

      LiftRules.unloadHooks.append(vendor.closeAllConnections_! _)

      DB.defineConnectionManager(DefaultConnectionIdentifier, vendor)
    }

    Schemifier.schemify(true, Schemifier.infoF _, User)
  }
  
  LiftRules.httpAuthProtectedResource.prepend {
    case Req(_, _, _) => Full(AuthRole("normal"))
  }
  
  LiftRules.authentication = HttpBasicAuthentication("api") {
    case (id, ps, req) => {
      val user = User.create
      logger info (id + " " + ps)
      User.logUserIn(user)
      logger info (User.currentUser)
      userRoles(AuthRole("normal") :: Nil)
      true
    }
  }

  LiftRules.dispatch.append(AuthServer)
}