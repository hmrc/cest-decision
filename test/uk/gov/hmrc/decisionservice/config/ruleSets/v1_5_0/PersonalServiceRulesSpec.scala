/*
 * Copyright 2020 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.decisionservice.config.ruleSets.v1_5_0

import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import play.api.libs.json.Json
import uk.gov.hmrc.decisionservice.config.ruleSets.BaseRuleSpec
import uk.gov.hmrc.decisionservice.models.PersonalService._
import uk.gov.hmrc.decisionservice.models.enums.{ArrangedSubstitute, RejectSubstitute, WeightedAnswerEnum}
import uk.gov.hmrc.decisionservice.ruleSets.PersonalServiceRules_v150

class PersonalServiceRulesSpec extends BaseRuleSpec with GuiceOneAppPerSuite {

  implicit val ruleSet = PersonalServiceRules_v150.ruleSet

  "For all the expected OUT rules" should {

    val actual = getRules(WeightedAnswerEnum.OUTSIDE_IR35)

    val expected = List(
      Json.obj(
        workerSentActualSubstitute -> ArrangedSubstitute.yesClientAgreed,
        workerPayActualSubstitute -> true
      ),
      Json.obj(
        workerSentActualSubstitute -> ArrangedSubstitute.noSubstitutionHappened,
        possibleSubstituteRejection -> RejectSubstitute.wouldNotReject,
        possibleSubstituteWorkerPay -> true
      ),
      Json.obj(
        possibleSubstituteRejection -> RejectSubstitute.wouldNotReject,
        possibleSubstituteWorkerPay -> true
      )
    )

    checkRules(expected, actual)
  }

  "For all the expected HIGH rules" should {

    val actual = getRules(WeightedAnswerEnum.HIGH)

    val expected = List(
      Json.obj(
        workerSentActualSubstitute -> ArrangedSubstitute.notAgreedWithClient,
        wouldWorkerPayHelper -> false
      ),
      Json.obj(
        workerSentActualSubstitute -> ArrangedSubstitute.noSubstitutionHappened,
        possibleSubstituteRejection -> RejectSubstitute.wouldReject,
        wouldWorkerPayHelper -> false
      ),
      Json.obj(
        possibleSubstituteRejection -> RejectSubstitute.wouldReject
      )
    )

    checkRules(expected, actual)
  }

  "For all the expected MEDIUM rules" should {

    val actual = getRules(WeightedAnswerEnum.MEDIUM)

    val expected = List(
      Json.obj(
        workerSentActualSubstitute -> ArrangedSubstitute.yesClientAgreed,
        workerPayActualSubstitute -> false
      ),
      Json.obj(
        workerSentActualSubstitute -> ArrangedSubstitute.noSubstitutionHappened,
        possibleSubstituteRejection -> RejectSubstitute.wouldNotReject,
        possibleSubstituteWorkerPay -> false,
        wouldWorkerPayHelper -> true
      ),
      Json.obj(
        possibleSubstituteRejection -> RejectSubstitute.wouldNotReject,
        possibleSubstituteWorkerPay -> false
      ),
      Json.obj(
        workerSentActualSubstitute -> ArrangedSubstitute.notAgreedWithClient,
        wouldWorkerPayHelper -> true
      ),
      Json.obj(
        workerSentActualSubstitute -> ArrangedSubstitute.noSubstitutionHappened,
        possibleSubstituteRejection -> RejectSubstitute.wouldReject,
        wouldWorkerPayHelper -> true
      )
    )

    checkRules(expected, actual)
  }
}
