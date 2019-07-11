@import uk.gov.hmrc.decisionservice.models.enums.WeightedAnswerEnum

{
  "@WeightedAnswerEnum.HIGH": [
    {
      "workerAsLineManager": true
    },
    {
      "workerReceivesBenefits": true
    }
  ],
  "@WeightedAnswerEnum.MEDIUM": [
    {
      "contactWithEngagerCustomer": true,
      "workerRepresentsEngagerBusiness": "workForEndClient"
    },
    {
      "contactWithEngagerCustomer": true,
      "workerRepresentsEngagerBusiness": "workAsBusiness"
    }
  ],
  "@WeightedAnswerEnum.LOW": [
    {
      "contactWithEngagerCustomer": false
    },
    {
      "workerRepresentsEngagerBusiness": "workAsIndependent"
    }
  ]
}