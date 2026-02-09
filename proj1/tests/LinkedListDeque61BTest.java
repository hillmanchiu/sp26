import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

/** Performs some basic linked list tests. */
public class LinkedListDeque61BTest {

     @Test
     /** In this test, we have three different assert statements that verify that addFirst works correctly. */
     public void addFirstTestBasic() {
         Deque61B<String> lld1 = new LinkedListDeque61B<>();

         lld1.addFirst("back"); // after this call we expect: ["back"]
         assertThat(lld1.toList()).containsExactly("back").inOrder();

         lld1.addFirst("middle"); // after this call we expect: ["middle", "back"]
         assertThat(lld1.toList()).containsExactly("middle", "back").inOrder();

         lld1.addFirst("front"); // after this call we expect: ["front", "middle", "back"]
         assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();

         /* Note: The first two assertThat statements aren't really necessary. For example, it's hard
            to imagine a bug in your code that would lead to ["front"] and ["front", "middle"] failing,
            but not ["front", "middle", "back"].
          */
     }

     @Test
     /** In this test, we use only one assertThat statement. IMO this test is just as good as addFirstTestBasic.
      *  In other words, the tedious work of adding the extra assertThat statements isn't worth it. */
     public void addLastTestBasic() {
         Deque61B<String> lld1 = new LinkedListDeque61B<>();

         lld1.addLast("front"); // after this call we expect: ["front"]
         assertThat(lld1.toList()).containsExactly("front");
         lld1.addLast("middle"); // after this call we expect: ["front", "middle"]
         lld1.addLast("back"); // after this call we expect: ["front", "middle", "back"]
         assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();
     }

     @Test
     /** This test performs interspersed addFirst and addLast calls. */
     public void addFirstAndAddLastTest() {
         Deque61B<Integer> lld1 = new LinkedListDeque61B<>();

         /* I've decided to add in comments the state after each call for the convenience of the
            person reading this test. Some programmers might consider this excessively verbose. */
         lld1.addLast(0);   // [0]
         lld1.addLast(1);   // [0, 1]
         lld1.addFirst(-1); // [-1, 0, 1]
         lld1.addLast(2);   // [-1, 0, 1, 2]
         lld1.addFirst(-2); // [-2, -1, 0, 1, 2]

         assertThat(lld1.toList()).containsExactly(-2, -1, 0, 1, 2).inOrder();
     }

     //Below, you'll write your own tests for LinkedListDeque61B.

    @Test
    public void isEmptyTest() {
        Deque61B<String> brisket = new LinkedListDeque61B<>();
        assertThat(brisket.isEmpty()).isEqualTo(true);
        brisket.addFirst("hydrogen bomb");
        brisket.addLast("cheesburger");
        assertThat(brisket.isEmpty()).isEqualTo(false);
    }

    @Test
    public void sizeTest () {
        Deque61B<String> brisket = new LinkedListDeque61B<>();
        assertThat(brisket.size()).isEqualTo(0);
        brisket.addFirst("hydrogen bomb");
        brisket.addLast("cheesburger");
        assertThat(brisket.size()).isEqualTo(2);
        brisket.addLast("diamond");
        assertThat(brisket.size()).isEqualTo(3);
        brisket.removeLast();
        brisket.removeLast();
        brisket.removeLast();
        assertThat(brisket.size()).isEqualTo(0);
        brisket.removeLast();
        assertThat(brisket.size()).isEqualTo(0);
    }
    @Test
    public void getFirst() {
        Deque61B<String> brisket = new LinkedListDeque61B<>();
        assertThat(brisket.getFirst()).isEqualTo(null);
        brisket.addFirst("hydrogen bomb");
        brisket.addLast("cheesburger");
        assertThat(brisket.getFirst()).isEqualTo("hydrogen bomb");
        brisket.addFirst("atom bomb");
        assertThat(brisket.getFirst()).isEqualTo("atom bomb");
    }

    @Test
    public void getLast(){
        Deque61B<String> brisket = new LinkedListDeque61B<>();
        assertThat(brisket.getLast()).isEqualTo(null);
        brisket.addFirst("hydrogen bomb");
        brisket.addLast("cheeseburger");
        assertThat(brisket.getLast()).isEqualTo("cheeseburger");
        brisket.addFirst("rat");
        assertThat(brisket.getLast()).isEqualTo("cheeseburger");
        brisket.addLast("diamond");
        assertThat(brisket.getLast()).isEqualTo("diamond");
    }

    @Test
    public void getTest() {
        Deque61B<String> brisket = new LinkedListDeque61B<>();
        assertThat(brisket.get(1)).isEqualTo(null);
        brisket.addLast("little boy");
        brisket.addLast("thin man");
        brisket.addLast("Fat man");
        assertThat(brisket.get(0)).isEqualTo("little boy");
        assertThat(brisket.get(1)).isEqualTo("thin man");
        assertThat(brisket.get(2)).isEqualTo("Fat man");
        assertThat(brisket.get(50)).isEqualTo(null);
        assertThat(brisket.get(-1)).isEqualTo(null);
    }
    @Test
    public void getRecursiveTest () {
        Deque61B<String> brisket = new LinkedListDeque61B<>();
        assertThat(brisket.get(1)).isEqualTo(null);
        brisket.addLast("little boy");
        brisket.addLast("thin man");
        brisket.addLast("Fat man");
        assertThat(brisket.get(0)).isEqualTo("little boy");
        assertThat(brisket.get(1)).isEqualTo("thin man");
        assertThat(brisket.get(2)).isEqualTo("Fat man");
        assertThat(brisket.get(50)).isEqualTo(null);
        assertThat(brisket.get(-1)).isEqualTo(null);
    }

    @Test
    public void removeFirstTest() {
        Deque61B<String> brisket = new LinkedListDeque61B<>();
        assertThat(brisket.removeFirst()).isEqualTo(null);
        brisket.addLast("little boy");
        assertThat(brisket.removeFirst()).isEqualTo("little boy");
        brisket.addLast("thin man");
        brisket.addFirst("Fat man");
        assertThat(brisket.removeFirst()).isEqualTo("Fat man");
        assertThat(brisket.toList()).containsExactly("thin man").inOrder();
        assertThat(brisket.size()).isEqualTo(1);
    }

    @Test
    public void remove_first_to_empty() {
        Deque61B<String> brisket = new LinkedListDeque61B<>();
        brisket.addLast("little boy");
        brisket.addLast("thin man");
        brisket.addFirst("Fat man");
        brisket.removeFirst();
        brisket.removeFirst();
        assertThat(brisket.toList()).containsExactly("thin man");
        brisket.removeFirst();
        assertThat(brisket.getFirst()).isEqualTo(null);
    }

    @Test
    public void removeLastTest() {
        Deque61B<String> brisket = new LinkedListDeque61B<>();
        assertThat(brisket.removeFirst()).isEqualTo(null);
        brisket.addLast("little boy");
        brisket.addLast("thin man");
        brisket.addLast("Fat man");
        assertThat(brisket.removeLast()).isEqualTo("Fat man");
        assertThat(brisket.toList()).containsExactly("little boy", "thin man").inOrder();
        assertThat(brisket.size()).isEqualTo(2);
    }

    @Test
    public void remove_last_to_empty() {
        Deque61B<String> brisket = new LinkedListDeque61B<>();
        brisket.addLast("little boy");
        brisket.addLast("thin man");
        brisket.addFirst("Fat man");
        brisket.removeLast();
        brisket.removeLast();
        assertThat(brisket.toList()).containsExactly("Fat man");
        brisket.removeLast();
        assertThat(brisket.getFirst()).isEqualTo(null);
    }

    @Test
    public void remove_first_to_one() {
        Deque61B<String> brisket = new LinkedListDeque61B<>();
        brisket.addLast("little boy");
        brisket.addLast("thin man");
        brisket.addFirst("Fat man");
        brisket.removeFirst();
        assertThat(brisket.toList()).containsExactly("little boy", "thin man");
        brisket.removeFirst();
        assertThat(brisket.getFirst()).isEqualTo("thin man");
    }

    @Test
    public void remove_last_to_one() {
        Deque61B<String> brisket = new LinkedListDeque61B<>();
        brisket.addLast("little boy");
        brisket.addLast("thin man");
        brisket.addFirst("Fat man");
        brisket.removeLast();
        assertThat(brisket.toList()).containsExactly("Fat man", "little boy");
        brisket.removeLast();
        assertThat(brisket.getFirst()).isEqualTo("Fat man");
    }

    @Test
    public void add_first_after_remove_to_empty() {
        Deque61B<String> brisket = new LinkedListDeque61B<>();
        brisket.addLast("little boy");
        brisket.addLast("thin man");
        brisket.addLast("Fat man");
        brisket.removeLast();
        brisket.removeLast();
        brisket.removeLast();
        assertThat(brisket.getFirst()).isEqualTo(null);
        brisket.addFirst("dinosaur");
        assertThat(brisket.getFirst()).isEqualTo("dinosaur");

    }
    @Test
    public void add_last_after_remove_to_empty() {
        Deque61B<String> brisket = new LinkedListDeque61B<>();
        brisket.addLast("little boy");
        brisket.addLast("thin man");
        brisket.addLast("Fat man");
        brisket.removeLast();
        brisket.removeLast();
        brisket.removeLast();
        assertThat(brisket.getFirst()).isEqualTo(null);
        brisket.addLast("dinosaur");
        assertThat(brisket.getFirst()).isEqualTo("dinosaur");
    }

    @Test
    public void to_list_test() {
        Deque61B<String> brisket = new LinkedListDeque61B<>();
        assertThat(brisket.toList()).containsExactly().inOrder();
        brisket.addFirst("cheese");
        assertThat(brisket.toList()).containsExactly("cheese").inOrder();
    }

}
