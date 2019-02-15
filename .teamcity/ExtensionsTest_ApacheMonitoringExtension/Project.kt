package ExtensionsTest_ApacheMonitoringExtension

import ExtensionsTest_ApacheMonitoringExtension.buildTypes.*
import ExtensionsTest_ApacheMonitoringExtension.vcsRoots.ExtensionsTest_ApacheMonitoringExtension_SatishGithub
import jetbrains.buildServer.configs.kotlin.v2017_2.Project
import jetbrains.buildServer.configs.kotlin.v2017_2.projectFeatures.VersionedSettings
import jetbrains.buildServer.configs.kotlin.v2017_2.projectFeatures.versionedSettings

object Project : Project({
    uuid = "56cb480f-ec0c-4c51-863c-c2a51a5f393a"
    id = "ExtensionsTest_ApacheMonitoringExtension"
    parentId = "ExtensionsTest"
    name = "ApacheMonitoringExtension"

    vcsRoot(ExtensionsTest_ApacheMonitoringExtension_SatishGithub)

    buildType(ExtensionsTest_ApacheMonitoringExtension_CleanBuild)
    buildType(ExtensionsTest_ApacheMonitoringExtension_SetupInLinux)
    buildType(ExtensionsTest_ApacheMonitoringExtension_IntegrationTestInLinux)
    buildType(ExtensionsTest_ApacheMonitoringExtension_StopLinux)
    buildType(ExtensionsTest_ApacheMonitoringExtension_Publish)

    features {
        versionedSettings {
            id = "PROJECT_EXT_9"
            mode = VersionedSettings.Mode.ENABLED
            buildSettingsMode = VersionedSettings.BuildSettingsMode.PREFER_SETTINGS_FROM_VCS
            rootExtId = ExtensionsTest_ApacheMonitoringExtension_SatishGithub.id
            showChanges = false
            settingsFormat = VersionedSettings.Format.KOTLIN
            storeSecureParamsOutsideOfVcs = true
        }
    }

    buildTypesOrder = arrayListOf(
            ExtensionsTest_ApacheMonitoringExtension_CleanBuild,
            ExtensionsTest_ApacheMonitoringExtension_SetupInLinux,
            ExtensionsTest_ApacheMonitoringExtension_IntegrationTestInLinux,
            ExtensionsTest_ApacheMonitoringExtension_StopLinux,
            ExtensionsTest_ApacheMonitoringExtension_Publish
    )
})
