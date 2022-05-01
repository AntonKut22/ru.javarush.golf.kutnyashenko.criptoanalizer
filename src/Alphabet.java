import java.util.ArrayList;
import java.util.List;


public final class Alphabet {

    public static final List<Character> CHARACTER_LIST = new ArrayList<>();

    static {
        for (char i = 'а'; i <= 'я'; i++) {
            CHARACTER_LIST.add(i);
        }
        CHARACTER_LIST.add('.');
        CHARACTER_LIST.add(',');
        CHARACTER_LIST.add('"');
        CHARACTER_LIST.add(':');
        CHARACTER_LIST.add('-');
        CHARACTER_LIST.add('!');
        CHARACTER_LIST.add('?');
        CHARACTER_LIST.add(' ');
    }
}
