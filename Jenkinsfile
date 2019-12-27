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
			steps{
                jira_call_create_issue()
            }
			echo 'Bug raised in Jira'
			
        }
        changed {
            echo 'Things were different before...'
        }
    }
}

def jira_call_create_issue()
{	
	response = bat(  returnStdout: true,
                    script: "curl -u manujadli:Jbourne@2702 --header "Content-Type: application/json" -X POST --data "{\"fields\":{\"project\":{\"key\":\"MET\"},\"summary\":\"Test ChargenNr\",\"description\":\"some description\",\"issuetype\":{\"id\":\"10006\"}}}" http://localhost:8080/rest/api/2/issue/"
                )
    echo "response: ${response}"    
    return "${status}"
}