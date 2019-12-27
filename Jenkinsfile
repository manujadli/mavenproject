pipeline {
    agent any

    stages {
        stage('Cleaning Stage') {
            steps {
                bat "mvn clean"
            }
        }
        stage('Testing Stage') {
            steps {
                bat "mvn test"
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
	    jira_call_create_issue()
	    echo 'Bug raised in Jira'
			
        }
        changed {
            echo 'Things were different before...'
        }
    }
}

def jira_call_create_issue()
{	
    echo 'Raising a bug In Jira'	
    echo 'Bug raised in Jira'	
    echo "response: OK"    
    return "response: OK"
}
