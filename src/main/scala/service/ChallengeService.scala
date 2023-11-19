package service

import zio._
import model.Challenge

trait ChallengeService {
  def createRandomMultiplication(): UIO[Challenge]
}

object ChallengeService {
  def createRandomMultiplication(): ZIO[ChallengeService, Nothing, Challenge] =
    ZIO.serviceWithZIO[ChallengeService](_.createRandomMultiplication())
}
