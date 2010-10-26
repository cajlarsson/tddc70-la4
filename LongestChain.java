import java.util.*;
class LongestChain {
   public Stack<WordRec> levelStack;

   private int goalWord; // slutord i breddenförstsökningen

   int wordLength;

    /*   final char[] alphabet = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
			     'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
			     'x', 'y', 'z', 'å', 'ä', 'ö', 'é' };
    
   int alphabetLength = alphabet.length;
    */
   public LongestChain(int wordLength) {
      this.wordLength = wordLength;
      levelStack = new Stack<WordRec>();
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

   public void BuildDistanceTable(WordRec levelWords, int startDepth)
   {
      if (levelWords == null)
	 return;

      levelStack.push(levelWords);

      WordRec level = new WordRec(levelWords.word, levelWords.father);
      
      while (level != null)
      {
	 WordList.setDistance(level.word, startDepth);
	 level = level.father;
      }
      WordRec tmp = MakeAllSons(levelWords);
      
      BuildDistanceTable(tmp, startDepth + 1);
   }

   public WordRec Build()
   {
   
      int lastword = levelStack.pop().word;
      WordRec out = new WordRec(lastword,null);
      while (!levelStack.empty())
      {
	 WordRec tmp = levelStack.pop();
	 // tmp.PrintChain();
	 // System.out.println();
	 // levelStack.peek().PrintChain();
	 // System.out.println();

	 while (! FourWord.neighbour(tmp.word, lastword))
	 {
	    /* if (tmp.father == null)
	       break;*/
	    tmp = tmp.father;
	 }

	 lastword = tmp.word;
	 out = new WordRec(tmp.word, out);
	 
      }
      return out;
   }
}
