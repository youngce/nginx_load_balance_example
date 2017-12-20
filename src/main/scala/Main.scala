import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer
import scala.io.StdIn
object Main extends App{
  val localHostname = java.net.InetAddress.getLocalHost.getHostName

  implicit val system = ActorSystem("my-system")
  implicit val mat = ActorMaterializer()
  // needed for the future flatMap/onComplete in the end
  implicit val executionContext = system.dispatcher

  val route =
    path("ping"/IntNumber) {i=>
      get {
        complete(s"pong from $localHostname ->${i%3}")
      }
    }

  val bindingFuture = Http().bindAndHandle(route, "0.0.0.0", 4321)

}
