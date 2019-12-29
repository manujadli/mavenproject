pipeline {
    agent any

    stages {
        stage('Cleaning Stage') {		
            steps {
			   script {
					try {
						bat "mvn clean"
					}
					catch (err) {
						currentBuild.result = 'UNSTABLE'
						clean_failed()
						throw err
					}
				 }
				
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
						currentBuild.result = 'UNSTABLE'
						echo 'Inside catch .. caught exception'
						echo 'Incremental Build has failed!'						
						echo 'Err: Incremental Build failed with Error: ' + err.getLocalizedMessage()	
						test_failed()
						throw new Exception("Testing failed. Something went wrong!")
						
					}
					
				}							
            }
        }
        stage('Packaging Stage') {
            steps {
				script {
					try {
						bat "mvn package -DskipTests"
					}
					catch (err) {
						currentBuild.result = 'UNSTABLE'
						packaging_failed()						
						throw new Exception("Packaging stage failed. Something went wrong!")
					}
				
				}
			    
            }
        }		
		
    }

    post {
        always {
            echo 'One way or another, I have finished'
			echo "RESULT: ${currentBuild.result}"
        }        
    }
}


def test_failed() {
    echo 'Inside test_failed()..'
    def command = """{\"fields\":{\"project\":{\"key\":\"MET\"},\"summary\":\"Maven Test Failed addTwoNumbersTest\",\"description\":\"addTwoNumbersTest(org.jenkins.maven.integration.JenkinsCalculatorTest) java.lang.AssertionError: expected:<11> but was:<15>\",\"reporter\":{\"name\":\"manujadli\"},\"issuetype\":{\"id\":\"10006\"}}}"""
    echo(command)
    response = httpRequest (consoleLogResponseBody: true,
      contentType: 'APPLICATION_JSON',
      httpMode: 'POST',
	  authentication: 'credentialsID',
      requestBody: command,
      url: "http://localhost:8080/rest/api/2/issue/",
      validResponseCodes: "200,201,400,500")
	drop_email_notification("MET-36 - Maven Test Failed addTwoNumbersTest")
    return response
}

def clean_failed() {
    echo 'Inside clean_failed()..'
    response = "200 OK"
    return response
}

def packaging_failed() {
    echo 'Inside packaging_failed()..'
    response = "200 OK"	
	def command = """{\"fields\":{\"project\":{\"key\":\"MET\"},\"summary\":\"Maven Test Failed addTwoNumbersTest\",\"description\":\"addTwoNumbersTest(org.jenkins.maven.integration.JenkinsCalculatorTest) java.lang.AssertionError: expected:<11> but was:<15>\",\"reporter\":{\"name\":\"manujadli\"},\"issuetype\":{\"id\":\"10006\"}}}"""
    echo(command)
    response = httpRequest (consoleLogResponseBody: true,
      contentType: 'APPLICATION_JSON',
      httpMode: 'POST',
	  authentication: 'credentialsID',
      requestBody: command,
      url: "http://localhost:8080/rest/api/2/issue/",
      validResponseCodes: "200,201,400,500")
	 drop_email_notification("MET-36 - Maven Test Failed addTwoNumbersTest")
    return response
}

def drop_email_notification(jira_id) {
	echo 'Inside drop_email_notification()..'
	echo "Build Status RESULT: ${currentBuild.result}"	
	echo "current build number: ${currentBuild.number}"	
	echo "previous build number: ${currentBuild.previousBuild.getNumber()}"
	echo "Build Description: ${currentBuild.getDescription()}"
	echo "Job Name: ${env.JOB_NAME}"	
	def causes = currentBuild.rawBuild.getCauses().toString()
    echo "causes: ${causes}"	
	
	
	mail bcc: '', body: '''
	Job Name  Metaswitch2
	Build Number  14
	Build Status  FAILURE
	Jira Bug : MET-42 - Maven Test Failed addTwoNumbersTest
	Description : addTwoNumbersTest(org.jenkins.maven.integration.JenkinsCalculatorTest) java.lang.AssertionError: expected:<12> but was:<15>


    -Manu''', cc: '', from: '', replyTo: '', subject: 'Jenkins Build Unstable - Packaging Failed', to: 'majadli2@in.ibm.com, Kirill.Lukashin@ibm.com'
	
	
}