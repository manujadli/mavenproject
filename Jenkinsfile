pipeline {
    agent any

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
						summary =  "Build Failure" + " <<>> " +  "${env.JOB_BASE_NAME}" + " <<>> " + "${env.STAGE_NAME}" + " <<>> " + ${currentBuild.number}
						echo 'Incremental Build has failed!'						
						echo "Err: Incremental Build failed with Error:  ${err}"	
						test_failed()
						
						
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
			echo "Summary is : ${summary}"			
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
