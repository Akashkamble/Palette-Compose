package com.akashk.palette.domain.data

import com.akashk.palette.core.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class DemoPaletteRepository @Inject constructor() : PaletteRepository {
    private val data = mutableListOf<Palette>()
    init {
        val palettes = (1..2).map { index ->
            Palette(
                id = index.toString(),
                name = "Palette $index",
                colorList = mutableListOf("#6750a4", "#4534ff", "#0004fc", "#6750d8"),
                modifiedAt = System.currentTimeMillis()
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

    override fun fetchPaletteById(id: Int): Flow<Result<Palette>> {
        val palette = data.first { id == id }
        return flowOf(Result.Success(palette))
    }

    override fun updatePalette(palette: Palette): Result<Unit> {
        data.find { p -> p.id == palette.id }?.copy(colorList = palette.colorList)
        return Result.Success(Unit)
    }
}
