package org.http4s.util

import scala.concurrent.{ExecutionContext, Future, Promise}
import scala.util.{Success, Failure}
import scalaz.{-\/, \/-}
import scalaz.syntax.either._
import fs2.util.Task

trait TaskFunctions {
  def unsafeTaskToFuture[A](task: Task[A]): Future[A] = {
    val p = Promise[A]()
    task.runAsync {
      case Right(a) => p.success(a)
      case Left(t) => p.failure(t)
    }
    p.future
  }

  def futureToTask[A](f: => Future[A])(implicit ec: ExecutionContext): Task[A] = {
    Task.async { cb =>
      f.onComplete {
        case Success(a) => cb(Right(a))
        case Failure(t) => cb(Left(t))
      }
    }
  }
}

object task extends TaskFunctions
