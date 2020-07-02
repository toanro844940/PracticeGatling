package computerdatabase

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class LoginScripts extends Simulation {
  
    val httpProtocol = http
    .baseUrl("https://accounts-uat.bravosurveys.com/") // Here is the root for all relative URLs
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8") // Here are the common headers
    .doNotTrackHeader("1")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .acceptEncodingHeader("gzip")
    .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36")

    val scn = scenario("Scenario name")
    .exec(
        http("request_1")
        .get("connect/authorize?response_type=code&client_id=FlowAtMe&state=123456&redirect_uri=https%3A%2F%2Finflow24-uat.bravotalents.com%2Flogin&scope=openid%20offline_access%20profile%20email%20address%20phone%20flowAtMeApi%20surveyInternalApi&code_challenge=7pvm2PldgMYIrKx8-AhwRC9AFhH_nqYDQE_aYKoSl20&code_challenge_method=S256&nonce=123456&ui_locales=en-US")       
    )
    .pause(1)
    .exec(
        http("request_2")
        .get("account/login?returnUrl=%2Fconnect%2Fauthorize%2Fcallback%3Fresponse_type%3Dcode%26client_id%3DFlowAtMe%26state%3D123456%26redirect_uri%3Dhttps%253A%252F%252Finflow24-uat.bravotalents.com%252Flogin%26scope%3Dopenid%2520offline_access%2520profile%2520email%2520address%2520phone%2520flowAtMeApi%2520surveyInternalApi%26code_challenge%3D7pvm2PldgMYIrKx8-AhwRC9AFhH_nqYDQE_aYKoSl20%26code_challenge_method%3DS256%26")
        .check(css("input[name='__RequestVerificationToken']","value").find.saveAs("LoginToken"))
        )
    .exec(session => {
        println(session("LoginToken").as[String])
        session
    })
    .exec(
        http("login_request_1")
        .post("Account/Login?returnurl=%2Fconnect%2Fauthorize%2Fcallback%3Fresponse_type%3Dcode%26client_id%3DFlowAtMe%26state%3D123456%26code_challenge_method%3DS256%26nonce%3D123456%26ui_locales%3Dnb-NO")
        .body(StringBody("""{
        "Email":"toantest@mailinator.com", 
        "Password": "Toan844940!", 
        "__RequestVerificationToken": "${LoginToken}",
        "RememberMe": "false"
        }""")).asJson       
        ) 
    setUp(scn.inject(atOnceUsers(1)).protocols(httpProtocol))   
}
