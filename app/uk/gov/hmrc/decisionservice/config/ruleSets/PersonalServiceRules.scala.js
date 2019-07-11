@import uk.gov.hmrc.decisionservice.models.enums.WeightedAnswerEnum._
@import uk.gov.hmrc.decisionservice.models.PersonalService._

{
  "@OUTSIDE_IR35": [
    {
      "@workerSentActualSubstitute": "yesClientAgreed",
      "@workerPayActualSubstitute": true
    },
    {
      "@possibleSubstituteRejection": "wouldNotReject",
      "@possibleSubstituteWorkerPay": true
    }
  ],
  "@HIGH": [
    {
      "@possibleSubstituteRejection": "wouldReject"
    },
    {
      "@workerSentActualSubstitute": "notAgreedWithClient",
      "@wouldWorkerPayHelper": false
    }
  ],
  "@MEDIUM": [
    {
      "@possibleSubstituteRejection": "wouldNotReject",
      "@possibleSubstituteWorkerPay": false
    },
    {
      "@workerSentActualSubstitute": "notAgreedWithClient",
      "@wouldWorkerPayHelper": true
    },
    {
      "@workerSentActualSubstitute": "yesClientAgreed",
      "@workerPayActualSubstitute": false
    },
    {
      "@workerSentActualSubstitute": "noSubstitutionHappened",
      "@possibleSubstituteRejection": "wouldReject",
      "@wouldWorkerPayHelper": true
    }
  ]
}