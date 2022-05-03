import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BruteForce {

    private final List<Character> CODE_ALPHABET = new ArrayList<>(Alphabet.alphabet);

    public void bruteForceDecoder(String file) {

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {

            int i = 0;
            StringBuilder stringBuilder = new StringBuilder();
            while (bufferedReader.ready() || i < 30) {
                String line = bufferedReader.readLine();
                stringBuilder.append(line);
                i++;
            }
            int key = decryption(stringBuilder.toString());
            if (key != -1) {
                EncodingDecoding decoding = new EncodingDecoding();
                decoding.coderDecoder(file, key, "decoder");
            } else {
                System.out.println("Не удалось подобрать ключ");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файла \"" + file + "\" не существует");
        } catch (IOException e) {
            System.out.println("Ошибка чтения/записи файла");
        }
    }

    private int decryption(String text) throws FileNotFoundException {
        int key = 1;
        boolean success = false;
        while (!success) {
            Collections.rotate(CODE_ALPHABET, 1);
            char[] textArrayChars = text.toLowerCase().toCharArray();
            List<Character> keyArray = new ArrayList<>();
            int temp = 0;
            for (char c : textArrayChars) {
                if (CODE_ALPHABET.contains(c)) {
                    int numberChar = CODE_ALPHABET.indexOf(c);
                    keyArray.add(temp, Alphabet.alphabet.get(numberChar));
                } else {
                    keyArray.add(temp, c);
                }
                temp++;
            }
            if (isSuccess(keyArray)) {
                return key;
            }
            key++;
            if (key == 41) {
                success = true;
            }
        }
        return -1;
    }

    private boolean isSuccess(List<Character> keyArray) {
        int aChar = Collections.frequency(keyArray, 'а');
        int oChar = Collections.frequency(keyArray, 'о');
        int eChar = Collections.frequency(keyArray, 'е');

        int sum = aChar + oChar + eChar;
        if (keyArray.size() / 100 * 20 < sum) {
            return true;
        }
        return false;
    }
}
