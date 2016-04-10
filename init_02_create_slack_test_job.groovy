import hudson.model.*;
import jenkins.model.*;

def j = Jenkins.instance

  def name = 'slack-test'
  def job = j.getItem(name)
  if (job != null) {
    job.delete()
  }
  println "--> creating $name"

  def configXml = """
  <project>
    <actions/>
    <description></description>
    <keepDependencies>false</keepDependencies>
    <properties>
    </properties>
    <scm class="hudson.scm.NullSCM"/>
    <assignedNode>master</assignedNode>
    <canRoam>false</canRoam>
    <disabled>false</disabled>
    <blockBuildWhenDownstreamBuilding>false</blockBuildWhenDownstreamBuilding>
    <blockBuildWhenUpstreamBuilding>false</blockBuildWhenUpstreamBuilding>
    <triggers/>
    <concurrentBuild>false</concurrentBuild>
    <builders>
      <hudson.tasks.Shell>
        <command>echo &apos;simple slack notification test&apos;</command>
      </hudson.tasks.Shell>
    </builders>
    <publishers>
      <jenkins.plugins.slack.SlackNotifier plugin="slack@2.0.1">
        <teamDomain>beedemo</teamDomain>
        <authToken>qvk5UF4xov8aAG32OvDxU6WG</authToken>
        <room>#jenkins-latest</room>
        <startNotification>true</startNotification>
        <notifySuccess>true</notifySuccess>
        <notifyAborted>true</notifyAborted>
        <notifyNotBuilt>true</notifyNotBuilt>
        <notifyUnstable>true</notifyUnstable>
        <notifyFailure>true</notifyFailure>
        <notifyBackToNormal>true</notifyBackToNormal>
        <notifyRepeatedFailure>true</notifyRepeatedFailure>
        <includeTestSummary>true</includeTestSummary>
        <commitInfoChoice>AUTHORS_AND_TITLES</commitInfoChoice>
        <includeCustomMessage>true</includeCustomMessage>
        <customMessage>custom message</customMessage>
      </jenkins.plugins.slack.SlackNotifier>
    </publishers>
    <buildWrappers/>
  </project>
"""
  def p = j.createProjectFromXML(name, new ByteArrayInputStream(configXml.getBytes("UTF-8")));
  
  Thread.start {
        sleep 10000
        println "--> setting agent port for jnlp"
        def env = System.getenv()
        int port = env['JENKINS_SLAVE_AGENT_PORT'].toInteger()
        Jenkins.instance.setSlaveAgentPort(port)
        println "--> setting agent port for jnlp... done"
  }