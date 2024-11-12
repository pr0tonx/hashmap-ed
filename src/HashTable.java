import java.util.LinkedList;

public abstract class HashTable {
    protected Object[] table;
    protected int size;
    private int collisions;

    public HashTable(int size) {
        this.size = size;
        this.table = new Object[size];
        this.collisions = 0;
    }

    protected void incrementCollisions() {
        collisions++;
    }

    protected abstract int hash(String item);

    public abstract void insert(String item);

    public abstract boolean search(String name);

    public int getCollisions() {
        return collisions;
    }


    public void printDistribution() {
        for (int i = 0; i < size; i++) {
            if (table[i] instanceof LinkedList) {
                System.out.println("Posição " + i + ": " + table[i]);
            } else if (table[i] != null) {
                System.out.println("Posição " + i + ": " + table[i]);
            }
        }
    }
}
