package com.akashk.palette.palettedetail

interface IDeleteColorUseCase {
    suspend operator fun invoke(viewState: PaletteDetailState): PaletteDetailState
}
