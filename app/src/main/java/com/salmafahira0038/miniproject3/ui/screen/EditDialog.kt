package com.salmafahira0038.miniproject3.ui.screen

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.salmafahira0038.miniproject3.R
import com.salmafahira0038.miniproject3.model.Makeup

@Composable
fun EditDialog(
    makeup: Makeup,
    bitmap: Bitmap?,
    onDismissRequest: () -> Unit,
    onUpdate: (String, String, Bitmap?) -> Unit,
    onChangeImageClick: () -> Unit
) {
    var judul by remember { mutableStateOf(makeup.judul) }
    var harga by remember { mutableStateOf(makeup.harga) }

    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card(
            modifier = Modifier.padding(16.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                bitmap?.let {
                    Image(
                        bitmap = it.asImageBitmap(),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1f)
                    )
                }

                OutlinedButton(
                    onClick = { onChangeImageClick() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    Text(text = stringResource(id = R.string.edit_gambar))
                }

                OutlinedTextField(
                    value = judul,
                    onValueChange = { judul = it },
                    label = { Text(stringResource(id = R.string.judul)) },
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Words,
                        imeAction = ImeAction.Next
                    ),
                    modifier = Modifier.padding(top = 8.dp)
                )

                OutlinedTextField(
                    value = harga,
                    onValueChange = { harga = it },
                    label = { Text(stringResource(id = R.string.harga)) },
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        capitalization = KeyboardCapitalization.Sentences,
                        imeAction = ImeAction.Done
                    ),
                    modifier = Modifier.padding(top = 8.dp)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    OutlinedButton(
                        onClick = { onDismissRequest() },
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Text(stringResource(R.string.batal))
                    }
                    OutlinedButton(
                        onClick = {
                            onUpdate(judul, harga, bitmap)
                        },
                        enabled = judul.isNotEmpty() && harga.isNotEmpty(),
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Text(stringResource(R.string.simpan))
                    }
                }
            }
        }
    }
}