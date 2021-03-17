# example-no-environment

This demonstrates that Jenkins currently does not pass environment variables to plugins. 

Just create a pipeline like this:

```pipeline {   
    agent any
    environment {
        GITUSER = 'MyExampleUser'
    }
    stages {
        stage('someTestStage') {
            steps {
                doStuff()
            }
        }
        stage('printViaShell') {
            steps {
                sh 'echo "$GITUSER"'            
            }
        }
    }
}```

and execute it, and you'll see that the shell variable is printed correctly and the plugin does not get the environment variable.
