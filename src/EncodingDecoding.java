import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EncodingDecoding {

    private final List<Character> CODE_ALPHABET = new ArrayList<>(Alphabet.alphabet);

    public void coderDecoder(String file, int key, String type) {
        if (key > Alphabet.alphabet.size()) {
            key = key % Alphabet.alphabet.size();
        }

        Collections.rotate(CODE_ALPHABET, key);
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
             BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(pathForWrite(file, type), true))) {
            if (type.equals("coder")) {
                while (bufferedReader.ready()) {
                    bufferedWriter.write(encryptionDecryption(bufferedReader.readLine(), Alphabet.alphabet, CODE_ALPHABET));
                    bufferedWriter.write("\n");
                }
                System.out.println("Данные успешно зашифрованы \n Выберите дальнейшее действие:");
            } else {
                while (bufferedReader.ready()) {
                    bufferedWriter.write(encryptionDecryption(bufferedReader.readLine(), CODE_ALPHABET, Alphabet.alphabet));
                    bufferedWriter.write("\n");
                }
                System.out.println("Данные успешно расшифрованы \n Выберите дальнейшее действие:");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файла \"" + file + "\" не существует");
        } catch (IOException e) {
            System.out.println("Ошибка чтения/записи файла");
        }
    }

    private char[] encryptionDecryption(String text, List<Character> alphabetOne, List<Character> alphabetTwo) {

        char[] textArrayChars = text.toLowerCase().toCharArray();
        char[] arrayCharsWithKeyShift = new char[textArrayChars.length];

        for (int i = 0; i < textArrayChars.length; i++) {
            if (alphabetOne.contains(textArrayChars[i])) {
                int numberChar = alphabetOne.indexOf(textArrayChars[i]);
                arrayCharsWithKeyShift[i] = alphabetTwo.get(numberChar);
            } else {
                arrayCharsWithKeyShift[i] = textArrayChars[i];
            }
        }
        return arrayCharsWithKeyShift;
    }

    private String pathForWrite(String path, String addPostfix) {
        Path folder = Path.of(path).getParent();
        Path originalFile = Path.of(path).getFileName();
        String stringOriginalFile = originalFile.toString();
        String nameFile = stringOriginalFile.substring(0, stringOriginalFile.lastIndexOf("."));
        String typeFile = stringOriginalFile.substring(stringOriginalFile.lastIndexOf("."));
        String newName = nameFile + "_" + addPostfix + typeFile;
        Path newPath = folder.resolve(newName);
        return newPath.toString();
    }
}
