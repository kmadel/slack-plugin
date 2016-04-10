FROM jenkins:1.642.4

USER jenkins
#copy list of non-standard plugins to install
COPY plugins.txt /usr/share/jenkins/plugins.txt
RUN /usr/local/bin/plugins.sh /usr/share/jenkins/plugins.txt

COPY target/slack.hpi /usr/share/jenkins/ref/plugins/slack.hpi
#copy init groovy script to create slack-test job and schedule it right away
COPY init_02_create_slack_test_job.groovy /var/jenkins_home/init.groovy.d/init_02_create_slack_test_job.groovy

