import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public final class Key {

    private static List<Character> characterList = new ArrayList<>();

    static {
        for (char i = 'а'; i <= 'я'; i++) {
            characterList.add(i);
        }
        characterList.add('.');
        characterList.add(',');
        characterList.add('"');
        characterList.add(':');
        characterList.add('-');
        characterList.add('!');
        characterList.add('?');
        characterList.add(' ');
    }

    private static List<Character> codeText;


    private static String toString(char[] a) {
        if (a == null)
            return "null";
        int iMax = a.length - 1;
        if (iMax == -1)
            return "";

        StringBuilder b = new StringBuilder();
        for (int i = 0; ; i++) {
            b.append(a[i]);
            if (i == iMax)
                return b.toString();
        }
    }

    public static void coder(String file, int key) throws FileNotFoundException{
        codeText = new ArrayList<>(characterList);
        Collections.rotate(codeText, key);

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
             BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(pathForWriteCoder(file), true))) {

            while (bufferedReader.ready()) {
                bufferedWriter.write(Key.encryption(bufferedReader.readLine()));
                bufferedWriter.write("\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void decoder(String file, int key) throws FileNotFoundException{
        codeText = new ArrayList<>(characterList);
        Collections.rotate(codeText, key);

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
             BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(pathForWriteDecoder(file), true))) {

            while (bufferedReader.ready()) {
                bufferedWriter.write(Key.decryption(bufferedReader.readLine()));
                bufferedWriter.write("\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static char[] encryption(String text) {

        char[] textArrayChars = text.toLowerCase().toCharArray();
        char[] abc = new char[textArrayChars.length];
        int temp = 0;
        for (char c : textArrayChars) {
            if (characterList.contains(c)) {
                int numberChar = characterList.indexOf(c);
                abc[temp] = codeText.get(numberChar);
                temp++;
            } else {
                abc[temp] = c;
                temp++;
            }
        }
        return abc;
    }

    private static char[] decryption(String text) {

        char[] textArrayChars = text.toLowerCase().toCharArray();
        char[] abc = new char[textArrayChars.length];
        int temp = 0;
        for (char c : textArrayChars) {
            if (codeText.contains(c)) {
                int numberChar = codeText.indexOf(c);
                abc[temp] = characterList.get(numberChar);
                temp++;
            } else {
                abc[temp] = c;
                temp++;
            }
        }
        return abc;
    }


    private static String pathForWriteCoder(String path) {
        Path folder = Path.of(path).getParent();
        String stringFolder = folder.toString();
        Path originalFile = Path.of(path).getFileName();
        String stringOriginalFile = originalFile.toString();
        String nameFile = stringOriginalFile.substring(0, stringOriginalFile.lastIndexOf("."));
        String typeFile = stringOriginalFile.substring(stringOriginalFile.lastIndexOf("."));
        return stringFolder + "\\" + nameFile + "_coder" + typeFile;
    }

    private static String pathForWriteDecoder(String path) {
        Path folder = Path.of(path).getParent();
        String stringFolder = folder.toString();
        Path originalFile = Path.of(path).getFileName();
        String stringOriginalFile = originalFile.toString();
        String nameFile = stringOriginalFile.substring(0, stringOriginalFile.lastIndexOf("."));
        String typeFile = stringOriginalFile.substring(stringOriginalFile.lastIndexOf("."));
        return stringFolder + "\\" + nameFile + "_decoder" + typeFile;
    }

}
