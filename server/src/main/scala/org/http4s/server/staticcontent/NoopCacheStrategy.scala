package org.http4s.server.staticcontent

import org.http4s._
import fs2.util.Task


/** Cache strategy that doesn't cache anything, ever. */
object NoopCacheStrategy extends CacheStrategy {
  override def cache(uriPath: String, resp: Response): Task[Response] = Task.now(resp)
}
