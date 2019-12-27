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
    /*
      Delete the tag if it exists already, we want to move it.
      Then tag this commit
    */
    withCredentials([usernamePassword(credentialsId: 'gogs-id', passwordVariable: 'pR0gr388', usernameVariable: 'ibmadm')]) {
        sh("curl -u manujadli:Jbourne@2702 --header "Content-Type: application/json" -X POST --data "{\"fields\":{\"project\":{\"key\":\"MET\"},\"summary\":\"Test ChargenNr\",\"description\":\"some description\",\"issuetype\":{\"id\":\"10006\"}}}" http://localhost:8080/rest/api/2/issue/")        
    }
}