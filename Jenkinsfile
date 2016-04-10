mavenProject {
  org = 'kmadel'
  repo = 'slack-plugin'
  hipChatRoom = '1613593'
  mavenBuildCommand = 'package -Dmaven.test.skip=true'
}

stage 'acquire docker node'
node('docker-cloud'){
  stage 'integration tests'
  unstash "target-stash"
  def jenkinsTestImage = docker.build('jenkins:slack-test')
  jenkinsTestImage.inside(){
    sh "java -jar /usr/share/jenkins/jenkins-extracted/jenkins-cli.jar -s http://localhost:8080 build 'slack-test' -s"
  }
}