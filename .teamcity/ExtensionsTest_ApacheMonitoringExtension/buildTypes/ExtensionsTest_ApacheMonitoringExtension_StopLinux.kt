package ExtensionsTest_ApacheMonitoringExtension.buildTypes

import jetbrains.buildServer.configs.kotlin.v2017_2.BuildType
import jetbrains.buildServer.configs.kotlin.v2017_2.buildSteps.exec
import jetbrains.buildServer.configs.kotlin.v2017_2.triggers.vcs

/**
 * @author Satish Muddam
 */
object ExtensionsTest_ApacheMonitoringExtension_StopLinux : BuildType({
    uuid = "844EAD46-C376-4590-9608-F6C1E7CAB67A"
    id = "ExtensionsTest_ApacheMonitoringExtension_StopLinux"
    name = "Stop Linux docker"

    vcs {
        root(ExtensionsTest_ApacheMonitoringExtension.vcsRoots.ExtensionsTest_ApacheMonitoringExtension_SatishGithub)

    }

    steps {
        exec {
            path = "make"
            arguments = "dockerStop"
        }
    }

    dependencies {
        dependency(ExtensionsTest_ApacheMonitoringExtension_IntegrationTestInLinux) {
            snapshot {

            }
        }
    }

    triggers {
        vcs {
        }
    }
})