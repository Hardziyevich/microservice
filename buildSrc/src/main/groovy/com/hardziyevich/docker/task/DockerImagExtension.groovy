package com.hardziyevich.docker.task

import org.gradle.api.provider.MapProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputFiles
import org.gradle.api.tasks.SkipWhenEmpty

interface DockerImagExtension {

    @Input
    MapProperty<String, String> getEnvArg()

    @Input
    Property<String> getNameImage()

    @SkipWhenEmpty
    @InputFiles
    Property<File> getDockerfile()
}