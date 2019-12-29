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
					 bat "mvn test"					
				}							
            }
        }
        stage('Packaging Stage') {
            steps {
                bat "mvn package -DskipTests"
            }
        }		
		
    }

}