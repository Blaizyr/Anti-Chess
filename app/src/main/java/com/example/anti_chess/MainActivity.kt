package com.example.anti_chess

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
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
    isCellIDVisible: Boolean = false,
    cellID: String,
    piece: Piece? = null,
    isActive: Boolean? = null,
    color: Color,
) {
    Box(
        modifier = modifier
            .background(color)

    ) {
        Box(
            modifier = modifier
                .fillMaxSize(1f)
                .padding(15.dp)
        ) {
            Text(
                text = if (isCellIDVisible) {cellID} else "", modifier
                    .align(Alignment.Center)
                    .fillMaxWidth()
                )
        }
    }
}

@Composable
fun ChessTable(
    modifier: Modifier = Modifier,
    isCellIDVisible: Boolean = false,
    pointedCell: Int? = null,
    pointCell: (Int) -> Unit
) {
    Card {
        LazyVerticalGrid(
            columns = GridCells.Fixed(8),
            modifier = modifier
                .padding(horizontal = 5.dp, vertical = 5.dp)
        ) {

            items(64) { index ->
                val row = index / 8
                val column = index % 8
                val color = if ((row + column) % 2 == 0) Color.White else Color.Black
                val cellID = computeCellID(column, row+1)

                ChessCell(
                    isCellIDVisible = isCellIDVisible,
                    cellID = cellID,
                    isActive = if (pointedCell == index) {true} else {false},
                    color = if (pointedCell == index) {Color.DarkGray} else color,
                    modifier = modifier
                        .weight(1f)
                        .clickable { pointCell(index) }
                )
            }
        }
    }
}

@Composable
fun GameView(viewModel: GameStateVM, modifier: Modifier = Modifier) {
    var isCellIDVisible by remember { mutableStateOf(false) }
    val uiState by viewModel.uiState.collectAsState()
    val pointedCell = uiState.pointedCell

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
            ChessTable(
                isCellIDVisible = isCellIDVisible,
                pointedCell = pointedCell,
            ) {
                viewModel.pointCell(it) //
            }
        }
        Box( modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Button(onClick = { isCellIDVisible = !isCellIDVisible }) {
                Text(
                    text = if (isCellIDVisible) "Hide ID of fields" else "Show ID of fields",
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainPreview() {
    GameView(GameStateVM())
}

fun computeCellID(column: Int, row: Int): String {
    val columnChar = ('a'.toInt() + column).toChar()
    return "$columnChar"+row
}