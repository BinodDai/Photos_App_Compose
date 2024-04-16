package com.binod.photosapp.ui.viewModel

import android.net.Uri
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel : ViewModel() {

    private val _images = MutableStateFlow<List<Uri>>(emptyList())
    val images = _images.asStateFlow()

    fun updateImages(selectedImages: List<Uri>) {
        _images.value = selectedImages
    }

    private val _textFieldValue = MutableStateFlow(50)
    val textFieldValue = _textFieldValue.asStateFlow()


    private val _triangularNumbers = MutableStateFlow<List<Int>>(emptyList())
    val triangularNumbers = _triangularNumbers.asStateFlow()

    fun generateTriangularNumbers(maxValue: Int) {
        val triangularNumbers = mutableListOf<Int>()
        var n = 1
        while (true) {
            val triangularNumber = n * (n + 1) / 2
            if (triangularNumber > maxValue) break
            triangularNumbers.add(triangularNumber)
            n++
        }
        _triangularNumbers.value = triangularNumbers
    }

    fun updateTextFieldValue(newValue: Int) {
        _textFieldValue.value = newValue
    }


}