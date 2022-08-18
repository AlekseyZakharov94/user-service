# User Service

### Description
Simple implementation of a Web application according to the following requirements:
Development tools: Java 1.7 (or higher), Tools: Spring Framework, Protocol: HTTP, Database: PostgreSQL
Note: instead of accessing the real database, you can implement a "stub" with simulated access and a time delay of 5-10 seconds.
Functionality:
• Adding a new user. We transfer user data to the server (username, email, PhoneNumber, etc.). We save the information in the database. The server response is the unique ID of the new user

• Getting user information. We transmit a unique user ID to the server. We read information from the database. The server response is user data.

• User status change (Online, Away, Offline). We transmit a unique user ID and a new status to the server. Changing the user status. Server response — unique user ID, new and previous status

• The transfer to the Away status should be made automatically 5 minutes after the last status change to online. Thus, if you “confirm” the online user's status every 5 minutes, there should not be an automatic transition to Away.


