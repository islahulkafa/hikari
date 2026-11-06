package app.hikari.presentation.browse.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import app.hikari.i18n.MR
import app.hikari.presentation.core.components.material.padding
import app.hikari.presentation.core.i18n.stringResource
import app.hikari.ui.browse.extension.BatchUpdateProgress

@Composable
fun BatchUpdateBanner(
    progress: BatchUpdateProgress,
    onCancel: () -> Unit,
    modifier: Modifier = Modifier,
) {
    ElevatedCard(
        modifier = modifier.fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier.padding(MaterialTheme.padding.medium),
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.padding.small),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = stringResource(
                        MR.strings.ext_updating_progress,
                        progress.completed,
                        progress.total,
                    ),
                    style = MaterialTheme.typography.titleMedium,
                )
                TextButton(onClick = onCancel) {
                    Text(text = stringResource(MR.strings.action_cancel))
                }
            }

            val animatedProgress by animateFloatAsState(
                targetValue = if (progress.total > 0) {
                    (progress.completed.toFloat() / progress.total.toFloat()).coerceIn(0f, 1f)
                } else {
                    0f
                },
                label = "batchUpdateProgress",
            )

            LinearProgressIndicator(
                progress = { animatedProgress },
                modifier = Modifier.fillMaxWidth(),
            )

            if (progress.currentExtensionName.isNotEmpty()) {
                Text(
                    text = stringResource(
                        MR.strings.ext_updating_current,
                        progress.currentExtensionName,
                    ),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }
    }
}
