pipeline {
    agent {
		label 'java'
	}
	
	options {
		// the gitlab connection to use with admpico token 
		gitLabConnection('git-pfc.rb.echonet')
		disableConcurrentBuilds()
		buildDiscarder(logRotator(numToKeepStr: '5', artifactNumToKeepStr: '5'))
		timeout(time: 1, unit: 'HOURS')
		preserveStashes()
	}

	parameters {
		booleanParam(name: "RELEASE", description: "Build a release from current commit.", defaultValue: false)
	}
	    
    libraries {
        lib('pfPipeline@master')
    }

    stages {
    
    	stage('build') {
			when {
				expression {!params.RELEASE}
			}
			steps {
				script {
					pom = readMavenPom file: 'pom.xml'
					// write version
					writeFile file: "version", text: "${pom.version}"
					stash name: "version", includes: "version"
				}

				// maven build
				pfMvnBuild(options: '-U')
			}
		}
		
   		stage('release') {
			when {
				expression {params.RELEASE}
			}
			steps {
				script {
					pom = readMavenPom file: 'pom.xml'
					version = pom.version.replace("-SNAPSHOT", "")
					// write version
					writeFile file: "version", text: "${version}"
					stash name: "version", includes: "version"
				}

				// do release
				pfMvnRelease(extraGoalsAndOptions: '-U')

			}
		}
		
        /*stage('Tag') {
           when {
               branch  "${RELEASE_BRANCH}"
           }
           steps {
               script{
                   encodePwd = java.net.URLEncoder.encode("${GITLAB_CREDS_PSW}", "UTF-8")
                   sh """
                   git config --global http.sslVerify false
                   git tag v${VERSION}
                   git push https://${GITLAB_CREDS_USR}:${encodePwd}@${GITLAB_HOST} v${VERSION}
                   """
               }
           }
        }*/

    }

    post {
        always {
            // register unit test result
			junit(testResults: '**/target/surefire-reports/**/*.xml', allowEmptyResults: true)
			// send notification ( mail, gitlab, slack, ... )
			pfSendNotification(to: 'jalel.hazbri@bnpparibas-pf.com');
            echo "Finished!"
            
        }
        success {
            echo "Successed Pipeline: ${currentBuild.fullDisplayName}."
        }
        unstable {
            echo "Unstable Pipeline: ${currentBuild.fullDisplayName}."
        }
        failure {
            echo "Failed Pipeline: ${currentBuild.fullDisplayName} : Something is wrong with ${env.BUILD_URL}"
        }
    }
}
