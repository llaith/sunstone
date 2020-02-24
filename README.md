# sunstone

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=org.llaith.sunstone%3Asunstone&metric=alert_status)](https://sonarcloud.io/dashboard?id=org.llaith.sunstone%3Asunstone)

This is the 'sunstone' programming example for a rules-based ordering service.

It's a work in progress (About 10 hours so far), but the most interesting bit is in the rules-engine logic, which can be seen being put through it's paces
[here](sunstone-adapter/sunstone-adapter-easyrules/src/test/java/org/llaith/sunstone/adapter/easyrules/OrderLogicRulesImplTest.java)

I need to generate the javadoc and more tests.

The OrderLogicRulesImpl is the part that would be considered the 'coding challenge'.

The 'bonus' is the following:
* strict separation of concerns, horizontal & vertical,
* supporting services in correct microservice style
* immutable in api, not in impl (demonstrating we can contain mutability)
* healthy maven structure easy to extend and modify (allowing multiple parents for example)
* all maven plugins locked down and enforcer at useful maximum
* sonarcloud integration
* a 'makefile' & docker based approach to clean builds
* uses jdbi instead of hibernate/jpa without much additional coding effort
* use of lightweight javalin rest-api (open-api to come when time permitting)

Please excuse the still incomplete state of some of the bonus material. Obviously the project needs more coverage, proper implementations of supporting services like repositories and the rest-api, and a rest-client like found in atlas:

I intend to extend this to be the next version of the previous bootstrap I did:
https://github.com/llaith/atlas-dropwizard-microservice-bootstrap

Technologies learned in the making of this (excuse any mistakes):
* easyrules rules engine (I have previously used drools)
* javalin http framework
* jdbi v3 (I have previously used v2 in dropwizard 1.x)

