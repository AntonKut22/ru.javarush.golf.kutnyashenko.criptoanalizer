import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String filePath;
        int key;
        int select;
        boolean isWorker = true;

        System.out.println("Вас приветствует криптоанализатор. Выберите необходимое действие:");

        while (isWorker) {

            System.out.println("1. Зашифровать данные");
            System.out.println("2. Дешифровать данные используя ключ");
            System.out.println("3. Дешифровать данные подбором ключа методом \"brute force\"");
            System.out.println("4. Выход");

            select = scanner.nextInt();
            scanner.nextLine();

            switch (select) {
                case 1:
                    System.out.println("Введите путь к файлу:");
                    filePath = scanner.nextLine();
                    System.out.println("Введите ключ");
                    key = scanner.nextInt();
                    TextEncoding textEncoding = new TextEncoding();
                    textEncoding.coder(filePath, key);
                    break;
                case 2:
                    System.out.println("Введите путь к файлу:");
                    filePath = scanner.nextLine();
                    System.out.println("Введите ключ");
                    key = scanner.nextInt();
                    TextDecoding textDecoding = new TextDecoding();
                    textDecoding.decoder(filePath, key);
                    break;
                case 3:
                    System.out.println("Введите путь к файлу:");
                    filePath = scanner.nextLine();
                    BruteForce bruteForce = new BruteForce();
                    bruteForce.bruteForceDecoder(filePath);
                    break;
                case 4:
                    isWorker = false;
                    break;
                default:
                    System.out.println("Необходимо ввести цифру от 1 до 4");
            }
        }
        scanner.close();
    }
}
