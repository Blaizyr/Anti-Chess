package com.example.anti_chess



enum class PieceColors { White, Black, NoColor; }


interface Move {
    fun moveFrom(currentPosition: Int, targetPosition: Int): Int
}

enum class PieceType: Move {

    Empty {
        override fun moveFrom(currentPosition: Int, targetPosition: Int): Int {
            TODO("Not yet implemented")
        }
    },

    Pawn {
        override fun moveFrom(currentPosition: Int, targetPosition: Int): Int {

            return if (checkCollision(currentPosition, targetPosition)) {
                return currentPosition
            } else {
                return targetPosition
            }
        }
    },

    Rook {
        override fun moveFrom(currentPosition: Int, targetPosition: Int): Int {
            return if (checkCollision(currentPosition, targetPosition)) {
                return currentPosition
            } else {
                return targetPosition
            }
            TODO("Not yet implemented")
        }

    },

    Knight {
        override fun moveFrom(currentPosition: Int, targetPosition: Int): Int {
            return if (checkCollision(currentPosition, targetPosition)) {
                return currentPosition
            } else {
                return targetPosition
            }
            TODO("Not yet implemented")
        }

    },

    Bishop {
        override fun moveFrom(currentPosition: Int, targetPosition: Int): Int {
            return if (checkCollision(currentPosition, targetPosition)) {
                return currentPosition
            } else {
                return targetPosition
            }
            TODO("Not yet implemented")
        }
    },

    Queen {
        override fun moveFrom(currentPosition: Int, targetPosition: Int): Int {
            return if (checkCollision(currentPosition, targetPosition)) {
                return currentPosition
            } else {
                return targetPosition
            }
            TODO("Not yet implemented")
        }

    },

    King {
        override fun moveFrom(previousPosition: Int, targetPosition: Int): Int {
            return if (checkCollision(previousPosition, targetPosition)) {
                return previousPosition
            } else {
                return targetPosition
            }
            TODO("Not yet implemented")
        }
    };
}

class Piece(
    val color: PieceColors,
    val pieceType: PieceType
) : Move by pieceType {
    fun move(currentPosition: Int, targetPosition: Int, gameStateUI: GameStateUI) {
        val previousPosition = moveFrom(currentPosition, targetPosition)
        gameStateUI.piecesPositions[targetPosition] = gameStateUI.piecesPositions[previousPosition]
        gameStateUI.piecesPositions[previousPosition] = Piece(PieceColors.NoColor, PieceType.Empty)
    }
}


 fun checkCollision(previousPosition: Int,  targetPosition: Int): Boolean {
     val isThereCollision: Boolean = true
     return isThereCollision
 }