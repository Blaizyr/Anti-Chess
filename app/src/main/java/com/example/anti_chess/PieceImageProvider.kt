package com.example.anti_chess

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun PieceImageProvider(piece: Piece): Int {
    val context = LocalContext.current

    fun buildResourceName(piece: Piece): String {
        return when (piece.color) {
            PieceColors.Black -> "black"
            PieceColors.White -> "white"
            else -> ""
        } + when (piece.pieceType) {
            PieceType.Rook -> "_rook"
            PieceType.Knight -> "_knight"
            PieceType.Bishop -> "_bishop"
            PieceType.Queen -> "_queen"
            PieceType.King -> "_king"
            PieceType.Pawn -> "_pawn"
            else -> ""
        }
    }

    val resourceName = buildResourceName(piece)

    fun getDrawableResourceId(resourceName: String): Int {
        return if (resourceName != "") {
            val resources = context.resources
            val packageName = context.packageName
            resources.getIdentifier(resourceName, "drawable", packageName)
        } else
            0
    }

    return getDrawableResourceId(resourceName)
}