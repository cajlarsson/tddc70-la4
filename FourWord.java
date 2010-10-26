abstract class FourWord
{
   // public static void main(String args[])
   // {
   //    String apapapa = "åäö?";
   //    int apa = toInt( apapapa.toCharArray());
   //    System.out.print(toString(apa));
   // }
   
   public final static
   char[] alphabet = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
		       'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
		       's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'å',
		       'ä', 'ö', 'é' };
   
   public static  int lookup(char c)
   {
      int z = 'z' - 'a';
      switch (c)
      {
	 case 'å': return z + 1;
	 case 'ä': return z + 2;
	 case 'ö': return z + 3;
         case 'é': return z + 4;
	 default : return c - 'a';
      }
   }
    
   public static int toInt(char[] in)
   {
      int value = 0;
      value |=  lookup(in[0]);
      value |=  lookup(in[1]) << 5 ;
      value |=  lookup(in[2]) << 10;
      value |=  lookup(in[3]) << 15 ;

      return value;
   }
   
   public static int toInt(String in)
   {
      return toInt(in.toCharArray());
   }

   
   
   
   public static boolean neighbour(int v1, int v2)
   {
       return ((v1 & 0xFFFE0 ) == (v2 & 0xFFFE0))
	 || ((v1 & 0xFFC1F) == (v2 & 0xFFC1F ))
	 || ((v1 & 0xF83FF) == (v2 & 0xF83FF))
	 || ((v1 & 0x7FFF) == (v2 & 0x7FFF));
   }

   public static String toString(int value)
   {
      char[] tmp = new char[4];
      for( int i = 0; i < 4; i++)
      {
	 tmp[i] = alphabet[(value >> (i * 5)& 0x1F)] ;
      }
      return new String(tmp);
   }
}