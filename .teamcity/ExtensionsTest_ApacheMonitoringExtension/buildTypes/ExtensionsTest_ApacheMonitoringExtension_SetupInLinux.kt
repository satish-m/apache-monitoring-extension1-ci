package ExtensionsTest_ApacheMonitoringExtension.buildTypes

import jetbrains.buildServer.configs.kotlin.v2017_2.BuildType
import jetbrains.buildServer.configs.kotlin.v2017_2.buildSteps.exec
import jetbrains.buildServer.configs.kotlin.v2017_2.buildSteps.maven
import jetbrains.buildServer.configs.kotlin.v2017_2.triggers.vcs

/**
 * @author Satish Muddam
 */
object ExtensionsTest_ApacheMonitoringExtension_SetupInLinux : BuildType({
    uuid = "3E3D413F-E17F-4273-851F-FD905161AEF4"
    id = "ExtensionsTest_ApacheMonitoringExtension_SetupInLinux"
    name = "Setup Linux Environment"

    vcs {
        root(ExtensionsTest_ApacheMonitoringExtension.vcsRoots.ExtensionsTest_ApacheMonitoringExtension_SatishGithub)

    }

    steps {
        exec {
            path = "make"
            arguments = "dockerRun"
        }

        //Waits for 5 minutes to send metrics to the controller
        exec {
            path = "make"
            arguments = "sleep"
        }
    }

    dependencies {
        dependency(ExtensionsTest_ApacheMonitoringExtension_CleanBuild) {
            snapshot {

            }
        }
    }

    triggers {
        vcs {
        }
    }
})