import java.util.LinkedList;

public class ChainedHashTable extends HashTable {

    public ChainedHashTable(int size) {
        super(size);
    }

    @Override
    public void insert(String item) {
        int index = hash(item);

        if (table[index] != null) {
            incrementCollisions();
            LinkedList<String> list = (LinkedList<String>) table[index];
            list.add(item);
        } else {
            LinkedList<String> list = new LinkedList<>();
            list.add(item);
            table[index] = list;
        }
    }

    @Override
    public boolean search(String name) {
        int index = hash(name);
        if (table[index] instanceof LinkedList) {
            LinkedList<String> list = (LinkedList<String>) table[index];
            return list.contains(name);
        }
        return false;
    }

    @Override
    protected int hash(String item) {
        return Math.abs(item.hashCode()) % size;
    }
}
