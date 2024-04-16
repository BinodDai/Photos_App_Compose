package com.binod.photosapp.ui

import ImageItem
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.binod.photosapp.ui.viewModel.MainViewModel

@Composable
fun TriangularSequenceScreen(viewModel: MainViewModel) {
    val triangularNumbers by viewModel.triangularNumbers.collectAsState()
    val textFieldValue by viewModel.textFieldValue.collectAsState()
    val images by viewModel.images.collectAsState()

    if (images.size < 2) {
        Text("Not enough images to display, Pick 2 images", modifier = Modifier.padding(16.dp))
        return
    }

    Scaffold(modifier = Modifier.fillMaxSize()) { paddingValue ->

        Box(modifier = Modifier.padding(paddingValue)) {

            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LazyColumn(modifier = Modifier.fillMaxWidth()) {
                    val maxNumber = textFieldValue
                    items(maxNumber) { index ->
                        val input = index + 1
                        if (triangularNumbers.contains(input)) {
                            ImageItem(item = images[0], index = input)
                        } else {
                            ImageItem(item = images[1], index = input)
                        }
                    }
                }

            }
        }
    }
}