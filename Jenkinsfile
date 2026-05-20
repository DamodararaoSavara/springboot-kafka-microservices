pipeline {
    agent any

    environment {
        DB_USERNAME = credentials('db-username')
        DB_PASSWORD = credentials('db-password')
        JWT_SECRET = credentials('jwt-secret')
    }

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

        stage('Docker Build Auth Service') {
             steps {
                 dir('auth-service') {
                     bat 'docker build -t damodararaosavara/auth-service:latest .'
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

        stage('Docker Build Order Service') {
             steps {
                  dir('order-service') {
                            bat 'docker build -t damodararaosavara/order-service:latest .'
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
        stage('Docker Build API Gateway') {
             steps {
                 dir('api-gateway') {
                            bat 'docker build -t damodararaosavara/api-gateway:latest .'
                 }
             }
        }

        stage('Docker Login') {
              steps {
                   withCredentials([usernamePassword(
                        credentialsId: 'dockerhub-creds',
                        usernameVariable: 'DOCKER_USERNAME',
                        passwordVariable: 'DOCKER_PASSWORD'
                   )]){
                         bat 'docker login -u %DOCKER_USERNAME% -p %DOCKER_PASSWORD%'
                   }
              }
        }
        stage('Docker Push Auth Service') {
             steps {
                 bat 'docker push damodararaosavara/auth-service:latest'
             }
        }

        stage('Docker Push Order Service') {
             steps {
                 bat 'docker push damodararaosavara/order-service:latest'
             }
        }
        stage('Docker Push API Gateway') {
             steps {
                 bat 'docker push damodararaosavara/api-gateway:latest'
             }
        }

    }
}