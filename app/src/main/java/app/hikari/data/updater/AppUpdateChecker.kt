package app.hikari.data.updater

import app.hikari.BuildConfig
import app.hikari.domain.release.interactor.GetApplicationRelease
import app.hikari.util.system.isFossBuildType
import dev.zacsweers.metro.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Inject
class AppUpdateChecker(
    private val getApplicationRelease: GetApplicationRelease,
) {

    suspend fun checkForUpdate(forceCheck: Boolean = false): GetApplicationRelease.Result {
        return GetApplicationRelease.Result.NoNewUpdate
    }
}

val GITHUB_REPO = "HikariCollection/hikari"

val RELEASE_TAG: String = "v${BuildConfig.VERSION_NAME}"

val RELEASE_URL = "https://github.com/$GITHUB_REPO/releases/tag/$RELEASE_TAG"
