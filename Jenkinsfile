def API = "curl -u manujadli:Jbourne@2702 --header "Content-Type: application/json" -X POST --data "{\"fields\":{\"project\":{\"key\":\"MET\"},\"summary\":\"Test ChargenNr\",\"description\":\"some description\",\"issuetype\":{\"id\":\"10006\"}}}" http://localhost:8080/rest/api/2/issue/"
def apiKey = ""

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
	    apiKey = bat (script: API, returnStdout: true)
	    echo 'Bug raised in Jira'
			
        }
        changed {
            echo 'Things were different before...'
        }
    }
}
