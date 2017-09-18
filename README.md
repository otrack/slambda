# slambda

This project aims to explore the writing of stateful Function-as-a-Service.

The code base uses
- AWS Lambda,
- a dedicated EC2 server running the [CRESON](https://github.com/otrack/infinispan-creson) framework, and
- the Lambda plugin for Maven available [here](https://github.com/SeanRoy/lambda-maven-plugin).

It implements a toy Lambda function that concatenates the values given in parameters to a list.
This list is shared _consistently_ between all instances of this lambda.
To this end, the lambda uses the CRESON framework and a dedicated nodes located @52.49.189.207:11222.
For the sake of simplicity, this address is hard-coded in the code of the lambda (Hello.java).

## Usage

First, install in your local Maven repository infinispan-creson using:

	git clone https://github.com/otrack/infinispan-creson
	mvn clean install -DskipTests

Clone this repository, then modify appropriately the parameters of AWS Lambda in pom.xml -> plugins -> lambda-maven-plugin -> configuration.
This implies modifying setting-up the region, creating an IAM and modifying the bucket where to store lambdas.

Then, build and deploy this lambda with:

	mvn clean package shade:shade lambda:deploy-lambda -DskipTests -DaccessKey="aws_key" -DsecretKey="aws_secret" -Ds3Bucket="my_bucket"

Once properly deployed, run it:

	aws lambda invoke --payload '{"key":"value"}' --function-name my-function-name output.txt
