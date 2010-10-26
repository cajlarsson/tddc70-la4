import java.util.Vector;
import java.io.*;

// Klassen WordList innehåller en ordlista och en datastruktur som håller
// reda på använda ord.

class WordList {
   static private Vector<String> list; // ordlista

   static private Vector<String> used; // databas med använda ord
	
   static private int[] resultar = new int[2097151]; //todo fixa
	
   static private boolean[] usedar = new boolean[2097151];

   static int wordLength;

   static int size; // antal ord i ordlistan

   // Read läser in en ordlista från filen fileName. Alla ord ska ha
   // wordLength bokstäver.
   static public void Read(int wordLength_, String fileName)
      throws FileNotFoundException, IOException {
      wordLength = wordLength_;
      EraseUsed();
      BufferedReader f = new BufferedReader(new FileReader(fileName));
         
      char[] buffer = new char[5];
      while (f.read(buffer) != -1)
      { 
	 int value = FourWord.toInt(buffer);
	 usedar[value] = true;
	 }
      f.close();
   }

   // WordAt returnerar ordet med angivet index i ordlistan.
   static public int WordAt(int index) {
      return index;
   }

   // Contains slår upp w i ordlistan och returnerar ordet om det finns med.
   // Annars returneras null.
   static public boolean Contains(int w) {
      return usedar[w];
   }

   // MarkAsUsedIfUnused kollar om w är använt tidigare och returnerar i så
   // fall false. Annars markeras w som använt och true returneras.
   static public void  MarkAsUsedIfUnused(int w) {
      usedar[w] = false;
   }

   // EraseUsed gör så att inga ord anses använda längre.
   static public void EraseUsed() 
   {
      for (int i = 0; i < 2097151; i++)
      {
	 usedar[i] = false;
	 resultar[i] = -1;
      }
   }

   static public void setDistance(int word, int distance)
   {
      resultar[word] = distance;
   }
   
   static public void printCrap()
   {
      int word = 0;
      int dist = 0;
      for (int i = 0 ; i < 2097151 ; i++)
      {
	 if (resultar[i] > dist)
	 {
	    word = i;
	    dist = resultar[i];
	 }
      }
      System.out.println( "ord: " + FourWord.toString(word) + " : " + dist);
   }
}
