# slambda

This project aims to explore the writing of stateful Function-as-a-Service.

The code base uses
- AWS Lambda
- a dedicated EC2 server running the [CRESON](https://github.com/otrack/infinispan-creson).
- the Lambda plugin for Maven available [here](https://github.com/SeanRoy/lambda-maven-plugin).

It implements a toy Lambda function that concatenates the values given in parameters to a list.
This list is shared between all instances (over time) of the lambda using the CRESON framework.

## Usage

to build and deploy the org.example.Hello lambda:

	mvn clean package shade:shade lambda:deploy-lambda -DskipTests -DaccessKey="KEY" -DsecretKey="SECRET"

Once properly deployed, to run this lambda:

	aws lambda invoke --payload '{"key":"value"}' --function-name my-function-dev output.txt
