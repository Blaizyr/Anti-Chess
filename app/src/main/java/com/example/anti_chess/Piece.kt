package com.example.anti_chess



enum class PieceColors { White, Black, NoColor; }


interface Move {
    fun move(index: Int): Int { return index }
}

enum class PieceType: Move {

    Empty {
        override fun move(index: Int): Int {
            return index
        }
    },

    Pawn {
        override fun move(index: Int): Int {
            val dIndex = index - 8
            return dIndex
        }
    },

    Rook {
        override fun move(index: Int): Int {
//            val dIndex =
            return index
        }
    },

    Knight {
        override fun move(index: Int): Int {
            return index
        }
    },

    Bishop {
        override fun move(index: Int): Int {
            return index
        }
    },

    Queen {
        override fun move(index: Int): Int {
            return index
        }
    },

    King {
        override fun move(index: Int): Int {
            return index
        }
    };
}

class Piece(
    val CellID: Int,
    val color: PieceColors,
    val pieceType: PieceType
) : Move by pieceType {

}


