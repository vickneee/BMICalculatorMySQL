pipeline {

    agent any

    environment {
            PATH = "/usr/local/bin:${env.PATH}"

            // Define Docker Hub credentials ID
            DOCKERHUB_CREDENTIALS_ID = 'Docker_Hub'
            // Define Docker Hub repository name
            DOCKERHUB_REPO = 'vickneee/bmi_calculator_mysql'
            // Define Docker image tag
            DOCKER_IMAGE_TAG = 'latest'
        }

    tools {
        maven 'Maven3'
    }

    stages {

        stage('Checking') {
            steps{
                git branch:'main', url:'https://github.com/vickneee/BMICalculatorMySQL.git'
            }
        }

        stage ('Build') {
            steps {
                sh  'mvn clean install'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn clean install'
            }
        }

        stage('Code Coverage') {
            steps {
                sh 'mvn jacoco:report'
            }
        }

        stage('Build Docker Image') {
             steps {
                sh 'docker build -t $DOCKERHUB_REPO:$DOCKER_IMAGE_TAG .'
             }
        }

        // Create repo in Docker Hub to push it (Run Dockerfile)
        stage('Push Docker Image to Docker Hub') {
            steps {
                withCredentials([usernamePassword(credentialsId: "${DOCKERHUB_CREDENTIALS_ID}", usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                    sh '''
                        docker login -u $DOCKER_USER -p $DOCKER_PASS
                        docker push $DOCKERHUB_REPO:$DOCKER_IMAGE_TAG
                    '''
                }
            }
        }
    }
}