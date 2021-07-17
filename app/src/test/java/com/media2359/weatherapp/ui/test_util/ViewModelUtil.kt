package com.media2359.weatherapp.ui.test_util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.cancel

/**
 * Ensure that [ViewModel.viewModelScope] is always cancelled after `block` is done executing.
 * This is need when running test because we create and destroy [ViewModel] manually.
 */
fun ViewModel.runTest(block: () -> Unit) {
    try {
        block.invoke()
    } finally {
        viewModelScope.cancel()
    }
}