# Course Project - Virtual Lifestyle Coach 

**Introduction to Service Design and Engineering | University of Trento**

**Server | Business Logic**

**Student**: Gianvito Taneburgo (182569)

This file aims to provide a short documentation for the course project. Further details can be found in the report [here](https://github.com/virtual-life-coach/common/blob/master/report.pdf).
The original instructions can be found [here](https://docs.google.com/document/u/1/d/1kU66KOoprmdypDEE1W1bs1iQsX-Vf7_SXH7gAm5UYMU/edit?usp=sharing).

The project was developed individually.

Server URL: [https://vlc-server-business-logic.appspot.com/](https://vlc-server-business-logic.appspot.com/)

## Project structure

The project repository is made up of the following *files* and **folders**:
* **src/main**: application source code
    * **java/vlc/bl**: Java source code
        * **activity**: user-tailored activity creation logic
            * *ActivityCreator.java*: user activity creator 
        * **cron**: cron jobs servlet
            * *ActivityCron.java*: create and send a daily activity to users
            * *LocalDatabaseCron.java*: reinitialize local database
            * *QuoteCron.java*: fetches and send a daily quote to users
            * *ReceiptCron.java*: fetches and send a daily receipt to users
        * **dispatcher**: business logic
            * *RequestDispatcher.java*: dispatch requests appropriately
    * **webapp**: web application
        * **WEB-INF**: web application resources
            * *appengine-web.xml*: GAE deployment descriptor
            * *cron.xml*: cron job definition
            * *logging.properties*: GAE logging level
            * *web.xml*: Java EE deployment descriptor
        * *index.html*: home page
        * *loading_cube.gif*: home page content
* *pom.xml*: Maven project descriptor
* *README.md*: this file
