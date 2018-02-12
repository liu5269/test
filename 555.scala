package test

import scala.concurrent.duration._
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class RecordedSimulation extends Simulation {

	val httpProtocol = http
		.baseURL("http://172.16.20.154:3000")
		.acceptEncodingHeader("gzip, deflate")
		.disableCaching
		.disableResponseChunksDiscarding




	val scn = scenario("RecordedSimulation")
			.exec(http("request_0"))
			
			
	
		
	
	setUp(scn.inject(atOnceUsers(1000))).throttle(holdFor(1 minute)).protocols(httpProtocol)
	

	

}