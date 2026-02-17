import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static com.google.common.truth.Truth.assertThat;

public class ArrayDeque61BTest {
    @Test
    public void addFirstTest() {
        Deque61B<String> Dog = new ArrayDeque61B<>();
        assertThat(Dog.isEmpty()).isTrue();

        Dog.addFirst("one");
        assertThat(Dog.isEmpty()).isFalse();
        assertThat(Dog.size()).isEqualTo(1);
        assertThat(Dog.toList()).containsExactly(  "one").inOrder();

        Dog.addFirst("two");
        assertThat(Dog.size()).isEqualTo(2);
        assertThat(Dog.toList()).containsExactly( "two", "one").inOrder();

        Dog.addFirst("three");
        assertThat(Dog.size()).isEqualTo(3);
        assertThat(Dog.toList()).containsExactly( "three", "two", "one").inOrder();

        Dog.addFirst("four");
        assertThat(Dog.size()).isEqualTo(4);
        assertThat(Dog.toList()).containsExactly( "four", "three", "two", "one").inOrder();

        Dog.addFirst("five");
        assertThat(Dog.size()).isEqualTo(5);
        assertThat(Dog.toList()).containsExactly("five", "four", "three", "two", "one").inOrder();

        Dog.addFirst("six");
        assertThat(Dog.size()).isEqualTo(6);
        assertThat(Dog.toList()).containsExactly("six", "five", "four", "three", "two", "one").inOrder();

        Dog.addFirst("seven");
        Dog.addFirst("eight");
        Dog.addFirst("nine");
        assertThat(Dog.size()).isEqualTo(9);
        assertThat(Dog.toList()).containsExactly("nine", "eight", "seven", "six", "five", "four", "three", "two", "one").inOrder();

        for(int i = 0; i<10; i++) {
            Dog.removeLast();
        }
        assertThat(Dog.size()).isEqualTo(0);
        assertThat(Dog.toList()).containsExactly().inOrder();

        Dog.addFirst("cheeseburger");
        assertThat(Dog.size()).isEqualTo(1);
        assertThat(Dog.toList()).containsExactly("cheeseburger").inOrder();

    }

    @Test
    public void addLastTest() {
        Deque61B<Integer> Dog = new ArrayDeque61B<>();
        assertThat(Dog.isEmpty()).isTrue();

        Dog.addLast(1);
        assertThat(Dog.isEmpty()).isFalse();
        assertThat(Dog.size()).isEqualTo(1);
        assertThat(Dog.toList()).containsExactly(  1).inOrder();

        Dog.addLast(2);
        assertThat(Dog.size()).isEqualTo(2);
        assertThat(Dog.toList()).containsExactly( 1, 2).inOrder();

        Dog.addLast(3);
        assertThat(Dog.size()).isEqualTo(3);
        assertThat(Dog.toList()).containsExactly( 1, 2, 3).inOrder();

        Dog.addLast(4);
        assertThat(Dog.size()).isEqualTo(4);
        assertThat(Dog.toList()).containsExactly( 1, 2, 3, 4).inOrder();

        Dog.addLast(5);
        assertThat(Dog.size()).isEqualTo(5);
        assertThat(Dog.toList()).containsExactly(1, 2, 3, 4, 5).inOrder();

        Dog.addLast(6);
        Dog.addLast(7);
        Dog.addLast(8);
        Dog.addLast(9);
        assertThat(Dog.size()).isEqualTo(9);
        assertThat(Dog.toList()).containsExactly(1, 2, 3, 4, 5, 6, 7, 8, 9).inOrder();

        for(int i = 0; i<10; i++) {
            Dog.removeLast();
        }
        assertThat(Dog.size()).isEqualTo(0);
        assertThat(Dog.toList()).containsExactly().inOrder();

        Dog.addLast(5);
        assertThat(Dog.size()).isEqualTo(1);
        assertThat(Dog.toList()).containsExactly(5).inOrder();

    }

    @Test
    public void toListTest() {
        Deque61B<Integer> Dog = new ArrayDeque61B<>();
        assertThat(Dog.isEmpty()).isTrue();
        assertThat(Dog.toList()).containsExactly().inOrder();

        Dog.addLast(1);
        assertThat(Dog.toList()).containsExactly(1).inOrder();

        Dog.addLast(2);
        assertThat(Dog.toList()).containsExactly(1, 2).inOrder();

        Dog.removeLast();
        assertThat(Dog.toList()).containsExactly(1).inOrder();

        Dog.addLast(2);
        assertThat(Dog.toList()).containsExactly(1, 2).inOrder();

        Dog.addLast(3);
        assertThat(Dog.toList()).containsExactly(1, 2, 3).inOrder();

        Dog.addLast(4);
        assertThat(Dog.toList()).containsExactly(1, 2, 3, 4).inOrder();
    }

    @Test
    public void isEmptyTest() {
        Deque61B<Integer> Dog = new ArrayDeque61B<>();
        assertThat(Dog.isEmpty()).isTrue();

        Dog.addFirst(5);
        assertThat(Dog.isEmpty()).isFalse();

        Dog.removeFirst();
        assertThat(Dog.isEmpty()).isTrue();
    }

    @Test
    public void sizeTest() {
        Deque61B<Integer> Dog = new ArrayDeque61B<>();
        assertThat(Dog.size()).isEqualTo(0);

        Dog.addFirst(5);
        assertThat(Dog.size()).isEqualTo(1);

        Dog.removeFirst();
        assertThat(Dog.size()).isEqualTo(0);
    }

    @Test
    public void getFirstTest() {
        Deque61B<Integer> Dog = new ArrayDeque61B<>();
        assertThat(Dog.getFirst()).isEqualTo(null);

        Dog.addFirst(5);
        assertThat(Dog.getFirst()).isEqualTo(5);

        Dog.removeFirst();
        assertThat(Dog.getFirst()).isEqualTo(null);

        Dog.removeFirst();
        assertThat(Dog.getFirst()).isEqualTo(null);
    }

    @Test
    public void getLastTest() {
        Deque61B<Integer> Dog = new ArrayDeque61B<>();
        assertThat(Dog.getFirst()).isEqualTo(null);

        Dog.addFirst(5);
        assertThat(Dog.getFirst()).isEqualTo(5);

        Dog.removeFirst();
        assertThat(Dog.getLast()).isEqualTo(null);

        Dog.removeFirst();
        assertThat(Dog.getLast()).isEqualTo(null);
    }

    @Test
    public void removeFirstTest() {
        Deque61B<Integer> Dog = new ArrayDeque61B<>();
        assertThat(Dog.getFirst()).isEqualTo(null);

        Dog.addFirst(5);
        Dog.addFirst(4);
        Dog.addFirst(3);
        Dog.addFirst(2);
        Dog.addFirst(1);

        assertThat(Dog.removeFirst()).isEqualTo(1);
        assertThat(Dog.getFirst()).isEqualTo(2);
        assertThat(Dog.size()).isEqualTo(4);

        assertThat(Dog.removeFirst()).isEqualTo(2);
        assertThat(Dog.getFirst()).isEqualTo(3);
        assertThat(Dog.size()).isEqualTo(3);

        assertThat(Dog.removeFirst()).isEqualTo(3);
        assertThat(Dog.getFirst()).isEqualTo(4);
        assertThat(Dog.size()).isEqualTo(2);

        assertThat(Dog.removeFirst()).isEqualTo(4);
        assertThat(Dog.getFirst()).isEqualTo(5);
        assertThat(Dog.size()).isEqualTo(1);

        assertThat(Dog.removeFirst()).isEqualTo(5);
        assertThat(Dog.getFirst()).isEqualTo(null);
        assertThat(Dog.size()).isEqualTo(0);

        assertThat(Dog.removeFirst()).isEqualTo(null);
        assertThat(Dog.getFirst()).isEqualTo(null);
        assertThat(Dog.size()).isEqualTo(0);

        for (int i = 0; i<32; i++) {
            Dog.addLast(i);
        }

        for(int j = 0; j<25; j++) {
            Dog.removeFirst();
        }
        assertThat(Dog.toList()).containsExactly( 25, 26, 27, 28, 29, 30, 31);
        assertThat(Dog.size()).isEqualTo(7);
    }

    @Test
    public void removeLastTest() {
        Deque61B<Integer> Dog = new ArrayDeque61B<>();
        assertThat(Dog.getLast()).isEqualTo(null);

        Dog.addLast(1);
        Dog.addLast(2);
        Dog.addLast(3);
        Dog.addLast(4);
        Dog.addLast(5);

        assertThat(Dog.removeLast()).isEqualTo(5);
        assertThat(Dog.getLast()).isEqualTo(4);
        assertThat(Dog.size()).isEqualTo(4);

        assertThat(Dog.removeLast()).isEqualTo(4);
        assertThat(Dog.getLast()).isEqualTo(3);
        assertThat(Dog.size()).isEqualTo(3);

        assertThat(Dog.removeLast()).isEqualTo(3);
        assertThat(Dog.getLast()).isEqualTo(2);
        assertThat(Dog.size()).isEqualTo(2);

        assertThat(Dog.removeLast()).isEqualTo(2);
        assertThat(Dog.getLast()).isEqualTo(1);
        assertThat(Dog.size()).isEqualTo(1);

        assertThat(Dog.removeLast()).isEqualTo(1);
        assertThat(Dog.getLast()).isEqualTo(null);
        assertThat(Dog.size()).isEqualTo(0);

        assertThat(Dog.removeLast()).isEqualTo(null);
        assertThat(Dog.getLast()).isEqualTo(null);
        assertThat(Dog.size()).isEqualTo(0);

        for (int i = 0; i<32; i++) {
            Dog.addLast(i);
        }

        for(int j = 0; j<24; j++) {
            Dog.removeLast();
        }
        assertThat(Dog.toList()).containsExactly(0, 1, 2, 3, 4, 5, 6, 7);
        assertThat(Dog.size()).isEqualTo(8);
    }

    @Test
    public void getTest() {
        Deque61B<String> Dog = new ArrayDeque61B<>();
        assertThat(Dog.get(0)).isEqualTo(null);

        Dog.addFirst("one");
        assertThat(Dog.get(0)).isEqualTo("one");

        Dog.addFirst("two");
        assertThat(Dog.get(0)).isEqualTo("two");
        assertThat(Dog.get(1)).isEqualTo("one");

        Dog.addFirst("three");
        assertThat(Dog.get(0)).isEqualTo("three");
        assertThat(Dog.get(1)).isEqualTo("two");
        assertThat(Dog.get(2)).isEqualTo("one");

        Dog.addFirst("four");
        assertThat(Dog.get(0)).isEqualTo("four");
        assertThat(Dog.get(1)).isEqualTo("three");
        assertThat(Dog.get(2)).isEqualTo("two");
        assertThat(Dog.get(3)).isEqualTo("one");

        assertThat(Dog.get(-1)).isEqualTo(null);
        assertThat(Dog.get(4)).isEqualTo(null);
        assertThat(Dog.get(100)).isEqualTo(null);
    }

    @Test
    public void iteratorTest() {
        Deque61B<Integer> testArray = new ArrayDeque61B<>();
        for (int k = 0; k < 8; k++) {
            testArray.addLast(k);
        }
        int j = 0;
        for (Integer x : testArray) {
            assertThat(x).isEqualTo(testArray.get(j));
            j += 1;
        }
        assertThat(testArray).containsExactly(0, 1, 2, 3, 4, 5, 6, 7).inOrder();
    }

    @Test
    public void testEqualDeques61B() {
        Deque61B<String> ad = new ArrayDeque61B<>();
        Deque61B<String> ad2 = new ArrayDeque61B<>();

        ad.addLast("front");
        ad.addLast("middle");
        ad.addLast("back");

        ad2.addLast("front");
        ad2.addLast("middle");
        ad2.addLast("back");

        assertThat(ad.equals(ad2)).isTrue();

        Deque61B<String> ad3 = new ArrayDeque61B<>();
        ad3.addLast("back");
        ad3.addLast("middle");
        ad3.addLast("front");
        assertThat(ad3).isNotEqualTo(ad2);
    }

    @Test
    public void testContains() {
        Deque61B<String> ad = new ArrayDeque61B<>();
        assertThat(((ArrayDeque61B<String>) ad).contains("front")).isFalse();

        ad.addLast("front");
        ad.addLast("middle");
        ad.addLast("back");
        assertThat(((ArrayDeque61B<String>) ad).contains("front")).isTrue();
    }

    @Test
    public void toStringTest() {
        Deque61B<Integer> ad = new ArrayDeque61B<>();
        ad.addLast(1);
        ad.addLast(2);
        ad.addLast(3);

        String test = "[1, 2, 3]";
        String rocket = ad.toString();
        assertThat(rocket).isEqualTo(test);

        Deque61B<Integer> ad2 = new ArrayDeque61B<>();
        assertThat(ad2.toString()).isEqualTo("[]");
    }

}


