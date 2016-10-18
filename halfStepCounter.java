public class halfStepCounter{ //Counts halfsteps between notes within one octave
	public static String[] noteDictionary = {"AG", "A", "AB", "B", "C", "CD", "D", "DE", "E", "F", "FG", "G", "AG", "A", "AB", "B", "C", "CD", "D", "DE", "E", "F", "FG", "G"};

	//Notation is # for sharps and "f" for flats (Note-Accidental) (e.g. A#)
	//Does not cover double flats or sharps
	public static int countHalfStep(String first, String second){
		return(Math.abs(findInDict(convertNotation(first)) - findInDict(convertNotation(second))));
	}

	//Helper Functions 
	//Returns position of note in the library
	public static int findInDict(String note){
		int pos = -1;
		String altNote = note.substring(1,note.length()) + note.substring(0,1);

		for(int x = 0; x < noteDictionary.length - 1; x++){
			if(note.equals(noteDictionary[x])
				|| altNote.equals(noteDictionary[x])){
				pos = x;
				x = noteDictionary.length;
			}
		}
		return pos;
	}

	//Converting flats and sharps into understandable langauge
	public static String convertNotation(String note){
		if(note.length() != 1){
			String temp = note.substring(0,1);
			if(note.substring(1, note.length()).equals("#")){
				note = noteDictionary[findInDict(temp) + 1];
			}
			else if(note.substring(1, note.length()).equals("f")){
				note = noteDictionary[findInDict(temp) - 1];
			}
		}
		return note;
	}


	public static void main(String[] args){
		/*String note1 = "E";
		String note2 = "A";
		String note3 = "AG";
		String note4 = "GA"; //Testing altNote
		System.out.println(findInDict(note1));
		System.out.println(findInDict(note2));
		System.out.println(findInDict(note3));
		System.out.println(findInDict(note4));*/

		/*String note5 = "E#";
		String note6 = "Af";
		System.out.println(convertNotation(note5));
		System.out.println(convertNotation(note6));*/

		System.out.println(countHalfStep("C", "D"));
		System.out.println(countHalfStep("Af", "Df"));
		System.out.println(countHalfStep("A", "A#"));
		System.out.println(countHalfStep("A", "A"));
		System.out.println(countHalfStep("G", "D"));
	}

}