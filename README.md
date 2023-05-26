# Mintos Project

Welcome to the Mintos project. This application provides weather information based on geographical coordinates.

# How to use
To use this application, follow these steps:

1. Install Docker on your machine.
2. Clone this repository to your local machine.
3. Build the Docker image by running the following command in the project directory:
   `docker build -t mintos .`
4. Run the Docker container by running the following command in the project directory:
   `./run.sh`
   Note: make sure you have executable permissions for the run.sh script, if not, run the following command to grant the executable permission:
   `chmod +x run.sh`
   Access the application by going to http://localhost:8080.

# Endpoints
This application has REST endpoint:

GET /weather: Returning the weather data determined by IP of the request originator.

# Useful links
Country details by IP can be retrieved using this public non-commercial endpoint: https://ipinfo.io/80.233.249.21/geo

Weather data can be retrieved using this public non-commercial endpoint: https://api.open-meteo.com/v1/dwd-icon?latitude=%f&longitude=%f&hourly=temperature_2m

# Dockerfile and run.sh
This application uses a Dockerfile to build a Docker image of the application.
The run.sh script is used to automate the process of starting the container with the appropriate configuration.
Please make sure you have Docker installed on your machine before using the Dockerfile and run.sh script.