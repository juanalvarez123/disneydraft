About Swagger Configuration 
---------------------------

Accessing Swagger Documents
---------------------------

When Swagger is enabled by Swagger feature, the Swagger documents will be available at the location URL constructed of the service 
endpoint location followed by /swagger.json or /swagger.yaml.

For example, lets assume a JAX-RS endpoint is published at 'http://host:port/context/services/' where 'context' is a web application 
context,  "/services" is a servlet URL. In this case its Swagger documents are available at 'http://host:port/context/services/swagger.json' 
and 'http://host:port/context/services/swagger.yaml'.

Starting from CXF 3.1.7 the CXF Services page will link to Swagger documents if Swagger2Feature is active. 

	*for this example we have the URL: http://localhost:9090/rest-api/swagger.json
	

Automatic UI Activation
-----------------------

This feature is available starting from CXF 3.1.7: Adding a Swagger UI Maven dependency is all what is needed to start accessing Swagger 
documents with the help of Swagger UI.

For example, lets assume a JAX-RS endpoint is published at 'http://host:port/context/services/'.

Open the browser and go to 'http://host:port/context/services/api-docs?/url=/swagger.json' which will return a Swagger UI page.

CXF Services page will also link to Swagger UI. Go to 'http://host:port/context/services/services' and follow a Swagger link which will 
return a Swagger UI page.

See samples/jax_rs/description_swagger2, samples/jax_rs/description_swagger2_web, samples/jax_rs/spring_boot and samples/jax_rs/spring_boot_scan 

Note that CXF OSGI endpoints can only depend on this feature starting from CXF 3.1.8.


	*for this example we have the URL: http://localhost:9090/rest-api/api-docs?url=/rest-api/swagger.json
	

Properties
----------


basePath-	the context root path+	null
contact-	the contact information+	"users@cxf.apache.org"
description-	the description+	"The Application"
filterClass-	a security filter+	null
host-	the host and port+	null
ignoreRoutes-	excludes specific paths when scanning all resources (see scanAllResources)++	null
license-	the license+	"Apache 2.0 License"
licenceUrl-	the license URL+	"http://www.apache.org/licenses/LICENSE-2.0.html"
prettyPrint-	when generating swagger.json, pretty-print the json document+	false
resourcePackage-	a list of comma separated package names where resources must be scanned+	a list of service classes configured at the endpoint
runAsFilter-	runs the feature as a filter	false
scan-	generates the swagger documentation+	true
scanAllResources-	scans all resources including non-annotated JAX-RS resources++	false
schemes-	the protocol schemes+	null
termsOfServiceUrl-	the terms of service URL+	null
title-	the title+	"Sample REST Application"
version-	the version+	"1.0.0"
