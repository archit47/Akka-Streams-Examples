/**
  * Created by archit on 11/12/16.
  */

import akka.stream._
import akka.stream.scaladsl._
import akka.{ NotUsed, Done }
import akka.actor.ActorSystem
import scala.concurrent._
import scala.concurrent.duration._


object Main extends App{

  println("Akka Streams")

  implicit val actorSystem = ActorSystem("AkkaStreams")
  implicit val materializer = ActorMaterializer()

  val source: Source[Int, NotUsed] = Source(0 to 99)
  val flow: Flow[Int, Int, NotUsed] = Flow[Int].map(_ + 1)
  val sink: Sink[Any, Future[Done]] = Sink.foreach(println)

  val graph: RunnableGraph[NotUsed] =
    source
      .via(flow)
      .to(sink)

  // materializer is implicitly used here
  graph.run()

}
