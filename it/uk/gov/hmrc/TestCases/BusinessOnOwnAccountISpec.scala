package uk.gov.hmrc.TestCases

import uk.gov.hmrc.decisionservice.ruleSets.BusinessOnOwnAccountRules_v20

class BusinessOnOwnAccountISpec extends BaseISpec {

  s"POST $path" should {

    "for the BusinessOnOwnAccount section" must {

      "Return the correct section result in the response for all rules" in {

        BusinessOnOwnAccountRules_v20.ruleSet.foreach { rule =>

          val res = postRequest(path, interview(businessOnOwnAccount = rule.rules))

          whenReady(res) { result =>
            result.status shouldBe OK
            result.body should include(s""""businessOnOwnAccount":"${rule.result}"""")
          }
        }
      }
    }
  }
}
