package com.akashk.palette.domain.data

import com.akashk.palette.core.Result
import kotlinx.coroutines.flow.Flow

interface PaletteRepository {
    fun fetchAllPalettes(): Flow<Result<List<Palette>>>
    suspend fun addPalette(palette: Palette): Result<Unit>
    suspend fun deletePalette(palette: Palette): Result<Unit>
    fun updatePalette(newPalette: Palette): Result<Unit>
}
