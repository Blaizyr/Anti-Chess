package com.example.anti_chess

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.anti_chess.ui.theme.AntiChessTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AntiChessTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GameView(viewModel = GameStateVM())
                }
            }
        }
    }
}

@Composable
fun ChessCell(
    modifier: Modifier = Modifier,
//    cellID: String,
    piece: Piece? = null,
    color: Color,
) {
    val context = LocalContext.current
    Box(
        modifier = modifier
            .background(color)

    ) {
        Box(
            modifier = modifier
                .fillMaxSize(1f)
                .padding(18.dp)
        ) {
            val drawableResourceId: Int = PieceImageProvider(piece!!)

            if (drawableResourceId != 0) {
                Image(
                    painter = painterResource(drawableResourceId),
                    contentDescription = piece.pieceType.name + " " + piece.color.name,
                    modifier = modifier.fillMaxSize()
                )
            } else {

            }
        }
    }
}

@Composable
fun ChessBoard(
    modifier: Modifier = Modifier,
    gameState: GameStateUI,
) {
    val context = LocalContext.current

    var pointedCellId by remember { mutableStateOf<Int?>(null) }
    var activePiece by remember { mutableStateOf<Piece?>(null) }

    fun activatePieceByID(pointedCellId: Int) {
        activePiece = gameState.piecesPositions[pointedCellId]
    }

    fun pointCell(cellIndex: Int) {
        pointedCellId = cellIndex
        val checkedPiece = gameState.piecesPositions[cellIndex]
        if (checkedPiece != null) {
            activatePieceByID(cellIndex)
        }
    }

    fun deactivatePiece() {
        activePiece = null
    }

    fun deselectCell() {
        pointedCellId = null
        deactivatePiece()
    }



    Card {
        LazyVerticalGrid(
            columns = GridCells.Fixed(8),
            modifier = modifier
                .padding(horizontal = 5.dp, vertical = 5.dp)
        ) {

            items(64) { cellIndex ->
                val row = cellIndex / 8
                val column = cellIndex % 8
                val color = if ((row + column) % 2 == 0) Color.White else Color.DarkGray
//                val cellID = computeCellID(column, row + 1)
                val isCellActive: Boolean = (cellIndex == pointedCellId)
                val pieceOnField = gameState.piecesPositions[cellIndex]
                val activePiece = activePiece ?: null

                ChessCell(
                    piece = pieceOnField,
                    color = if (isCellActive) {
                        Color.Gray
                    } else color,
                    modifier = modifier
                        .weight(1f)
                        .clickable {
                            if (!isCellActive) {
                                if (activePiece != null) {
                                    activePiece.move(pointedCellId!!, cellIndex, gameState)
                                    deselectCell()
                                } else {
                                    pointCell(cellIndex)
                                }
                            } else {
                                deselectCell()
                            }
                        }
                )
            }
        }
    }
}

@Composable
fun GameView(viewModel: GameStateVM, modifier: Modifier = Modifier) {
    val uiState by viewModel.uiState.collectAsState()

    Column {
        Box(modifier = modifier.fillMaxWidth()) {
            Text(
                text = "AntiChess",
                modifier = Modifier.align(Alignment.TopCenter)
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            ChessBoard(
                gameState = uiState,
            )
        }
        /*
                Box(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                    Button(onClick = { isCellIDVisible = !isCellIDVisible }) {
                        Text(
                            text = if (isCellIDVisible) "Hide ID of fields" else "Show ID of fields",
                        )
                    }
                }
        */
    }
}


fun computeCellID(column: Int, row: Int): String {
    val columnChar = ('a'.code + column).toChar()
    return "$columnChar" + row
}

@Preview(showBackground = true)
@Composable
fun MainPreview() {
    GameView(GameStateVM())
}
