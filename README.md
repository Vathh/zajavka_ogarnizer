The application is designed to streamline the management of a company that provides office equipment service (computers, printers, copiers, scanners, etc.) to businesses. The project facilitates cooperation among service employees. It was developed based on personal experience working in a service where such an application was lacking.
</br>
</br>
The entire context of the application revolves around adding and managing tasks commissioned by clients, which will henceforth be referred to simply as tasks.
</br>

Tasks are divided into three categories:
 - Services performed at the client's location (away work)
 - Services conducted at the company's headquarters (service)
 - Orders (order)
# Features

The application allows:
- Storing clients and users in a database
- Managing tasks categorized as away work(at the client's location), service(at the company's headquarters), and order
- Viewing lists of tasks, users, and clients
- Adding and deleting users
- Handling user authentication and authorization
- Fetching random data from a dedicated API for different types of tasks and clients

### API Endpoints and Roles

API requests go through an authorization process. Here is a list of available options divided by roles:

**Admin:**
- Add and delete clients
- View lists, add, and delete users
- View lists and delete closed tasks
- User permissions listed below

**User:**
- View and manage assigned tasks
- Update task statuses
- Fetch task details and statistics

### Technologies Used

- Spring Framework:
  - Spring Boot
  - Spring Data JPA
  - Spring Security
  - Spring Web
- Gradle as a build tool
- PostgreSQL database
- Flyway for database migrations
- Thymeleaf for templating
- OpenAPI/Swagger for API documentation
- Lombok for boilerplate code reduction
- MapStruct for object mapping
- JUnit and Mockito for testing
- Testcontainers for containerized testing
- WireMock for HTTP stubbing and mocking
- REST Assured for API testing


## Usage

Default login credentials:
 - Admin:
   - Username: Wojtek
   - Password: wojtek123
 - Serviceman:
   - Username: Karol
   - Password: karol123

For the application to function correctly, it is necessary to log in, as it uses the data of the logged-in user.




