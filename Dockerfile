FROM jenkins:1.642.4

USER root
RUN mkdir -p /data
RUN mkdir -p /usr/share/jenkins/jenkins-extracted
RUN unzip -d /usr/share/jenkins/jenkins-extracted /usr/share/jenkins/jenkins.war
RUN cp /usr/share/jenkins/jenkins-extracted/WEB-INF/jenkins-cli.jar /data/jenkins-cli.jar
RUN chown -R jenkins /data
RUN rm -rf /usr/share/jenkins/jenkins-extracted

#copy list of non-standard plugins to install
COPY plugins.txt /usr/share/jenkins/plugins.txt
RUN /usr/local/bin/plugins.sh /usr/share/jenkins/plugins.txt

COPY target/slack.hpi /usr/share/jenkins/ref/plugins/slack.hpi
#copy init groovy script to create slack-test job and schedule it right away
COPY init_02_create_slack_test_job.groovy /var/jenkins_home/init.groovy.d/init_02_create_slack_test_job.groovy

USER jenkins