public class halfStepCounter{ //Counts halfsteps between notes within one octave
	public static String[] accidentals = {"bb", "b", "n", "#", "x"};
	public static String[] noteDictionary = {"GA", "A", "AB", "B", "C", "CD", "D", "DE", "E", "F", "FG", "G", "GA", "A", "AB", "B", "C", "CD", "D", "DE", "E", "F", "FG", "G"};
	public static String[] sharpScales = {"G", "D", "A", "E", "B", "F#", "C#"};
	public static String[] flatScales = {"C", "F", "Bb", "Eb", "Ab", "Db", "Gb", "Cb"};

//================Creating Scales=============================================

	public static String[] majorScale(String tonic){
		String[] scale = new String[8];
		String _tonic = convertNotation(tonic);
		scale[0] = tonic;
		scale[7] = tonic;
		_tonic = right(_tonic, 2);
		scale[1] = _tonic;
		_tonic = right(_tonic, 2);
		scale[2] = _tonic;
		_tonic = right(_tonic, 1);
		scale[3] = _tonic; //Need to convert to musical notation
		_tonic = right(_tonic, 2);
		scale[4] = _tonic;
		_tonic = right(_tonic, 2);
		scale[5] = _tonic;
		_tonic = right(_tonic, 2);
		scale[6] = _tonic;
		reconvertNotation(scale);
		return scale;
	}

	public static String[] minorScale(String tonic){
		String[] scale = new String[8];
		scale[0] = tonic;
		scale[7] = tonic;
		tonic = right(tonic, 2);
		scale[1] = tonic;
		tonic = right(tonic, 1);
		scale[2] = tonic;
		tonic = right(tonic, 2);
		scale[3] = tonic; //Need to convert to musical notation
		tonic = right(tonic, 2);
		scale[4] = tonic;
		tonic = right(tonic, 1);
		scale[5] = tonic;
		tonic = right(tonic, 2);
		scale[6] = tonic;
		return scale;
	}

	public static void reconvertNotation(String[] scale){
		String accidental = scaleAccidental(scale[0]);
		for(int x = 1; x < scale.length - 1; x++){
			if(scale[x].length() > 1){
				if(scale[x - 1].substring(0,1).equals(scale[x].substring(0,1))){
					scale[x] = scale[x].substring(1,2) + accidental;
				}
				else{
					scale[x] = scale[x].substring(0,1) + accidental;
				}
			}
		}
	}


//===================================================================================

//================Counting Half Steps===============================================

	//Notation is # for sharps and "f" for flats (Note-Accidental) (e.g. A#)
	//Does not cover double flats or sharps
	public static int countHalfStep(String first, String second){
		return (Math.abs(findInDict(convertNotation(first)) - findInDict(convertNotation(second))));
	}

	public static String right(String note, int steps){
		String diatonicNote = "";
		diatonicNote = noteDictionary[findInDict(convertNotation(note)) + steps];
		/*if(diatonicNote.substring(0,1).equals(note)){
			diatonicNote = diatonicNote.substring(1, diatonicNote.length()) + "b";
		}
		else{
			diatonicNote = diatonicNote.substring(0, 1) + "#";
		}*/
		return diatonicNote;
	}

	public static String left(String note, int steps){
		String diatonicNote = "";
		diatonicNote = noteDictionary[findInDictR(convertNotation(note)) - steps];
		/*if(diatonicNote.length() != 1){
			diatonicNote = diatonicNote.substring(0,1) + "b";
		}*/
		return diatonicNote;
	}
//===================================================================================


//==========================Helper Functions=========================================

	//Returns position of note in the library
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

	//Variation to findInDict. Starts from the end of the list
	public static int findInDictR(String note){
		int pos = -1;
		String altNote = note.substring(1, note.length()) + note.substring(0,1);

		for(int x = noteDictionary.length - 1; x > 0; x--){
			if(note.equals(noteDictionary[x])
				|| altNote.equals(noteDictionary[x])){
				pos = x;
				x = 0;
			}
		}
		return pos;
	}

	//Converting flats and sharps into understandable langauge
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

	//Figures out whether a scale is predominently sharp or flat
	public static String scaleAccidental(String tonic){
		boolean sharp = false;
		for(int x = 0; x < sharpScales.length; x++){
			if(tonic.equals(sharpScales[x])){
				sharp = true;
			}
		}
		if(sharp = true){
			return "#";
		}
		else{
			return "b";
		}
	}

	//Displays the scale
	public static String displayScale(String[] scale){
		String print = "";
		for(int x = 0; x < scale.length; x++){
			print = print + scale[x] + ", ";
		}
		print = print.substring(0, print.length() - 2);
		return print;
	}
//===================================================================================

	public static void main(String[] args){
		String note1 = "E";
		String note2 = "A";
		String note3 = "AG";
		String note4 = "GA"; //Testing altNote
		System.out.println(findInDict(note1));
		System.out.println(findInDict(note2));
		System.out.println(findInDict(note3));
		System.out.println(findInDict(note4));

		String note5 = "E#";
		String note6 = "Ab";
		System.out.println(convertNotation(note5));
		System.out.println(convertNotation(note6));

		System.out.println(countHalfStep("C", "D"));
		System.out.println(countHalfStep("Ab", "Db"));
		System.out.println(countHalfStep("A", "A#"));
		System.out.println(countHalfStep("A", "A"));
		System.out.println(countHalfStep("G", "D"));

		System.out.println(displayScale(majorScale("C")));
		System.out.println(displayScale(majorScale("D")));
		System.out.println(displayScale(majorScale("C#")));		
		System.out.println(displayScale(majorScale("Cb")));
		System.out.println(displayScale(minorScale("A")));
		System.out.println(displayScale(minorScale("C")));
	}

}