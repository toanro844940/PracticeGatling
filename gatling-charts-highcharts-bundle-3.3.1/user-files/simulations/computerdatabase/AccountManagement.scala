package computerdatabase

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._
import computerdatabase.CommonAction

class AccountManagement extends Simulation {

    val CommonInstance = new CommonAction()
    val sessionHeaders = Map("Authorization" -> "Bearer  ${access_token}",
                             "Content-Type" -> "application/json", "Customer-Id" -> "2")
    val sessionHeadersWithoutCustomerID = Map("Authorization" -> "Bearer  ${access_token}")
    val jsonHeader = Map("Content-Type" -> "application/json")
    val formParamHeader = Map("Content-Type" -> "application/x-www-form-urlencoded")
    val headerUploadFile = Map("Authorization" -> "Bearer  ${access_token}",
                             "Content-Type" -> "multipart/form-data; boundary=----WebKitFormBoundarydU5BoO4sHIMK3ngT", "Customer-Id" -> "2")

    object EditProfile{
        val editProfile = exec(
        http("Navigate to Edit Profle page")
        .get("https://accounts.inflow24.com/manage?returnUrl=https%3A%2F%2Fyskarriereveiviser.inflow24.com%2Fapp%2Femployee%2Fdashboard%3FclientId%3DYSInFlow24%26ui_locales%3Den-US&clientId=YSInFlow24&afterDeleteUrl=https%3A%2F%2Fyskarriereveiviser.inflow24.com%2Flogin%3FclientId%3DYSInFlow24%26deleted%3Dtrue")
        .check(css("input[name='__RequestVerificationToken']","value").find.saveAs("RequestVerificationToken_EP"))   
        )
        .exec(
        http("Edit profile of the user")
        .post("https://accounts.inflow24.com/connect/manage/index?returnUrl=https%3A%2F%2Fyskarriereveiviser.inflow24.com%2Fapp%2Femployee%2Fdashboard%3FclientId%3DYSInFlow24%26ui_locales%3Den-US&clientId=FlowAtMe")
        .header("content-type", "application/x-www-form-urlencoded")
        .formParam("UserId", "${userId}")
        .formParam("Roles[0]", "User")
        .formParam("IsEmailConfirmed", "True")
        .formParam("Status", "True")
        .formParam("ReturnUrl", "https://yskarriereveiviser.inflow24.com/app/employee/dashboard?clientId=YSInFlow24&ui_locales=en-US")
        .formParam("Status", "True")
        .formParam("StatusMessage", "")
        .formParam("Email", "toantest@mailinator.com")
        .formParam("Username", "toantest@mailinator.com")
        .formParam("FirstName", "Tony")
        .formParam("MiddleName", "Tan")
        .formParam("LastName", "Van")
        .formParam("DateOfBirth", "2004-01-01")
        .formParam("CountryCallingCode", "+84")
        .formParam("PhoneNumber", "123456789")
        .formParam("Gender", "0")
        .formParam("Country", "New Zealand")
        .formParam("__RequestVerificationToken", "${RequestVerificationToken_EP}")
        .check(status.is(302).saveAs("Edit Profile"))
        .disableFollowRedirect
        )
    }

    object ChangePassword{
        val changePassword = exec(
        http("Navigate to change password page")
        .get("https://accounts.inflow24.com/manage/ChangePassword?returnUrl=https%3A%2F%2Fyskarriereveiviser.inflow24.com%2Fapp%2Femployee%2Fdashboard%3FclientId%3DYSInFlow24%26ui_locales%3Den-US&clientId=YSInFlow24")
        .headers(sessionHeaders)
        .check(css("input[name='__RequestVerificationToken']","value").find.saveAs("RequestVerificationToken_PWD"))      
        )
        .exec(
            http("change password for the account")
            .post("https://accounts.inflow24.com/Manage/ChangePassword?returnUrl=https%3A%2F%2Fyskarriereveiviser.inflow24.com%2Fapp%2Femployee%2Fdashboard%3FclientId%3DYSInFlow24%26ui_locales%3Den-US&clientId=YSInFlow24")
            .headers(sessionHeadersWithoutCustomerID)
            .headers(formParamHeader)
            .formParam("OldPassword", "Toan844940!")
            .formParam("NewPassword", "Toan844940!")
            .formParam("ConfirmPassword", "Toan844940!")
            .formParam("__RequestVerificationToken", "${RequestVerificationToken_PWD}")
        )
    }
    
    val httpProtocol = http
    .baseUrl("https://accounts.inflow24.com/") // Prod env
    //.baseUrl("https://api-profile-uat.bravotalents.com/") // UAT env
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8") // Here are the common headers
    .doNotTrackHeader("1")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .acceptEncodingHeader("gzip")
    .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36")

    val edit_profile = scenario("Edit Profile").exec(CommonInstance.Login.login, CommonInstance.DashboardPage.navigateDashboard, EditProfile.editProfile, CommonInstance.DashboardPage.navigateDashboard)
    val change_password = scenario("Change Password").exec(CommonInstance.Login.login, CommonInstance.DashboardPage.navigateDashboard, ChangePassword.changePassword, CommonInstance.DashboardPage.navigateDashboard)
    setUp(
        edit_profile.inject(atOnceUsers(1)),
        change_password.inject(atOnceUsers(1))
    ).protocols(httpProtocol)
}
