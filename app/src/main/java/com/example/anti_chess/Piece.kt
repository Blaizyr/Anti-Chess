package com.example.anti_chess



enum class PieceColors { White, Black; }


interface move {
    fun move(index: Int): Int { return index }
}

enum class PieceType: move {
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
    val fieldID: Array<Array<String>>,
    val color: PieceColors,
    val pieceType: PieceType
) : move by pieceType {

}


