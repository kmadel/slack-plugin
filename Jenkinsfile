mavenProject {
  org = 'kmadel'
  repo = 'slack-plugin'
  hipChatRoom = '1613593'
  mavenBuildCommand = 'package -Dmaven.test.skip=true'
}

stage 'acquire docker node'
node('docker-cloud'){
  stage 'build jenkins-slack image'
  unstash "target-stash"
  def jenkinsTestImage = docker.build('jenkins:slack-test')
  stage 'integration tests'
  sh "docker rm -f jenkins-slack"
  sh "docker run -d --name jenkins-slack jenkins:slack-test"
  //sleep to wait for jenkins to come up
  sleep 9
  sh "docker exec -t jenkins-slack curl -s -D - http://localhost:8080/ -o /dev/null"
  sh "docker exec -t jenkins-slack java -jar /usr/share/jenkins/jenkins-extracted/jenkins-cli.jar -s http://localhost:8080/ build 'slack-test' -s"
}