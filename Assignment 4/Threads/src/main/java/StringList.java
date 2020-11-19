import java.util.List;
import java.util.ArrayList;

class StringList {

    List<String> strings = new ArrayList<String>();

    public void add(String str) {
        int pos = strings.indexOf(str);
        if (pos < 0) {
            strings.add(str);
        }
    }

    public boolean contains(String str) {
        return strings.indexOf(str) >= 0;
    }

    public int size() {
        return strings.size();
    }

    public String toString() {
        return strings.toString();
    }

    public void remove(String index) {
        strings.remove(index);
    }

    public String remove(StringList string, int index) {

        if (index < 0) {
            return null;
        }
        return string.toString();
    }

    public void DisplayList() {
        for (String lists : strings) {
            System.out.println(lists);
        }
    }

    public String Count() {
        ArrayList<Integer> count = new ArrayList<>();
        for (String nstr : strings) {
            count.add(nstr.length());
        }
        return count.toString();
    }

    public String Reverse(String index) {
        char[] str = new char[index.length()];
        
        int strIndex = 0;
        for (int i = index.length() - 1; i >= 0; i--) {
            str[strIndex] = index.charAt(i);
            strIndex++;
        }
        String reverse = "";
        for (int i = 0; i < index.length(); i++) {
            reverse = reverse + str[i];
        }return strings.toString();
        
    }
    
}
