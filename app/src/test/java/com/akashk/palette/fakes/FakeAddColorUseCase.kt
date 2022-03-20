package com.akashk.palette.fakes

import com.akashk.palette.colorpicker.ColorPickerState
import com.akashk.palette.colorpicker.IAddColorUseCase
import io.mockk.coEvery
import io.mockk.mockk

class FakeAddColorUseCase {
    val mock: IAddColorUseCase = mockk()

    fun mockResultOfAddColorUseCase(viewState: ColorPickerState) {
        coEvery {
            mock.invoke(any(), any(), any())
        } returns viewState
    }
}
