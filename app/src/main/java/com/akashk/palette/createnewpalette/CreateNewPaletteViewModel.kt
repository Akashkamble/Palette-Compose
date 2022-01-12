package com.akashk.palette.createnewpalette

import androidx.lifecycle.ViewModel
import com.akashk.palette.core.ui.UIText
import com.akashk.palette.palettelist.PaletteListViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@HiltViewModel
class CreateNewPaletteViewModel @Inject constructor() : ViewModel() {

    private val _viewState: MutableStateFlow<NewPaletteState> =
        MutableStateFlow(NewPaletteState.Initial)
    val viewState: StateFlow<NewPaletteState> = _viewState

    fun enterPaletteName(name : String) {
        val currentName = viewState.value.paletteName
        val error = (_viewState.value as? NewPaletteState.Active)?.paletteNameError

        _viewState.value = NewPaletteState.Active(
            paletteName = name,
            paletteNameError = null
        )
    }

    fun onContinue(name : String) {
        val currentName = viewState.value.paletteName
        if(currentName.isEmpty()){
            _viewState.value = NewPaletteState.Active(
                paletteName = name,
                paletteNameError = UIText.StringText("Enter valid palette name")
            )
            return
        }
        _viewState.value = NewPaletteState.NavigateToColorPicker(
            paletteName = currentName
        )
    }
}