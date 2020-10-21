18th October 2020
1. now building on graalvm's java
1. now based on AWS lambda base 
1. includes bootstrap and certs

next steps
1. after mvn build, build again using native-image (may need to install native image)
1. then zip it all up and make this the output artifact
1. make the lambda deploy without a layer
