package computerdatabase

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class ScriptDemo  extends Simulation {
    val sessionHeaders = Map("Authorization" -> "Bearer  ${authToken}",
                             "Content-Type" -> "application/json")
    val authToken = "eyJhbGciOiJSUzI1NiIsImtpZCI6ImEzck1VZ01Gdjl0UGNsTGE2eUYzekFrZnF1RSIsInR5cCI6IkpXVCIsIng1dCI6ImEzck1VZ01Gdjl0UGNsTGE2eUYzekFrZnF1RSJ9.eyJuYmYiOjE1OTM0MjM1NzQsImV4cCI6MTU5MzQyNzE3NCwiaXNzIjoiaHR0cHM6Ly9hY2NvdW50cy5pbmZsb3cyNC5jb20iLCJhdWQiOlsiaHR0cHM6Ly9hY2NvdW50cy5pbmZsb3cyNC5jb20vcmVzb3VyY2VzIiwiZmxvd0F0TWVBcGkiLCJzdXJ2ZXlJbnRlcm5hbEFwaSJdLCJjbGllbnRfaWQiOiJZU0luRmxvdzI0Iiwic3ViIjoiZDRhMGQyZmItNWEyYi00ZWE3LTk1NmQtMDI5YWMwM2ZlNWNlIiwiYXV0aF90aW1lIjoxNTkzNDExNDYyLCJpZHAiOiJsb2NhbCIsImdpdmVuX25hbWUiOiJSZXdpZG9zIiwiZmFtaWx5X25hbWUiOiJRdWlzb3MiLCJnZW5kZXIiOlsiTWFsZSIsIk1hbGUiXSwiYmlydGhkYXRlIjpbIjIwMDQtMDEtMDFUMDA6MDA6MDBaIiwiMjAwNC0wMS0wMVQwMDowMDowMFoiXSwiYWRkcmVzcyI6IntcImNvdW50cnlcIjpudWxsLFwiY291bnRyeU5hbWVcIjpcIk5ldyBaZWFsYW5kXCJ9IiwiZW1haWwiOiJ0b2FudGVzdEBtYWlsaW5hdG9yLmNvbSIsInBob25lX251bWJlciI6IjU1NTU1NSIsInJvbGUiOiJVc2VyIiwib3JnYW5pemF0aW9uIjoiW10iLCJoYXNQYXNzd29yZCI6InRydWUiLCJzY29wZSI6WyJvcGVuaWQiLCJwcm9maWxlIiwiZW1haWwiLCJhZGRyZXNzIiwicGhvbmUiLCJmbG93QXRNZUFwaSIsInN1cnZleUludGVybmFsQXBpIl0sImFtciI6WyJwd2QiXX0.OplErxe7UNpxnTxzP3vXbsoxZVp4V3nkP-ggEAa1WDvwvRhsU_3eelPFwsFpXfJHkHOWF1WluIH_p_DCtYEt3vFy06voSvaZ93G_ZzL6cIygCxzNjJhwElvdG4cLdm-nc25NKXboq6drKoJh9m1iW5ecQxG8FLILVuWkGIs2giLKGEMABDaZk86APiVY_Acy-M4dzYcc_vpfIIv0AUVOGMEN7TGBGCNVRC3HY66Q2tTDBhMA1dhzVsTL9z-DPaEUh6hKWB3hQ1mFR3nFogxFUO5G5UQWEkbkTOviZbbHiiZ1NJJJFqN7et8UZhJH908lk-4FtmH9G6Z6ZsRHfTnQyw"
                        
    val httpProtocol = http
    .baseUrl("https://api.inflow24.com/") // Here is the root for all relative URLs
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8") // Here are the common headers
    .doNotTrackHeader("1")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .acceptEncodingHeader("gzip")
    .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36")

    val scn = scenario("Scenario Name") // A scenario is a chain of requests and pauses
    .exec(
      http("request_1")
        .put("api/languageSkill/")
        .headers(sessionHeaders)
        .formParam("curriculumVitaeId": "ea287aea-9d4a-4ecf-87a1-409e74e49dd4")
        .formParam("id": "46")
        .formParam("language": "Something")
        .formParam("proficiency": "Elementary")
    )

    setUp(scn.inject(atOnceUsers(1)).protocols(httpProtocol))
}
