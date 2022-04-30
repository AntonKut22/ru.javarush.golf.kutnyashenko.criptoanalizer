import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

//        String originalPath = "D:\\Обучение\\JRU\\ru.javarush.golf.kutnyashenko.criptoanalizer\\Vojna_i_mir._Tom_1.txt";
//        String coderPath = "D:\\Обучение\\JRU\\ru.javarush.golf.kutnyashenko.criptoanalizer\\Vojna_i_mir._Tom_1_coder.txt";
//        Key.coder(originalPath, 5);
//        Key.decoder(coderPath, 5);


        Scanner scanner = new Scanner(System.in);
        String filePath;
        int key;

        System.out.println("Вас приветствует криптоанализатор. Выберите необходимое действие:");

        while (true) {

            System.out.println("1. Зашифровать данные");
            System.out.println("2. Дешифровать данные используя ключ");
            System.out.println("3. Дешифровать данные подбором ключа методом \"brute force\"");
            System.out.println("4. Выход");

            int select = scanner.nextInt();

            if (select == 1) {
                System.out.println("Введите путь к файлу:");
                filePath = scanner.nextLine();
                System.out.println("Введите ключ");
                key = scanner.nextInt();
                try {
                    Key.coder(filePath, key);
                } catch (FileNotFoundException e){
                    System.out.println("Файла \"" + filePath + "\" не существует");
                }
                System.out.println("Данные успешно зашифрованы \n Выберите дальнейшее действие:");
            } else if (select == 2) {
                System.out.println("Введите путь к файлу:");
                filePath = scanner.nextLine();
                System.out.println("Введите ключ");
                key = scanner.nextInt();
                try {
                    Key.decoder(filePath, key);
                } catch (FileNotFoundException e){
                    System.out.println("Файла \"" + filePath + "\" не существует");
                }
                System.out.println("Данные успешно дешифрованы \n Выберите дальнейшее действие:");
            } else if (select == 3) {
                System.out.println("Введите путь к файлу:");
                filePath = scanner.nextLine();
                //TODO Добавть ссылку на метод
                System.out.println("Выберите дальнейшее действие:");
            } else if (select == 4) {
                break;
            } else {
                System.out.println("Необходимо ввести цифру от 1 до 4");
            }
        }
    }

}
