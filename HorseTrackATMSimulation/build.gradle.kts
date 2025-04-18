plugins {
    id("java")
    id("jacoco")
}

group = "com.vikingcloud.horsetrack"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

// Add unit test support via JUnit
tasks.test {
    useJUnitPlatform()
}

// Add code coverage support via JaCoCo
tasks.jacocoTestReport {
    dependsOn(tasks.test) // run tests before generating report

    reports {
        xml.required.set(true)
        csv.required.set(false)
        html.required.set(true) // enable human-readable HTML report
    }
}
