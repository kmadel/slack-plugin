node('docker-cloud'){
  sh "docker version"
  
  checkout scm
  
  docker.image('kmadel/maven:3.3.3-jdk-8').inside(){
    sh 'mvn package -Dmaven.test.skip=true'
  }
  
  def jenkinsTestImage = docker.build('jenkins:slack-test')
  jenkinsTestImage.inside(){
    sh "curl http://localhost:8080/jnlpJars/jenkins-cli.jar -o jenkins-cli.jar"
    sh "java -jar jenkins-cli.jar -s http://localhost:8080 build \"slack-test\" -s"
  }
}