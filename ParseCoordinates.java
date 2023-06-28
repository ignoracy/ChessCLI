package szaszki2;

public class ParseCoordinates {
	
	public static int Ycoordinate(String enteredPosition) {
		int pieceY;
		switch(enteredPosition.charAt(0)) {
		
	    case 'a':
	  	   pieceY = 0;
	  	   break;
	    case 'b':
	  	   pieceY = 1;
	  	   break;
	    case 'c':
	  	   pieceY = 2;
	  	   break;
	    case 'd':
	  	   pieceY = 3;
	  	   break;
	    case 'e':
	  	   pieceY = 4;
	  	   break;
	    case 'f':
	  	   pieceY = 5;
	  	   break;
	    case 'g':
	  	   pieceY = 6;
	  	   break;
	    case 'h':
	  	   pieceY = 7;
	  	   break;
	    default:
	  	  pieceY = 10;
	    }
		return pieceY;
	}
	
	public static int Xcoordinate(String enteredPosition) {
		int pieceX;
		pieceX = Character.getNumericValue(enteredPosition.charAt(1))-1;
		return pieceX;
	}
}
