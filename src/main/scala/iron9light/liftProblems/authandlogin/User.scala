package iron9light.liftProblems.authandlogin

import net.liftweb.mapper.{MetaMegaProtoUser, MegaProtoUser}


/**
 * @author il
 * @version 12/20/11 9:32 PM
 */

class User extends MegaProtoUser[User] {
  def getSingleton = User
}

object User extends User with MetaMegaProtoUser[User]