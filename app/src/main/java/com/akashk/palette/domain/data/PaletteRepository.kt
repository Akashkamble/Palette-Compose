package com.akashk.palette.domain.data

import com.akashk.palette.core.Result
import com.akashk.palette.domain.data.Palette
import kotlinx.coroutines.flow.Flow

interface PaletteRepository {
    fun fetchAllPalettes() : Flow<Result<List<Palette>>>
    suspend fun addPalette(palette : Palette) : Result<Unit>
    suspend fun deletePalette(palette : Palette) : Result<Unit>
}