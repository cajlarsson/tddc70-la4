abstract class FourWord
{
   public static  int toInt(String in)
   {
      int value = 0;
      if (in.length() == 4)
      {
	 byte[] temp = in.getBytes();
	 
	 for(int i= 0; i < 4 ; i++)
	 {
	    value |= temp[i] << i;
	 }
	 return value;
      }
      return 0xFFFFFFFF;
   }
   
   public static boolean neighbour(int v1, int v2)
   {
       return ((v1 & 0x1FFFE0 ) == (v2 & 0x1FFFE0))
	 || ((v1 & 0x1FFC1F) == (v2 & 0x1FFC1F ))
	 || ((v1 & 0x1F83FF) == (v2 & 0x1F83FF))
	 || ((v1 & 0xFFFF) == (v2 & 0xFFFF));
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