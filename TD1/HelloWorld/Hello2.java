public class Hello2 {

   public static void main(String[] args)
   {

      if (args.length == 0)
          System.out.println("Bonjour inconnu(e)");
      else
        for (int i = 0; i < args.length; i++)
           System.out.println("Bonjour " +  args[i]);
   }
}
