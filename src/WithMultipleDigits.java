
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class WithMultipleDigits {
    public static void main(String[] args) {
        String[] space = new String[]{
                " ",
                " ",
                " ",
        };

        String[] NumArr0 = new String[]{
                " _ ",
                "| |",
                "|_|",
        };

        String[] NumArr1 = new String[]{
                "   ",
                "  |",
                "  |",
        };

        String[] NumArr2 = new String[]{
                " _ ",
                " _|",
                "|_ ",
        };

        String[] NumArr3 = new String[]{
                " _ ",
                " _|",
                " _|",
        };

        String[] NumArr4 = new String[]{
                "   ",
                "|_|",
                "  |",
        };

        String[] NumArr5 = new String[]{
                " _ ",
                "|_ ",
                " _|",
        };

        String[] NumArr6 = new String[]{
                " _ ",
                "|_ ",
                "|_|",
        };


        String[] NumArr7 = new String[]{
                " _ ",
                "  |",
                "  |",
        };

        String[] NumArr8 = new String[]{
                " _ ",
                "|_|",
                "|_|",
        };

        String[] NumArr9 = new String[]{
                " _ ",
                "|_|",
                " _|",
        };


        String[] plus = new String[]{
                "   ",
                " + ",
                "   ",
        };

        String[] minus = new String[]{
                "   ",
                " _ ",
                "   ",
        };

        String[] equals = new String[]{
                "   ",
                " = ",
                "   ",
        };

        HashMap<Integer, String[]> numToPretzel = new HashMap<>();
        numToPretzel.put(0, NumArr0);
        numToPretzel.put(1, NumArr1);
        numToPretzel.put(2, NumArr2);
        numToPretzel.put(3, NumArr3);
        numToPretzel.put(4, NumArr4);
        numToPretzel.put(5, NumArr5);
        numToPretzel.put(6, NumArr6);
        numToPretzel.put(7, NumArr7);
        numToPretzel.put(8, NumArr8);
        numToPretzel.put(9, NumArr9);

        HashMap<String, String[]> SymbolToPretzel = new HashMap<>();
        SymbolToPretzel.put("+", plus);
        SymbolToPretzel.put("-", minus);
        SymbolToPretzel.put("=", equals);


        Scanner reader = new Scanner(System.in);  // Reading from System.in
        System.out.println("Enter an equation: ");
        String n = reader.nextLine(); // Scans the next token of the input as an int.
        reader.close();


        n = n.replace(" ", "");

        int i = 0;
        String[] nums = new String[2];

        for (; i < n.length(); ++i) {
            if (n.charAt(i) == '+' || n.charAt(i) == '-') {
                nums = n.split("[+,-]");
                break;
            }
        }

        int int1 = Integer.parseInt(nums[0]);
        int int2 = Integer.parseInt(nums[1]);
        int result = 0;

        ArrayList<String[]> onPage = new ArrayList<>();

        onPage.add(numToPretzel.get(int1));

        switch (n.charAt(i)) {
            case '+':
                result = int1 + int2;
                onPage.add(SymbolToPretzel.get("+"));
                break;
            case '-':
                result = int1 - int2;
                onPage.add(SymbolToPretzel.get("-"));
                break;
            default:
        }

        onPage.add(numToPretzel.get(int2));
        onPage.add(SymbolToPretzel.get("="));
        onPage.add(numToPretzel.get(result));

        for(int j = 0; j < 3; ++j) {
            String tet = new String();
            for(int k = 0; k < onPage.size(); ++k) {
                tet = tet + onPage.get(k)[j];
            }
            System.out.println(tet);
        }
    }
}