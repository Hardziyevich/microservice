package com.hardziyevich.docker.plugin

import com.hardziyevich.docker.task.DockerImageTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.logging.LogLevel
import org.gradle.api.logging.Logger
import org.gradle.api.logging.Logging

class DockerImageRunner implements Plugin<Project> {

    static final String DOCKERFILE_PATH = 'Dockerfile'
    private static final Logger log = Logging.getLogger(DockerImageRunner.class)

    @Override
    void apply(Project project) {
        project.tasks.register('dockerImageBuild', DockerImageTask,{
            group = 'docker'
            description = 'Task runs Dockerfile.'
            image.dockerfile = project.file(DOCKERFILE_PATH)
        })
    }
}
