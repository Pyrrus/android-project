# Android project using Android, XML, and Java

## This verson using betterdoctor api using city and state and firebase database  12/16/16

#### By **Adam Gorbahn**

## Description
This app made with api call, and using new views like recyclerview. Also, add database call firebase to save data from the user. Lastly, add GestureDetector to have it more user interaction with the app.

## Specifications

#### 'Menu' Bar 

user input      | output
--------------- | -------------
Click on 'home' | will go to the home Activity.  

user input       | output
---------------- | -------------
Click on 'about' | will go to the about Activity. 

user input       | output
---------------- | -------------
Click on 'favorite' | will go to the favorite Activity. 

user input       | output
---------------- | -------------
Click on 'logout' | will go to the login Activity. remove the user auth from the app. 

#### 'search' Bar 

user input      | output
--------------- | -------------
Click on 'search' icon | will make a search bar. 

user input      | output
--------------- | -------------
Click on 'home' | will go to the home Activity.  

user input       | output
---------------- | -------------
Click on 'about' | will go to the about Activity. 

user input       | output
---------------- | -------------
Click on 'favorite' | will go to the favorite Activity. 

user input       | output
---------------- | -------------
Click on 'logout' | will go to the login Activity. remove the user auth from the app. 

#### 'home' Activity 

user input                       | output
-------------------------------- | -------------
input city name, and pick the state | this will go to the doctor Activity. 

Have landscape mode when the device on the horizontal. 

#### 'login' Activity 

user input                       | output
-------------------------------- | -------------
input both user email, and password | this will go to the home Activity.

user input                       | output
-------------------------------- | -------------
touch 'Don't have an account? Sign up here!' | this will go to the Create Account Activity.

Have landscape mode when the device on the horizontal.

#### 'Create Account' Activity 

user input                       | output
-------------------------------- | -------------
input both user email, name, and passwords | this will go to the home Activity.

user input                       | output
-------------------------------- | -------------
touch 'Already have an account? Log in here!' | this will go to the Login Activity.

Have landscape mode when the device on the horizontal.

#### 'doctor' Activity 

user input                       | output
-------------------------------- | -------------
click on doctor information getting information from API  | this will go to the doctor view Activity. 

user input                       | output
-------------------------------- | -------------
clicked and held the doctor information  | this will save it to the your favorite doctor. 

#### 'favorite' Activity 

user input                       | output
-------------------------------- | -------------
click on doctor information getting information from database  | this will go to the doctor view Activity. 

user input                       | output
-------------------------------- | -------------
double clicked doctor information  | this will remove it from favorite doctor. 


#### 'doctor view' Activity 

user input                       | output
-------------------------------- | -------------
click on phone number  | this will go outside of the app. then call new app which is phone app. 

user input                       | output
-------------------------------- | -------------
click on button  | this will save it to the your favorite doctor.

Have landscape mode when the device on the horizontal.

## Bugs
* if using Android 2.1. It will not work. (Look in Setup)

## Setup/Installation Requirements

if using Android 2.1
* Disable instant run in setting -> Build, Execution, Deployment -> remove all checkbox
* then Build -> clean project

## Technologies Used

* Java
* XML

### License

*GPL*

Copyright (c) 2016 **Adam Gorbahn**
