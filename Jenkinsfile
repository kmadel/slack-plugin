node('docker-cloud'){
  sh "docker version"
  
  checkout scm
  
  docker.image('kmadel/maven:3.3.3-jdk-8').inside(){
    sh 'mvn package -Dmaven.test.skip=true'
  }
  
  def jenkinsTestImage = docker.build('jenkins:slack-test')
  sh "docker rm -f jenkins-slack"
  sh "docker run -d -p 81:8080 --name jenkins-slack jenkins:slack-test"
  sh "docker exec -t jenkins-slack java -jar /var/jenkins_home/war/WEB-INF/jenkins-cli.jar -s http://localhost:8080 build 'slack-test' -s"
}