package com.akashk.palette.createnewpalettescreen

import app.cash.turbine.test
import com.akashk.palette.createnewpalette.CreateNewPaletteViewModel
import com.akashk.palette.createnewpalette.NewPaletteState
import com.google.common.truth.Truth.assertThat

class CreateNewPaletteViewModelRobot {
    private lateinit var viewModel: CreateNewPaletteViewModel

    fun buildViewModel() = apply {
        viewModel = CreateNewPaletteViewModel()
    }

    fun enterName(name: String) = apply {
        viewModel.enterPaletteName(name = name)
    }

    fun onContinueClick(name: String) = apply {
        viewModel.onContinue(name)
    }

    /**
     * Launch a coroutine that will observe our [viewModel]'s view state and ensure that we consume
     * all of the supplied [viewStates] in the same order that they are supplied.
     *
     * We should call this near the front of the test, to ensure that every view state we emit
     * can be collected by this.
     */
    suspend fun expectViewStates(
        action: CreateNewPaletteViewModelRobot.() -> Unit,
        viewStates: List<NewPaletteState>,
    ) = apply {
        viewModel.viewState.test {
            action()

            for (state in viewStates) {
                assertThat(awaitItem()).isEqualTo(state)
            }

            this.cancel()
        }
    }
}
