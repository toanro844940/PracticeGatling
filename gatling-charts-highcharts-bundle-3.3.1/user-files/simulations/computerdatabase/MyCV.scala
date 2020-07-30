package computerdatabase

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._
import computerdatabase.CommonAction

class MyCV extends Simulation {

   object randomStringGenerator {
    def randomString(length: Int) = scala.util.Random.alphanumeric.filter(_.isLetter).take(length).mkString
    }


    val sessionHeaders = Map("Authorization" -> "Bearer  ${access_token}",
                             "Content-Type" -> "application/json", "Customer-Id" -> "2")
    val sessionHeadersWithoutCustomerID = Map("Authorization" -> "Bearer  ${access_token}")
    val jsonHeader = Map("Content-Type" -> "application/json")
    val formParamHeader = Map("Content-Type" -> "application/x-www-form-urlencoded")
    val headerUploadFile = Map("Authorization" -> "Bearer  ${access_token}",
                             "Content-Type" -> "multipart/form-data; boundary=----WebKitFormBoundarydU5BoO4sHIMK3ngT", "Customer-Id" -> "2")
    val randomLanguageName = Iterator.continually(Map("LanguageName" -> randomStringGenerator.randomString(10)))
    val randomSkillName = Iterator.continually(Map("SkillName" -> randomStringGenerator.randomString(10)))
    val feedUser = csv("user.csv").circular
    
   object AddSection{       
    val WorkExp = exec(
      http("add_WorkExperience")
      .post("https://api.inflow24.com/api/work-experience/")
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
    
    val Project = exec(
      http("add_Project")
      .post("https://api.inflow24.com/api/project/")
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
    .pause(3)

    val Certification = exec(
      http("add_Certification")
      .post("https://api.inflow24.com/api/certification/")
      .headers(sessionHeaders)
      .body(StringBody("""{
      "attachments": [
        {
          "name": "${upload_nameAttachment}",
          "uniqueName": "${upload_uniqueNameAttachment}",
          "url": ""
        }
      ],
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

    val Skill = exec(
      http("add_Skill")    
      .post("https://api.inflow24.com/api/skill/")     
      .headers(sessionHeaders)
      .body(StringBody("""{
      "curriculumVitaeId": "${CV_ID}",
      "name": "${SkillName}"
      }""")).asJson
    )
    
    val Language = exec(
      http("add_Language")
      .post("https://api.inflow24.com/api/languageSkill/")
      .headers(sessionHeaders)
      .body(StringBody("""{
        "curriculumVitaeId":"${CV_ID}", 
        "language": "${LanguageName}",
        "proficiency": "Beginner"
        }""")).asJson
    )

    val Education = exec(
      http("add_Education")
      .post("https://api.inflow24.com/api/education/")
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
      "school": "FPT Polytechnic",
      "studyField": "",
      "studyMajorSubject": "Information Technology",
      "toMonth": "2",
      "toYear": "2020"
      }""")).asJson
    )

    val Course = exec(
      http("add_Course")
      .post("https://api.inflow24.com/api/course/")
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

    val Reference = exec(
      http("add_Reference")
      .post("https://api.inflow24.com/api/reference/")
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
  }
  

  object UpdateSection{
    val WorkExp = exec(
      http("update_WorkExperience")
      .put("https://api.inflow24.com/api/work-experience/")
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

    val Project = exec(
      http("update_Project")
      .put("https://api.inflow24.com/api/project/")
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
    val Certification = exec(
      http("update_Certfication")
      .put("https://api.inflow24.com/api/certification/")
      .headers(sessionHeaders)
      .body(StringBody("""{
      "id": "${ID_Certification}",
       "attachments": [
        {
          "name": "${upload_nameAttachment}",
          "uniqueName": "${upload_uniqueNameAttachment}",
          "url": ""
        }
      ],
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

    val Skill = exec(
      http("update_Skill")
      .put("https://api.inflow24.com/api/skill/")
      .headers(sessionHeaders)
      .body(StringBody("""{
      "id": "${ID_Skill}",
      "curriculumVitaeId": "${CV_ID}",
      "name": "${SkillName} is updated"
      }""")).asJson
    )

    val Education = exec(
      http("update_Education")
      .put("https://api.inflow24.com/api/education/")
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
      "school": "FPT Polytechnic updated",
      "studyField": "",
      "studyMajorSubject": "Information Technology",
      "toMonth": "2",
      "toYear": "2020"
      }""")).asJson
    )

    val Language = exec(
      http("update_Language")
      .put("https://api.inflow24.com/api/languageSkill/")
      .headers(sessionHeaders)
      .body(StringBody("""{
      "curriculumVitaeId":"${CV_ID}", 
      "id": "${ID_Language}", 
      "language": "${LanguageName} is updated",
      "proficiency": "Elementary",
      }""")).asJson
    )

    val Course = exec(
      http("update_Course")
      .put("https://api.inflow24.com/api/course/")
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

    val Reference = exec(
      http("update_Reference")
      .put("https://api.inflow24.com/api/reference/")
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
    .pause(5)
  }

  object DeleteSection{
    val WorkExp = exec(
      http("delete_WorkExperience")
      .delete("https://api.inflow24.com/api/work-experience/${ID_WorkExperience}")
      .headers(sessionHeaders)
    )
    val Project = exec(
      http("delete_Project")
      .delete("https://api.inflow24.com/api/project/${ID_Project}")
      .headers(sessionHeaders)
    )    
    val Certification = exec(
      http("delete_Certification")
      .delete("https://api.inflow24.com/api/certification/${ID_Certification}")
      .headers(sessionHeaders)
    )
    val Skill = exec(
      http("delete_Skill")
      .delete("https://api.inflow24.com/api/skill/${ID_Skill}")
      .headers(sessionHeaders)
    )   
    val Language = exec(
      http("delete_Language")
      .delete("https://api.inflow24.com/api/languageSkill/${ID_Language}")
      .headers(sessionHeaders)
    )
    val Education = exec(
      http("delete_Education")
      .delete("https://api.inflow24.com/api/education/${ID_Education}")
      .headers(sessionHeaders)
    )
    val Course = exec(
      http("delete_Course")
      .delete("https://api.inflow24.com/api/course/${ID_Course}")
      .headers(sessionHeaders)
    )
    val Reference = exec(
      http("delete_Reference")
      .delete("https://api.inflow24.com/api/reference/${ID_Reference}")
      .headers(sessionHeaders)
    )      

  }

  val CommonInstance = new CommonAction()

  val httpProtocol = http
    .baseUrl("https://api.inflow24.com/") // Prod env
    //.baseUrl("https://api-profile-uat.bravotalents.com/") // UAT env
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8") // Here are the common headers
    .doNotTrackHeader("1")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .acceptEncodingHeader("gzip")
    .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36")

    val addSections = 
                      scenario("Add sections in CV")
                      .feed(feedUser)
                      .feed(randomLanguageName)
                      .feed(randomSkillName)  
                      .repeat(2){
                      exec(
                      CommonInstance.Login.login, 
                      CommonInstance.DashboardPage.navigateDashboard, 
                      AddSection.WorkExp,
                      AddSection.Project,
                      CommonInstance.UploadFile.uploadFile,
                      AddSection.Certification,
                      AddSection.Skill,
                      AddSection.Language,
                      AddSection.Education,
                      AddSection.Course,
                      AddSection.Reference,
                      CommonInstance.Logout.logout)
                      }
                      
    val updateSections =  
                      repeat(2){
                      scenario("Update sections in CV")
                      .feed(feedUser)
                      .feed(randomLanguageName)
                      .feed(randomSkillName)
                      exec(
                      CommonInstance.Login.login, 
                      CommonInstance.DashboardPage.navigateDashboard, 
                      CommonInstance.Get_CSV_IDS_Section.GetCVSIDSection,
                      UpdateSection.WorkExp,
                      UpdateSection.Project,
                      CommonInstance.UploadFile.uploadFile,
                      UpdateSection.Certification,
                      UpdateSection.Skill,
                      UpdateSection.Language,
                      UpdateSection.Education,
                      UpdateSection.Course,
                      UpdateSection.Reference,
                      CommonInstance.Logout.logout)
                      }   
                                          
    val deleteSections = 
                      scenario ("Delete sections in CV")
                      .feed(feedUser)
                      .repeat(2){
                      exec(
                      CommonInstance.Login.login, 
                      CommonInstance.DashboardPage.navigateDashboard, 
                      CommonInstance.Get_CSV_IDS_Section.GetCVSIDSection,
                      DeleteSection.WorkExp,
                      DeleteSection.Project,
                      DeleteSection.Certification,
                      DeleteSection.Skill,
                      DeleteSection.Language,
                      DeleteSection.Education,
                      DeleteSection.Course,
                      DeleteSection.Reference,
                      CommonInstance.Logout.logout)
                      }   

    setUp(
      addSections.inject(rampUsers(2) during(10 seconds)
      //updateSections.inject(nothingFor(15.seconds),atOnceUsers(1)),
     // deleteSections.inject(nothingFor(30.seconds),atOnceUsers(1))
    ).protocols(httpProtocol)
)
}
