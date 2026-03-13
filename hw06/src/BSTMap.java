import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    K thisKey;
    V thisValue;
    BSTMap<K, V> parent;
    BSTMap<K, V> right;
    BSTMap<K, V> left;

    public BSTMap() {
        thisValue = null;
        parent = null;
        right = null;
        left = null;
        thisKey = null;
    }



    @Override
    public void put(K key, V value) {
        //first attempt complete
        if (this.thisKey == null) {
            this.thisKey = key;
            this.thisValue = value;
        }
        if (key.compareTo(this.thisKey) < 0) {
            if (this.left == null) {
                this.left = new BSTMap<>();
                this.left.parent = this;
                this.left.thisValue = value;
                this.left.thisKey = key;
            }
            this.left.put(key, value);
        } else if (key.compareTo(this.thisKey) > 0) {
            if (this.right == null) {
                this.right = new BSTMap<>();
                this.right.parent = this;
                this.right.thisValue = value;
                this.right.thisKey = key;
            }
            this.right.put(key, value);
        } else if (this.thisKey == key) {
            this.thisValue = value;
        }
    }

    @Override
    public V get(K key) {
        //first attempt completed
        if (this.thisKey == null) {
            return null;
        }
        if (this.thisKey.equals(key)) {
            return this.thisValue;
        } else if (key.compareTo(this.thisKey) < 0) {
            if (this.left != null) {
                return this.left.get(key);
            }
            return null;
        } else {
            if (this.right != null) {
                return this.right.get(key);
            }
            return null;
        }
    }

    @Override
    public boolean containsKey(K key) {
        //first attempt complete
        if (this.thisKey == null) {
            return false;
        }
        if ((this.thisKey).equals(key)) {
            return true;
        } else if (key.compareTo(this.thisKey) < 0) {
            if ((this.left) != null) {
                return (this.left).containsKey(key);
            }
            return false;
        } else if (key.compareTo(this.thisKey) > 0) {
            if ((this.right) != null) {
                return (this.right).containsKey(key);
            }
            return false;
        } else {
            return false;
        }
    }

    @Override
    public int size() {
        //first attempt complete
        if (this.thisKey == null) {
            return 0;
        } else if (this.left == null && this.right == null) {
            return 1;
        } else if (this.left != null) {
            if (this.right != null) {
                return 1 + this.left.size() + this.right.size();
            }
            return 1 + this.left.size();
        } else {
            return 1 + this.right.size();
        }
    }

    @Override
    public void clear() {
        //first attempt completed
        this.left = null;
        this.right = null;
        this.thisValue = null;
        this.thisKey = null;
    }

    @Override
    public Set<K> keySet() {
        //first attempt complete
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key) {
        //first attempt complete
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        //first attempt complete
        throw new UnsupportedOperationException();
    }
}

