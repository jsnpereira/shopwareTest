pipeline {
    agent any
    
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
    			echo 'Start install'
                sh 'mvn clean install -Dmaven.test.skip=true'
            }
        }
        stage('Test') {
        	
            steps {
                echo "You choose: ${params.pickBrowser}"
             	echo 'Testing..'
                sh 'mvn install -DartifactId=testng -Dbrowser=${params.pickBrowser}'
            }
        }
        stage('publish') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}
