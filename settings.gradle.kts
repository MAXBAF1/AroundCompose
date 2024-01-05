pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
val keyProps = java.util.Properties().apply {
    file("local.properties").takeIf { it.exists() }?.inputStream()?.use { load(it) }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        // below code is added
        maven {
            url = uri("https://api.mapbox.com/downloads/v2/releases/maven")
            authentication {
                create<BasicAuthentication>("basic")
            }
            credentials {
                username = "mapbox"
                password = keyProps.getProperty("MAPBOX_MAP_TOKEN")
            }
        }
    }
}


rootProject.name = "AroundCompose"
include(":app")
 