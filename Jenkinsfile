def CONTAINER_NAME="kwetter"
def CONTAINER_TAG="latest"

node {
    stage('Initialize'){
        def dockerHome = tool 'Docker'
        def mavenHome  = tool 'Maven3'
        env.PATH = "${dockerHome}/bin:${mavenHome}/bin:${env.PATH}"
    }

    stage('Checkout') {
        checkout scm
    }

    stage('Build'){
        sh "mvn clean install"
    }

    stage('Sonar'){
        try {
            sh "mvn sonar:sonar"
        } catch(error){
            echo "The sonar server could not be reached ${error}"
        }
    }

    stage('Docker-compose'){
        try {
            sh "sudo docker-compose down"
            sh "sudo docker-compose up -d"
        }catch(error){}
    }
}