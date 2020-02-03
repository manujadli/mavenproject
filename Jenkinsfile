def JOB_NAME
def BUILD_NUMBER
def STAGE_NAME
def EXCEPTION_DETAILS
def SUMMARY
def DETAILED_DESCRIPTION


pipeline {
    agent any
		environment {
		ORGANISATION = 'dishadmin'
		GOGSREPO = 'cicd_test_os'
		TEST_ENV = 'dev'
		GOGS_HOST = "http://172.27.14.80:32734/"
		NEXUS_URL = "http://172.27.14.80:32739/"
		VNF_NAME = 'cicd_test_os'	
		projectKey = 'MET'
		summary = 'Default Summary'
		detailed_desc = "Default Description"
		issue_type = 'Bug'
		assignee = 'manujadli'
	}

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
						echo 'Inside catch .. caught exception'
						echo 'Marking Build as UNSTABLE'
						currentBuild.result = 'UNSTABLE'	
						JOB_NAME = "${env.JOB_BASE_NAME}"
						BUILD_NUMBER = "${currentBuild.number}"
						STAGE_NAME = "${env.STAGE_NAME}"
						temp_var = "${env.JOB_BASE_NAME} : " + "${currentBuild.number} : " + "${env.STAGE_NAME}"
						echo "temp_var ::  ${temp_var}"
						echo 'Incremental Build has failed!'						
						throw new Exception("Tesing stage failed with error : ${err}")
						
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
	
		success {
					echo 'I have got success'					
			}
        
		failure {
				 echo 'I have failed'
				 echo "Summary is ${JOB_NAME}"
				 
			}
		
		always {
            echo 'One way or another, I have finished'
			echo "RESULT: ${currentBuild.result}"				
        }        
    }
}


def test_failed() {
    echo 'Inside test_failed()..'
    
}

def clean_failed() {
    echo 'Inside clean_failed()..'
    response = "200 OK"
    return response
}

def packaging_failed() {
    echo 'Inside packaging_failed()..'
    
}
