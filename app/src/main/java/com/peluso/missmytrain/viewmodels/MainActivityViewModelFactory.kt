package com.peluso.missmytrain.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.peluso.missmytrain.models.RecyclerViewCell
import com.peluso.missmytrain.models.ResourceProvider

class MainActivityViewModelFactory(val resourceProvider: ResourceProvider,
                                   val recyclerFiller: (List<RecyclerViewCell>)->Unit): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainActivityViewModel(resourceProvider,recyclerFiller) as T
    }
}