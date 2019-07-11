@import uk.gov.hmrc.decisionservice.models.enums.WeightedAnswerEnum

{
  "@WeightedAnswerEnum.OUTSIDE_IR35": [
    {
      "workerSentActualSubstitute": "yesClientAgreed",
      "workerPayActualSubstitute": true
    },
    {
      "possibleSubstituteRejection": "wouldNotReject",
      "possibleSubstituteWorkerPay": true
    }
  ],
  "@WeightedAnswerEnum.HIGH": [
    {
      "possibleSubstituteRejection": "wouldReject"
    },
    {
      "workerSentActualSubstitute": "notAgreedWithClient",
      "wouldWorkerPayHelper": false
    }
  ],
  "@WeightedAnswerEnum.MEDIUM": [
    {
      "possibleSubstituteRejection": "wouldNotReject",
      "possibleSubstituteWorkerPay": false
    },
    {
      "workerSentActualSubstitute": "notAgreedWithClient",
      "wouldWorkerPayHelper": true
    },
    {
      "workerSentActualSubstitute": "yesClientAgreed",
      "workerPayActualSubstitute": false
    },
    {
      "workerSentActualSubstitute": "noSubstitutionHappened",
      "possibleSubstituteRejection": "wouldReject",
      "wouldWorkerPayHelper": true
    }
  ]
}