package com.example.anti_chess

import kotlin.math.abs


enum class PieceColors { White, Black, NoColor; }


interface Move {
    fun isValidMove(currentPosition: Int, targetPosition: Int): Boolean
    fun hasCollision(currentPosition: Int, targetPosition: Int): Boolean
}

enum class PieceType : Move {

    Empty {
        override fun isValidMove(currentPosition: Int, targetPosition: Int): Boolean {
            TODO("Not yet implemented")
        }

        override fun hasCollision(currentPosition: Int, targetPosition: Int): Boolean {
            TODO("Not yet implemented")
        }
    },

    Pawn {
        override fun isValidMove(currentPosition: Int, targetPosition: Int): Boolean {
            // Czy przy Pawnach funkcja powinna przyjmować argument koloru?
            // ??
            val diff = targetPosition - currentPosition
            val diffAbs = abs(diff)

            // Initial two-squares forward move
            if (diffAbs == 16 &&
                (currentPosition in 8..15 ||
                currentPosition in 49..56)
                ) {
                return true
            }
            // Standard pawn move
            if (diffAbs == 8) {
                return true
            }
            // Diagonally strike
            if (diffAbs == 9 || diffAbs == 7) {
                // check if oponents piece stand on target cell
                return false
            }
            return false
        }

        override fun hasCollision(currentPosition: Int, targetPosition: Int): Boolean {
            var hasCollision = false
            if (true) {
                hasCollision = false
            }
            return hasCollision
        }
    },

    Rook {
             override fun isValidMove(currentPosition: Int, targetPosition: Int): Boolean {
            val diff = abs(targetPosition - currentPosition)
            // Movement along a column
            if (diff % 8 == 0 && currentPosition >= 0 && targetPosition <= 63) {
                return true
            }

            // Movement along a row
            if (diff <= 7 && currentPosition / 8 == targetPosition / 8 ) {
                return true
            }

            return false
        }

        override fun hasCollision(currentPosition: Int, targetPosition: Int): Boolean {
            return false
         }
    },

    Knight {

        override fun isValidMove(currentPosition: Int, targetPosition: Int): Boolean {
            val diff = abs(targetPosition - currentPosition)

            return (diff == 10 || diff == 6 || diff == 15 || diff == 17) &&
                    currentPosition >= 0 && targetPosition <= 63
        }

        override fun hasCollision(currentPosition: Int, targetPosition: Int): Boolean {
            return false
        }
    },

    Bishop {

        override fun isValidMove(currentPosition: Int, targetPosition: Int): Boolean {
            val diff = abs(targetPosition - currentPosition)
            return diff % 7 == 0 || diff % 9 == 0
        }

        override fun hasCollision(currentPosition: Int, targetPosition: Int): Boolean {
            return false
        }
    },

    Queen {

        override fun isValidMove(currentPosition: Int, targetPosition: Int): Boolean {

            val diff = abs(targetPosition - currentPosition)

            return (diff % 8 == 0 || diff <= 7) ||  // Horizontal
                    (currentPosition / 8 == targetPosition / 8) ||  // Vertical
                    (diff % 7 == 0 || diff % 9 == 0)  // Diagonal
        }

        override fun hasCollision(currentPosition: Int, targetPosition: Int): Boolean {
            return false
        }
    },

    King {

        override fun isValidMove(currentPosition: Int, targetPosition: Int): Boolean {

            val diff = abs(targetPosition - currentPosition)
            // Castlings

            // Basic moves
            return (diff == 1 || diff == 8 || diff == 7 || diff == 9) &&
                    (currentPosition / 8 == targetPosition / 8 || currentPosition % 8 == targetPosition % 8)
        }

        override fun hasCollision(currentPosition: Int, targetPosition: Int): Boolean {
            return false
        }
    };
}

class Piece(
    val color: PieceColors,
    val pieceType: PieceType,
) : Move by pieceType {

/*    previous implementation:
    fun move(currentPosition: Int, targetPosition: Int, gameStateUI: GameStateUI) {
        val previousPosition = moveFrom(currentPosition, targetPosition) // moveFrom nie ma zwracać poprzedniej pozycji
        gameStateUI.piecesPositions[targetPosition] = gameStateUI.piecesPositions[previousPosition]
        gameStateUI.piecesPositions[previousPosition] = Piece(PieceColors.NoColor, PieceType.Empty)
    }
*/
    fun move(currentPosition: Int, targetPosition: Int, gameStateUI: GameStateUI) {

        if (isValidMove(currentPosition, targetPosition)
            && !hasCollision(currentPosition, targetPosition)) {
            gameStateUI.piecesPositions[targetPosition] = gameStateUI.piecesPositions[currentPosition]
            gameStateUI.piecesPositions[currentPosition] = Piece(PieceColors.NoColor, PieceType.Empty)
        }
    }
}


