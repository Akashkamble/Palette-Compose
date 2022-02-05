package com.akashk.palette.createnewpalette

import androidx.lifecycle.ViewModel
import com.akashk.palette.R
import com.akashk.palette.core.ui.UIText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class CreateNewPaletteViewModel @Inject constructor() : ViewModel() {

    private val _viewState: MutableStateFlow<NewPaletteState> =
        MutableStateFlow(NewPaletteState())
    val viewState: StateFlow<NewPaletteState> = _viewState

    fun enterPaletteName(name: String) {
        _viewState.value = viewState.value.copy(
            paletteName = name,
            paletteNameError = null
        )
    }

    fun onContinue(name: String, navigateIfValid: () -> Unit) {
        val currentName = viewState.value.paletteName
        if (currentName.isEmpty()) {
            _viewState.value = viewState.value.copy(
                paletteName = name,
                paletteNameError = UIText.ResourceText(R.string.err_enter_valid_palette_name)
            )
            return
        } else {
            navigateIfValid.invoke()
        }
    }
}
