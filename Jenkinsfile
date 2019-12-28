pipeline {
    agent any

    stages {
        stage('Cleaning Stage') {
		println "hello Manu its cleaning stage"
            steps {
                bat "mvn clean"
            }
        }
        stage('Testing Stage') {
            steps {       
		    echo 'Testing Stage'				
            }
        }
        stage('Packaging Stage') {
            steps {
                bat "mvn package"
            }
        }		
		
    }

    post {
        always {
            echo 'One way or another, I have finished'           
        }
        success {
            echo 'I succeeeded!'
        }
        unstable {
            echo 'I am unstable :/'
        }
        failure {
            echo 'I failed :('
	    echo 'Raising a bug In Jira'			
	    echo 'Bug raised in Jira'
			
        }
        changed {
            echo 'Things were different before...'
        }
    }
}
