
# off-payroll-decision


## Endpoint URLs
POST /decide/

## Service Definitions

Requests use the HTTP `POST` method

### Decision Service

#### Functionality

* Evaluates an __Interview__
* Provides a __Decision__ based on a versioned set of "Decision Tables" _Note:_ This service supports multiple versions of Decision Tables, this version is 'addressed' in the version number in the request and response JSON. 


## Request

* Body contains an __Interview__ JSON
- A [JSON Schema](../test/resources/schema/1.4.0-final/off-payroll-request-schema.json) defines this __Interview__.
- [Example](../test/resources/schema/1.4.0-final/off-payroll-request-sample.json) JSON with all fields populated.


| Attribute        | Required           | Description                                                          |
| :----------------|:------------------:| :--------------------------------------------------------------------|
| version          | true               | The version of the Interview being used |
| correlationID    | true               | A value unique to the consumer, to identify this Interview and correlate to its Decision|
| interview        | true               | A set of questions and answers grouped in clusters used to conduct the Interview |
| setup            | false              | cluster |
| exit             | false              | cluster |
| personalService  | false              | cluster |
| control          | false              | cluster |
| financialRisk    | false              | cluster |
| partAndParcel    | false              | cluster |

 _Note:_ The __Interview__ does not need to contain all the clusters. Within each cluster there are a number of __ClusterElements__ a ClusterElement represents a Question with an answer. For a complete list of  __ClusterElements__ refer to the [JSON Schema](../test/resources/schema/1.1.1-final/off-payroll-request-schema.json) Once all the __Clusters__ are present in this __Interview__ then a __Decision__ response will be present. Depending on how the __Interview__ has been formed a __Decision__ may be arrived at before all __Clusters__ and __ClusterElements__ are present, this is known as a __Hard Exit__


## Response

* HTTP 200 OK
* Body contains __Decision__ JSON
- A [JSON Schema](../test/resources/schema/1.4.0-final/off-payroll-response-schema.json) defines this __Decision__.
- [Example](../test/resources/schema/1.4.0-final/off-payroll-response-sample.json) JSON with all fields populated.


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

