package ExtensionsTest_ApacheMonitoringExtension.buildTypes

import jetbrains.buildServer.configs.kotlin.v2017_2.BuildType
import jetbrains.buildServer.configs.kotlin.v2017_2.buildSteps.maven
import jetbrains.buildServer.configs.kotlin.v2017_2.triggers.vcs

/**
 * @author Satish Muddam
 */
object ExtensionsTest_ApacheMonitoringExtension_IntegrationTestInLinux : BuildType({
    uuid = "1005B247-87BC-44BD-BC80-C96B76FD9588"
    id = "ExtensionsTest_ApacheMonitoringExtension_IntegrationTestInLinux"
    name = "IntegrationTest in Linux"

    vcs {
        root(ExtensionsTest_ApacheMonitoringExtension.vcsRoots.ExtensionsTest_ApacheMonitoringExtension_SatishGithub)

    }

    steps {
        maven {
            goals = "clean install"
            mavenVersion = defaultProvidedVersion()
            jdkHome = "%env.JDK_18%"
        }
    }

    dependencies {
        dependency(ExtensionsTest_ApacheMonitoringExtension_SetupInLinux) {
            snapshot {

            }
        }
    }

    triggers {
        vcs {
        }
    }
})