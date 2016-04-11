import hudson.model.*;
import jenkins.model.*;

def j = Jenkins.instance

  def name = 'slack-migration-test'
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
      <jenkins.plugins.slack.SlackNotifier_-SlackJobProperty plugin="slack@1.8.1">
        <teamDomain>beedemo</teamDomain>
        <token>qvk5UF4xov8aAG32OvDxU6WG</token>
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
        <showCommitList>true</showCommitList>
        <includeCustomMessage>true</includeCustomMessage>
        <customMessage>custom message</customMessage>
      </jenkins.plugins.slack.SlackNotifier_-SlackJobProperty>
    </properties>
    <scm class="hudson.scm.NullSCM"/>
    <assignedNode>master</assignedNode>
    <canRoam>true</canRoam>
    <disabled>false</disabled>
    <blockBuildWhenDownstreamBuilding>false</blockBuildWhenDownstreamBuilding>
    <blockBuildWhenUpstreamBuilding>false</blockBuildWhenUpstreamBuilding>
    <triggers/>
    <concurrentBuild>false</concurrentBuild>
    <builders>
      <hudson.tasks.Shell>
        <command>echo &apos;slack migration test&apos;</command>
      </hudson.tasks.Shell>
    </builders>
    <publishers/>
    <buildWrappers/>
  </project>
"""
  def p = j.createProjectFromXML(name, new ByteArrayInputStream(configXml.getBytes("UTF-8")));
