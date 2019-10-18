
# CEST API v2.4 (Future API, includes latest Business On Own Account rules)

## Endpoint URL
`POST` /decide

## Service Definitions

Requests use the HTTP `POST` method

### Decision Service

#### Functionality

* Evaluates an __Interview__
* Provides a __Decision__ based on a versioned set of "Decision Tables" _Note:_ This service supports multiple versions of Decision Tables, this version is 'addressed' in the version number in the request and response JSON. 


## Request

* Body contains an __Interview__ JSON
- A [JSON Schema](../test/resources/schema/2.0/off-payroll-request-schema.json) defines this __Interview__.
- [Example](../test/resources/schema/2.0/off-payroll-request-sample.json) JSON with all fields populated.


| Attribute        | Required           | Description                                                          |
| :----------------|:------------------:| :--------------------------------------------------------------------|
| version          | true               | The version of the Interview being used |
| correlationID    | true               | A value unique to the consumer, to identify this Interview and correlate to its Decision|
| interview        | true               | A set of questions and answers grouped in sections used to conduct the Interview |
| setup            | false              | About you and the work Section |
| exit             | false              | Worker’s duties Section |
| personalService  | false              | Substitutes and helpers Section |
| control          | false              | Working arrangements Section |
| financialRisk    | false              | Worker’s financial risk Section |
| partAndParcel    | false              | Worker’s involvement Section |
| businessOnOwnAccount | false          | Worker's Contracts Section |


## Response

* HTTP 200 OK
* Body contains __Decision__ JSON
- A [JSON Schema](../test/resources/schema/2.0/off-payroll-response-schema.json) defines this __Decision__.
- [Example](../test/resources/schema/2.0/off-payroll-response-sample.json) JSON with all fields populated.


| Attribute            | Required           | Description                                                                                                    |
| :------------------- |:------------------:| :--------------------------------------------------------------------------------------------------------------|
| version              | true               | The version of the Interview sent in the request and therefore applied to this Response                      |
| correlationID        | true               | Unique number to identify this Interview and correlate it to its  decision |
| result               | true               | An enumeration of "Outside IR35" &#124; "Inside IR35" &#124; "Unknown"|
| score                | true               | A map of scores  |


* HTTP 400 Bad Request for invalid/error scenarios

```json
	{
		"code": 4001,
		"message": "Lorem ipsum dolor sit amet, consectetur adipiscing elit"
	}
```

| Attribute         | Required           | Description                                                                                                 |
| :-----------------|:------------------:| :-----------------------------------------------------------------------------------------------------------|
| code              | true               | Extension to the HTTP Status Code. Use this code to reference internationalised text for errors. Refer to this [document](errors.md) for a full list or error codes. |
| message           | true               | English readable message as to the reason for the error.                                                    |

