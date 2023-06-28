package szaszki2;
import java.util.Scanner;


public class ChessGame {
	static boolean gameOver = false;
  public static void main(String[] args) {
      Chessboard chessboard = new Chessboard();
//      Chessboard chessboard1 = chessboard.clone();
//      Chessboard chessboard1 = chessboard;
//      Chessboard chessboard2 = chessboard;
      Scanner scanner = new Scanner(System.in);
      chessboard.initializeBoard();
      
      int moveCounter = 0;
//      BlackPlayer.pointCount = 0;
//      WhitePlayer.pointCount = 0;
      while (!gameOver) {
          
//          printChessboard(chessboard);

          if (moveCounter%2 == 0) {
        	  printChessboardForWhite(chessboard);
        	  System.out.println("White to move!");
          }
          else {
        	  printChessboardForBlack(chessboard);
        	  System.out.println("Black to move!");};
          
          System.out.println("Enter the coordinates of the piece you want to move (eg. a1 or b2):");
          String readPosition = scanner.nextLine();
          if(readPosition.length()!=2||!Character.isLetter(readPosition.charAt(0))||!Character.isDigit(readPosition.charAt(1))) {
        	  System.out.println("Wrong coordinates. Please try again.");
        	  continue;
          }
          int pieceX, pieceY;
          
          pieceX = ParseCoordinates.Xcoordinate(readPosition);
          pieceY = ParseCoordinates.Ycoordinate(readPosition);

//          pieceX = Character.getNumericValue(readPosition.charAt(1));
          //int pieceX = scanner.nextInt();
          //int pieceY = scanner.nextInt();

          Piece piece = chessboard.getPieceAt(pieceX, pieceY);
          
          if (piece == null) {
              System.out.println("No piece found at the specified coordinates!");
              continue;
          }
          
          if ((piece.isWhite && moveCounter%2 == 0) || (!piece.isWhite && moveCounter%2 == 1)){
//        	  moveCounter++;
          }
          else {
        	  System.out.println("This piece is of the wrong color.");
        	  continue;
          }

          
          System.out.println("Enter coordinates of the destination where you want to put this piece.");
           readPosition = scanner.nextLine();
          if(readPosition.length()!=2) {
        	  System.out.println("Wrong coordinates. Please try again.");
        	  continue;
          }
          int destX, destY;
          
          destX = ParseCoordinates.Xcoordinate(readPosition);
          destY = ParseCoordinates.Ycoordinate(readPosition);

          


          
          if (chessboard.movePiece(piece, destX, destY, chessboard)) {
        	    if (chessboard.checkChecker(chessboard)) {
        	        if (chessboard.blackCheck && moveCounter % 2 == 1 && !piece.isWhite()) {
        	            chessboard.board[pieceX][pieceY] = chessboard.board[destX][destY];
        	            chessboard.board[destX][destY] = null;
        	            Chessboard copiedBoard = chessboard.clone();
//        	            Chessboard copiedBoard = new Chessboard(chessboard);
        	            if(copiedBoard.mateChecker( moveCounter)) {
        	            	System.out.println("Checkmate. White wins");
        	            	gameOver = true;
        	            	break;
        	            }
        	            System.out.println("This move isn't allowed now - Black is in check");
        	            continue;
        	        } else if (chessboard.whiteCheck && moveCounter % 2 == 0 && piece.isWhite()) {
        	        	chessboard.board[pieceX][pieceY] = chessboard.board[destX][destY];
        	            chessboard.board[destX][destY] = null;
//        	            Chessboard copiedBoard = chessboard.clone();
        	            Chessboard copiedBoard = chessboard.clone();
        	            if(copiedBoard.mateChecker( moveCounter)) {
        	            	System.out.println("Checkmate. Black wins");
        	            	gameOver = true;
        	            	break;
        	            }
        	            System.out.println("This move isn't allowed now - White is in check");
        	            continue;
        	        } else {
        	            moveCounter++;
//        	            chessboard1 = chessboard;
        	        }
        	    } else {
        	        moveCounter++;
//        	        chessboard1 = chessboard;
        	    }
        	}
          if(chessboard.blackCheck || chessboard.whiteCheck) {
        	  Chessboard copiedBoard = chessboard.clone();
        	  copiedBoard.isOriginal= false;

	            if(copiedBoard.mateChecker( moveCounter)) {
	            	System.out.println("Checkmate.");
	            	gameOver = true;
	            	if(chessboard.blackCheck) {
	            		System.out.println("White wins.");
	            		printChessboardForBlack(chessboard);
	            	}
	            	else {System.out.println("Black wins");
	            	printChessboardForWhite(chessboard);}
	            	
	            	break;
	            }
          }



          
          if (isGameOver(chessboard)) {
              System.out.println("Game over!");
              gameOver = true;
          }
      }

      scanner.close();
  }
  public static void setGameOver() {
	  gameOver = true;
  }
  private static void printChessboardForWhite(Chessboard chessboard) {
//      Piece[][] board = chessboard.getBoard();

     if(chessboard.checkChecker(chessboard) && !gameOver) {
    	 System.out.println("Warning: Check!");
     }
//      System.out.println("White Player points:"+ WhitePlayer.pointCount);
      System.out.println("  a b c d e f g h");

      for (int i = 7; i >= 0; i--) {
          System.out.print((i+1) + " ");
          for (int j = 0; j <= 7; j++) {
//              Piece piece = board[i][j];
              Piece piece = chessboard.getPieceAt(i, j);
              if (piece == null) {
                  System.out.print("- ");
              } else {
                  String symbol = getPieceSymbol(piece);


                  System.out.print(symbol + " ");
              }
          }
          System.out.println();
      }
      System.out.println("  a b c d e f g h");
  }
  
  private static void printChessboardForBlack(Chessboard chessboard) {


      
	  if(chessboard.checkChecker(chessboard) && !gameOver) {
	    	 System.out.println("Warning: Check!");
	     }

      System.out.println("  h g f e d c b a");

      for (int i = 0; i < 8; i++) {
          System.out.print((i+1) + " ");
          for (int j = 7; j >= 0; j--) {
              Piece piece = chessboard.getPieceAt(i, j);
              if (piece == null) {
                  System.out.print("- ");
              } else {
                  String symbol = getPieceSymbol(piece);
                  System.out.print(symbol + " ");
              }
          }
          System.out.println();
      }
      System.out.println("  h g f e d c b a");
  }

  private static String getPieceSymbol(Piece piece) {
      if (piece instanceof Pawn) {
          return piece.isWhite() ? "\u265f" : "\u2659";
      } else if (piece instanceof Rook) {
          return piece.isWhite() ? "\u265c" : "\u2656";
      } else if (piece instanceof Knight) {
          return piece.isWhite() ? "\u265e" : "\u2658";
      } else if (piece instanceof Bishop) {
          return piece.isWhite() ? "\u265d" : "\u2657";
      } else if (piece instanceof Queen) {
          return piece.isWhite() ? "\u265b" : "\u2655";
      } else if (piece instanceof King) {
          return piece.isWhite() ? "\u265a" : "\u2654";
      } else {
          return ".";
      }
  }



  private static boolean isGameOver(Chessboard chessboard) {
      
      return false;
  }
  
}
