package computerdatabase

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class ScriptDemo  extends Simulation {
    val sessionHeaders = Map("Authorization" -> "Bearer eyJhbGciOiJSUzI1NiIsImtpZCI6ImEzck1VZ01Gdjl0UGNsTGE2eUYzekFrZnF1RSIsInR5cCI6IkpXVCIsIng1dCI6ImEzck1VZ01Gdjl0UGNsTGE2eUYzekFrZnF1RSJ9.eyJuYmYiOjE1OTQyODEyNzMsImV4cCI6MTU5NDI4NDg3MywiaXNzIjoiaHR0cHM6Ly9hY2NvdW50cy5pbmZsb3cyNC5jb20iLCJhdWQiOlsiaHR0cHM6Ly9hY2NvdW50cy5pbmZsb3cyNC5jb20vcmVzb3VyY2VzIiwic3VydmV5SW50ZXJuYWxBcGkiLCJmbG93QXRNZUFwaSJdLCJjbGllbnRfaWQiOiJZU0luRmxvdzI0Iiwic3ViIjoiZDRhMGQyZmItNWEyYi00ZWE3LTk1NmQtMDI5YWMwM2ZlNWNlIiwiYXV0aF90aW1lIjoxNTk0MjAwNTYxLCJpZHAiOiJsb2NhbCIsImdpdmVuX25hbWUiOiJJ4oCZbSBSZXdpZG9zIiwiZmFtaWx5X25hbWUiOiJRdWlub2Egc2FsYWQiLCJnZW5kZXIiOiJNYWxlIiwiYmlydGhkYXRlIjoiMjAwNC0wMS0wMVQwMDowMDowMFoiLCJhZGRyZXNzIjoie1wiY291bnRyeVwiOm51bGwsXCJjb3VudHJ5TmFtZVwiOlwiTmV3IFplYWxhbmRcIn0iLCJlbWFpbCI6InRvYW50ZXN0QG1haWxpbmF0b3IuY29tIiwicGhvbmVfbnVtYmVyIjoiNTU1NTU1Iiwicm9sZSI6IlVzZXIiLCJvcmdhbml6YXRpb24iOiJbXSIsImhhc1Bhc3N3b3JkIjoidHJ1ZSIsInNjb3BlIjpbIm9wZW5pZCIsInByb2ZpbGUiLCJhZGRyZXNzIiwiZW1haWwiLCJwaG9uZSIsInN1cnZleUludGVybmFsQXBpIiwiZmxvd0F0TWVBcGkiLCJvZmZsaW5lX2FjY2VzcyJdLCJhbXIiOlsicHdkIl19.qiZb8V0ZMGQMaTwmsK6yceJBvVnMnupsy8wkY0-B7e12lt9dtLKAnB2NwD5R67uwwrlNLnLps1dSzMfHeCjEDg8VFLg9W7q-MYm6sJNbQWR85WV0ZuWFu6ISH_jjvID3oQ4Fu-nn0jdxhJ3j2zux0Q-7Hj1viBbBI5_cFGSL8yu5dJGcZCUaUotOL9PannRG8iRB5fhljrLguRvPerIa-VCbzsJw-90n1b4k52YkTsYEPviQ-FFsrPkCIO4MIztji3UXj4smqePfYMCEYs_se5GMFv2Yc7dOQNOnILV2EgNpcahQ7PZM_jDD8wmkxFrWRf7YdOcFQIQpVwaZW1jfkg",
                             "Content-Type" -> "application/json")
                       
    val httpProtocol = http
    .baseUrl("https://api.inflow24.com/") // Here is the root for all relative URLs
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8") // Here are the common headers
    .doNotTrackHeader("1")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .acceptEncodingHeader("gzip")
    .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36")

    val scn = scenario("Scenario Name") // A scenario is a chain of requests and pauses
    .exec(
      http("get_CVS")
      .get("api/applicant/with-cvs")
      .headers(sessionHeaders)
      .check(jsonPath("$.cvList[0].id").saveAs("CV_ID"))
    )
    .pause(3)
    .exec(
      http("add_Language")
      .post("api/languageSkill/")
      .headers(sessionHeaders)
      .body(StringBody("""{
        "curriculumVitaeId":"${CV_ID}", 
        "language": "Gatling gun 3",
        "proficiency": "Beginner"
        }""")).asJson
    )
    .exec(
      http("add_Education")
      .post("api/education/")
      .headers(sessionHeaders)
      .body(StringBody("""{
      "attachments": [],
      "countryId": "",
      "curriculumVitaeId": "${CV_ID}",
      "degree": "lowerDegree",
      "description": "This is description",
      "fromMonth": "2",
      "fromYear": "2018",
      "grade": "",
      "school": "FPT Polytechnic 1",
      "studyField": "",
      "studyMajorSubject": "Information Technology",
      "toMonth": "2",
      "toYear": "2020"
      }""")).asJson
    )
    .exec(
      http("add_Course")
      .post("api/course/")
      .headers(sessionHeaders)
      .body(StringBody("""{
      "attachments": [],
      "comment": "This is testing",
      "curriculumVitaeId": "${CV_ID}",
      "fromMonth": "2",
      "fromYear": "2018",
      "name": "Gatling gun",
      "toMonth": "2",
      "toYear": "2020"
      }""")).asJson
    )
    .exec(
      http("add_Reference")
      .post("api/reference/")
      .headers(sessionHeaders)
      .body(StringBody("""{
      "attachments": [],
      "curriculumVitaeId": "${CV_ID}",
      "email": "tony@mailinator.com",
      "name": "Tony van",
      "phone": "0123456789",
      "relationship": "My boss"
      }""")).asJson
    )
    .exec(
      http("get_CVS_IDSection")
      .get("api/applicant/with-cvs")
      .headers(sessionHeaders)
      .check(jsonPath("$.cvList[0].educationHistory[0].id").saveAs("ID_Education"))
      .check(jsonPath("$.cvList[0].languageSkills[0].id").saveAs("ID_Language"))
      .check(jsonPath("$.cvList[0].references[0].id").saveAs("ID_Reference"))
    )
    .exec(
      http("update_Education")
      .put("api/education/")
      .headers(sessionHeaders)
      .body(StringBody("""{
      "attachments": [],
      "countryId": "",
      "curriculumVitaeId": "${CV_ID}",
      "degree": "lowerDegree",
      "description": "This is description",
      "fromMonth": "2",
      "fromYear": "2018",
      "grade": "",
      "id": "${ID_Education}",
      "school": "FPT Polytechnic 1",
      "studyField": "",
      "studyMajorSubject": "Information Technology",
      "toMonth": "2",
      "toYear": "2020"
      }""")).asJson
    )
    .exec(
      http("update_Language")
      .put("api/languageSkill/")
      .headers(sessionHeaders)
      .body(StringBody("""{
      "curriculumVitaeId":"${CV_ID}", 
      "id": "${ID_Language}", 
      "language": "Something updated 2",
      "proficiency": "Elementary"
      }""")).asJson
    )
    .exec(
      http("update_Reference")
      .put("api/reference/")
      .headers(sessionHeaders)
      .body(StringBody("""{
      "attachments": [],
      "curriculumVitaeId": "${CV_ID}",
      "email": "tony@mailinator.com",
      "id": "${ID_Reference}",
      "name": "Tony van updated",
      "phone": "0123456789",
      "relationship": "My boss"
      }""")).asJson
    )
    .exec(
      http("delete_Education")
      .delete("api/education/${ID_Education}")
      .headers(sessionHeaders)
    )
    .exec(
      http("delete_Language")
      .delete("api/language/${ID_Language}")
      .headers(sessionHeaders)
    )
    .exec(
      http("delete_Reference")
      .delete("api/reference/${ID_Reference}")
      .headers(sessionHeaders)
    )
      
      
      
    setUp(scn.inject(atOnceUsers(1)).protocols(httpProtocol))
}
