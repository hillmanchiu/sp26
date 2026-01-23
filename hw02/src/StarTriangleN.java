public class StarTriangleN {
   /**
     * Prints a right-aligned triangle of stars ('*') with N lines.
     * The first row contains 1 star, the second 2 stars, and so on. 
     */
   public static void starTriangle(int N) {
      // TODO: Fill in this function
      for (int i = 1; i<N; i++) {
         for (int j = N-i; j>0; j--) {
            System.out.print(" ");
         }
         for (int k = 0; k<i; k++) {
            System.out.print("*");
         }
         System.out.println("");
      }
   }
   
   public static void main(String[] args) {
      starTriangle(7);
   }
}