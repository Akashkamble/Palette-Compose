package com.akashk.palette.fakes

import com.akashk.palette.palettedetail.IDeleteColorUseCase
import com.akashk.palette.palettedetail.PaletteDetailState
import io.mockk.coEvery
import io.mockk.mockk

class FakeDeleteColorUseCase {
    val mock: IDeleteColorUseCase = mockk()

    fun mockResultOfDeleteColorUseCase(viewState: PaletteDetailState) {
        coEvery {
            mock.invoke(any())
        } returns viewState
    }
}
