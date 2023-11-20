package controller

import service.ChallengeService
import zio.json.*
import zio.http.*
import zio._
import model._

object ChallengeController {
  def apply(): Http[ChallengeService, Throwable, Request, Response] =
    Http.collectZIO[Request] {
      case Method.GET -> Root / "challenges" / "random" => {
        ChallengeService.createRandomMultiplication().map(response => Response.json(response.toJson))
      }
      case req@(Method.POST -> Root / "challenges" / "attempt") => {
        for {
          u <- req.body.asString.map(_.fromJson[ChallengeAttempt])
          r <- u match
            case Left(e) =>
              ZIO
                .debug(s"Failed to parse the input: $e")
                .as(Response.text(e).withStatus(Status.BadRequest))
            case Right(u) =>
              ChallengeService.checkAttempt(u).map(out => Response.json(out.toJson))
        } yield r
      }.orDie
    }
}
