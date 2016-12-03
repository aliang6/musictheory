public class musicTheoryToolkit{

//Instance Variables=======================
	public static String[] accidentals = {"bb", "b", "n", "#", "x"};
	public static String[] noteDictionary = {"GA", "A", "AB", "B", "C", "CD", "D", "DE", "E", "F", "FG", "G", "GA", "A", "AB", "B", "C", "CD", "D", "DE", "E", "F", "FG", "G"};
	public static String[] notes = {"A", "B", "C", "D", "E", "F", "G", "A", "B", "C", "D", "E", "F", "G"};
	public static String[] perfectInterval = {"d", "P", "A"};
	public static String[] majorInterval = {"d", "m", "M", "A"};
	public static int[] intervalHalfSteps = {0, 2, 4, 5, 7, 9, 11, 12};
//=========================================



//Helper Functions=========================
	public static String intervalFinder(String note1, String note2){ //Figures out the interval between two notes.
		int intervalNumber = 0;
		int halfSteps = countHalfStep(note1, note2);
		System.out.println(halfSteps);
		String note1Letter = note1.substring(0,1);
		String note2Letter = note2.substring(0,1);
		intervalNumber = intervalNumber(note1Letter, note2Letter);
		System.out.println(intervalNumber);
		int defaultHalfSteps = intervalHalfSteps[intervalNumber];
		System.out.println(defaultHalfSteps);
		if (intervalNumber == 1 ||
			intervalNumber == 4 ||
			intervalNumber == 5 ||
			intervalNumber == 8){
			int position = halfSteps - defaultHalfSteps + 3;
			System.out.println(position);
			String intervalType = perfectInterval[position];
			System.out.println(intervalType);
			String interval = intervalType + Integer.toString(intervalNumber);
			return interval;
		}
		else {
			int position = halfSteps - defaultHalfSteps + 4;
			String intervalType = majorInterval[position];
			String interval = intervalType + Integer.toString(intervalNumber);
			return interval;
		}
		
	}

	public static int intervalNumber(String note1, String note2){ //Figures out the number in the interval. Ex: B and C equals 2. Does not accept accidentals.
			int firstNote = findNote(note1);
			int secondNote = 0;
			for(int x = firstNote; x < notes.length - 1; x++){
				if(note2.equals(notes[x])) {
					secondNote = x;
					x = notes.length;
				}
			}
			return secondNote - firstNote + 1;
	}


	public static int findNote(String note){
		int pos = 0;

		for(int x = 0; x < notes.length - 1; x++){
			if(note.equals(notes[x])) {
				pos = x;
				x = notes.length;
			}
		}
		return pos;
	}


	public static int findInDict(String note){
		int pos = -1;
		String altNote = note.substring(1, note.length()) + note.substring(0,1);

		for(int x = 0; x < noteDictionary.length - 1; x++){
			if(note.equals(noteDictionary[x])
				|| altNote.equals(noteDictionary[x])){
				pos = x;
				x = noteDictionary.length;
			}
		}
		return pos;
	}

	//Notation is # for sharps and "f" for flats (Note-Accidental) (e.g. A#)
	//Does not cover double flats or sharps
	public static int countHalfStep(String first, String second){
		int halfSteps = 0;
		for(int x = findInDict(convertNotation(first)); x < findInDict(convertNotation(second)); x++){
				x += 1;
		}
		return (Math.abs(findInDict(convertNotation(first)) - findInDict(convertNotation(second))));
	}

	//Converting flats and sharps into computer-understandable langauge
	public static String convertNotation(String note){
		if(note.length() != 1){
			String temp = note.substring(0,1);
			if(note.substring(1, 2).equals("#")){
				note = noteDictionary[findInDict(temp) + 1];
			}
			else if(note.substring(1, 2).equals("b")){
				note = noteDictionary[findInDict(temp) - 1];
			}
		}
		return note;
	}
//==========================================






public static void main(String[] args){
	/*System.out.println(findInDict("A")); //Should be 0;
	System.out.println(findInDict("B")); //Should be 1;
	System.out.println(findInDict("C")); //Should be 2;

	System.out.println(intervalNumber("A", "B")); //Should be 2;
	System.out.println(intervalNumber("B", "A")); //Should be 2;
	System.out.println(intervalNumber("A", "G")); //Should be 7;

	String note1 = "E"; //Should be 8
	String note2 = "A"; //Should be 1
	String note3 = "AG"; //Should be 0
	String note4 = "GA"; //Testing altNote. Should be 0.
	System.out.println(findInDict(note1)); 
	System.out.println(findInDict(note2));
	System.out.println(findInDict(note3));
	System.out.println(findInDict(note4));

	String note5 = "E#";
	String note6 = "Ab";
	System.out.println(convertNotation(note5)); //Should be F. Might need to add EF.
	System.out.println(convertNotation(note6)); //Should be GA 

	System.out.println(countHalfStep("C", "D")); //Should be 2
	System.out.println(countHalfStep("Ab", "Db")); //Should be 5
	System.out.println(countHalfStep("A", "A#")); //Should be 1
	System.out.println(countHalfStep("A", "A")); //Should be 0
	System.out.println(countHalfStep("G", "D")); //Should be 5 */

	System.out.println(intervalFinder("A", "A")); //Should be P1;
	System.out.println(intervalFinder("A", "B")); //Should be M2;
	System.out.println(intervalFinder("C", "A")); //Should be M6;
	System.out.println(intervalFinder("C", "B")); //Should be M6;

}


}