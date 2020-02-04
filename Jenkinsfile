def JOB_NAME
def BUILD_NUMBER
def STAGE_NAME
def EXCEPTION_DETAILS
def SUMMARY
def DETAILED_DESCRIPTION
def var1


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
						throw new Exception("Throw some random exception")
					}
					catch (err) {
						echo 'Inside catch .. caught exception'
						echo 'Marking Build as UNSTABLE'
						currentBuild.result = 'UNSTABLE'	
						JOB_NAME = "${env.JOB_BASE_NAME}"
						BUILD_NUMBER = "${currentBuild.number}"
						STAGE_NAME = "${env.STAGE_NAME}"
						SUMMARY = "Pipeline ${JOB_NAME} : " + "Stage ${env.STAGE_NAME} >> Failed"  
						var1 = "Pipleline >> ${env.JOB_BASE_NAME} : " + "Build Number >> ${currentBuild.number} : " + "Stage >> ${env.STAGE_NAME}"
						DETAILED_DESCRIPTION = "${var1}. ${env.STAGE_NAME} stage failed with error : ${err}"
						echo "SUMMARY ::  ${SUMMARY}"
						echo 'Incremental Build has failed!'						
						throw new Exception("Tesing stage failed with error : ${err}")
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
						SUMMARY = "Pipeline ${JOB_NAME} : " + "Stage ${env.STAGE_NAME} >> Failed"  
						var1 = "Pipleline >> ${env.JOB_BASE_NAME} : " + "Build Number >> ${currentBuild.number} : " + "Stage >> ${env.STAGE_NAME}"
						DETAILED_DESCRIPTION = "${var1}. ${env.STAGE_NAME} stage failed with error : ${err}"
						echo "SUMMARY ::  ${SUMMARY}"
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
				 echo "snapshot is :  ${SUMMARY}"
				 echo "Raising a JIRA Bug for ${JOB_NAME} : ${STAGE_NAME}"
				 build job: 'JiraAPI', 
					parameters: [
						string(name: 'projectKey', value: "${projectKey}"),
						string(name: 'summary', value: "${SUMMARY}"),
						string(name: 'description', value: "${DETAILED_DESCRIPTION}"),
						string(name: 'issuetype', value: "${issue_type}"),
						string(name: 'assignee', value: "${assignee}")
					]
				 
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
