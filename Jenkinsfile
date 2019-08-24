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
    			script{
             		env.BROWSER = '${params.pickBrowser}'
            	}
    			echo 'Start install'
                sh 'mvn clean install -Dmaven.test.skip=true'
            }
        }
        stage('Test') {
        	
            steps {
            	echo 'Environment: ${env.BROWSER}'
            	echo 'Params: ${params.pickBrowser}'
            	echo 'Params 2: ${pickBrowser} '
                echo 'Testing..'
                sh 'mvn install -DartifactId=testng -Dbrowser="${env.BROWSER}"'
            }
        }
        stage('publish') {
            steps {
                echo 'publishing....'
            }
        }
    }
}