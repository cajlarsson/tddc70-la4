
class FourWord
{
   public static void main (String args[])
   {
      FourWord hej = new FourWord("abcd");
      System.out.print(hej.toString());
   }

   final char[] alphabet = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
			     'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
			     'u', 'v', 'w', 'x', 'y', 'z', 'å', 'ä', 'ö', 'é' };

   final int lookup( char c)
   {
      for (int i = 0; i < 30; i++)
      {
	 if ( c== alphabet[i])
	    return i;
      }
      return -1;
   }

   private int value;

   
   public FourWord(String in)
   {
      
      if (in.length() == 4)
      {
	 char[] temp = in.toCharArray();

	 for(int i= 1; i < 4 ; i++)
	 {
	    value |= ((int) lookup((temp[i])) << (i *5));
	    	    
	 }
      }
   }
   
   public boolean neighbour(FourWord comp)
   {
      return ((comp.value & 0xFFFFFF00) == (value & 0xFFFFFF00))
	 || ((comp.value & 0xFFFF00FF) == (value & 0xFFFF00FF))
	 || ((comp.value & 0xFF00FFFF) == (value & 0xFF00FFFF))
	 || ((comp.value & 0x00FFFFFF) == (value & 0x00FFFFFF));
   }

   public boolean equal(FourWord comp)
   {
      return comp.value == value;
   }
 
   public String toString()
   {
      char[] tmp = new char[4];
      for( int i = 0; i < 4; i++)
      {
	 tmp[i] = alphabet[(value >> (i * 5)& 0x1F)] ;
      }

      return new String(tmp);
   }

   public int toInt()
   {
      return value;
   }
}