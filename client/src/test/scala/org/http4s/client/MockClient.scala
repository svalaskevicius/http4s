package org.http4s
package client

import fs2.util.Task

object MockClient {
  def apply(service: HttpService, dispose: Task[Unit] = Task.now(())) = Client(
    open = service.map(resp => DisposableResponse(resp, dispose)),
    shutdown = Task.now(())
  )
}
