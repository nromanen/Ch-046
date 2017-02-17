# Ch-046

# About project
This application was created for travian's players. It allows players to create and manage alliances, add players into alliances, create and manage villages and armies, ask help when your village is under attack.

To run this application you need:
```
Node 6.9,
Java 8,
Gradle 3.2,
PostgreSQL
```
## Installation:
```
git clone https://github.com/nromanen/Ch-046.git
```
Create DB named "travian",
Check if the username, password and port for DB in src/main/resources/hibernate.properties are correct.
Restore DB travian using file src/main/resources/travian_dump.sql

## Run Application
To run Application go to Ch-046 folder

and next execute command
```
gradle runApp
```
After instalation node_modules and compile TypeScript's files to JavaScript (it can take several minutes) application will be run on
```
localhost:8080/travian
```
## Create war file

To create war file use 
```
gradle buildApp
```
