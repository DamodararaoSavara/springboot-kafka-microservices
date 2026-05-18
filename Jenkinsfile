pipeline {
    agent any

    tools {
        maven 'Maven'
        jdk 'JDK17'
    }

    stages {

        stage('Checkout') {
            steps {
                git branch: 'main',
                url: 'https://github.com/DamodararaoSavara/springboot-kafka-microservices.git'
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean install'
            }
        }

    }
}