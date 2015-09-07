package bootstrap.liftweb

import net.liftweb._
import util._
import Helpers._

import common._
import http._
import sitemap._
import Loc._
import net.liftmodules.JQueryModule
import net.liftweb.http.js.jquery._

import com.joescii.fpr.rest.Downloads

/**
 * A class that's instantiated early and run.  It allows the application
 * to modify lift's environment
 */
class Boot {
  def boot {
    // where to search snippet
    LiftRules.addToPackages("com.joescii.fpr")

    // Build SiteMap
    val entries = List(
      Menu.i("Home") / "index", // the simple way to declare a menu
      Menu.i("Presenter Login") / "presenter"
    )

    // set the sitemap.  Note if you don't want access control for
    // each page, just comment this line out.
    LiftRules.setSiteMap(SiteMap(entries:_*))

    //Show the spinny image when an Ajax call starts
    LiftRules.ajaxStart =
      Full(() => LiftRules.jsArtifacts.show("ajax-loader").cmd)
    
    // Make the spinny image go away when it ends
    LiftRules.ajaxEnd =
      Full(() => LiftRules.jsArtifacts.hide("ajax-loader").cmd)

    // Force the request to be UTF-8
    LiftRules.early.append(_.setCharacterEncoding("UTF-8"))

    // Use HTML5 for rendering
    LiftRules.htmlProperties.default.set((r: Req) =>
      new Html5Properties(r.userAgent))

    //Init the jQuery module, see http://liftweb.net/jquery for more information.
    LiftRules.jsArtifacts = JQueryArtifacts
    JQueryModule.InitParam.JQuery=JQueryModule.JQuery172
    JQueryModule.init()

    // We're doing some evil stuff in the browser, so tell Lift to let us have our pitfalls.
    LiftRules.securityRules = () => {
      SecurityRules(content = Some(ContentSecurityPolicy(
        styleSources = List(
          ContentSourceRestriction.UnsafeInline,
          ContentSourceRestriction.All            // We're being lazy and using google fonts
        ),
        fontSources = List(
          ContentSourceRestriction.All            // Google fonts!
        ),
        scriptSources = List(
          ContentSourceRestriction.UnsafeEval,
          ContentSourceRestriction.Self
        )
      )))
    }

    LiftRules.earlyResponse.append { (req: Req) =>
      if(Props.mode != Props.RunModes.Development &&
         req.path.partPath.headOption == Some("presenter") &&
         req.header("X-Forwarded-Proto") != Full("https")) {
        val uriAndQuery = req.uri + (req.request.queryString.map(s => "?"+s) openOr "")
        val uri = "https://%s%s".format(req.request.serverName, uriAndQuery)
        Full(PermRedirectResponse(uri, req, req.cookies: _*))
      }
      else Empty
    }

    LiftRules.statelessDispatch.append(Downloads)
  }
}
