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
  sh "docker run -a stdin -a stdout -p 81:8080 --name jenkins-slack jenkins:slack-test"
  sh "docker exec -t jenkins-slack java -jar /usr/share/jenkins/jenkins-extracted/jenkins-cli.jar -s http://localhost:8080 build 'slack-test' -s"
}