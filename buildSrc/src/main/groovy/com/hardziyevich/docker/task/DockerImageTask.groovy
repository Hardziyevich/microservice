package com.hardziyevich.docker.task

import org.gradle.api.tasks.Exec
import org.gradle.api.tasks.Nested

import static com.hardziyevich.docker.task.DockerBuildCommand.*

abstract class DockerImageTask extends Exec {

    @Nested
    abstract DockerImagExtension getImage()

    @Override
    protected void exec() {
        commandLine buildCommandForImage()
        super.exec()
    }

    List<String> buildCommandForImage() {
        def command = [DOCKER.command, BUILD.command]
        def env = getImage().getEnvArg().get()
        if (!env.isEmpty()) {
            command << BUILD_ARG.command
            env.each { command.add("$it.key=$it.value") }
        }
        command.addAll([NAME_TAG.command, "${getImage().getNameImage().get()}", PATH.command])
        return command
    }
}
