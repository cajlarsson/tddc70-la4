class FourWord
{

   private int value = 0;

   public FourWord(String in)
   {
      
      if (in.length() == 4)
      {
	 byte[] temp = in.getBytes();

	 for(int i= 0; i < 4 ; i++)
	 {
	    value |= temp[i] << i;
	    	    
	 }
      }
   }
   
   public boolean neighbour(FourWord comp)
   {
      return ((comp.value & 0xFF) == (value & 0xFF))
	 || ((comp.value & 0xFF00) == (value & 0xFF00))
	 || ((comp.value & 0xFF0000) == (value & 0xFF0000))
	 || ((comp.value & 0xFF000000) == (value & 0xFF000000));
   }

   public boolean equal(FourWord comp)
   {
      return comp.value == value;
   }

   public String toString()
   {
      byte[] tmp = new byte[4];
      for (int i = 0; i < 4 ; i++)
      {
	 tmp[3-i] = (byte)(value >> (3-i)) ;
      }

      return new String(tmp);
   }

   public int toInt()
   {
      return value;
   }
}