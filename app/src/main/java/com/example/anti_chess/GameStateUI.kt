package com.example.anti_chess
import com.example.anti_chess.PieceType.*
import com.example.anti_chess.PieceColors.*


data class GameStateUI(
    val pointedCell: Int? = null,
    val piecesPositions: MutableList<Piece> = MutableList(64) {
        when (it) {
            in 0..15 -> Piece(it, Black, when (it) {
                0, 7 -> Rook
                1, 6 -> Knight
                2, 5 -> Bishop
                3 -> Queen
                4 -> King
                else -> Pawn
            })
            in 48..63 -> Piece(it, White, when (it) {
                56, 63 -> Rook
                57, 62 -> Knight
                58, 61 -> Bishop
                59 -> Queen
                60 -> King
                else -> Pawn
            })
            else -> Piece(it, NoColor, Empty)
        }
    }
)