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
				bat "curl -u manujadli:Jbourne@2702 --header "Content-Type: application/json" -X POST --data "{\"fields\":{\"project\":{\"key\":\"MET\"},\"summary\":\"Test ChargenNr\",\"description\":\"some description\",\"issuetype\":{\"id\":\"10006\"}}}" http://localhost:8080/rest/api/2/issue/"
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
