package com.akashk.palette.domain.data

import com.akash.palette.sqldelight.LocalPalette
import com.akash.palette.sqldelight.PaletteQueries
import com.akashk.palette.core.Result
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import java.lang.Exception
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalPaletteRepository @Inject constructor(
    private val queries: PaletteQueries
) : PaletteRepository {
    override fun fetchAllPalettes(): Flow<Result<List<Palette>>> {
        return queries.selectAll()
            .asFlow()
            .mapToList()
            .map { paletteList ->
                val domainPalettes = paletteList.map { localPalette ->
                    localPalette.toPalette()
                }
                Result.Success(domainPalettes)
            }
    }

    override suspend fun addPalette(palette: Palette): Result<Unit> {
        return try {
            queries
                .insertPalette(palette.toLocalPalette())
            Result.Success(Unit)
        } catch (e : Exception) {
            Result.Error(e)
        }

    }

    override suspend fun deletePalette(palette: Palette): Result<Unit> {
        return try {
            queries.deletePalette(palette.id)
            Result.Success(Unit)
        } catch (e : Exception) {
            Result.Error(e)
        }
    }

    override suspend fun updatePalette(newPalette: Palette): Result<Unit> {
        return try {
            queries.insertPalette(newPalette.toLocalPalette())
            Result.Success(Unit)
        } catch (e : Exception) {
            Result.Error(e)
        }
    }

    override fun fetchPaletteById(id: String): Palette {
        return try {
            queries
                .getPaletteById(id)
                .executeAsOne()
                .toPalette()
        } catch (e : Exception) {
            throw IllegalArgumentException("palette with provided id is not present in the database")
        }
    }


    private fun LocalPalette.toPalette() : Palette {
        return Palette(
            id = this.id,
            name = this.name,
            colorList = this.colors.split(",").toMutableList(),
            modifiedAt = this.modifiedAt.toLong()
        )
    }

    private fun Palette.toLocalPalette() : LocalPalette {
        return LocalPalette(
            id = this.id,
            name = this.name,
            colors = this.colorList.joinToString(),
            modifiedAt = this.modifiedAt.toString()
        )
    }
}