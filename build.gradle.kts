plugins {
    java
}

group = "katas"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(16))
        vendor.set(JvmVendorSpec.ADOPTOPENJDK)
    }

    version = JavaVersion.VERSION_16
}

tasks {
    compileJava {
        options.release.set(16)
        options.isFork = true
        options.compilerArgs?.addAll( listOf("--enable-preview"))
    }

    compileTestJava {
        options.release.set(16)
        options.isFork = true
        options.compilerArgs?.addAll( listOf("--enable-preview"))
    }

    test {
        useJUnitPlatform();
        jvmArgs(listOf("--enable-preview"))
    }
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.7.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.assertj:assertj-core:3.19.0")
}