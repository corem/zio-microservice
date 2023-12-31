import zio.http.Server
import zio.{Scope, ZIO, ZIOAppArgs, ZIOAppDefault}
import controller.ChallengeController
import service.{ChallengeServiceImpl, RandomGeneratorServiceImpl}

object Main extends ZIOAppDefault {
  def run: ZIO[Environment with ZIOAppArgs with Scope, Throwable, Any] =
    val httpApps =  ChallengeController()
    Server
      .serve(
        httpApps.withDefaultErrorResponse
      )
      .provide(
        Server.defaultWithPort(8080),
        RandomGeneratorServiceImpl.layer,
        ChallengeServiceImpl.layer
      )
}