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
				script {
					try {
						echo 'Starting Testing Stage'
						bat "mvn test"	
					}
					catch (err) {
						echo 'Inside catch .. caught exception'
						echo 'Something failed, I should sound the klaxons!'
						echo 'Err: Incremental Build failed with Error: ' + ${err}			
						
					}
					
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
    def command = """{\"fields\":{\"project\":{\"key\":\"MET\"},\"summary\":\"Maven Test Failed addTwoNumbersTest\",\"description\":\"addTwoNumbersTest(org.jenkins.maven.integration.JenkinsCalculatorTest) java.lang.AssertionError: expected:<11> but was:<15>\",\"reporter\":{\"name\":\"manujadli\"},\"issuetype\":{\"id\":\"10006\"}}}"""
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