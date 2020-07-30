package computerdatabase

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._
class CommonAction extends Simulation {
  object Login {
        val login = exec(
            http("Acess to YS application")
            .get("https://yskarriereveiviser.inflow24.com/")
        )
        .exec(
        http("Get Authorize")
        .get("https://accounts.inflow24.com/connect/authorize?response_type=code&client_id=YSInFlow24&state=WGdWRWVxQ0dqZVB3R0k1YTNkdXp0V2k1V1pKSEt2LnR6TjJNa3JXRHF3czZl&redirect_uri=https%3A%2F%2Fyskarriereveiviser.inflow24.com%2Flogin&scope=openid%20offline_access%20profile%20email%20address%20phone%20flowAtMeApi%20surveyInternalApi&code_challenge=E2f5TOyp5ufEuucKbzQ2fc-qqoymJyGrrVFHR1dJ30Y&code_challenge_method=S256&nonce=WGdWRWVxQ0dqZVB3R0k1YTNkdXp0V2k1V1pKSEt2LnR6TjJNa3JXRHF3czZl&ui_locales=en-US")
        )
        .exec(
        http("Get Login Page")
        .get("https://accounts.inflow24.com/account/login?returnUrl=%2Fconnect%2Fauthorize%2Fcallback%3Fresponse_type%3Dcode%26client_id%3DYSInFlow24%26state%3DWGdWRWVxQ0dqZVB3R0k1YTNkdXp0V2k1V1pKSEt2LnR6TjJNa3JXRHF3czZl%26redirect_uri%3Dhttps%253A%252F%252Fyskarriereveiviser.inflow24.com%252Flogin%26scope%3Dopenid%2520offline_access%2520profile%2520email%2520address%2520phone%2520flowAtMeApi%2520surveyInternalApi%26code_challenge%3DE2f5TOyp5ufEuucKbzQ2fc-qqoymJyGrrVFHR1dJ30Y%26code_challenge_method%3DS256%26nonce%3DWGdWRWVxQ0dqZVB3R0k1YTNkdXp0V2k1V1pKSEt2LnR6TjJNa3JXRHF3czZl%26ui_locales%3Den-US")
        .check(css("input[name='__RequestVerificationToken']","value").find.saveAs("RequestVerificationToken"))
        )
        .exec(
        http("Login into the application")
        .post("https://accounts.inflow24.com/Account/Login?returnurl=%2Fconnect%2Fauthorize%2Fcallback%3Fresponse_type%3Dcode%26client_id%3DYSInFlow24%26state%3DWGdWRWVxQ0dqZVB3R0k1YTNkdXp0V2k1V1pKSEt2LnR6TjJNa3JXRHF3czZl%26redirect_uri%3Dhttps%253A%252F%252Fyskarriereveiviser.inflow24.com%252Flogin%26scope%3Dopenid%2520offline_access%2520profile%2520email%2520address%2520phone%2520flowAtMeApi%2520surveyInternalApi%26code_challenge%3DE2f5TOyp5ufEuucKbzQ2fc-qqoymJyGrrVFHR1dJ30Y%26code_challenge_method%3DS256%26nonce%3DWGdWRWVxQ0dqZVB3R0k1YTNkdXp0V2k1V1pKSEt2LnR6TjJNa3JXRHF3czZl%26ui_locales%3Den-US")
        .header("content-type", "application/x-www-form-urlencoded")
        .formParam("Email", "${Email}")
        .formParam("Password", "${Password}")
        .formParam("__RequestVerificationToken", "${RequestVerificationToken}")
        .formParam("RememberMe", "false")
        .check(status.is(302).saveAs("RCPostReturnUrl"))
        .disableFollowRedirect
        )
        .exec(
            http("Call back after logging in")
            .get("https://accounts.inflow24.com/connect/authorize/callback?response_type=code&client_id=YSInFlow24&state=WGdWRWVxQ0dqZVB3R0k1YTNkdXp0V2k1V1pKSEt2LnR6TjJNa3JXRHF3czZl&redirect_uri=https%3A%2F%2Fyskarriereveiviser.inflow24.com%2Flogin&scope=openid%20offline_access%20profile%20email%20address%20phone%20flowAtMeApi%20surveyInternalApi&code_challenge=E2f5TOyp5ufEuucKbzQ2fc-qqoymJyGrrVFHR1dJ30Y&code_challenge_method=S256&nonce=WGdWRWVxQ0dqZVB3R0k1YTNkdXp0V2k1V1pKSEt2LnR6TjJNa3JXRHF3czZl&ui_locales=en-US")  
            .check(headerRegex("Location","code=([^&]*)").saveAs("codeLogin"))
            .check(headerRegex("Location","state=([^&]*)").saveAs("stateLogin"))
            .check(headerRegex("Location","session_state=([^&]*)").saveAs("sessionStateLogin"))
            .check(status.is(302).saveAs("CallbackUrl"))
            .disableFollowRedirect
            )
        .exec(
            http("Login with login code and state")
            .get("https://yskarriereveiviser.inflow24.com/login?code=${codeLogin}&scope=openid%20profile%20email%20address%20phone%20flowAtMeApi%20surveyInternalApi%20offline_access&state=${stateLogin}&session_state=${sessionStateLogin}")
            )
        .exec(
            http("Call token to get access token")
            .post("https://accounts.inflow24.com/connect/token")
            .header("Content-Type", "application/x-www-form-urlencoded")
            .formParam("grant_type", "authorization_code")
            .formParam("code", "${codeLogin}")
            .formParam("redirect_uri", "https://yskarriereveiviser.inflow24.com/login")
            .formParam("code_verifier", "ckpnQXZXcFRCZFVBb25UbGFUQ2I0TU9SbFNwcnFYanNlUlNUSkZZbkJ6Nl9L")
            .formParam("client_id", "YSInFlow24")
            .check(jsonPath("$.id_token").saveAs("id_token"))
            .check(jsonPath("$.access_token").saveAs("access_token"))
        )
    }
    val sessionHeaders = Map("Authorization" -> "Bearer  ${access_token}",
                             "Content-Type" -> "application/json", "Customer-Id" -> "2")
    val sessionHeadersWithoutCustomerID = Map("Authorization" -> "Bearer  ${access_token}")
    val jsonHeader = Map("Content-Type" -> "application/json")
    val formParamHeader = Map("Content-Type" -> "application/x-www-form-urlencoded")
    val headerUploadFile = Map("Authorization" -> "Bearer  ${access_token}",
                             "Content-Type" -> "multipart/form-data; boundary=----WebKitFormBoundarydU5BoO4sHIMK3ngT", "Customer-Id" -> "2")
    object DashboardPage{
        val navigateDashboard = exec(
            http("Navigate to Dashboard page")
            .get("https://yskarriereveiviser.inflow24.com/app/employee/dashboard?clientId=YSInFlow24&ui_locales=en-US")
        )
        .exec(
            http("Get content context")
            .get("https://api.inflow24.com/api/content/")
            .headers(sessionHeaders)
        )
        .exec(
            http("Post YS context")
            .post("https://api.inflow24.com/api/applicant/refreshness-with-cv/ys")
            .headers(sessionHeaders)
            .header("Accept", "application/json")
            .header("Content-Type", "application/json")
            .header("Referer", "https://yskarriereveiviser.inflow24.com/app/employee/dashboard?clientId=YSInFlow24&ui_locales=en-US")
        )
        .exec(
            http("Get data of me")
            .post("https://api.inflow24.com/api/user-survey/me")
            .headers(sessionHeaders)
            .header("Accept", "application/json")
            .header("Content-Type", "application/json")
            .header("Referer", "https://yskarriereveiviser.inflow24.com/app/employee/dashboard?clientId=YSInFlow24&ui_locales=en-US")
            .body(StringBody("""{
            "appContext": "ys"
            }""")).asJson
            .check(jsonPath("$.userId").saveAs("userId"))
        )
        .exec(
            http("Get data of userinfo")
            .get("https://accounts.inflow24.com/connect/userinfo")
            .headers(sessionHeaders)
            .header("Referer", "https://yskarriereveiviser.inflow24.com/app/employee/dashboard?clientId=YSInFlow24&ui_locales=en-US")
        )
        .exec(
            http("get_CVS")
            .get("https://api.inflow24.com/api/applicant/with-cvs")
            .headers(sessionHeaders)
            .check(jsonPath("$.cvList[0].id").saveAs("CV_ID"))
            .check(jsonPath("$.applicant.id").saveAs("profile_ID"))
        )      
    }
    object UploadFile{
        val uploadFile = exec(
            http("upload_attachmentCertification")
            .post("https://api.inflow24.com/api/uploadfile")
            .headers(headerUploadFile)
            .bodyPart(RawFileBodyPart("file", "resources/image.png").contentType("image/png").fileName("image.png")).asMultipartForm
            .check(jsonPath("$.name").saveAs("upload_nameAttachment"))
            .check(jsonPath("$.uniqueName").saveAs("upload_uniqueNameAttachment"))
            .check(jsonPath("$.url").saveAs("upload_urlAttachment"))
        )
    }
    object  Get_CSV_IDS_Section{
        val GetCVSIDSection = exec(
            http("get_CVS_IDSection")
            .get("https://api.inflow24.com/api/applicant/with-cvs")
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
    }
    object Logout{
        val logout = exec(
            http("get logout ID")
            .get("https://accounts.inflow24.com/connect/endsession?ui_locales=en-US&id_token_hint=${id_token}&post_logout_redirect_uri=https://yskarriereveiviser.inflow24.com/login")
            .check(headerRegex("Location","logoutId=([^&]*)").saveAs("codeLogout"))
            .check(status.is(302).saveAs("PostLogout"))
            .disableFollowRedirect
        )
        .exec(
            http("logout with logout ID")
            .get("https://accounts.inflow24.com/account/logout?logoutId=${codeLogout}")
        )
    }
}
