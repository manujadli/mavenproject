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
			jira_call_create_issue()
			echo 'Bug raised in Jira'
			
        }
        changed {
            echo 'Things were different before...'
        }
    }
}

def jira_call_create_issue()
{	
	echo 'Raising a bug In Jira'	
	echo 'Bug raised in Jira'	
    echo "response: OK"  

	def post = new URL("http://localhost:8080/rest/api/2/issue/").openConnection();
	def message = '{\"fields\":{\"project\":{\"key\":\"MET\"},\"summary\":\"Test ChargenNr\",\"description\":\"some description\",\"issuetype\":{\"id\":\"10006\"}}}'
	post.setRequestMethod("POST")
	post.setDoOutput(true)
	post.setRequestProperty("Content-Type", "application/json")
	post.getOutputStream().write(message.getBytes("UTF-8"));
	def postRC = post.getResponseCode();
	println(postRC);
	if(postRC.equals(200)) {
		println(post.getInputStream().getText());
	}


	
    return "response: OK"
}
