package app.hikari.network

import android.content.Context
import app.hikari.core.common.preference.Preference
import app.hikari.core.common.preference.PreferenceStore
import app.hikari.core.metro.IsDebugBuild
import app.hikari.util.system.WebViewUtil
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.Inject
import dev.zacsweers.metro.SingleIn

@Inject
@SingleIn(AppScope::class)
class NetworkPreferences(
    preferenceStore: PreferenceStore,
    @IsDebugBuild isDebugBuild: Boolean,
    context: Context,
) {

    val redactSensitiveHeaders: Preference<Boolean> = preferenceStore.getBoolean(
        "redact_sensitive_headers",
        true,
    )

    val verboseLogging: Preference<Boolean> = preferenceStore.getBoolean(
        "verbose_logging",
        isDebugBuild,
    )

    val dohProvider: Preference<Int> = preferenceStore.getInt("doh_provider", -1)

    val defaultUserAgent: Preference<String> = preferenceStore.getString(
        "default_user_agent",
        WebViewUtil.getInferredUserAgent(context),
    )

    init {
        if (defaultUserAgent.isSet() && defaultUserAgent.get().contains("Chrome/14")) {
            defaultUserAgent.delete()
        }
    }
}
