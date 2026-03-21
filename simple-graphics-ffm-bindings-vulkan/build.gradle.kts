plugins {
    id("java-library")
    id("io.github.sgtsilvio.gradle.javadoc-links")
    id("maven-publish")
}

group = "io.codetoil"
version = "0.1.0-SNAPSHOT"
base.archivesName = "simple-graphics-ffm-bindings-vulkan"

val junitVersion: String by project

val nonJar by configurations.creating

java {
    withJavadocJar()
    withSourcesJar()
}

repositories {
    mavenCentral()
}

dependencies {
    nonJar(files("../LICENSE.md"))

    testImplementation(platform("org.junit:junit-bom:${junitVersion}"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}

tasks.jar {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    from(nonJar)
}

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/Codetoil/simple-graphics-ffm-bindings")
            credentials {
                username = project.findProperty("gpr.user") as String? ?: System.getenv("USERNAME")
                password = project.findProperty("gpr.key") as String? ?: System.getenv("TOKEN")
            }
        }
    }
    publications {
        register<MavenPublication>("gpr") {
            pom {
                packaging = "jar"

                name = "Simple Graphics FFM Bindings (Vulkan)"
                url = "https://github.com/Codetoil/simple-graphics-ffm-bindings"
                inceptionYear = "2026"
                licenses {
                    license {
                        name = "GPL-3.0-or-later"
                        url = "https://www.gnu.org/licenses/gpl-3.0.en.html"
                        distribution = "repo"
                    }
                }
                developers {
                    developer {
                        id = "codetoil"
                        name = "Anthony Michalek (Codetoil)"
                        email = "ianthisawesomee@gmail.com"
                        url = "https://codetoil.io"
                        roles = setOf("owner", "architect", "developer")
                    }
                }
                issueManagement {
                    system = "GitHub Issues"
                    url = "https://github.com/Codetoil/simple-graphics-ffm-bindings/issues"
                }
                ciManagement {
                    system = "GitHub Actions"
                    url = "https://github.com/Codetoil/simple-graphics-ffm-bindings/actions"
                }
                scm {
                    connection = "scm:git:git://github.com/Codetoil/simple-graphics-ffm-bindings.git"
                    developerConnection = "scm:git:ssh://github.com/Codetoil/simple-graphics-ffm-bindings.git"
                    url = "https://github.com/Codetoil/simple-graphics-ffm-bindings"
                }
            }
            from(components["java"])
        }
    }
}

tasks.javadocLinks {
    urlProvider = { id -> urlProviderFunc(id) }
}