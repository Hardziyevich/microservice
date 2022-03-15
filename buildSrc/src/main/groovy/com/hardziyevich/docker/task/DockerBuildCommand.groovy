package com.hardziyevich.docker.task

enum DockerBuildCommand {

    DOCKER('docker'),
    BUILD('build'),
    PATH('.'),
    BUILD_ARG('--build-arg'),
    NAME_TAG('-t')

    final String command

    DockerBuildCommand(String command) {
        this.command = command
    }
}