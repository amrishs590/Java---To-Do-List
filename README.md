**📝 To-Do List Web Application**

A simple yet secure To-Do List Web App built using JSP (Client), Java Servlets (Backend), MySQL (Database), and Apache Tomcat (Server).
This project allows users to create, view, update, and delete tasks efficiently, demonstrating a secure full-stack Java web application setup.

--- 

**🚀 Features**

- ➕ Add new tasks
- ✏️ Edit existing tasks
- ✅ Mark tasks as completed or pending
- ❌ Delete tasks
- 💾 Data persistence using MySQL
- 🔐 User session handling
- 🛡️ Secured against XSS and CSRF attacks
- 🌐 Simple and responsive JSP UI

---

**🧱 Tech Stack**

Layer	Technology Used
Client (Frontend)	JSP, HTML, CSS
Server (Backend)	Java Servlets
Database	MySQL
Web Server	Apache Tomcat
IDE (Recommended)	Eclipse / IntelliJ IDEA
Build Tool	Manual Deployment (WAR)

---

**⚙️ Setup Instructions**

**1️⃣ Prerequisites**
  Make sure you have the following installed:

- Java JDK 8+
- Apache Tomcat 9+
- MySQL Server
- Eclipse IDE for Enterprise Java Developers
- MySQL Connector JAR (add to lib folder of Tomcat or project

**2️⃣ Database Setup**

CREATE DATABASE todolist;
USE todolist;
CREATE TABLE tasks (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    description TEXT,
    status ENUM('Pending', 'Completed') DEFAULT 'Pending'
);

**3️⃣ Configure Database Connection**

- private static final String URL = "jdbc:mysql://localhost:3306/todolist";
- private static final String USER = "root";
- private static final String PASSWORD = "yourpassword";

**4️⃣ Deploy Project to Tomcat**

- Open Eclipse
- Go to File → Import → Existing Projects into Workspace
- Select the project folder
- Add the project to Tomcat Server
- Start the server and open the app at
- 👉 http://localhost:8080/ToDoList

---

**🛡️ Security Features**

**Security is a key focus in this project**. It includes protection against common web vulnerabilities:

**🔒 Cross-Site Scripting (XSS) Protectio**

- All user inputs are validated and encoded before rendering in JSP pages.
- Output data is HTML-escaped using JSTL tags or Java string escaping to prevent script injection.
- Special characters like <, >, ", ', and & are sanitized before display.

**🔑 Cross-Site Request Forgery (CSRF) Protection**

- Each form submission includes a unique CSRF token stored in the session.
- The server validates this token before processing any form request.
- Requests without valid tokens are rejected, ensuring that no malicious third-party site can perform unauthorized actions.

**🧰 Additional Security**

- Database operations use Prepared Statements (no SQL injection).
- Session timeout is enforced to prevent unauthorized access.
- Sensitive data like passwords are never stored in plain text.

**🧠 Learning Highlights**

- Implemented MVC pattern with JSP & Servlets
- Secure JDBC connection setup with MySQL
- Applied XSS & CSRF prevention mechanisms
- Hands-on experience deploying to Tomcat Server

