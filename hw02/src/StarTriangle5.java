public class StarTriangle5 {
   /**
     * Prints a right-aligned triangle of stars ('*') with 5 lines.
     * The first row contains 1 star, the second 2 stars, and so on. 
     */
   public static void starTriangle5() {
      // TODO: Fill in this function
      for (int i = 1; i<7; i++) {
         for (int j = 5-i; j>0; j--) {
            System.out.print(" ");
         }
         for (int k = 0; k<i; k++) {
            System.out.print("*");
         }
         System.out.println("");
      }
   }
   
   public static void main(String[] args) {
      starTriangle5();
   }
}