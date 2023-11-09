package Controllers

//import plugins.sbtplay.api.libs.json._

import com.typesafe.config.Config
import com.typesafe.sslconfig.util.ConfigLoader

import javax.security.auth.login.Configuration
import scala.annotation.StaticAnnotation

class MyController @Inject() (config: Configuration, c: ControllerComponents) extends AbstractController(c) {
  def getFoo = Action {
    Ok(config.get[String]("foo"))
  }
}

case class AppConfig(title: String, baseUri: URI)
object AppConfig {
  implicit val configLoader: ConfigLoader[AppConfig] = new ConfigLoader[AppConfig] {
    def load(rootConfig: Config, path: String): AppConfig = {
      val config = rootConfig.getConfig(path)
      AppConfig(
        title = config.getString("title"),
        baseUri = new URI(config.getString("baseUri"))
      )
    }
  }
}

case class ControllerComponents()

class Inject() extends StaticAnnotation

