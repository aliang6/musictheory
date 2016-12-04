public class musicTheoryToolkit{

//Instance Variables=======================
	public static String[] accidentals = {"bb", "b", "n", "#", "x"};
	public static String[] noteDictionary = {"GA", "A", "AB", "B", "C", "CD", "D", "DE", "E", "F", "FG", "G", "GA", "A", "AB", "B", "C", "CD", "D", "DE", "E", "F", "FG", "G"};
	public static String[] notes = {"A", "B", "C", "D", "E", "F", "G", "A", "B", "C", "D", "E", "F", "G"};
	public static String[] perfectInterval = {"d", "P", "A"};
	public static String[] majorInterval = {"d", "m", "M", "A"};
	public static int[] intervalHalfSteps = {0, 2, 4, 5, 7, 9, 11, 12};
//=========================================

//Interval Finder==========================
public static String intervalFinder(String note1, String note2){ //Figures out the interval between two notes.
		int intervalNumber = 0;
		int halfSteps = countHalfStep(note1, note2);
		//System.out.println(halfSteps);
		String note1Letter = note1.substring(0,1);
		String note2Letter = note2.substring(0,1);
		intervalNumber = intervalNumber(note1Letter, note2Letter);
		//System.out.println(intervalNumber);
		int defaultHalfSteps = intervalHalfSteps[intervalNumber - 1];
		//System.out.println(defaultHalfSteps);
		if (intervalNumber == 1 ||
			intervalNumber == 4 ||
			intervalNumber == 5 ||
			intervalNumber == 8 ) {
			int position = halfSteps - defaultHalfSteps + 1;
			//System.out.println(position);
			String intervalType = perfectInterval[position];
			//System.out.println(intervalType);
			String interval = intervalType + Integer.toString(intervalNumber);
			return interval;
		}
		else {
			int position = halfSteps - defaultHalfSteps + 2;
			//System.out.println(position);
			String intervalType = majorInterval[position];
			//System.out.println(intervalType);
			String interval = intervalType + Integer.toString(intervalNumber);
			return interval;
		}
		
	}
//=========================================


//Interval Progression=====================
	public static String nextNote(String note, String interval){ //Gives the next note given the starting note and the interval
		String noteLetter = note.substring(0, 1);
		int accidentalValue = 0;
		if(note.length() > 2){
			if(note.substring(1, 3).equals("bb")) {
				accidentalValue = -2;
			}
		}
		else if(note.length() > 1){
			if(note.substring(1, 2).equals("b")) {
				accidentalValue = -1;
			}
			else if(note.substring(1,2).equals("#")) {
				accidentalValue = 1;
			}
			else if(note.substring(1,2).equals("x")) {
				accidentalValue = 1;
			}
		}
		int intervalNumber = Integer.parseInt(interval.substring(1,2));
		String secondNote = notes[findNote(noteLetter) + intervalNumber - 1];
		String intervalType = interval.substring(0,1);
		//Interpretting the number of halfsteps based off the type of interval========
		int defaultHalfSteps = 0;
		if (intervalType.equals("M") ||
			intervalType.equals("P")) {
			defaultHalfSteps = intervalHalfSteps[intervalNumber - 1];
		}
		else if (intervalType.equals("A")){
			defaultHalfSteps = intervalHalfSteps[intervalNumber - 1] + 1;
		}
		else if (intervalType.equals("m")){
			defaultHalfSteps = intervalHalfSteps[intervalNumber - 1] - 1;
		}
		else if (intervalNumber == 1 ||
			intervalNumber == 4 ||
			intervalNumber == 5 ||
			intervalNumber == 8){
			if (intervalType.equals("d")){
				defaultHalfSteps = intervalHalfSteps[intervalNumber - 1] - 1;
			}
		}
		else {
			defaultHalfSteps = intervalHalfSteps[intervalNumber - 1] - 2;
		}

		//=============================================================================
		//System.out.println(note + secondNote);
		int halfSteps = countHalfStep(noteLetter, secondNote);
		int quality = defaultHalfSteps - halfSteps + 2 + accidentalValue;
		//System.out.println(halfSteps);
		//System.out.println(defaultHalfSteps);
		//System.out.println(quality);
		String accidental = accidentals[quality];
		secondNote += accidental;
		if(secondNote.substring(1, 2).equals("n")){
			secondNote = secondNote.substring(0,1);
		}
		return secondNote;
	}

//=========================================


//Major Scale Construction=================
	public static String[] majorScale(String tonic){
		String[] majorScale = new String[8];
		majorScale[0] = tonic;
		//System.out.println(majorScale[0]);
		majorScale[1] = nextNote(tonic, "M2");
		//System.out.println(majorScale[1]);
		majorScale[2] = nextNote(tonic, "M3");
		//System.out.println(majorScale[2]);
		majorScale[3] = nextNote(tonic, "P4");
		//System.out.println(majorScale[3]);
		majorScale[4] = nextNote(tonic, "P5");
		//System.out.println(majorScale[4]);
		majorScale[5] = nextNote(tonic, "M6");
		//System.out.println(majorScale[5]);
		majorScale[6] = nextNote(tonic, "M7");
		//System.out.println(majorScale[6]);
		majorScale[7] = tonic;
		return majorScale;
	}

//=========================================

//Minor Scale Construction=================
	public static String[] naturalMinorScale(String tonic){
		String[] majorScale = new String[8];
		majorScale[0] = tonic;
		//System.out.println(majorScale[0]);
		majorScale[1] = nextNote(tonic, "M2");
		//System.out.println(majorScale[1]);
		majorScale[2] = nextNote(tonic, "M3");
		//System.out.println(majorScale[2]);
		majorScale[3] = nextNote(tonic, "P4");
		//System.out.println(majorScale[3]);
		majorScale[4] = nextNote(tonic, "P5");
		//System.out.println(majorScale[4]);
		majorScale[5] = nextNote(tonic, "M6");
		//System.out.println(majorScale[5]);
		majorScale[6] = nextNote(tonic, "M7");
		//System.out.println(majorScale[6]);
		majorScale[7] = tonic;
		return majorScale;
	}

	public static String[] harmonicMinorScale(String tonic){ //Sharping the seventh note in the natural minor scale
		String[] minorScale = new String[8];
		minorScale[0] = tonic;
		//System.out.println(minorScale[0]);
		minorScale[1] = nextNote(tonic, "M2");
		//System.out.println(minorScale[1]);
		minorScale[2] = nextNote(tonic, "m3");
		//System.out.println(minorScale[2]);
		minorScale[3] = nextNote(tonic, "P4");
		//System.out.println(minorScale[3]);
		minorScale[4] = nextNote(tonic, "P5");
		//System.out.println(minorScale[4]);
		minorScale[5] = nextNote(tonic, "m6");
		//System.out.println(minorScale[5]);
		minorScale[6] = nextNote(tonic, "M7");
		//System.out.println(minorScale[6]);
		minorScale[7] = tonic;
		return minorScale;
	}

	public static String[] melodicMinorScale(String tonic){ //Sharping the sixth and seventh note in the natural minor scale
		String[] minorScale = new String[8];
		minorScale[0] = tonic;
		//System.out.println(minorScale[0]);
		minorScale[1] = nextNote(tonic, "M2");
		//System.out.println(minorScale[1]);
		minorScale[2] = nextNote(tonic, "m3");
		//System.out.println(minorScale[2]);
		minorScale[3] = nextNote(tonic, "P4");
		//System.out.println(minorScale[3]);
		minorScale[4] = nextNote(tonic, "P5");
		//System.out.println(minorScale[4]);
		minorScale[5] = nextNote(tonic, "M6");
		//System.out.println(minorScale[5]);
		minorScale[6] = nextNote(tonic, "M7");
		//System.out.println(minorScale[6]);
		minorScale[7] = tonic;
		return minorScale;
	}

//=========================================



//Helper Functions=========================

	public static int intervalNumber(String note1, String note2){ //Figures out the number in the interval. Ex: B and C equals 2. Does not accept accidentals.
			int firstNote = findNote(note1);
			int secondNote = 0;
			for(int x = firstNote; x < notes.length - 1; x++){
				if(note2.equals(notes[x])) {
					secondNote = x;
					x = notes.length;
				}
			}
			return (secondNote - firstNote + 1);
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

	public static int findInDict(String noteTwo, String note){
		int pos = -1;
		String altNote = noteTwo.substring(1, noteTwo.length()) + noteTwo.substring(0,1);

		for(int x = findInDict(convertNotation(note)); x < noteDictionary.length - 1; x++){
			if(noteTwo.equals(noteDictionary[x])
				|| altNote.equals(noteDictionary[x])){
				pos = x;
				x = noteDictionary.length;
			}
		}
		return pos;
	}

	//Notation is # for sharps and "b" for flats (Note-Accidental) (e.g. A#)
	//Does not cover double flats or double sharps
	public static int countHalfStep(String first, String second){
		int halfSteps = 0;
		for(int x = findInDict(convertNotation(first)); x < findInDict(convertNotation(second), convertNotation(first)); x++){
				halfSteps += 1;
		}
		return halfSteps;
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

	public static String displayScale(String[] scale){
		String print = "";
		for(int x = 0; x < scale.length; x++){
			print = print + scale[x] + ", ";
		}
		print = print.substring(0, print.length() - 2);
		return print;
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

	/* System.out.println(intervalFinder("A", "A")); //Should be P1;
	System.out.println(intervalFinder("A", "B")); //Should be M2;
	System.out.println(intervalFinder("C", "A")); //Should be M6;
	System.out.println(intervalFinder("C", "B")); //Should be M7;
	System.out.println(intervalFinder("A#", "B")); //Should be m2;
	System.out.println(intervalFinder("Ab", "B")); //Should be A2;	*/
	//System.out.println(intervalFinder("Ab", "B#")); //Unknown; 4 HalfSteps 

	//Major Scale Tests
	System.out.println(nextNote("C", "M2")); //Should be D
	System.out.println(nextNote("C", "P4")); //Should be F
	System.out.println(nextNote("Cb", "P4")); //Should be F
	System.out.println(nextNote("C#", "P4")); //Should be F

	System.out.println(displayScale(majorScale("C"))); //No Accidentals

	System.out.println(displayScale(majorScale("G"))); //One Sharp: F
	System.out.println(displayScale(majorScale("D"))); //Two Sharps: F and C
	System.out.println(displayScale(majorScale("A"))); //Three Sharps: F, C, and G
	System.out.println(displayScale(majorScale("E"))); //Four Sharps: F, C, G, and D
	System.out.println(displayScale(majorScale("B"))); //Five Sharps: F, C, G, D, and A
	System.out.println(displayScale(majorScale("F#"))); //Six Sharps: F, C, G, D, A, and E

	System.out.println(displayScale(majorScale("C#"))); //All Sharps
	System.out.println(displayScale(majorScale("F"))); //One Flat: B
	System.out.println(displayScale(majorScale("Bb"))); //Two Flats: B and E
	System.out.println(displayScale(majorScale("Eb"))); //Three Flats: B, E, and A
	System.out.println(displayScale(majorScale("Ab"))); //Four Flats: B, E, A, and D
	System.out.println(displayScale(majorScale("Db"))); //Five Flats: B, E, A, D, and G
	System.out.println(displayScale(majorScale("Gb"))); //Six Flats: B, E, A, D, G, and C
	System.out.println(displayScale(majorScale("Cb"))); //All Flats

	//Natural Minor Scale Tests
	System.out.println(displayScale(naturalMinorScale("A"))); //No Accidentals
	System.out.println(displayScale(naturalMinorScale("E"))); //One Sharp: F
	System.out.println(displayScale(naturalMinorScale("B"))); //Two Sharps: F and C
	System.out.println(displayScale(naturalMinorScale("F#"))); //Three Sharps: F, C, and G
	System.out.println(displayScale(naturalMinorScale("C#"))); //Four Sharps: F, C, G, and D
	System.out.println(displayScale(naturalMinorScale("G#"))); //Five Sharps: F, C, G, D, and A
	System.out.println(displayScale(naturalMinorScale("D#"))); //Six Sharps: F, C, G, D, A, and E
	System.out.println(displayScale(naturalMinorScale("C#"))); //All Sharps

	System.out.println(displayScale(naturalMinorScale("D"))); //One Flat: B
	System.out.println(displayScale(naturalMinorScale("G"))); //Two Flats: B and E
	System.out.println(displayScale(naturalMinorScale("C"))); //Three Flats: B, E, and A
	System.out.println(displayScale(naturalMinorScale("F"))); //Four Flats: B, E, A, and D
	System.out.println(displayScale(naturalMinorScale("Bb"))); //Five Flats: B, E, A, D, and G
	System.out.println(displayScale(naturalMinorScale("Eb"))); //Six Flats: B, E, A, D, G, and C
	System.out.println(displayScale(naturalMinorScale("Ab"))); //All Flats

	//Harmonic Minor Scale Tests
	System.out.println(displayScale(harmonicMinorScale("A"))); //No Accidentals
	System.out.println(displayScale(harmonicMinorScale("E"))); //One Sharp: F
	System.out.println(displayScale(harmonicMinorScale("B"))); //Two Sharps: F and C
	System.out.println(displayScale(harmonicMinorScale("F#"))); //Three Sharps: F, C, and G
	System.out.println(displayScale(harmonicMinorScale("C#"))); //Four Sharps: F, C, G, and D
	System.out.println(displayScale(harmonicMinorScale("G#"))); //Five Sharps: F, C, G, D, and A
	System.out.println(displayScale(harmonicMinorScale("D#"))); //Six Sharps: F, C, G, D, A, and E
	System.out.println(displayScale(harmonicMinorScale("C#"))); //All Sharps

	System.out.println(displayScale(harmonicMinorScale("D"))); //One Flat: B
	System.out.println(displayScale(harmonicMinorScale("G"))); //Two Flats: B and E
	System.out.println(displayScale(harmonicMinorScale("C"))); //Three Flats: B, E, and A
	System.out.println(displayScale(harmonicMinorScale("F"))); //Four Flats: B, E, A, and D
	System.out.println(displayScale(harmonicMinorScale("Bb"))); //Five Flats: B, E, A, D, and G
	System.out.println(displayScale(harmonicMinorScale("Eb"))); //Six Flats: B, E, A, D, G, and C
	System.out.println(displayScale(harmonicMinorScale("Ab"))); //All Flats

	//Melodic Minor Scale Tests
	System.out.println(displayScale(melodicMinorScale("A"))); //No Accidentals
	System.out.println(displayScale(melodicMinorScale("E"))); //One Sharp: F
	System.out.println(displayScale(melodicMinorScale("B"))); //Two Sharps: F and C
	System.out.println(displayScale(melodicMinorScale("F#"))); //Three Sharps: F, C, and G
	System.out.println(displayScale(melodicMinorScale("C#"))); //Four Sharps: F, C, G, and D
	System.out.println(displayScale(melodicMinorScale("G#"))); //Five Sharps: F, C, G, D, and A
	System.out.println(displayScale(melodicMinorScale("D#"))); //Six Sharps: F, C, G, D, A, and E
	System.out.println(displayScale(melodicMinorScale("C#"))); //All Sharps

	System.out.println(displayScale(melodicMinorScale("D"))); //One Flat: B
	System.out.println(displayScale(melodicMinorScale("G"))); //Two Flats: B and E
	System.out.println(displayScale(melodicMinorScale("C"))); //Three Flats: B, E, and A
	System.out.println(displayScale(melodicMinorScale("F"))); //Four Flats: B, E, A, and D
	System.out.println(displayScale(melodicMinorScale("Bb"))); //Five Flats: B, E, A, D, and G
	System.out.println(displayScale(melodicMinorScale("Eb"))); //Six Flats: B, E, A, D, G, and C
	System.out.println(displayScale(melodicMinorScale("Ab"))); //All Flats
}


}