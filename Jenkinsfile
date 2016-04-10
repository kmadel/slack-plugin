node('docker-cloud'){
  sh "docker version"
  
  checkout scm
  
  docker.image('kmadel/maven:3.3.3-jdk-8').inside(){
    sh 'mvn package'
  }
  
  def jenkinsTestImage = docker.build('jenkins:slack-test')
  jenkinsTestImage.inside(){
    sh "java -jar /var/jenkins_home/war/WEB-INF/jenkins-cli.jar -s http://localhost:8080 build \"slack-test\" -s"
  }
}