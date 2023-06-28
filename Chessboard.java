package szaszki2;

//import java.util.Iterator;

class Chessboard implements Cloneable{
  public Piece[][] board;


  public Chessboard() {
      board = new Piece[8][8];

  }
  public boolean isOriginal = true;
  @Override
  protected Chessboard clone() {
      try {
          Chessboard cloned = (Chessboard) super.clone();
          
          
          cloned.board = new Piece[8][8];
          for (int i = 0; i < 8; i++) {
              for (int j = 0; j < 8; j++) {
                  Piece piece = this.board[i][j];
                  if (piece != null) {
                      cloned.board[i][j] = piece.clone();
                  }
              }
          }
          
         
          
          return cloned;
      } 
      catch (CloneNotSupportedException e) {
         
          return null;
      }
  }
 

  public void initializeBoard() {
      

      
      for (int i = 0; i < 8; i++) {
          board[1][i] = new Pawn(true, 1, i, false);
          board[6][i] = new Pawn(false, 6, i, false);
      }
      for(int i = 2; i < 6; i++) {
    	  for(int j = 0; j < 8; j++) {
    		  board[i][j] = null;
    	  }
	}
      
      
      
      
      board[0][0] = new Rook(true, 0, 0, false);
      board[0][7] = new Rook(true, 0, 7, false);
      board[7][0] = new Rook(false, 7, 0, false);
      board[7][7] = new Rook(false, 7, 7, false);

      
      board[0][1] = new Knight(true, 0, 1, false);
      board[0][6] = new Knight(true, 0, 6, false);
      board[7][1] = new Knight(false, 7, 1, false);
      board[7][6] = new Knight(false, 7, 6, false);

      
      board[0][2] = new Bishop(true, 0, 2, false);
      board[0][5] = new Bishop(true, 0, 5, false);
      board[7][2] = new Bishop(false, 7, 2, false);
      board[7][5] = new Bishop(false, 7, 5, false);

      
      board[0][3] = new Queen(true, 0, 3, false);
      board[7][3] = new Queen(false, 7, 3, false);

     
      board[0][4] = new King(true, 0, 4, false);
      board[7][4] = new King(false, 7, 4, false);
  }

  public Piece getPieceAt(int x, int y) {
      return board[x][y];
  }


  
  public boolean movePiece(Piece piece, int newX, int newY, Chessboard chessboard) {
      if (piece.isValidMove(newX, newY, chessboard)) {
          int currentX = piece.getX();
          int currentY = piece.getY();
          
          Piece sourcePiece = board[currentX][currentY];
          Piece destinationPiece = board[newX][newY];
          Piece destinationPiecePiece = chessboard.getPieceAt(newX, newY);
 if (sourcePiece == null) {
            	  return false;
              }
          if (destinationPiece == null) {
              
              board[newX][newY] = sourcePiece;
             
              board[currentX][currentY] = null;
              piece.setX(newX);
              piece.setY(newY);
              if(this.isOriginal) {
              System.out.println("Move successful");
              }
              return true;
          } else if(destinationPiece.isWhite != sourcePiece.isWhite) {

        	  
        	  board[newX][newY] = sourcePiece;
              board[currentX][currentY] = null;
              piece.setX(newX);
              piece.setY(newY);
              System.out.println("Move successful");
              if(destinationPiecePiece instanceof King) {
            	  if (destinationPiece.isWhite) {
            		  System.out.println("Black wins!");
            	  }
            	  else {
            		  System.out.println("White wins!");
            	  }
            	  ChessGame.setGameOver();
              }
              return true;
          }
          
          else {
        	  if(this.isOriginal) {
              System.out.println("Invalid move - Destination square is occupied!");
              }
              return false;
          }
      } else {
    	  if(this.isOriginal) {
          System.out.println("Invalid move!");
    	  }
          return false;
      }
//      return false;
  }
  public Piece[][] getBoard() {
        return board;
    }
  
  public boolean whiteCheck = false;
  public boolean blackCheck = false;
  

  
  public boolean checkChecker(Chessboard chessboard) {
	    for (int ixx = 0; ixx < 8; ixx++) {
	        for (int iyy = 0; iyy < 8; iyy++) {
	            if (chessboard.getPieceAt(ixx, iyy) != null) {
	                for (int ixxx = 0; ixxx < 8; ixxx++) {
	                    for (int iyyy = 0; iyyy < 8; iyyy++) {
	                        if (ixx < 0 || iyy < 0 || ixxx < 0 || iyyy < 0 || ixx > 7 || iyy > 7 || ixxx > 7 || iyyy > 7) {
	                            continue;
	                        }
	                        if (chessboard.getPieceAt(ixx, iyy) != null && chessboard.getPieceAt(ixxx, iyyy) != null) {
	                            if (chessboard.getPieceAt(ixxx, iyyy) instanceof King) {
	                                if (chessboard.getPieceAt(ixx, iyy).isValidMove(ixxx, iyyy, chessboard) && chessboard.getPieceAt(ixx, iyy).isWhite != chessboard.getPieceAt(ixxx, iyyy).isWhite) {
	                                    if (chessboard.getPieceAt(ixxx, iyyy).isWhite) {
	                                        whiteCheck = true;
	                                        return true;
	                                    } else {
	                                        blackCheck = true;
	                                        return true;
	                                    }
	                                }
	                            }
	                        }
	                    }
	                }
	            }
	        }
	    }
	    return false;
	}

  


  
  
  
  
  public boolean mateChecker(int moveCounter) {
	    if (!checkChecker(this)) {
	        return false;
	    }

	    for (int i = 0; i < 8; i++) {
	        for (int j = 0; j < 8; j++) {
	            Piece piece = getPieceAt(i, j);
	            if (piece != null && piece.isWhite() == (moveCounter % 2 == 0)) {
	                for (int newX = 0; newX < 8; newX++) {
	                    for (int newY = 0; newY < 8; newY++) {
	                        if (piece.isValidMove(newX, newY, this)) {
	                            Chessboard tempBoard = this.clone();
	                            tempBoard.isOriginal= false;
	                            Piece sourcePiece = tempBoard.getPieceAt(i, j);
	                            if (sourcePiece != null) {
	                                tempBoard.movePiece(sourcePiece, newX, newY, tempBoard);
	                                if (!tempBoard.checkChecker(tempBoard)) {
	                                    return false;
	                                }
	                            }
	                        }
	                    }
	                }
	            }
	        }
	    }

	    return true;
	}

  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
}