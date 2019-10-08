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
		
		stage('run-parallel-branches') {
			steps {
				parallel(
					a: {
						echo "This is branch a"
					   },
					b: {
						echo "This is branch b"
					   }
				)
			}
		}
    }
}