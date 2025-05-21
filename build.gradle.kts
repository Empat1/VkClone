// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.android.library) apply false
    id("vkid.manifest.placeholders") version "1.1.0" apply true
}

vkidManifestPlaceholders {
    // Добавьте плейсхолдеры сокращенным способом. Например, vkidRedirectHost будет "vk.com", а vkidRedirectScheme будет "vk$clientId".
    // Или укажите значения явно через properties, если не хотите использовать плейсхолдеры.

    vkidRedirectHost = "vk.com"
    vkidRedirectScheme = "vk53605575"
    vkidClientId = "53605575"
    vkidClientSecret = project.findProperty("VKID_CLIENT_SECRET").toString()

}