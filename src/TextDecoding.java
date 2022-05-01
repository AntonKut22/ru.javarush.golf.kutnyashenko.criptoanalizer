import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TextDecoding {

    private final List<Character> CODE_TEXT = new ArrayList<>(Alphabet.CHARACTER_LIST);

    public void decoder(String file, int key) {
        if (key > 40){
            key = key % 40;
        }
        Collections.rotate(CODE_TEXT, key);

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
             BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(pathForWriteDecoder(file), true))) {

            while (bufferedReader.ready()) {
                bufferedWriter.write(decryption(bufferedReader.readLine()));
                bufferedWriter.write("\n");
            }
            System.out.println("Данные успешно дешифрованы \n Выберите дальнейшее действие:");
        } catch (FileNotFoundException e) {
            System.out.println("Файла \"" + file + "\" не существует");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private char[] decryption(String text) {

        char[] textArrayChars = text.toLowerCase().toCharArray();
        char[] abc = new char[textArrayChars.length];
        int temp = 0;
        for (char c : textArrayChars) {
            if (CODE_TEXT.contains(c)) {
                int numberChar = CODE_TEXT.indexOf(c);
                abc[temp] = Alphabet.CHARACTER_LIST.get(numberChar);
            } else {
                abc[temp] = c;
            }
            temp++;
        }
        return abc;
    }

    private String pathForWriteDecoder(String path) {
        Path folder = Path.of(path).getParent();
        String stringFolder = folder.toString();
        Path originalFile = Path.of(path).getFileName();
        String stringOriginalFile = originalFile.toString();
        String nameFile = stringOriginalFile.substring(0, stringOriginalFile.lastIndexOf("."));
        String typeFile = stringOriginalFile.substring(stringOriginalFile.lastIndexOf("."));
        return stringFolder + "\\" + nameFile + "_decoder" + typeFile;
    }

}
