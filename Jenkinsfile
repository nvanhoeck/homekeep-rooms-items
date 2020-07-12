pipeline {
    agent any
    environment {
        NEW_VERSION = '0.0.21'
        ORG = 'homekeep'
        APP_NAME = 'homekeep-rooms-items'
    }

    tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven "apache-maven"
    }

    triggers {
        pollSCM('') // Enabling being build on Push
    }

    stages {
        stage('Clean') {
            when {
                expression {
                    BRANCH_NAME == 'develop'
                }
            }
            environment {
                PREVIEW_NAMESPACE = "$APP_NAME-$BRANCH_NAME".toLowerCase()
                HELM_RELEASE = "$PREVIEW_NAMESPACE".toLowerCase()
            }

            steps {
                // Get some code from a GitHub repository
                git 'https://github.com/nvanhoeck/homekeep-rooms-items.git'
                echo "building version ${NEW_VERSION}"
                echo 'Cleaning...'
                bat "mvn versions:set -DnewVersion=$NEW_VERSION"
                bat "mvn clean"
            }
        }

        stage('Build') {
            steps {
                // Get some code from a GitHub repository
                echo 'Building...'
                bat "mvn package"
            }
        }
        stage('Promote?') {
         when {
                        expression {
                            BRANCH_NAME == 'develop'
                        }
                    }

        steps {
            script {
                  try {
                    RELEASED = input(
                        id: 'userInput', message: 'Link patch to environment and release',
                        parameters: [
                            [$class: 'ChoiceParameterDefinition', defaultValue: 'No', choices: "Yes\nNo", description: 'Push to production', name: 'PROD']
                    ])
                      	env.RESPONSE = 'true'
                  } catch(Exception e) {
                        env.RESPONSE = 'false'
                        currentBuild.result = 'SUCCESS'
                  }
                }
            }
        }

        stage('Release') {
            when {
                expression {
                    BRANCH_NAME == 'develop'
                    RELEASED == 'true'
                }
            }

            steps {
                // Get some code from a GitHub repository
                bat "git checkout master"
                bat "git commit -am ${NEW_VERSION}"
                bat "git tag -a ${NEW_VERSION} -m ${NEW_VERSION}"
                bat "git merge origin/develop"
                bat "git push --set-upstream origin master"
                bat "mvn azure-functions:deploy"
            }
        }
    }
}
