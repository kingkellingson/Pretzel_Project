import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class PrintOutEverything {
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
                "|_|", /*or*/
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

        String[] becomes = new String[]{
                "   ",
                "-->",
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
        SymbolToPretzel.put(">", becomes);


        //////////////  START LOGIC  ///////////////


//        Scanner reader = new Scanner(System.in);  // Reading from System.in
//        System.out.println("Enter an equation: ");
//        String n = reader.nextLine(); // Scans the next token of the input as an int.
//        reader.close();
//
//
//        n = n.replace(" ", "");
//
//        int i = 0;
//        String[] nums = new String[2];
//
//        for (; i < n.length(); ++i) {
//            if (n.charAt(i) == '+' || n.charAt(i) == '-') {
//                nums = n.split("[+,-]");
//                break;
//            }
//        }

        int int1 = 0, int2 = 0, count = 0;
        for (int j = 0; j < 10; ++j) {
            for (int k = 0; k < 10; ++k) {

                int1 = j;
                int2 = k;

                int result = int1 + int2;

                ArrayList<String[]> onPage = new ArrayList<>();

                onPage.add(numToPretzel.get(int1));

                onPage.add(SymbolToPretzel.get("+"));
                onPage.add(numToPretzel.get(int2));
                onPage.add(SymbolToPretzel.get("="));

                if (result/10>0) {
//                    onPage.add(numToPretzel.get(result/10));
//                    onPage.add(numToPretzel.get(result%10));
                }
                else {
                    count++;
                    onPage.add(numToPretzel.get(result));
                    PrintEquation(onPage);
                }
            }
        }
        for (int j = 9; j >= 0; --j) {
            for (int k = j; k >= 0; --k) {
                count++;
                int1 = j;
                int2 = k;

                int result = int1 - int2;

                ArrayList<String[]> onPage = new ArrayList<>();

                onPage.add(numToPretzel.get(int1));

                onPage.add(SymbolToPretzel.get("-"));
                onPage.add(numToPretzel.get(int2));
                onPage.add(SymbolToPretzel.get("="));
                onPage.add(numToPretzel.get(result));

                PrintEquation(onPage);
            }
        }
        System.out.println("There are " + count + " valid equations with one digit answers");
    }





    private static void PrintEquation(ArrayList<String[]> onPage) {
        for(int j = 0; j < 3; ++j) {
            String test = new String();
            for(int k = 0; k < onPage.size(); ++k) {
                test = test + onPage.get(k)[j];
            }
            System.out.println(test);
        }
    }
}

/*
Build all the different withdrawals (taking away, and it still is valid)
Build all the different increments (adding, and it still is valid)

Subtract 1 only
0 ->
1 ->
2 ->
3 ->
4 ->
5 ->
6 -> 5
7 -> 1
8 -> 0, 6, 9
9 -> 3, 5


Adding 1 only
0 -> 8
1 -> 7
2 ->
3 -> 9
4 ->
5 -> 6, 9
6 -> 8
7 ->
8 ->
9 -> 8

Move one stick only
0 -> 6, 9
1 ->
2 -> 3
3 -> 2, 5
4 ->
5 -> 3
6 -> 0, 9
7 ->
8 ->
9 -> 0, 6

        " _ ",
        "|_ ",
        "|_|",

Should work
8-1=7 -> 6+1=7



1. Start with a simple equation
2. change one part of it
3. Should not be two of the same numbers in the same cycle


Things it could do:

Generate an incorrect equation, weird symbols that is x moves away
Generate an incorrect equation, right numbers that is x moves away
Generate a correct equation (with right numbers) that is x move away

*/