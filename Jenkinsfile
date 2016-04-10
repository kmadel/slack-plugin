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
  try {
    sh "docker rm -f jenkins-slack"
  } catch(e) {
    echo "jenkins-slack image does not exist"
  }
  
  sh "docker run -d --name jenkins-slack jenkins:slack-test"
  //wait for jenkins to come up
  //waitUntil {
    writeFile file: 'jenkins-status', text: ''
    sh "docker exec -t jenkins-slack curl -s -o /dev/null -w \'%{http_code}\' http://localhost:8080/ > jenkins-status"
    def status = readFile 'jenkins-status'
    echo status
    status == '200'
    //}
  sh "docker exec -t jenkins-slack java -jar /usr/share/jenkins/jenkins-extracted/jenkins-cli.jar -s http://localhost:8080/ build 'slack-test' -s"
}