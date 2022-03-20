package com.akashk.palette.fakes

import com.akashk.palette.core.Result
import com.akashk.palette.domain.data.Palette
import com.akashk.palette.domain.data.PaletteRepository
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf

class FakePaletteRepository {
    val mock: PaletteRepository = mockk()

    fun mockPaletteListResult(result: Result<List<Palette>>) {
        coEvery {
            mock.fetchAllPalettes()
        } returns flowOf(result)
    }

    fun mockFetchPaletteById(palette: Palette) {
        every {
            mock.fetchPaletteById(any())
        } returns palette
    }

    fun mockDeletePalette(palette: Palette) {
        coEvery {
            mock.deletePalette(palette = palette)
        } returns Result.Success(Unit)
    }

    fun mockAddPalette(palette: Palette) {
        coEvery {
            mock.addPalette(palette = palette)
        } returns Result.Success(Unit)
    }

    fun mockUpdatePalette(palette: Palette) {
        coEvery {
            mock.updatePalette(palette)
        } returns Result.Success(Unit)
    }
}
