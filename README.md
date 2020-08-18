# Labforward Code Challenge for Backend Engineer Candidate

This is a simple Hello World API for recruiting purposes. You, as a candidate, should work on the challenge on your own account. Please clone the repo to your account and create a PR with your solution. 

## Introduction

You can run the application by typing

	./gradlew bootRun

This will start up a Spring Boot application with Tomcat server running on 8080.

Show all other possible tasks:

	./gradlew tasks
	
## Your Task	

You need to add a new endpoint to the API to allow users to *update the greetings they created*. 

## Acceptance Criteria

This task is purposefully open-ended. You are free to come up with your own implementation based on your assumptions. You are also welcome to improve the existing code by refactoring, cleaning up, etc. where necessary. Hint: there is a missing core piece in the application :) 

Extra points for describing a user interface which utilizes the API with the new endpoint. This can be a text document, simple mock-ups, or even an interactive HTML proof-of-concept. Be creative and show us how you approach UI problems.

We understand that not everyone has the same amount of "extra" time. It is also up to you to determine the amount of time you spend on the exercise. So that the reviewer understands how you are defining the scope of work, please clearly indicate your own “Definition of Done” for the task in a README file along with any other pertinent information.

Regardless of how far you take the solution towards completion, please assume you are writing production code. Your solution should clearly communicate your development style, abilities, and approach to problem solving. 

Let us know if you have any questions, and we look forward to seeing your approach.

Good Luck!

## Solution Submitted

- Added *update* greeting endpoint which is a PUT method to update an existing greeting.
- Refactored existing code and added more validation logics.
- Added and Modified test to maximize code coverage.
- Added CORS configuration.
- Added basic spring actuator functionality to check application health. [click to explore](http://localhost:8080/actuator/health)
- Added Swagger for endpoint documentation. [click to explore](http://localhost:8080/swagger-ui.html#/)
- Added Angular Based interactive UI using exposed endpoints

## UI Design

Interactive angular based UI application has been added inside same project (just to make testing and viewing both app less problematic). UI app location is : (project root)/src/main/greeting-ui. 

Please note, both main project code and deployable app is there to check, inside greeting-ui and /greeting-ui/dist respectively.

### Requirement for running UI
- Spring Boot Backend Application of Greeting CRUD
- Node JS
- Angular CLI (preferably version 10 or more)

#### Steps to run for running UI app

1. Node JS source code or installer can be found [here](https://nodejs.org/en/download/)

2. Install the angular CLI using the npm package manager:

    > npm install -g @angular/cli

1. Run spring boot application by running **./gradlew bootRun** from root project folder.

2. Once spring boot application is up and running, please go to (project root)/src/main/greeting-ui:

    > cd <project root>/src/main/greeting-ui

3. Now angular application can be started by command:

    > ng serve
    
    
This will launch UI application on port 4200.
 
Thanks.



