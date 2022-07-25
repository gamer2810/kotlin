plugins {
    kotlin("jvm")
    id("jps-compatible")
}

dependencies {
    testApi(project(":compiler:fir:entrypoint"))
    testApi(project(":compiler:cli"))
    testApi(intellijCore())

    testRuntimeOnly(project(":core:descriptors.runtime"))

    testImplementation(projectTests(":compiler:test-infrastructure-utils"))

    testRuntimeOnly(commonDependency("net.java.dev.jna:jna"))
}

optInToExperimentalCompilerApi()

sourceSets {
    "main" { none() }
    "test" { projectDefault() }
}

testsJar()
