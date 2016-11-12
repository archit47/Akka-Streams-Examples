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
  val flow1: Flow[Int, Int, NotUsed] = Flow[Int].map(_ + 1)
  val sink: Sink[Any, Future[Done]] = Sink.foreach(println)


  /* No Flow */
  /* Connecting the Source to Sink directly */

  source
    .to(sink)
    .run()  // materializer is implicitly used here



  /* Single Flow */
  /* Source - Flow - Sink */

  val graph1: RunnableGraph[NotUsed] =
    source
      .via(flow1)
      .to(sink)

  // materializer is implicitly used here
  graph1.run()



  /* Multiple Flows */
  /* Source - Flow - Flow - Sink */

  val flow2: Flow[Int, Int, NotUsed] = Flow[Int].map(_*2)
  val graph2: RunnableGraph[NotUsed] =
    source
      .via(flow1)  // Flows can be re-used
      .via(flow2)  // Flows can clubbed - can be concatenated.
      .to(sink)

  graph2.run()

}
