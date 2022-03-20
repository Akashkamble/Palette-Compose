package com.akashk.palette.domain.data

import com.akashk.palette.core.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import java.lang.IllegalArgumentException
import javax.inject.Inject

class DemoPaletteRepository @Inject constructor() : PaletteRepository {
    private val data = mutableListOf<Palette>()

    init {
        val palettes = (1..2).map { index ->
            Palette(
                id = index.toString(),
                name = "Palette $index",
                colorList = mutableListOf(
                    "#8EDC51",
                    "#51DCD4",
                    "#D27C7C",
                    "#3D8A95",
                    "#8E51DC",
                    "#5C51DC",
                    "#FFFFFF",
                    "#000000"
                ),
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
        val paletteFromData = data.find { p -> p.id == palette.id }
        val index = data.indexOf(paletteFromData)
        data.removeAt(index)
        return Result.Success(Unit)
    }

    override suspend fun updatePalette(newPalette: Palette): Result<Unit> {
        val palette = data.find { p -> p.id == newPalette.id }
        val index = data.indexOf(palette)
        data.removeAt(index = index)
        data.add(index, newPalette)
        return Result.Success(Unit)
    }

    override fun fetchPaletteById(id: String): Palette {
        val palette = data.find { p -> p.id == id }
        if (palette != null) {
            return palette
        }
        throw IllegalArgumentException("palette id is not valid")
    }
}
