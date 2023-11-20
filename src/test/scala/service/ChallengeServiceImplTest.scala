package service

import zio._
import zio.test._
import zio.test.Assertion.equalTo
import model._
import service._

object ChallengeServiceImplTest extends ZIOSpecDefault {
  def spec = {
    suite("Test Challenge Service")(
      test("Challenge service should provide challenge") {
        for {
          _ <- TestRandom.setSeed(42L)
          randomService <- ZIO.service[RandomGeneratorService]
          challenge <- ChallengeServiceImpl(randomService).createRandomMultiplication()
        } yield assertTrue(challenge == Challenge(9, 4))
      },
      test("ChallengeService should check a ChallengeAttempt and yield true") {
        val challenge = ChallengeAttempt(User("TestUser"), Challenge(2, 3), 6)
        for {
          check <- ChallengeServiceImpl(null).checkAttempt(challenge)
        } yield assertTrue(check)
      }
    ).provide(
      RandomGeneratorServiceImpl.layer
    )
  }
}
