import java.util.*;

public class OneChange {
    static Set<Integer> addKeySet;
    static Set<Integer> subtractKeySet;
    static Set<Integer> moveKeySet;
    static HashMap<String, String[]> SymbolToPretzel;
    static HashMap<Integer, String[]> numToPretzel



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

        String[] orString = new String[]{
                "   ",
                " OR",
                "   ",
        };

        numToPretzel = new HashMap<>();
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

        SymbolToPretzel = new HashMap<>();
        SymbolToPretzel.put("+", plus);
        SymbolToPretzel.put("-", minus);
        SymbolToPretzel.put("=", equals);
        SymbolToPretzel.put(">", becomes);
        SymbolToPretzel.put("o", orString);

        HashMap<Integer, List<Integer>> addOnePretzel = new HashMap<>();
        addOnePretzel.put(0, List.of(8));
        addOnePretzel.put(1, List.of(7));
        addOnePretzel.put(3, List.of(9));
        addOnePretzel.put(5, List.of(6,9));
        addOnePretzel.put(6, List.of(8));
        addOnePretzel.put(9, List.of(8));
        addKeySet = addOnePretzel.keySet();

        HashMap<Integer, List<Integer>> subtractOnePretzel = new HashMap<>();
        subtractOnePretzel.put(6, List.of(5));
        subtractOnePretzel.put(7, List.of(1));
        subtractOnePretzel.put(8, List.of(0,6,9));
        subtractOnePretzel.put(9, List.of(3,5));
        subtractKeySet = subtractOnePretzel.keySet();

        HashMap<Integer, List<Integer>> moveOnePretzel = new HashMap<>();
        moveOnePretzel.put(0, List.of(6,9));
        moveOnePretzel.put(2, List.of(3));
        moveOnePretzel.put(3, List.of(2,5));
        moveOnePretzel.put(5, List.of(3));
        moveOnePretzel.put(6, List.of(0,9));
        moveOnePretzel.put(9, List.of(0,6));
        moveKeySet = moveOnePretzel.keySet();



        //////////////  START LOGIC  ///////////////




        int int1 = 0, int2 = 0, count = 0;
        for (int j = 0; j < 10; ++j) {
            for (int k = 0; k < 10; ++k) {

                int1 = j;
                int2 = k;
                int result = int1 + int2;

                ArrayList<String[]> onPage = new ArrayList<>();

                BuildEquation(int1, int2, result, onPage);

                if (testSwap(int1, int2)) {
                    doAdd(int2, int1, onPage);
                }
                testSubtract(result, int1, int2, onPage);

                PrintEquation(onPage);
            }
        }

        System.out.println("There are " + count + " valid equations with one digit answers");
    }

    private static boolean testSwap(int int1, int int2) {
        boolean canAddToFirst = false, canTakeFromSecond = false;
        if(int1 == 0 || int1 == 1 || int1 == 3 || int1 == 5 || int1 == 6 || int1 == 9) {
            canAddToFirst = true;
        }
        if(int2>=6) {
            canTakeFromSecond = true;
        }
        return canAddToFirst && canTakeFromSecond;
    }

    private static void doAdd(int addFirst, int takeFrom, ArrayList<String[]> onPage) {
        switch (addFirst) {
            case 0, 6, 9:
                onPage.add(numToPretzel.get(8));
                break;
            case 1:
                onPage.add(numToPretzel.get(7));
                break;
            case 3, 5:
                onPage.add(numToPretzel.get(9));
                break;
        }
        onPage.add(SymbolToPretzel.get("+"));
        testSubtract(takeFrom, onPage);
    }

    private static void testSubtract(int takeFrom, ArrayList<String[]> onPage) {
        switch (takeFrom) {
            case 6,9:
                onPage.add(numToPretzel.get(5));
                break;
            case 7:
                onPage.add(numToPretzel.get(1));
                break;
            case 8:
                onPage.add(numToPretzel.get(0));
                break;

        }
    }

    private static void BuildEquation(int int1, int int2, int result, ArrayList<String[]> onPage) {
        onPage.add(numToPretzel.get(int1));
        onPage.add(SymbolToPretzel.get("+"));
        onPage.add(numToPretzel.get(int2));
        onPage.add(SymbolToPretzel.get("="));
        onPage.add(numToPretzel.get(result));
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