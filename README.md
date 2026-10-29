# Chess

A simple two-player chess game made in Java with a Swing UI.

## Overview

The game shows an 8x8 board in a fixed-size 640x640 window. Two players take turns at the same computer, clicking a piece and then a destination square. The game enforces legal moves, detects check, checkmate and stalemate, and shows the game state in the window title.

## Features

- Standard starting position with piece images for both sides.
- Movement rules for pawns, knights, bishops, rooks, queens and kings, including the pawn's two-square first move and diagonal captures.
- Legal-move filtering: a move is only allowed if it does not leave the mover's own king in check.
- Legal destinations for the selected piece are highlighted in green.
- Pawn promotion: a pawn reaching the last rank automatically becomes a queen.
- Check, checkmate and stalemate detection. The window title shows whose turn it is, "(Check)" when the side to move is in check, and "Checkmate!" or "Stalemate!" when the game ends.

## Limitations

- Castling and en passant are not implemented.
- Promotion is always to a queen.
- There is no draw detection other than stalemate (no fifty-move rule or threefold repetition), and no undo, save or computer opponent.
- Invalid selections are reported only on standard output (for example, "Not a valid move!").

## Tech stack

- Java 8 (developed with 1.8.0_265)
- Swing / AWT for the UI
- No external dependencies and no build tool configuration

## Getting started

### Prerequisites

- A JDK, version 1.8 or later.

### Build and run

The repository contains only the sources and images; there is no Maven, Gradle or IDE project file. The simplest route is to open the source in your favourite Java IDE with `src` as the source root. It should build with any Java 1.8 version, and you can then run `Game.Main`.

The piece images are loaded from the relative path `res/`, so the working directory must be the repository root. To build and run from the command line instead:

```sh
javac -d out src/Game/*.java src/Pieces/*.java
java -cp out Game.Main
```

## Usage

- The game starts with White to move.
- Click one of your own pieces to select it. Its legal destinations are highlighted.
- Click a highlighted square to move there. Clicking any other square cancels the selection.
- Play alternates between White and Black until checkmate or stalemate, after which the board stops accepting moves.

## Project structure

```
res/                  Piece images (black_*.png, white_*.png)
src/Game/
  Main.java           Entry point
  Game.java           Singleton game state: turn handling, move processing, setup, check/mate detection
  GUI.java            Swing window and 8x8 grid of buttons
  ChessButton.java    JButton that knows its board coordinates
  Board.java          Board state (2D array of pieces)
  Move.java           A board coordinate / move target
src/Pieces/
  Piece.java          Abstract piece with legal-move filtering
  Pawn.java, Knight.java, Bishop.java, Rook.java, Queen.java, King.java
  Type.java           Piece type enum
```

Happy Chess-ing(?)!
