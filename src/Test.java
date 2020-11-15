import java.util.Iterator;

public class Test {
    public static void main(String[] args) {
        HashMap<String, Integer> map = new HashMap<>();
        Iterator<HashMap.Node<String, Integer>> itr = map.iterator();
        System.out.println(itr.hasNext());
        map.put("Cat", 1);
        map.put("Skittle", 1);
        System.out.println(itr.hasNext());
        System.out.println(itr.hasNext());
        System.out.println(itr.next().key);
        System.out.println(itr.hasNext());

    }
}
