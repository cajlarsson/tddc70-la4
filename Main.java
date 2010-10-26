// Ursprungskod för denna laboration är författad av Viggo Kann, KTH.

import java.io.IOException;

class Main {
   final static private int WordLength = 4;

   final static private String WordListFileName = "word4";

   public static void main(String args[]) throws IOException {
      if (args.length < 1 || args.length > 1) {
	 System.out.println("syntax: java Main slutord");
	 return;
      }
      WordList.Read(WordLength, WordListFileName);
      //	LongestChain lc = new LongestChain(WordLength);
      //lc.CheckAllStartWords(FourWord.toInt(args[0].toCharArray()));
      WordRec apan = new WordRec(FourWord.toInt(args[0]),null);
      WordList.MarkAsUsedIfUnused(FourWord.toInt(args[0]));
      LongestChain lc = new LongestChain(4);
      lc.BuildDistanceTable(apan,1);
      
      WordList.printCrap();
   }
}
