package org.http4s.examples

import org.http4s.scalatra.ScalatraService
import org.http4s.Status
import scalaz.{State, Monad, Unapply}
import effectful._
import scalaz._
import org.http4s.scalatra.ScalatraService.Action

object ScalatraExample extends ScalatraService {
  GET("/gone") {
    status = Status.Gone
    s"status is ${status}"
  }

  GET("/pure") { "pure" }
}