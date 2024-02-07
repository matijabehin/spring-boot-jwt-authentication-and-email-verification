# Backend Web Application with JWT Token Authentication and Email Verification
This is a straightforward backend web application designed for user authentication using JWT tokens and email verification.
## Registration Requirements:
To register, user needs to provide:
* email
* username
* password

  
![1](https://github.com/matijabehin/spring-boot-jwt-authentication-and-email-verification/assets/45948237/3b0fe28b-0826-4128-9f38-6a6411abc02c)

Upon registration, provided data is stored in a PostgreSQL database. The 'enabled' and 'unlocked' tables are initially set to false, meaning the users cannot login until they confirm their email address.


![2](https://github.com/matijabehin/spring-boot-jwt-authentication-and-email-verification/assets/45948237/13e3e5ea-cef2-447a-85eb-f62d1ef17e2e)



## Email Verification Process:
After registration, user receives an email for email verification. To complete verification, user needs to click on a button that directs him to a link for email verification. This process must be completed within 20 minutes, as the verification link expires after that time.

For this project, email verification is facilitated through Mailtrap, a service designed for testing email workflows. Mailtrap allows the use of fake email addresses for testing purposes, ensuring that the email verification process can be thoroughly tested without sending emails to real users.

Mailtrap provides a simulated SMTP server environment where emails can be sent and received for testing, helping developers verify the functionality of email-related features in their applications without affecting real users or sending emails to actual email addresses.

![3](https://github.com/matijabehin/spring-boot-jwt-authentication-and-email-verification/assets/45948237/78837356-a8fb-43ee-b843-76b40a93965b)



## Logging In:
Once verified, user can login and a JWT token is assigned to him.

![4](https://github.com/matijabehin/spring-boot-jwt-authentication-and-email-verification/assets/45948237/d620c829-c46c-480e-a4d8-fab3ca937fbb)
