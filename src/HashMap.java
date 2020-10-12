import java.util.Iterator;

public class HashMap<K, V> implements Map<K, V>, Iterable<HashMap.Node<K, V>> {

    /**
     * Node to store the K-V pair
     * @param <K> Key
     * @param <V> Value
     */
    public static class Node<K, V> {
        K key;
        V value;
        Node<K,V> next;
        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

    }

    static final int DEFAULT_INITIAL_CAPACITY = 16;
    static final float DEFAULT_LOAD_FACTOR = 0.75F;
    static final int SCALE_FACTOR = 2;

    private int cap;
    private float loadFactor;
    private int size;
    private Node<K,V>[] array;

    class MyHashMapIterator<T> implements Iterator<T> {
        int count;
        Node<K,V> next;

        MyHashMapIterator() {
            this.count = -1;
            this.next = null;
        }

        /**
         * check if the iterator has next
         * @return {@code true} if the iterator has next
         */
        public boolean hasNext() {
            if (next != null) {
                next = next.next;
            }

            while (next == null && ++count < array.length) {
                next = array[count];
            }

            return next != null;
        }

        /**
         * get next item
         * @return next in the iterator
         */
        public T next() {
            return (T)next;
        }
    }

    /**
     * get an iterator fot the hashmap
     * @return a iterator that can iterate through the current hashmap
     */
    public Iterator< Node<K,V>> iterator() {
        return new MyHashMapIterator<Node<K,V>>();
    }

    /**
     * create a new HashMap with default setting
     */
    public HashMap() {
        this(DEFAULT_INITIAL_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    /**
     * create a hashmap with the specified capacity and loading factor
     * @param cap an int of capacity
     * @param loadFactor an float of the load factor
     */
    public HashMap(int cap, float loadFactor) {
        this.cap = cap;
        this.loadFactor = loadFactor;
        this.size = 0;
        this.array = (Node<K, V>[]) new Node[cap];
    }

    /**
     * get the size of the current hashmap
     * @return an int value of the size
     */
    public int size() {
        return this.size;
    }

    /**
     * check if the current hashmap is empty
     * @return {@code true} if the hashmap is empty
     */
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * calculate hashcode for the given key
     * @param key the key from imput
     * @return an int value of hashcode (>= 0)
     */
    private int hash(Object key) {
        if (key == null) {
            return 0;
        }
        return key.hashCode() & 0X7FFFFFFF;
    }

    /**
     * check if the given key is in the hashmap
     * @param key the input key
     * @return  {@code true} if the key is found in the hashmap
     */
    public boolean containsKey(Object key) {
        int index = hash(key) % array.length;
        Node<K, V> node = array[index];
        while (node != null) {
            if (node.key == key || node.key.equals(key)) {
                return true;
            }
            node = node.next;
        }
        return false;
    }

    /**
     * get the value that corresponds to given key
     * @param key the input key
     * @return the value corresponds to the given key
     */
    public V get(Object key) {
        int index =  hash(key) % array.length;
        Node<K,V> node = array[index];
        while (node != null) {
            if (node.key == key || node.key.equals(key)) {
                return node.value;
            }
            node = node.next;
        }
        return null;

    }

    /**
     * put a key value pair to the hashmap, update value if key is already in the the map
     * @param key   the input key
     * @param value the new value
     * @return the old value of the key
     */
    public V put(K key, V value) {
        int index =  hash(key) % array.length;
        Node<K, V> head = array[index];
        Node<K, V> node = head;
        while (node != null) {
            if (node.key == key || node.key.equals(key)) {
                return node.value;
            }
            node = node.next;
        }

        Node<K, V> newNode = new Node(key, value);
        newNode.next = head;
        array[index] = newNode;
        this.size++;
        if ((float)(size) / array.length >= loadFactor) {
            resize();
        }

        return null;

    }

    /**
     * resize the internal array when load factor is reached
     */
    private void resize() {
        Node<K,V>[] old = array;
        this.array = (Node<K,V> [])new Node[array.length * SCALE_FACTOR];
        this.size = 0;
        for (Node<K, V> node: old) {
            while(node != null) {
                put(node.key, node.value);
                node = node.next;
            }
        }
    }

    /**
     * remove key value pair form the hashmap
     * @param key the input key
     * @return the value correspond with the key in the hashmap
     */
    public V remove(K key) {
        int index =  hash(key) % array.length;
        Node<K, V> node = array[index];
        Node<K, V> prev = null;
        while (node != null) {
            if (node.key == key || node.key.equals(key)) {
                if (prev != null) {
                    prev.next = node.next;
                } else {
                    array[index] = node.next;
                }
                size--;
                return node.value;
            }
            node = node.next;
        }
        return null;

    }

}
