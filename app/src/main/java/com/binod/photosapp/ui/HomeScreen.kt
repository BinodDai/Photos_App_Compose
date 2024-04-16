package com.binod.photosapp.ui

import ImageItem
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.binod.photosapp.R
import com.binod.photosapp.ui.components.CustomButton
import com.binod.photosapp.ui.viewModel.MainViewModel

@Composable
fun HomeScreen(navController: NavController, viewModel: MainViewModel) {


    val multiplePhotoLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickMultipleVisualMedia(2)
    ) { uris: List<Uri> ->
        viewModel.updateImages(uris)
    }

    val images by viewModel.images.collectAsState()
    var textFieldValue by remember { mutableStateOf("") }

    Scaffold(modifier = Modifier.fillMaxSize()) { paddingValue ->

        Box(modifier = Modifier.padding(paddingValue)) {

            Column(
                modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center,
            ) {
                LazyColumn(modifier = Modifier.fillMaxWidth()) {
                    items(items = images)
                    { item ->
                        ImageItem(item)
                    }
                }

                TextField(
                    value = textFieldValue,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    onValueChange = { textFieldValue = it },
                    visualTransformation = VisualTransformation.None,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    label = { Text(stringResource(id = R.string.enter_size) + " (Default Size is 50)") }
                )

                CustomButton(label = R.string.choose_images, onClick = {
                    multiplePhotoLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                })

                if (images.isNotEmpty()) {
                    CustomButton(label = R.string.generate_images, onClick = {
                        val count = textFieldValue.toIntOrNull() ?: 50
                        viewModel.updateTextFieldValue(count)
                        viewModel.generateTriangularNumbers(count)
                        navController.navigate("detail")
                    })
                }
            }

        }
    }

}


