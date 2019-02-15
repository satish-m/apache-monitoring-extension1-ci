package ExtensionsTest_ApacheMonitoringExtension.buildTypes

import jetbrains.buildServer.configs.kotlin.v2017_2.BuildType
import jetbrains.buildServer.configs.kotlin.v2017_2.buildSteps.maven
import jetbrains.buildServer.configs.kotlin.v2017_2.triggers.vcs

/**
 * @author Satish Muddam
 */

object ExtensionsTest_ApacheMonitoringExtension_Publish : BuildType({
    uuid = "8C916335-6808-4E47-BCEF-ED7C1E0FC897"
    id = "ExtensionsTest_ApacheMonitoringExtension_Publish"
    name = "Publish build artifact"

    vcs {
        root(ExtensionsTest_ApacheMonitoringExtension.vcsRoots.ExtensionsTest_ApacheMonitoringExtension_SatishGithub)
    }

    steps {
        maven {
            goals = "mvn github-release:release"
            mavenVersion = defaultProvidedVersion()
            jdkHome = "%env.JDK_18%"
        }
    }

    dependencies {
        dependency(ExtensionsTest_ApacheMonitoringExtension_StopLinux) {
            snapshot {

            }
        }
    }

    triggers {
        vcs {
        }
    }


    artifactRules = """
       target/ApacheMonitor-*.zip
    """.trimIndent()

})