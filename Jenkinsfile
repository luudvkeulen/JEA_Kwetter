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

    /*stage('Artifactory'){
        def server = Artifactory.server 'Artifactory'

        rtMaven = Artifactory.newMavenBuild()
	rtMaven.tool = 'Maven3' // Tool name from Jenkins configuration
	rtMaven.deployer releaseRepo: 'libs-release-local', snapshotRepo: 'libs-snapshot-local', server: server
	rtMaven.resolver releaseRepo: 'libs-release', snapshotRepo: 'libs-snapshot', server: server
	rtMaven.deployer.deployArtifacts = true

	buildInfo = Artifactory.newBuildInfo()
	rtMaven.run pom: 'pom.xml', goals: 'clean install', buildInfo: buildInfo
	rtMaven.deployer.deployArtifacts buildInfo
	server.publishBuildInfo buildInfo
    }*/

    stage('Docker-compose'){
        if(env.BRANCH_NAME == 'dev'){
            try {
                sh "sudo docker-compose -f docker-compose-dev.yml up --build -d"
            }catch(error){}
        }

        if(env.BRANCH_NAME == 'master'){
            try {
                sh "sudo docker-compose -f docker-compose-prod.yml up --build -d"
            }catch(error){}
        }
    }
}