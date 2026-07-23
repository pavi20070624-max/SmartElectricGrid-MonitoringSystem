#  SmartElectricGrid-MonitoringSystem
Smart Grid Monitoring System built using Spring Boot Microservices for real-time transformer monitoring, sensor data management, alert generation, and maintenance tracking.

# Project Overview:

The Smart Grid Monitoring System is a Spring Boot Microservices application designed to monitor the health and performance of electrical transformers. It manages transformer information, records sensor readings, generates alerts for abnormal conditions, and tracks maintenance activities through independent microservices.

The project consists of four core services—Transformer Service, Sensor Service, Alert Service, and Maintenance Service—that communicate using REST APIs and RestTemplate. Each service has its own database, ensuring a modular, scalable, and loosely coupled architecture.

The application demonstrates key backend development concepts such as RESTful APIs, Spring Data JPA, MySQL, DTO Pattern, Bean Validation, Global Exception Handling, and inter-service communication, making it a practical implementation of a real-world microservices architecture.

# System Architecture:
```
                    +----------------------+
                    |      API Client      |
                    | (Postman / Frontend) |
                    +----------+-----------+
                               |
                               ▼
        ------------------------------------------------
        |           Spring Boot Microservices           |
        ------------------------------------------------

      +------------------------+
      | Transformer Service    |
      | Manages transformers   |
      +-----------+------------+
                  |
                  | Validates Transformer
                  ▼
      +------------------------+
      | Sensor Service         |
      | Stores sensor data     |
      +-----------+------------+
                  |
                  | Detects abnormal readings
                  ▼
      +------------------------+
      | Alert Service          |
      | Generates alerts       |
      +-----------+------------+
                  |
                  | Creates maintenance requests
                  ▼
      +------------------------+
      | Maintenance Service    |
      | Tracks maintenance     |
      +------------------------+

     Each Microservice
     ✔ Independent Business Logic
     ✔ Dedicated MySQL Database
     ✔ RESTful APIs
```
# Architecture Highlights:
1)Transformer Service manages transformer information.

2)Sensor Service validates transformers and stores sensor readings.

3)Alert Service generates alerts based on abnormal sensor conditions.

4)Maintenance Service manages maintenance requests generated from alerts.

5)Services communicate through REST APIs using RestTemplate.

# WorkFlow:
```
                     Start
                       │
                       ▼
         Register a Transformer
                       │
                       ▼
      Transformer Service stores data
                       │
                       ▼
          Record Sensor Readings
                       │
                       ▼
      Sensor Service validates the
      transformer using Serial Number
                       │
                       ▼
         Store Sensor Readings
                       │
                       ▼
      Are the readings abnormal?
                 │           │
              No │           │ Yes
                 ▼           ▼
         Store Successfully  Generate Alert
                              │
                              ▼
                      Alert Service creates
                      alert with severity
                              │
                              ▼
                  Create Maintenance Request
                              │
                              ▼
                 Maintenance Service assigns
                      and tracks status
                              │
                              ▼
                  Maintenance Completed
                              │
                              ▼
                            End
```
# Technology Stack:
Technology Stack

Programming Language -> Java 21

Framework	-> Spring Boot

Build Tool	-> Maven

Database	-> MySQL

ORM	Spring Data JPA (Hibernate)

API Development	Spring Web (REST APIs)

Inter-Service Communication	RestTemplate

Validation	-> Bean Validation (Jakarta Validation)

Boilerplate Reduction	Lombok

Testing	Postman

Version Control	Git & GitHub

IDE	IntelliJ IDEA

# MicroService:

# Transformer Service

Responsibility:

Manages transformer information such as registration, location, capacity, and operational status. It also provides transformer details to other services for validation.

Endpoints:
| Method | Endpoint                          | Description                      |
| ------ | --------------------------------- | -------------------------------- |
| POST   | `/transformers`                   | Add a new transformer            |
| GET    | `/transformers`                   | Get all transformers             |
| GET    | `/transformers/{id}`              | Get transformer by ID            |
| GET    | `/transformers/serial/{serialNo}` | Get transformer by serial number |
| PUT    | `/transformers/{id}`              | Update transformer details       |
| DELETE | `/transformers/{id}`              | Delete a transformer             |


# Sensor Service :
Responsibilty:

Records transformer sensor readings and validates the transformer before storing the data.

Endpoints:

| Method | Endpoint                         | Description                    |
| ------ | -------------------------------- | ------------------------------ |
| POST   | `/sensor`                        | Record sensor data             |
| GET    | `/sensor`                        | Get all sensor readings        |
| GET    | `/sensor/{id}`                   | Get a sensor reading by ID     |
| GET    | `/sensor/transformer/{serialNo}` | Get readings for a transformer |
| DELETE | `/sensor/{id}`                   | Delete a sensor record         |

# Alert Service:
Responsibility

Creates and manages alerts whenever abnormal sensor readings are detected.

Endpoints:

| Method | Endpoint       | Description        |
| ------ | -------------- | ------------------ |
| POST   | `/alerts`      | Create a new alert |
| GET    | `/alerts`      | Get all alerts     |
| GET    | `/alerts/{id}` | Get alert by ID    |
| PUT    | `/alerts/{id}` | Update an alert    |
| DELETE | `/alerts/{id}` | Delete an alert    |

# Maintenance Service:
Responsibility:

Tracks maintenance requests raised from alerts and updates their progress until completion.

Endpoints:

| Method | Endpoint            | Description                  |
| ------ | ------------------- | ---------------------------- |
| POST   | `/maintenance`      | Create a maintenance request |
| GET    | `/maintenance`      | Get all maintenance records  |
| GET    | `/maintenance/{id}` | Get maintenance record by ID |
| PUT    | `/maintenance/{id}` | Update maintenance status    |
| DELETE | `/maintenance/{id}` | Delete a maintenance record  |


# Inter-Service Communication:

The microservices communicate using REST APIs through Spring's RestTemplate, allowing each service to access the information it needs while remaining independent.

Communication Flow
Sensor Service → Transformer Service
Validates the transformer using its serial number before storing sensor readings.
Alert Service → Sensor Service
Retrieves sensor information required for generating alerts.
Maintenance Service → Transformer Service
Retrieves transformer details before creating and managing maintenance requests.

# Alert Generation Rules:

| Condition                        | Alert Type    | Priority |
| -------------------------------- | ------------- | -------- |
| Temperature > **90°C**           | OVERHEATING   | HIGH     |
| Voltage < **180V** or > **260V** | VOLTAGE_FAULT | HIGH     |
| Current > **100A**               | OVERCURRENT   | HIGH     |
| Oil Level < **30%**              | LOW_OIL_LEVEL | CRITICAL |

Note: The threshold values are configurable and can be adjusted based on transformer specifications.

# Project Structure:
```
Smart-Grid-Monitoring-System
│
├── Transformer Service
├── Sensor Service
├── Alert Service
├── Maintenance Service
│
├── README.md
└── .gitignore
```
# Database:

Each microservice maintains its own database.

Transformer Database
Sensor Database
Alert Database
Maintenance Database
This follows the Database per Microservice design pattern.

# Running the Project:
Running the Project
Start the services in the following order:

Transformer Service
Sensor Service
Alert Service
Maintenance Service
API Gateway
Access all APIs through:

http://localhost:8090
Example:

GET http://localhost:8090/transformers

# Testing:

The APIs were tested using Postman.

Example workflows tested:

Add Transformer

Add Sensor Reading

Automatic Alert Generation

Automatic Maintenance Scheduling

Maintenance Completion

Alert Resolution

# Feature Implemented:

REST APIs
CRUD Operations
Bean Validation
Global Exception Handling
DTO Pattern
Layered Architecture
Spring Data JPA
MySQL Integration
RestTemplate Communication
API Gateway Routing
Automatic Alert Generation
Automatic Maintenance Scheduling
Alert Resolution
Postman Testing

# Output(Testing):

<img width="1179" height="815" alt="image" src="https://github.com/user-attachments/assets/b4819188-d1a3-4b42-b79e-4db34bfc8631" />


<img width="1164" height="708" alt="image" src="https://github.com/user-attachments/assets/04dbc468-1aac-41bf-9245-4105ce165b46" />


<img width="1187" height="561" alt="image" src="https://github.com/user-attachments/assets/0f815a1a-176c-494d-b6ec-518e6ceaf41a" />


<img width="1182" height="644" alt="image" src="https://github.com/user-attachments/assets/58c426b0-290a-4c2b-81fa-6f545e283314" />


<img width="1182" height="795" alt="image" src="https://github.com/user-attachments/assets/f6cd48fe-c42a-4487-a7d7-c19063d743b8" />


<img width="1183" height="818" alt="image" src="https://github.com/user-attachments/assets/722b1da2-1106-4487-817e-9b86be44a964" />


<img width="1186" height="784" alt="image" src="https://github.com/user-attachments/assets/9c42f67f-0ac4-4cd9-8ba8-43c1f9ee4f4c" />


<img width="1179" height="673" alt="image" src="https://github.com/user-attachments/assets/933b8265-30f5-441c-8487-72ea5425351e" />


<img width="1183" height="824" alt="image" src="https://github.com/user-attachments/assets/5f387e06-f9bf-43a6-b243-50a87f836701" />


<img width="1180" height="792" alt="image" src="https://github.com/user-attachments/assets/c30bf140-88a8-4fa2-8740-da5737aa4530" />


<img width="1187" height="809" alt="image" src="https://github.com/user-attachments/assets/1cadf03d-33f4-47dc-86c5-08b8f4e085b2" />


<img width="1179" height="675" alt="image" src="https://github.com/user-attachments/assets/d2946da5-1428-4682-a301-4ec8c822bc1c" />

# Author:

PAVITHRA S

B.E. Computer Science and Engineering

Spring Boot | Java | Microservices | Backend Development







