package computerdatabase

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class ScriptDemo  extends Simulation {
    val sessionHeaders = Map("Authorization" -> "Bearer  eyJhbGciOiJSUzI1NiIsImtpZCI6ImEzck1VZ01Gdjl0UGNsTGE2eUYzekFrZnF1RSIsInR5cCI6IkpXVCIsIng1dCI6ImEzck1VZ01Gdjl0UGNsTGE2eUYzekFrZnF1RSJ9.eyJuYmYiOjE1OTQ2OTc3MDQsImV4cCI6MTU5NDcwMTMwNCwiaXNzIjoiaHR0cHM6Ly9hY2NvdW50cy5pbmZsb3cyNC5jb20iLCJhdWQiOlsiaHR0cHM6Ly9hY2NvdW50cy5pbmZsb3cyNC5jb20vcmVzb3VyY2VzIiwic3VydmV5SW50ZXJuYWxBcGkiLCJmbG93QXRNZUFwaSJdLCJjbGllbnRfaWQiOiJZU0luRmxvdzI0Iiwic3ViIjoiZDQ1OWNiMzEtZTQ0Mi00ODA4LWEzNGYtZTU0ZTIxZDk2ODZmIiwiYXV0aF90aW1lIjoxNTk0NjM2Nzk1LCJpZHAiOiJsb2NhbCIsImdpdmVuX25hbWUiOiIxMjMiLCJmYW1pbHlfbmFtZSI6IlF1aXNvcyIsImdlbmRlciI6Ik1hbGUiLCJiaXJ0aGRhdGUiOiIxOTkwLTAxLTAxVDAwOjAwOjAwWiIsImFkZHJlc3MiOiJ7XCJjb3VudHJ5XCI6bnVsbCxcImNvdW50cnlOYW1lXCI6XCJWaWV0bmFtXCJ9IiwiZW1haWwiOiJ0b255NzEzMkBtYWlsaW5hdG9yLmNvbSIsInBob25lX251bWJlciI6IjEyMzQ1Njc4OSIsInJvbGUiOiJVc2VyIiwib3JnYW5pemF0aW9uIjoiW10iLCJoYXNQYXNzd29yZCI6InRydWUiLCJzY29wZSI6WyJvcGVuaWQiLCJwcm9maWxlIiwiYWRkcmVzcyIsImVtYWlsIiwicGhvbmUiLCJzdXJ2ZXlJbnRlcm5hbEFwaSIsImZsb3dBdE1lQXBpIiwib2ZmbGluZV9hY2Nlc3MiXSwiYW1yIjpbInB3ZCJdfQ.axfMid9IcbACAIJJZ_N9ssF8umRYyJ54OSXC_idU4o_o0g2EBAu2z14z0sjz81FjwTEECGFsrrdKH4Bhi4upCIcCJuka4Bwc2RhPIqptRzdRth13D7cz62r3jabHLRow-8ZKJX45gd1Kq__6CSn9tcL0KF6yM9FQvXUwDKJ-Y8akh7L-2ygHvcPX8XmuUiO-cQc2ktehUNtYbEJHzpy23IiROEZbQfwdGwMjJ-8YlbLHqq7HGMUmuNu9BmHOvUdcyLVRTIxyKFsCEySEKcALGL200ksOgUSZ34xv4HTcj_4cddsOxXCcECUKBSqbTYwxc3uwyBLg97seCo2bTfeKwQ",
                             "Content-Type" -> "application/json")
    val headerUploadFile = Map("Authorization" -> "Bearer  eyJhbGciOiJSUzI1NiIsImtpZCI6ImEzck1VZ01Gdjl0UGNsTGE2eUYzekFrZnF1RSIsInR5cCI6IkpXVCIsIng1dCI6ImEzck1VZ01Gdjl0UGNsTGE2eUYzekFrZnF1RSJ9.eyJuYmYiOjE1OTQ2OTc3MDQsImV4cCI6MTU5NDcwMTMwNCwiaXNzIjoiaHR0cHM6Ly9hY2NvdW50cy5pbmZsb3cyNC5jb20iLCJhdWQiOlsiaHR0cHM6Ly9hY2NvdW50cy5pbmZsb3cyNC5jb20vcmVzb3VyY2VzIiwic3VydmV5SW50ZXJuYWxBcGkiLCJmbG93QXRNZUFwaSJdLCJjbGllbnRfaWQiOiJZU0luRmxvdzI0Iiwic3ViIjoiZDQ1OWNiMzEtZTQ0Mi00ODA4LWEzNGYtZTU0ZTIxZDk2ODZmIiwiYXV0aF90aW1lIjoxNTk0NjM2Nzk1LCJpZHAiOiJsb2NhbCIsImdpdmVuX25hbWUiOiIxMjMiLCJmYW1pbHlfbmFtZSI6IlF1aXNvcyIsImdlbmRlciI6Ik1hbGUiLCJiaXJ0aGRhdGUiOiIxOTkwLTAxLTAxVDAwOjAwOjAwWiIsImFkZHJlc3MiOiJ7XCJjb3VudHJ5XCI6bnVsbCxcImNvdW50cnlOYW1lXCI6XCJWaWV0bmFtXCJ9IiwiZW1haWwiOiJ0b255NzEzMkBtYWlsaW5hdG9yLmNvbSIsInBob25lX251bWJlciI6IjEyMzQ1Njc4OSIsInJvbGUiOiJVc2VyIiwib3JnYW5pemF0aW9uIjoiW10iLCJoYXNQYXNzd29yZCI6InRydWUiLCJzY29wZSI6WyJvcGVuaWQiLCJwcm9maWxlIiwiYWRkcmVzcyIsImVtYWlsIiwicGhvbmUiLCJzdXJ2ZXlJbnRlcm5hbEFwaSIsImZsb3dBdE1lQXBpIiwib2ZmbGluZV9hY2Nlc3MiXSwiYW1yIjpbInB3ZCJdfQ.axfMid9IcbACAIJJZ_N9ssF8umRYyJ54OSXC_idU4o_o0g2EBAu2z14z0sjz81FjwTEECGFsrrdKH4Bhi4upCIcCJuka4Bwc2RhPIqptRzdRth13D7cz62r3jabHLRow-8ZKJX45gd1Kq__6CSn9tcL0KF6yM9FQvXUwDKJ-Y8akh7L-2ygHvcPX8XmuUiO-cQc2ktehUNtYbEJHzpy23IiROEZbQfwdGwMjJ-8YlbLHqq7HGMUmuNu9BmHOvUdcyLVRTIxyKFsCEySEKcALGL200ksOgUSZ34xv4HTcj_4cddsOxXCcECUKBSqbTYwxc3uwyBLg97seCo2bTfeKwQ",
                             "Content-Type" -> "multipart/form-data; boundary=----WebKitFormBoundarydU5BoO4sHIMK3ngT")
    object randomStringGenerator {
    def randomString(length: Int) = scala.util.Random.alphanumeric.filter(_.isLetter).take(length).mkString
    }

    val randomLanguageName = Iterator.continually(
    // Random number will be accessible in session under variable "OrderRef"
    Map("LanguageName" -> randomStringGenerator.randomString(10))
    )

    val httpProtocol = http
    .baseUrl("https://api.inflow24.com/") // Prod env
    //.baseUrl("https://api-profile-uat.bravotalents.com/") // UAT env
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8") // Here are the common headers
    .doNotTrackHeader("1")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .acceptEncodingHeader("gzip")
    .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36")

    val scn = scenario("Scenario Name") // A scenario is a chain of requests and pauses\
    .feed(randomLanguageName)
    .exec(
      http("get_surveys")
      .post("api/user-survey/me")
      .headers(sessionHeaders)
      .body(StringBody("""{
        "appContext":"ys", 
        }""")).asJson
    )
    .exec(
      http("uploadfile")
      .post("api/uploadfile")
      .headers(headerUploadFile)
      //.bodyPart(ByteArrayBodyPart("resouces", photoArray).fileName("image.png").contentType("image/png")).asMultipartForm
      .bodyPart(RawFileBodyPart("file", "resources/image.png").contentType("image/png").fileName("image.png")).asMultipartForm
      .check(jsonPath("$.name").saveAs("upload_name"))
      .check(jsonPath("$.uniqueName").saveAs("upload_uniqueName"))
      .check(jsonPath("$.url").saveAs("upload_URL"))
    )
    .exec(
      http("get_socialnetwork")
      .get("api/social-network/types")
      .headers(sessionHeaders)
    )
    .exec(
      http("get_userinfo")
      .get("https://accounts.inflow24.com/connect/userinfo")
      .headers(sessionHeaders)
    )
    .exec(
      http("get_country")
      .get("api/country")
      .headers(sessionHeaders)
      .check(jsonPath("$[247].id").saveAs("ID_CountryVN"))
    )
    .exec(
      http("get_CVS")
      .get("api/applicant/with-cvs")
      .headers(sessionHeaders)
      .check(jsonPath("$.cvList[0].id").saveAs("CV_ID"))
      .check(jsonPath("$.applicant.id").saveAs("profile_ID"))
    )
    
    .pause(3)
    .exec(
      http("update_profile")
      .put("api/applicant/")
      .headers(sessionHeaders)
      .body(StringBody("""{
        "birthday": "1998-10-01T00:00:00",
        "city": "TP HCM new",
        "countryId": "228ddc41-ffd7-472f-9663-96dc52662377",
        "countryName": "New Zealand",
        "cvAttachment": [],
        "email": "toantest@mailinator.com",
        "firstName": "Tony",
        "fullName": "I’m Rewidos Middle Name Quinoa salad",
        "gender": 0,
        "id": "${profile_ID}",
        "interestedCareers": [],
        "lastName": "Quinoa salad",
        "maritalStatus": 0,
        "middleName": "Middle Name",
        "mobile": "555555",
        "profileImage": [],
        "socialNetworkProfiles": [],
        "state": "Tan Binh",
        "streetAddress": "Cong Hoa",
        "summary": "This is for testing",
        "zipCode": "1234"
        }""")).asJson
    )
    // Add sections
    .exec(
      http("add_WorkExperience")
      .post("api/work-experience/")
      .headers(sessionHeaders)
      .body(StringBody("""{
      "address": "",
      "attachments": [],
      "company": "Company is added",
      "curriculumVitaeId": "${CV_ID}",
      "description": "Description is added",
      "fromMonth": null,
      "fromYear": "2015",
      "title": "Position is added",
      "toMonth": null,
      "toYear": "2018"
      }""")).asJson
    )
    .exec(
      http("add_Project")
      .post("api/project/")
      .headers(sessionHeaders)
      .body(StringBody("""{
      "attachments": [],
      "curriculumVitaeId": "${CV_ID}",
      "description": "Descriptions is added",
      "fromMonth": null,
      "fromYear": "2018",
      "name": "Project is added",
      "position": "Position is added",
      "toMonth": null,
      "toYear": "2020",
      }""")).asJson
    )
    .exec(
      http("add_Certification")
      .post("api/certification/")
      .headers(sessionHeaders)
      .body(StringBody("""{
      "attachments": [],
      "authority": "Authority is added",
      "curriculumVitaeId": "${CV_ID}",
      "description": "Descriptions is added",
      "fromMonth": "1",
      "fromYear": "2010",
      "licenseNumber": null,
      "name": "Certification is added",
      "toMonth": "12",
      "toYear": "2020"
      }""")).asJson
    )
    .exec(
      http("add_Skill")
      .post("api/skill/")
      .headers(sessionHeaders)
      .body(StringBody("""{
      "curriculumVitaeId": "${CV_ID}",
      "name": "Skill is added"
      }""")).asJson
    )
    
    .exec(
      http("add_Language")
      .post("api/languageSkill/")
      .headers(sessionHeaders)
      .body(StringBody("""{
        "curriculumVitaeId":"${CV_ID}", 
        "language": "${LanguageName}",
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
    .pause(5)
    .exec(
      http("get_CVS_IDSection")
      .get("api/applicant/with-cvs")
      .headers(sessionHeaders)
      .check(jsonPath("$.cvList[0].educationHistory[0].id").saveAs("ID_Education"))
      .check(jsonPath("$.cvList[0].languageSkills[0].id").saveAs("ID_Language"))
      .check(jsonPath("$.cvList[0].courses[0].id").saveAs("ID_Course"))
      .check(jsonPath("$.cvList[0].references[0].id").saveAs("ID_Reference"))
      .check(jsonPath("$.cvList[0].workHistory[0].id").saveAs("ID_WorkExperience"))
      .check(jsonPath("$.cvList[0].projects[0].id").saveAs("ID_Project"))
      .check(jsonPath("$.cvList[0].certificates[0].id").saveAs("ID_Certification"))
      .check(jsonPath("$.cvList[0].skills[0].id").saveAs("ID_Skill"))
    )
    // Update Sections
    .exec(
      http("update_WorkExperience")
      .put("api/work-experience/")
      .headers(sessionHeaders)
      .body(StringBody("""{
      "id": "${ID_WorkExperience}",
      "address": "",
      "attachments": [],
      "company": "Company is updated",
      "curriculumVitaeId": "${CV_ID}",
      "description": "Description is updated",
      "fromMonth": null,
      "fromYear": "2010",
      "title": "Position is updated",
      "toMonth": null,
      "toYear": "2020"
      }""")).asJson
    )
    .exec(
      http("update_Project")
      .put("api/project/")
      .headers(sessionHeaders)
      .body(StringBody("""{
      "id": "${ID_Project}",
      "attachments": [],
      "curriculumVitaeId": "${CV_ID}",
      "description": "Descriptions is updated",
      "fromMonth": null,
      "fromYear": "2002",
      "name": "Project is updated",
      "position": "Position is updated",
      "toMonth": null,
      "toYear": "2010",
      "url": ""
      }""")).asJson
    )
    .exec(
      http("update_Certfication")
      .put("api/certification/")
      .headers(sessionHeaders)
      .body(StringBody("""{
      "id": "${ID_Certification}",
      "attachments": [],
      "authority": "Authority is updated",
      "curriculumVitaeId": "${CV_ID}",
      "description": "Descriptions is updated",
      "fromMonth": "9",
      "fromYear": "2009",
      "licenseNumber": null,
      "name": "Certification is updated",
      "toMonth": "1",
      "toYear": "2012"
      }""")).asJson
    )
    .exec(
      http("update_Skill")
      .put("api/skill/")
      .headers(sessionHeaders)
      .body(StringBody("""{
      "id": "${ID_Skill}",
      "curriculumVitaeId": "${CV_ID}",
      "name": "Skill is updated"
      }""")).asJson
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
      "proficiency": "Elementary",
      "attachments": [{
        "name": "${upload_name}",
        "uniqueName": "${upload_uniqueName}",
        "url": "https://api-profile-uat.bravotalents.com/api/candidates-b2b/get-attachment/${ID_Language}"
      }]
      }""")).asJson
    )
    .exec(
      http("update_Course")
      .put("api/course/")
      .headers(sessionHeaders)
      .body(StringBody("""{
      "attachments": [],
      "comment": "This is testing",
      "curriculumVitaeId": "${CV_ID}",
      "fromMonth": "2",
      "fromYear": "2018",
      "id": "${ID_Course}",
      "name": "Gatling gun updated",
      "toMonth": "2",
      "toYear": "2020"
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
    .pause(3)
    .exec(
      http("delete_Education")
      .delete("api/education/${ID_Education}")
      .headers(sessionHeaders)
    )
    // Delete sections
    .exec(
      http("delete_WorkExperience")
      .delete("api/work-experience/${ID_WorkExperience}")
      .headers(sessionHeaders)
    )
    .exec(
      http("delete_Project")
      .delete("api/project/${ID_Project}")
      .headers(sessionHeaders)
    )
    .exec(
      http("delete_Certification")
      .delete("api/certification/${ID_Certification}")
      .headers(sessionHeaders)
    )
    .exec(
      http("delete_Skill")
      .delete("api/skill/${ID_Skill}")
      .headers(sessionHeaders)
    )
    /*
    .exec(
      http("delete_Language")
      .delete("api/languageSkill/${ID_Language}")
      .headers(sessionHeaders)
    )*/
    .exec(
      http("delete_Course")
      .delete("api/course/${ID_Course}")
      .headers(sessionHeaders)
    )
    .exec(
      http("delete_Reference")
      .delete("api/reference/${ID_Reference}")
      .headers(sessionHeaders)
    )      

    setUp(scn.inject(atOnceUsers(1)).protocols(httpProtocol))
}
