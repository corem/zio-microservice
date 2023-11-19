package controller

import service.ChallengeService
import zio.json.*
import zio.http.*

object ChallengeController {
  def apply(): Http[ChallengeService, Throwable, Request, Response] =
    Http.collectZIO[Request] {
      case Method.GET -> Root / "challenges" / "random" => {
        ChallengeService.createRandomMultiplication().map(response => Response.json(response.toJson))
      }
    }
}
