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
				echo 'Starting Testing Stage'
				try{
					bat "mvn test"
				}
				catch (exc) {
					echo 'Something failed, I should sound the klaxons!'
					echo err.getMessage()
					throw new Exception("Something went wrong!")
				}
								
            }
        }
        stage('Packaging Stage') {
            steps {
                bat "mvn package -DskipTests"
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
		raise_jira_bug()	    
	    echo 'Bug raised in Jira'
			
        }
        changed {
            echo 'Things were different before...'
        }
    }
}


def raise_jira_bug() {
    echo 'Inside raise_jira_bug()..'
    def command = """{\"fields\":{\"project\":{\"key\":\"MET\"},\"summary\":\"Test ChargenNr\",\"description\":\"some description\",\"reporter\":{\"name\":\"manujadli\"},\"issuetype\":{\"id\":\"10006\"}}}"""
    echo(command)
    response = httpRequest (consoleLogResponseBody: true,
      contentType: 'APPLICATION_JSON',
      httpMode: 'POST',
	  authentication: 'credentialsID',
      requestBody: command,
      url: "http://localhost:8080/rest/api/2/issue/",
      validResponseCodes: "200,201,400,500")
    return response
}