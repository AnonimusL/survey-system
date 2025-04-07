# survey-system
The survey-system is a comprehensive platform designed to streamline the survey creation and analysis process. It serves as a versatile tool for conducting surveys, collecting user responses, offering pre-defined survey templates, recommending questions based on user input, and generating insightful reports.

## Table of Contents
- [Setup Instructions](README.md)
- [Functional requirements](docs/functional-requirements.md)
- [Design requirements](docs/design-requirements.md)
- [Non-functional requirements](docs/non-functional-requirements.md)

# Setup Instructions For Local Development Environment

## Backend side 

## Step 1: Clone the Repository
```bash
git clone https://github.com/RAFSoftLab/survey-system
cd survey-system
```

## Step 2: Build Docker Images for Services
### Service 1 - User Service
```bash
cd user-service
docker build -t user-service .
cd ..
```
### Service 2 - Survey Service
```bash
cd survey-service
docker build -t survey-service .
cd ..
```
## Step 3: Start Docker Compose
```bash
docker-compose up -d
```

In the docker-compose.yml file, a MySQL service is defined with the latest MySQL image, configured to run on port 3306. 

Each service (user-service, target-survey-service, survey-service) is linked to this MySQL service, creating separate databases.

Each service is exposed to its respective port:

- user-service on port 8080
- survey-service on port 8081
- target-survey-service on port 8083

Now, you have Docker containers running for your services along with MySQL.

## Frontend side
Frontend is not dockerized. Therefore, to run the frontend, you'll need to have Angular and Node.js installed on your machine. 

## Step 4: Start Angular application
Navigate to your Angular project directory and run the following commands:
```bash
cd angular-frontend
npm install
ng serve
```

Access your Angular frontend at http://localhost:4200 in your web browser.

![ss](https://github.com/RAFSoftLab/survey-system/assets/72028519/9e0e913b-c33a-40bd-9dda-76a773370ce0)




