# slambda

This project aims to explore the writing of stateful Function-as-a-Service.

The code base uses
- AWS Lambda,
- a dedicated EC2 server running the [CRESON](https://github.com/otrack/infinispan-creson) framework, and
- the Lambda plugin for Maven available [here](https://github.com/SeanRoy/lambda-maven-plugin).

It implements a toy Lambda function that concatenates the values given in parameters to a list.
This list is shared between all instances (over time) of the lambda using the CRESON framework.

## Usage

First, install in your local Maven repository infinispan-creson (**JPA** branch) using:

	git clone https://github.com/otrack/infinispan-creson
	git checkout jpa
	mvn clean install -DskipTests

Then, build and deploy this lambda:

	git clone 
	mvn clean package shade:shade lambda:deploy-lambda -DskipTests -DaccessKey="KEY" -DsecretKey="SECRET"

Once properly deployed, run it:

	aws lambda invoke --payload '{"key":"value"}' --function-name my-function-name output.txt
