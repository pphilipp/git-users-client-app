package com.example.design_system

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

@Composable
fun SimpleAlertDialog(
    show: Boolean,
    dialogMessage: String,
    onDismiss: () -> Unit = {},
    onConfirm: () -> Unit = {}
) {
    if (show) {
        AlertDialog(
            onDismissRequest = onDismiss,
            confirmButton = {
                TextButton(onClick = onConfirm) { Text(text = stringResource(R.string.alert_dialog_btn_text_ok)) }
            },
            dismissButton = {
                TextButton(onClick = onDismiss) { Text(text = stringResource(R.string.alert_dialog_btn_text_cancel)) }
            },
            title = {
                Text(text = stringResource(R.string.alert_dialog_title_text_something_went_wrong))
                    },
            text = {
                Text(text = dialogMessage)
            }
        )
    }
}
