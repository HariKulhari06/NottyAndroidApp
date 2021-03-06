package com.hari.notty.ui.addnote

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Category
import androidx.compose.material.icons.rounded.EditCalendar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.hari.notty.R
import com.hari.notty.ui.components.NottyTextField
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination()
@Composable
fun AddNoteScreen(
    navigator: DestinationsNavigator,
    noteId: String? = null
) {
    val scrollState = rememberScrollState()
    val title = rememberSaveable {
        mutableStateOf("")
    }
    val note = rememberSaveable {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier
            .background(MaterialTheme.colors.surface)
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        AddNoteHeader()
        NottyTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            value = title.value,
            onValueChange = { title.value = it },
            label = stringResource(R.string.title),
            singleLine = true
        )
        NottyTextField(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(bottom = 16.dp)
                .padding(horizontal = 16.dp)
                .navigationBarsWithImePadding(),
            value = note.value,
            onValueChange = { note.value = it },
            label = stringResource(R.string.note_here)
        )
    }
}

@Composable
fun AddNoteHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "15/01 11:00 AM",
                style = MaterialTheme.typography.subtitle2
            )
            Spacer(modifier = Modifier.size(4.dp))
            IconButton(
                onClick = { },
                modifier = Modifier.size(16.dp)
            ) {
                Icon(
                    imageVector = Icons.Rounded.EditCalendar,
                    contentDescription = "Change Date"
                )
            }
        }


        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "Tag",
                style = MaterialTheme.typography.subtitle2
            )
            Spacer(modifier = Modifier.size(4.dp))
            IconButton(
                onClick = { },
                modifier = Modifier.size(16.dp)
            ) {
                Icon(imageVector = Icons.Rounded.Category, contentDescription = "Change Tag")
            }
        }


    }
}