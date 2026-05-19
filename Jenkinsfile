pipeline {
    agent any

    stages {

        stage('Checkout') {
            steps {
                git branch: 'main',
                url: 'https://github.com/DamodararaoSavara/springboot-kafka-microservices.git'
            }
        }

        stage('Build Auth Service') {
            steps {
                dir('auth-service') {
                    bat 'mvn clean install'
                }
            }
        }

        stage('Build Order Service') {
            steps {
                dir('order-service') {
                    bat 'mvn clean install'
                }
            }
        }

        stage('Build API Gateway') {
            steps {
                dir('api-gateway') {
                    bat 'mvn clean install'
                }
            }
        }

    }
}