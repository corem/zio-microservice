package model

import zio._
import zio.test._
import zio.json._

object ChallengeTest extends ZIOSpecDefault {
  def spec = {
    suite("Challenge Encoder / Decoder")(
      test("Converts from class to Json") {
        assertTrue(Challenge(3,2).toJson == """{"valueA":3,"valueB":2}""")
      },
      test("Converts from json to case class") {
        assertTrue("""{"valueA":3,"valueB":2}""".fromJson[Challenge].getOrElse(null) == Challenge(3, 2))
      }
    )
  }
}
