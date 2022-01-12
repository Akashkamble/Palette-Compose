package com.akashk.palette.palettelist

import com.akashk.palette.core.Result
import com.akashk.palette.domain.data.Palette
import com.akashk.palette.domain.data.PaletteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class DemoPaletteRepository @Inject constructor() : PaletteRepository {
    private val data = mutableListOf<Palette>()
    init {
        val palettes = (1..10).map { index ->
            Palette(
                id = index,
                name = "Palette $index",
                colorList = listOf("#6750a4", "#4534ff", "#0004fc", "#6750d8")
            )
        }
        data.addAll(palettes)
    }
    override fun fetchAllPalettes(): Flow<Result<List<Palette>>> {
        return flowOf(Result.Success(data = data))
    }

    override suspend fun addPalette(palette: Palette): Result<Unit> {
        data.add(palette)
        return Result.Success(Unit)
    }

    override suspend fun deletePalette(palette: Palette): Result<Unit> {
        data.remove(palette)
        return Result.Success(Unit)
    }
}
