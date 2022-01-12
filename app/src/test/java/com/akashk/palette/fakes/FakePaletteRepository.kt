package com.akashk.palette.fakes

import com.akashk.palette.core.Result
import com.akashk.palette.domain.data.Palette
import com.akashk.palette.domain.data.PaletteRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf

class FakePaletteRepository {
    val mock: PaletteRepository = mockk()

    fun mockPaletteListResult(result: Result<List<Palette>>) {
        coEvery {
            mock.fetchAllPalettes()
        } returns flowOf(result)
    }
}
