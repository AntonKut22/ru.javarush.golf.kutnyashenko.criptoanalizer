import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TextEncoding {

    private final List<Character> CODE_ALPHABET = new ArrayList<>(Alphabet.alphabet);

    public void coder(String file, int key) {
        if (key > Alphabet.alphabet.size()) {
            key = key % Alphabet.alphabet.size();
        }
        Collections.rotate(CODE_ALPHABET, key);
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
             BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(pathForWriteCoder(file), true))) {

            while (bufferedReader.ready()) {
                bufferedWriter.write(encryption(bufferedReader.readLine()));
                bufferedWriter.write("\n");
            }
            System.out.println("Данные успешно зашифрованы \n Выберите дальнейшее действие:");
        } catch (FileNotFoundException e) {
            System.out.println("Файла \"" + file + "\" не существует");
        } catch (IOException e) {
            System.out.println("Ошибка чтения/записи файла");
        }
    }

    private char[] encryption(String text) {

        char[] textArrayChars = text.toLowerCase().toCharArray();
        char[] arrayCharsWithKeyShift = new char[textArrayChars.length];
        int temp = 0;
        for (char c : textArrayChars) {
            if (Alphabet.alphabet.contains(c)) {
                int numberChar = Alphabet.alphabet.indexOf(c);
                arrayCharsWithKeyShift[temp] = CODE_ALPHABET.get(numberChar);
            } else {
                arrayCharsWithKeyShift[temp] = c;
            }
            temp++;
        }
        return arrayCharsWithKeyShift;
    }

    private String pathForWriteCoder(String path) {
        Path folder = Path.of(path).getParent();
        String stringFolder = folder.toString();
        Path originalFile = Path.of(path).getFileName();
        String stringOriginalFile = originalFile.toString();
        String nameFile = stringOriginalFile.substring(0, stringOriginalFile.lastIndexOf("."));
        String typeFile = stringOriginalFile.substring(stringOriginalFile.lastIndexOf("."));
        return stringFolder + "\\" + nameFile + "_coder" + typeFile;
    }

}
