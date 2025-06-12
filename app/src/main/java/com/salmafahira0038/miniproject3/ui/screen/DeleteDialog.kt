package com.salmafahira0038.miniproject3.ui.screen

import android.content.res.Configuration
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.salmafahira0038.miniproject3.R
import com.salmafahira0038.miniproject3.ui.theme.MiniProject3Theme


@Composable
fun DeleteDialog(
    onDismissRequest: () -> Unit,
    onConfirm:() -> Unit
){
    AlertDialog(
        text = { Text(text = stringResource(id = R.string.pesan_hapus))
        },
        confirmButton = {
            TextButton(onClick = onConfirm) {
                Text(text = stringResource(id = R.string.hapus))
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismissRequest()}) {
                Text(text = stringResource(id = R.string.batal_hapus))
            }
        },
        onDismissRequest = {onDismissRequest()}
    )
}

@Preview(showSystemUi = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showSystemUi = true)
@Composable
fun DeletDialogPreview(){
    MiniProject3Theme  {
        DeleteDialog(onDismissRequest = {}, onConfirm = {})
    }
}