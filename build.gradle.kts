plugins {
    id("io.freefair.aggregate-javadoc") version "9.2.0"
    id("io.github.sgtsilvio.gradle.javadoc-links") version "0.10.0" apply false
}

dependencies {
    javadoc(project(":simple-graphics-ffm-bindings-vulkan"))
    javadoc(project(":simple-graphics-ffm-bindings-glfw-vulkan"))
    javadoc(project(":simple-graphics-ffm-bindings-glfw"))
}