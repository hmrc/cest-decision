# Check Employment Status for Tax (CEST/Off-Payroll) Decision Rule Engine

This μ-service is the backend decision rule engine for the Check Employment Status for Tax frontend μ-service.

## API
| Task    | Http Method | Version   | Description |
|:--------|:------------|-----------|-------------|
|`/decide`  | `POST`    |`1.5.0-final`|Returns a 'decision' on Employment Status for Tax Purposes|


## Supported versions 
| Version | Description |
|:--------|:------------|
|`2.4`|Current version, includes finalised Business on Own Account. [More...](./docs/api_2.4.md)|


## Deprecated Versions
| Version | Description |
|:--------|:------------|
|`2.2`|Includes Business On Own Account questions. [More...](./docs/api_2.2.md)|
|`2.1`|Revised Business on Own Account. [More...](./docs/api_2.1.md)|
|`2.0`|Draft with Business on Own Account. [More...](./docs/api_2.0.md)|
|`1.5.0-final`|Previous version, pre Business On Own Account, now deprecated. [More...](./docs/api_1.5.0-final.md)|
|`1.4.0-final`|Previous version, now deprecated. [More...](./docs/api_1.4.0-final.md)|
|`1.3.0-final`|Previous version, now deprecated. [More...](./docs/api_1.3.0-final.md)|
|`1.2.0-final`|Previous version, now deprecated. [More...](./docs/api_1.2.0-final.md)|
|`1.1.1-final`|Previous version, now deprecated. [More...](./docs/api_1.1.1-final.md)|
|`1.1.0-final`|Public Beta version, now deprecated. [More...](./docs/api_1.1.0-final.md)|


## Running the application locally
To run the application execute

```
sbt "run 9849" 

```

To check the latest dependency versions

```
sbt dependencyUpdates

```
 
## License

This code is open source software licensed under the [Apache 2.0 License](http://www.apache.org/licenses/LICENSE-2.0.html)
