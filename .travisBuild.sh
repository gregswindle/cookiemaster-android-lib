#!/bin/bash

function sonarScanOnPullRequest {
  if [ "$TRAVIS_PULL_REQUEST" != "false" ]; then
    sonar-scanner -Dsonar.analysis.mode=preview \
                  -Dsonar.github.pullRequest=$TRAVIS_PULL_REQUEST \
                  -Dsonar.github.repository=$TRAVIS_REPO_SLUG \
                  -Dsonar.github.oauth=$GITHUB_ACCESS_TOKEN \
                  -Dsonar.host.url=https://sonarqube.com \
                  -Dsonar.login=$SONAR_TOKEN \
                  -Dsonar.jacoco.reportPaths=library/build/jacoco/testDebugUnitTest.exec
  fi
}

sonarScanOnPullRequest
