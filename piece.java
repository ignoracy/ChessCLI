package szaszki2;

abstract class Piece implements Cloneable{
	  public boolean isWhite;
	  public int x;
	  public int y;
	  public int formerX;
	  public int formerY;

	  public boolean isEmpty = true;
	  public Piece(boolean isWhite, int x, int y, boolean isEmpty) {
	      this.isWhite = isWhite;
	      this.x = x;
	      this.y = y;
	      this.formerX = x;
	      this.formerY = y;
	  }
	  @Override
	  protected Piece clone() {
	      try {
	          Piece clonedPiece = (Piece) super.clone();

	          

	          return clonedPiece;
	      } catch (CloneNotSupportedException e) {

	          return null;
	      }
	  }
//	  public int points;
//	  public int getPoints() {
//		  return points;
//	  }
	  public boolean isWhite() {
	      return isWhite;
	  }
	  
	  public int getX() {
	      return x;
	  }

	  public int getY() {
	      return y;
	  }

	  public void setX(int x) {
		  this.formerX = this.x;
	      this.x = x;
	  }

	  public void setY(int y) {
		  this.formerY = this.y;
	      this.y = y;
	  }
	  
	  public void unmove() {
		  this.x = this.formerX;
		  this.y = this.formerY;
	  }
//	  protected String symbol = "x";
	  public boolean isValidMove(int x, int y, Chessboard chessboard) {
//		  System.out.println("ivm ogolny sie robi");
	      return true;
	  }
	}

	class Pawn extends Piece {
	  public Pawn(boolean isWhite, int x, int y, boolean isEmpty) {
	      super(isWhite, x, y, isEmpty);
	  }
//	  public int points = 1;
	  
//	  protected String symbol = isWhite() ? "\u265f" : "\u2659";

	  @Override
	  public boolean isValidMove(int newX, int newY, Chessboard chessboard) {
		  if (this instanceof Pawn) {
//			System.out.println("iVM instance");
		}
	      int currentX = getX();
	      int currentY = getY();
	      
	      if(this.isWhite&&currentX==1&&newX==3&&newY==currentY&&chessboard.getPieceAt(newX, newY)==null) {
	    	  return true;
	      }
	      if(!this.isWhite&&currentX==6&&newX==4&&newY==currentY&&chessboard.getPieceAt(newX, newY)==null) {
	    	  return true;
	      }
	      
	      if ((newX == currentX+1 && newY == currentY &&this.isWhite&&chessboard.getPieceAt(newX, newY)==null)) {
	          return true; 
	      } else if (newX == currentX-1 && newY == currentY&& !this.isWhite&&chessboard.getPieceAt(newX, newY)==null) {
	          return true; 
	      } else if(chessboard.getPieceAt(newX, newY)!=null&&newX == currentX+1 &&( newY == currentY+1 || newY== currentY-1)&&this.isWhite){
	    	  return true;
	      } else if(chessboard.getPieceAt(newX, newY)!=null&&newX == currentX-1 &&( newY == currentY+1 || newY== currentY-1)&&!this.isWhite){
	    	  return true;
	      }
	      
	      
	      else
	      {
//	    	  System.out.println("ivm false");
	          return false;
	      }
	  }
	}

	class Rook extends Piece {
	  public Rook(boolean isWhite, int x, int y, boolean isEmpty) {
	      super(isWhite, x, y, isEmpty);
	  }
//	  public int points = 5;
//	  protected String symbol = isWhite() ? "\u265c" : "\u2656";
	    @Override
	    public boolean isValidMove(int newX, int newY, Chessboard chessboard) {
	    	for(int ix = Math.min(getX(), newX); ix<=Math.max(newX, getX()); ix++) {
	    		for(int iy = Math.min(getY(), newY); iy<=Math.max(newY, getY()); iy++) {
	    			if(chessboard.getPieceAt(ix, iy)!=null) {
	    				if ((ix==newX && iy== newY)||(ix==getX()&&iy==getY())) {
	    					continue;
	    				}
	    				else return false;
	    			}
	    		}}
//	    	}
	        if (newX == getX() || newY == getY()) {
	            return true;
	        } else {
	            return false;
	        }
	    }
	  
	}

	
	class Knight extends Piece {
	  public Knight(boolean isWhite, int x, int y, boolean isEmpty) {
	      super(isWhite, x, y, isEmpty);
	  }
//	  public int points = 3;
//	  public String symbol = isWhite() ? "\u265e" : "\u2658";
//	  protected String symbol = "\u265e";
//	  public String getSymbol() {
//		  return symbol;
//	  }
	  @Override
	  public boolean isValidMove(int newX, int newY, Chessboard chessboard) {
	      int deltaX = Math.abs(newX - getX());
	      int deltaY = Math.abs(newY - getY());
	      
	      if ((deltaX == 2 && deltaY == 1) || (deltaX == 1 && deltaY == 2)) {
	          return true;
	      } else {
	          return false;
	      }
	  }
	}

	
	class Bishop extends Piece {
	  public Bishop(boolean isWhite, int x, int y, boolean isEmpty) {
	      super(isWhite, x, y, isEmpty);
	  }
//	  public int points = 3;
//	  protected String symbol = isWhite() ? "\u265d" : "\u2657";

	  @Override
	  public boolean isValidMove(int newX, int newY, Chessboard chessboard) {
	      int deltaX = Math.abs(newX - getX());
	      int deltaY = Math.abs(newY - getY());

	     
	      if (deltaX == deltaY) {
	          int currentX = getX();
	          int currentY = getY();

	          int stepX = (newX > currentX) ? 1 : -1;  
	          int stepY = (newY > currentY) ? 1 : -1;

	          currentX += stepX;
	          currentY += stepY;

	        
	          while (currentX != newX && currentY != newY) {
	              if (chessboard.getPieceAt(currentX, currentY) != null) {
	            	  
//	            	  if()
	                 
	                  return false;
	              }

	              currentX += stepX;
	              currentY += stepY;
	          }

	          return true;
	      } else {
	          return false;
	      }
	  }
	  
	}

	
	class Queen extends Piece {
	  public Queen(boolean isWhite, int x, int y, boolean isEmpty) {
	      super(isWhite, x, y, isEmpty);
	  }
//	  public int points = 9;
//	  public String symbol = isWhite() ? "\u265b" : "\u2655";
	  @Override
	  public boolean isValidMove(int newX, int newY, Chessboard chessboard) {
	      int deltaX = Math.abs(newX - getX());
	      int deltaY = Math.abs(newY - getY());

	      if (deltaX == 0 || deltaY == 0 || deltaX == deltaY) {
	          int currentX = getX();
	          int currentY = getY();

	          if (deltaX == 0 || deltaY == 0) {
	              int stepX = (newX > currentX) ? 1 : ((newX < currentX) ? -1 : 0);
	              int stepY = (newY > currentY) ? 1 : ((newY < currentY) ? -1 : 0);

	              currentX += stepX;
	              currentY += stepY;

	              while (currentX != newX || currentY != newY) {
	                  if (chessboard.getPieceAt(currentX, currentY) != null) {
	                      return false;
	                  }

	                  currentX += stepX;
	                  currentY += stepY;
	              }
	          }
	          else {
	              int stepX = (newX > currentX) ? 1 : -1;
	              int stepY = (newY > currentY) ? 1 : -1;

	              currentX += stepX;
	              currentY += stepY;

	              while (currentX != newX && currentY != newY) {
	                  if (chessboard.getPieceAt(currentX, currentY) != null) {
	                      return false;
	                  }

	                  currentX += stepX;
	                  currentY += stepY;
	              }
	          }

	          return true;
	      } else {
	          return false;
	      }
	  }


	}

	
	class King extends Piece {
	  public King(boolean isWhite, int x, int y, boolean isEmpty) {
	      super(isWhite, x, y, isEmpty);
	  }
//	  public int points = 1000;
//	  public String symbol = this.isWhite() ? "\u265a" : "\u2654";
	  @Override
	    public boolean isValidMove(int newX, int newY, Chessboard chessboard) {
	        int currentX = getX();
	        int currentY = getY();
	        int deltaX = Math.abs(newX - currentX);
	        int deltaY = Math.abs(newY - currentY);
	        
	        if(this.isWhite&&currentX==0&&currentY==4&&(chessboard.getPieceAt(0, 7) instanceof Rook)&&(chessboard.getPieceAt(0, 5)==null)&&(chessboard.getPieceAt(0, 6)==null)) {
	        	
	        	return true;
	        }
	       
	        return (deltaX <= 1 && deltaY <= 1);
	    }
	  
	}

