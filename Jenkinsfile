pipeline {
    agent any
    tools {
        maven 'mvn'  // Name of the Maven installation defined in Global Tool Configuration
    }
    stages{
        stage('Build'){
            steps{
                echo 'Building'   
                sh 'mvn -B -DskipTests clean package'
            }
        }
        stage('Test'){
            steps{
                echo 'Testing'
                sh 'mvn test'
            }
        }
    }
    post{
    	always{
    		archiveArtifacts artifacts: '**/target/*.jar', allowEmptyArchive: true
    		junit '**/target/surefire-reports/*.xml'
    	}
    	failure{
    		echo "failed"
    	}
    }
}
