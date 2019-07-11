@import uk.gov.hmrc.decisionservice.models.enums.WeightedAnswerEnum._
@import uk.gov.hmrc.decisionservice.models.PartAndParcel._

{
  "@HIGH": [
    {
      "@workerAsLineManager": true
    },
    {
      "@workerReceivesBenefits": true
    }
  ],
  "@MEDIUM": [
    {
      "@contactWithEngagerCustomer": true,
      "@workerRepresentsEngagerBusiness": "workForEndClient"
    },
    {
      "@contactWithEngagerCustomer": true,
      "@workerRepresentsEngagerBusiness": "workAsBusiness"
    }
  ],
  "@LOW": [
    {
      "@contactWithEngagerCustomer": false
    },
    {
      "@workerRepresentsEngagerBusiness": "workAsIndependent"
    }
  ]
}