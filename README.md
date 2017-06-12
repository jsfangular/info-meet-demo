# info-meet-demo
Demo for infoMEET.

From JSF to Angular 2+.

How to run (based on latest working configuration on which it was checked)?

1. Server (JSF client):
- install Java JDK 1.8
- install Maven > 3
- invoke 'mvn clean install' to build project
- install Tomcat >= 8.5
- use IDE if You like - for example: Eclipse
- deployed application is available on localhost:8080/server

2. Client (Angular):
- install Node, NPM
- invoke 'npm install' to build project
- use IDE if You like - for example Visual Studio Code
- invoke 'ng serve' to run webpack and build process
- deployed application is available on localhost:4200/

The purpose:
- make possible to develop frontend client (Angular, React, Vue, etc) with connection to JSF session (for time of rewriting).

General idea:
- use JSF with Spring Container (DI), Spring MVC
- FacesServlet is building FacesContext
- FacesContext is set as request attribute in PhaseListener
- In PhaseListener forward current 'JSF request' is done. Forward to regular Spring MVC dispatcher servlet.
- Spring Controller is able to 'see' FacesContext and invoke any JSF bean or action (also read JSF session information!).
- Frontend client sends request to JSF server with current sessionId (see: XMLHttpRequest.withCredentials).
