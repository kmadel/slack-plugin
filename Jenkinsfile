node('docker-cloud'){
  sh "docker version"
  checkout scm
  sh 'mvn package'
  def jenkinsTestImage = docker.build('jenkins:slack-test')
  jenkinsTestImage.inside(){
    sh "java -jar /var/jenkins_home/war/WEB-INF/jenkins-cli.jar -s http://localhost:8080 build \"slack-test\" -s"
  }
}