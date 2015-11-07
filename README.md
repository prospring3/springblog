## ProSpring 3 - SpringBlog Application
The SpringBlog application is the sample application for the book Pro Spring 3, which covers various topics in using Spring Framework. 

The application is built with:
- Spring Framework 4.2.2
- Service layer with implementation with JPA (Hibernate with Spring Data JPA) and MyBatis
- Spring AOP
- Spring MVC, JSP, jQuery, jQueryUI, jqGrid, CKEditor
- RESTful-WS
- Spring Batch
- Spring Integration 

## Purpose
The main purpose is to showcase the topics discussed in the book. On the other hand, it can be used as a reference application for developers in using latest version of Spring Framework with related tools to build a JEE application.

## Check out sources
`git clone git://github.com/prospring3/springblog.git` 

## Book
[Pro Spring 3](http://www.apress.com/9781430241072) 

## Notes for running the application within STS
I found problem in running this application id tc Server within STS (version 3.7.1), but works with Tomcat 8.
So please following the instructions below in running the application in STS and Tomcat in your local environment:
* Download and unzip Apache Tomcat 8 in your local PC
* Copy the file "/lib/spring-instrument-tomcat-4.2.2.RELEASE.jar into the "lib" folder of the extracted Tomcat server
* In STS, create a new server which points to the Tomcat 8 installed above
* Deploy the application to the Tomcat Server and verify that the application is up and running

For any questions, please feel free to contact me and I will take a look.
