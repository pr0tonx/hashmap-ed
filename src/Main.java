import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int size = 5000;
        String csvFile = "D:\\Java Projects\\hashmap-ed\\src\\female_names.csv";
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o nome que deseja buscar: ");
        String nameToSearch = scanner.nextLine();

        OpenAddressingHashTable openAddressingHashTable = new OpenAddressingHashTable(size);
        ChainedHashTable chainedHashTable = new ChainedHashTable(size);


        long startTimeInsert = System.nanoTime();
        performInsertions(openAddressingHashTable, csvFile);
        long endTimeInsert = System.nanoTime();
        double openInsertTime = (endTimeInsert - startTimeInsert) / 1_000_000_000.0;

        startTimeInsert = System.nanoTime();
        performInsertions(chainedHashTable, csvFile);
        endTimeInsert = System.nanoTime();
        double chainInsertTime = (endTimeInsert - startTimeInsert) / 1_000_000_000.0;


        long startTimeSearch = System.nanoTime();
        boolean foundInOpenHash = openAddressingHashTable.search(nameToSearch);
        long endTimeSearch = System.nanoTime();
        double openSearchTime = (endTimeSearch - startTimeSearch) / 1_000_000_000.0;

        startTimeSearch = System.nanoTime();
        boolean foundInChainedHash = chainedHashTable.search(nameToSearch);
        endTimeSearch = System.nanoTime();
        double chainSearchTime = (endTimeSearch - startTimeSearch) / 1_000_000_000.0;


        System.out.println("Relatório:");

        System.out.println("Tabela com Endereçamento Aberto:");

        openAddressingHashTable.printDistribution();

        System.out.println("\nTabela com Lista Encadeada:");

        chainedHashTable.printDistribution();

        System.out.println("\nTabela com Endereçamento Aberto:");
        System.out.println("Número de colisões: " + openAddressingHashTable.getCollisions());
        System.out.printf("Tempo de inserção: %.9f segundos%n", openInsertTime);
        System.out.printf("Tempo de busca: %.9f segundos%n", openSearchTime);
        System.out.println(nameToSearch + " encontrado: " + foundInOpenHash);


        System.out.println("\nTabela com Lista Encadeada:");
        System.out.println("Número de colisões: " + chainedHashTable.getCollisions());
        System.out.printf("Tempo de inserção: %.9f segundos%n", chainInsertTime);
        System.out.printf("Tempo de busca: %.9f segundos%n", chainSearchTime);
        System.out.println(nameToSearch + " encontrado: " + foundInChainedHash);
    }

    private static void performInsertions(HashTable hashTable, String csvFile) {
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                hashTable.insert(line.trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
