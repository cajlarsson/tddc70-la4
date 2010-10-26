class LongestChain {
   private Queue q; // kö som används i breddenförstsökningen

   private int goalWord; // slutord i breddenförstsökningen

   int wordLength;

   final char[] alphabet = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
			     'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
			     'x', 'y', 'z', 'å', 'ä', 'ö', 'é' };

   int alphabetLength = alphabet.length;

   public LongestChain(int wordLength) {
      this.wordLength = wordLength;
      q = new Queue();
   }

   // IsGoal kollar om w är slutordet.
   private boolean IsGoal(int w) {
      return w == goalWord;
   }

   // MakeSons skapar alla ord som skiljer på en bokstav från x.
   // Om det är första gången i sökningen som ordet skapas så läggs det
   // in i kön q.
   public WordRec MakeSons(int tword, WordRec x)  
   {
      int word = tword;
      for ( int i = 0; i < 4 ; i++)
	 for ( int j = 0 ; j < 30; j++)
	 {
	    int temp = word & (0xFFFFFF ^ (0x1F << (i * 5)));
    	    temp |= j << (i * 5);
    	    if ( WordList.Contains(temp))
	    {
	       WordList.MarkAsUsedIfUnused(temp);
	       x = new WordRec(temp, x);
	    }
	 }
      return x;
   }

   public WordRec MakeSons(int tword)
   {
      return MakeSons(tword, null);
   }

   public WordRec MakeAllSons (WordRec words)
   {
      WordRec tmp = words;
      WordRec out = null;
      while ( tmp != null)
      {
 	 out = MakeSons(tmp.word, out);
	 tmp = tmp.father;
      }
      return out;
   }
   
   // BreadthFirst utför en breddenförstsökning från startWord för att
   // hitta kortaste vägen till endWord. Den kortaste vägen returneras
   // som en kedja av ordposter (WordRec).
   // Om det inte finns något sätt att komma till endWord returneras null.
   public WordRec BreadthFirst(int startWord, int  endWord)
   {
      // rec = new WordRec(endWord);
      // rec = MakeSons(rec);
      return new WordRec(FourWord.toInt("alsa"),null);
   }

   public void BuildDistanceTable(WordRec levelWords, int startDepth)
   {
      if (levelWords == null)
	 return;

      WordRec level = new WordRec(levelWords.word, levelWords.father);
      while (level != null)
      {
	 WordList.setDistance(level.word, startDepth);
	 level = level.father;
      }
      BuildDistanceTable(MakeAllSons(levelWords), startDepth +1);
   }
   
   // CheckAllStartWords hittar den längsta kortaste vägen från något ord
   // till endWord. Den längsta vägen skrivs ut.
   public void CheckAllStartWords(int endWord) {
      int maxChainLength = -1;
      WordRec maxChainRec = null;
      for (int i = 0; i < WordList.size; i++) {
	 WordRec x = BreadthFirst(WordList.WordAt(i), endWord);
	 if (x != null) {
	    int len = x.ChainLength();
	    if (len > maxChainLength) {
	       maxChainLength = len;
	       maxChainRec = x;
	       // x.PrintChain(); // skriv ut den hittills längsta kedjan
	    }
	 }
      }
      System.out.println("Längsta ordkedjan har " + maxChainLength + " ord:");
      maxChainRec.PrintChain();
   }
}
