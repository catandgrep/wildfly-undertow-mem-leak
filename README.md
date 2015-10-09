# The Problem

Wildfly does not release "io.undertow.servlet.spec.ServletContextImpl" after Application Undeployments. This results in Memory Leak after contiuous redeployments:

```
6 instances of "io.undertow.servlet.spec.ServletContextImpl", loaded by "org.jboss.modules.ModuleClassLoader @ 0xe0226c10" occupy 5,051,528 (14.46%) bytes.

Biggest instances:

io.undertow.servlet.spec.ServletContextImpl @ 0xe1b64db8 - 1,039,080 (2.97%) bytes.
io.undertow.servlet.spec.ServletContextImpl @ 0xe1e7bfe8 - 1,038,960 (2.97%) bytes.
io.undertow.servlet.spec.ServletContextImpl @ 0xe16e23f8 - 1,038,536 (2.97%) bytes.
io.undertow.servlet.spec.ServletContextImpl @ 0xe1a745d0 - 1,038,488 (2.97%) bytes.
io.undertow.servlet.spec.ServletContextImpl @ 0xe1696090 - 893,456 (2.56%) bytes.
```

# Reproduce

* clone this repository
* run
```mvn clean verify```
* import the target/wildfly/heap.hprof into the Eclipse Memory Analyzer


# Test Description

* Wildfly Arquillian Container Managed
* Wildfly Final 9.0.1.Final (same with 10.0.0.CR2)
* 5 Test Classes, each simulating a single deployment and undeployment
* 1 Test Class writes the Heap Dump at the end.
