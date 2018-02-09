package test

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class RecordedSimulation extends Simulation {
  val t_concurrency = Integer.getInteger("concurrency", 1000).toInt
  val t_rampUp = Integer.getInteger("ramp-up", 1).toInt
  val t_holdFor = Integer.getInteger("hold-for", 60).toInt
	val httpProtocol = http
		.baseURL("http://172.16.20.154:3000")
		.inferHtmlResources(BlackList(""".*\.js""", """.*\.css""", """.*\.gif""", """.*\.jpeg""", """.*\.jpg""", """.*\.ico""", """.*\.woff""", """.*\.(t|o)tf""", """.*\.png"""), WhiteList())
		.acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
		.acceptEncodingHeader("gzip, deflate")
		.acceptLanguageHeader("zh-CN,zh;q=0.9")
		.userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.140 Safari/537.36")

	val headers_0 = Map("Upgrade-Insecure-Requests" -> "1")



	val scn = scenario("RecordedSimulation").forever(){
		.exec(http("request_0")
			.get("/")
			.headers(headers_0))
			}
			
	val execution = scenario.inject(rampUsers(t_concurrency) over t_rampUp)
	setUp(execution).throttle(holdFor(t_holdFor)).maxDuration(t_rampUp + t_holdFor)
}