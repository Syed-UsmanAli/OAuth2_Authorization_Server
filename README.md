# OAuth2 Authorization Server

This demo project has implemented a light weight authorization server for 2 types of OAuth2 Grant Flow

A) Grant Type - Password
B) Grant Type - Authorization Code

Implementation was done as a Spring Boot Application  with  user data as In-memory. Project's .jar was then dockerized and uploaded to docker Hub.

CI Server - Local Jenkins was configured to pull the code from GitHub on every commit, Build the code using maven and spin up
an instance of the Spring boot Application and then trigger the Junit test cases.

Note : Junit test case has been written only for the grant type - password for the generation of valid "Access Token"
       whereas, Postman Requests was implemented for both grant type - password and Authorization Code for generating 
       a valid "Access Token" along with "Refresh Token". 
       
Steps on how to setup the project , build, execute and validate the test cases has been captured in a document with screenshots and uploaded withing this .git
project. Please refer the "Project Setup Document" mentioned in the Important Links below.

Important Link(s)

Project Setup Document --> https://github.com/Syed-UsmanAli/OAuth2_Authorization_Server/blob/master/Syed_OAuth2_AuthorizationServer_Project_Documentation.pdf

GitHub Repo -- > https://github.com/Syed-UsmanAli/OAuth2_Authorization_Server Refer "master" branch

Docker Hub Repo --> https://hub.docker.com/repository/docker/zavid2006/springboot-oauth2-authcode.jar

PostMan Requests Collection for testing --> https://github.com/Syed-UsmanAli/OAuth2_Authorization_Server/blob/master/OAuth2_Password_And_Authorization_Code_Flows.postman_collection.json


