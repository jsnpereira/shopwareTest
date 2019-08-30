pipeline {
    agent {
        docker {
             image 'maven:3-alpine'
             args '-v $HOME/.m2:/root/.m2'
        }
    }

    parameters {
        choice(choices: ['chrome', 'firefox'], description: 'What browser?', name: 'pickBrowser')
    }

    stages {
        stage('Build') {
            steps {
             git(
     			  url: 'https://github.com/jsnpereira/shopwareTest.git',
       			  credentialsId: '5d840afb-760b-4c77-b1a2-7be3ef4206f6',
       			  branch: "master"
    			   )

            echo 'Environment: ${params.pickBrowser}'
    			  echo 'Start install'
            sh 'mvn clean install -Dmaven.test.skip=true'
          }
        }
        stage('Test') {

            steps {
                 echo 'Testing..'
                echo 'Environment: ${params.pickBrowser}'
               	sh 'mvn install -DartifactId=testng -Dbrowser=$${params.pickBrowser}'
            }
        }
        stage('publish') {
            steps {
                echo 'publishing....'
            }
        }
    }
}
