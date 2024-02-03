import java.util.*;

public class OneChangeOnlyAddition {
    static Set<Integer> addKeySet;
    static Set<Integer> subtractKeySet;
    static Set<Integer> moveKeySet;
    static HashMap<String, String[]> SymbolToPretzel;
    static HashMap<Integer, String[]> numToPretzel;



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
                " † ",
                "   ",
        };

        String[] minus = new String[]{
                "   ",
                " – ",
                "   ",
        };

        String[] equals = new String[]{
                "   ",
                " = ",
                "   ",
        };

        String[] becomes = new String[]{
                "         ",
                "   -->   ",
                "         ",
        };

        String[] orString = new String[]{
                "         ",
                "    OR   ",
                "         ",
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
        SymbolToPretzel.put("or", orString);

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

        int[] toAddTo = new int[] {-1,-1,-1};
        int[] toTakeFrom = new int[] {-1,-1,-1};
        int[] numsInEquation = new int[] {-1,-1,-1};

        //cycle through all the possible pairs of equations
        for (int j = 0; j < 10; ++j) {
            if (j == 2 || j == 4) continue;

            //if you can add, add to correct position
            checkChanges(toAddTo, toTakeFrom, j, 0);
            numsInEquation[0] = j;
            for (int k = 0; k < 10; ++k) {
                if (k == 2 || k == 4) continue;

                checkChanges(toAddTo, toTakeFrom, k, 1);
                numsInEquation[1] = k;

                int result = j + k;
                checkChanges(toAddTo, toTakeFrom, result, 2);
                numsInEquation[2] = result;

                ArrayList<String[]> onPage = new ArrayList<>();
                BuildEquation(j, k, result, onPage);

                //at this point I know which ones are add to and which ones are subtract from
                boolean foundChange = false;
                for (int l = 0; l < 3; ++l) {
                    for (int m = 0; m < 3; ++m) {

                        if (l == m) continue;
                        if (toAddTo[l] != -1 && toTakeFrom[m] != -1) {
                            if (!foundChange) {
                                foundChange = true;
                                onPage.add(SymbolToPretzel.get(">"));
                            }
                            int[] changedNumsInEquation = numsInEquation.clone();

                            doChange(toAddTo[l], "add", changedNumsInEquation, l);
                            doChange(toTakeFrom[m], "subtract", changedNumsInEquation, m);
                            BuildEquation(changedNumsInEquation[0], changedNumsInEquation[1], changedNumsInEquation[2], onPage);
                            onPage.add(SymbolToPretzel.get("or"));
                        }
                    }
                }
                if (foundChange) {
                    onPage.remove(onPage.size() - 1); //removes the last "or" symbol
                    PrintEquation(onPage);
                }

                toAddTo[1] = -1;
                toAddTo[2] = -1;
                toTakeFrom[1] = -1;
                toTakeFrom[2] = -1;
                numsInEquation[1] = -1;
                numsInEquation[2] = -1;
            }
            toAddTo = new int[] {-1,-1,-1};
            toTakeFrom = new int[] {-1,-1,-1};
            numsInEquation = new int[] {-1,-1,-1};
        }
    }

    private static void checkChanges(int[] toAddTo, int[] toTakeFrom, int currNum, int currPos) {
        if(currNum == 0 || currNum == 1 || currNum == 3 || currNum == 5 || currNum == 6 || currNum == 9) {
            toAddTo[currPos] = currNum;
        }
        if(currNum == 6 || currNum == 7 || currNum == 8 || currNum == 9) {
            toTakeFrom[currPos] = currNum;
        }
    }

    private static void doChange(int toChange, String type, int[] numsInEquation, int location) {
        if (type.equals("add")) {
            switch (toChange) {
                case 0, 6, 9 -> numsInEquation[location] = 8;
                case 1 -> numsInEquation[location] = 7;
                case 3 -> numsInEquation[location] = 9;
                case 5 -> numsInEquation[location] = 6;
            }
        }
        if (type.equals("subtract")) {
            switch (toChange) {
                case 6 -> numsInEquation[location] = 5;
                case 7 -> numsInEquation[location] = 1;
                case 8 -> numsInEquation[location] = 0;
                case 9 -> numsInEquation[location] = 3;
            }
        }
    }

    private static void BuildEquation(int int1, int int2, int result, ArrayList<String[]> onPage) {
        onPage.add(numToPretzel.get(int1));
        onPage.add(SymbolToPretzel.get("+"));
        onPage.add(numToPretzel.get(int2));
        onPage.add(SymbolToPretzel.get("="));
        if (result >= 10) {
            onPage.add(numToPretzel.get(result/10));
            onPage.add(numToPretzel.get(result%10));
        }
        else {
            onPage.add(numToPretzel.get(result));
        }
    }

    private static void PrintEquation(ArrayList<String[]> onPage) {
        for(int j = 0; j < 3; ++j) {
            String test = new String();
            for(int k = 0; k < onPage.size(); ++k) {
                test = test + onPage.get(k)[j];
            }
            System.out.println(test);
        }
        onPage.clear();
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