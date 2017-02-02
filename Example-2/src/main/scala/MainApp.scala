/**
  * Created by archit on 2/2/17.
  */


import akka.stream.scaladsl._
import akka.{ NotUsed, Done }
import akka.actor.ActorSystem
import scala.concurrent._
import akka.stream._
import scala.concurrent.ExecutionContext.Implicits.global

object MainApp extends App{

  println("Akka Streams")

  implicit val system = ActorSystem("AkkaStreams")
  implicit val material = ActorMaterializer()


  val names = List("abc","bcd","cde","def","efg","fgh")
  val namesIterator = names.iterator

  val sourceOfNames = Source(names)

  val nameLengthFlow = Flow[String].map{_.length}

  val sink: Sink[Int, Future[Done]] = Sink.foreach(println)

  val graph = sourceOfNames.via(nameLengthFlow).to(sink)

  graph.run()


  // Same as above, but with Iterator
  Source.fromIterator[String]{() => namesIterator}.via(Flow[String].map{_.length}).to(sink).run()


}
