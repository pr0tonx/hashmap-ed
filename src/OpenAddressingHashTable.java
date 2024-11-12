// OpenAddressingHashTable.java
public class OpenAddressingHashTable extends HashTable {

    public OpenAddressingHashTable(int size) {
        super(size);
    }

    @Override
    public void insert(String item) {
        int index = hash(item);
        int originalIndex = index;

        while (table[index] != null) {
            incrementCollisions();
            index = (index + 1) % size;
            if (index == originalIndex) {
                break;
            }
        }
        table[index] = item;
    }

    @Override
    public boolean search(String name) {
        int index = hash(name);
        int originalIndex = index;

        while (table[index] != null) {
            if (table[index].equals(name)) {
                return true;
            }
            index = (index + 1) % size;
            if (index == originalIndex) {
                break;
            }
        }
        return false;
    }

    @Override
    protected int hash(String item) {
        return Math.abs(item.hashCode()) % size;
    }
}
