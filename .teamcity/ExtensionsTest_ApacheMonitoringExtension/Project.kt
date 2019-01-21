package ExtensionsTest_ApacheMonitoringExtension

import ExtensionsTest_ApacheMonitoringExtension.vcsRoots.*
import ExtensionsTest_ApacheMonitoringExtension.vcsRoots.ExtensionsTest_ApacheMonitoringExtension_SatishGithub
import jetbrains.buildServer.configs.kotlin.v2018_2.*
import jetbrains.buildServer.configs.kotlin.v2018_2.Project
import jetbrains.buildServer.configs.kotlin.v2018_2.projectFeatures.VersionedSettings
import jetbrains.buildServer.configs.kotlin.v2018_2.projectFeatures.versionedSettings

object Project : Project({
    uuid = "56cb480f-ec0c-4c51-863c-c2a51a5f393a"
    id("ExtensionsTest_ApacheMonitoringExtension")
    parentId("ExtensionsTest")
    name = "ApacheMonitoringExtension"

    vcsRoot(ExtensionsTest_ApacheMonitoringExtension_SatishGithub)

    features {
        versionedSettings {
            id = "PROJECT_EXT_2"
            mode = VersionedSettings.Mode.ENABLED
            buildSettingsMode = VersionedSettings.BuildSettingsMode.PREFER_SETTINGS_FROM_VCS
            rootExtId = "${ExtensionsTest_ApacheMonitoringExtension_SatishGithub.id}"
            showChanges = false
            settingsFormat = VersionedSettings.Format.KOTLIN
            storeSecureParamsOutsideOfVcs = true
        }
    }
})
