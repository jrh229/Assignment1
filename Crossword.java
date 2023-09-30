
public class Crossword implements WordPuzzleInterface {

    private int length;

    private static char[] cilicia = {'e', 't', 'a', 'o', 'i', 'n', 's', 'h', 'r', 'd', 'l', 'u','c', 'm', 'w', 'f', 'g', 'y', 'p', 'b', 'v', 'k', 'j', 'x', 'q', 'z'};
    //Let me just say, I spent no less than half an hour perplexed as hell, because when I originally had the alphabet in most ideal order, it suddenly broke my program
    //It took me 35 minutes to figure out that I had the entire alphabet in upper case
    private int[] LADIESANDGENTLEMENWEGOTHIM = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};  //letter used
    private int[] STOPPUTTINGLETTERSYOUTWAT = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};   //letter count for #
    private StringBuilder[] Row;
    private StringBuilder[] Col;
    private int[] rowfoundminus;
    private int[] colfoundminus;

    private int[][] mfcounter; //WHICH MF LETTER IS GOING TO BE ATTEMPTED IN SOLVE
    private char[][] armenia; //READ OUT
    private char[][] artsakh; //WRITE IN
    public char[][] Lebanon; //TEMPORARY BOARD
    private DictInterface dict;

    

    @Override
    public char[][] fillPuzzle(char[][] board, DictInterface dictionary) {
        length = board.length;                                                   //Declare length variable
        armenia = board;                                                         //Declaring read out board
        dict = dictionary;                                                       //Declaring dictionary
        Row = new StringBuilder[length];                                         //Intiializing Row Strbldr
        Col = new StringBuilder[length];                                         //Intiializing Col Strbldr
        rowfoundminus = new int[length];                                         //Initialize  Negative sign location row
        colfoundminus = new int[length];                                         //Initialize  Negative sign location col
        char[][] Lebanon = new char[length][length];                             //Create a placeholder double array
        mfcounter = new int[length][length];


        for (int a = 0; a < mfcounter.length; a++) {                            //initailize mf counter double array
            for (int b = 0; b < mfcounter.length; b++) {
                mfcounter[a][b] = 0;
            }
        }

        for (int a = 0; a < length; a++) {                                      //Double for loop for row
            int lastMinusRow = -1;                                              //Location int
            int lastMinusCol = -1;                                              //Location int
            StringBuilder temprow = new StringBuilder("");                  // Initalize StringBuilders
            StringBuilder tempcol = new StringBuilder("");                  // Initalize StringBuilders

            for (int b = 0; b < length; b++) {

                char rowplacement = board[a][b];                                //Takes char located at board[i][n];
                char colplacement = board[b][a];                                //Takes char located at board[i][n];
                

                if (rowplacement == '-') {                                      //If -
                    lastMinusRow = b;                                           //WE FOUND A -, PUT IT IN THE TRACKER
                }
                if (colplacement == '-') {                                      //If -
                    lastMinusCol = b;                                           //WE FOUND A -, PUT IT IN THE TRACKER
                }

                Lebanon[a][b] = rowplacement;                                   //Put Char into double array
                temprow.append(rowplacement);                                   //append char to temp StrBuilder
                tempcol.append(colplacement);

            }

            Row[a] = temprow;                                                   //StrBuilder Array = temp strbuilder
            Col[a] = tempcol;                                                   //StrBuilder Array = temp strbuilder
            colfoundminus[a] = lastMinusCol;                                    //Chart occurence of - sign in col array
            rowfoundminus[a] = lastMinusRow;                                    //Chart occurence of - sign in row array
        }

            for (int a = 0; a < length; a++) {                                  //Double array loop finding letters
                for (int b = 0; b < length; b++) {

                    char letter = board[a][b];

                        if (Character.isAlphabetic(letter)) {                   //If a letter
                            LADIESANDGENTLEMENWEGOTHIM[letter - 'a']++;         //Put into double array
                        }
                    }
                }

        artsakh = Lebanon;                                                      //Write in board = placeholder

        if (solve(0, 0)) {                                              //Solve starting at 0
            return artsakh;                                                     //YES
        }
        else {
            return null;                                                        //NO
        }
    }

    public boolean solve(int row, int col) {

        if (armenia[row][col] == '+') {                                         //If + Sign :D
            for (int a = 0; a < 26; a++) {                                      //Loop through alphabet
                
                boolean lessthan;
                boolean zero;
                Row[row].setCharAt(col, cilicia[a]);                            //StrBldr array row = char
                Col[col].setCharAt(row, cilicia[a]);                            //StrBldr array col = char
                LADIESANDGENTLEMENWEGOTHIM[a]++;                                //Add to the # of times  letter [i] is used

                if (isValid(row, col, cilicia[a]) ) {                           //If isValid && the letter CAN be used

                    lessthan =(LADIESANDGENTLEMENWEGOTHIM[a] < STOPPUTTINGLETTERSYOUTWAT[a]);
                    zero = (STOPPUTTINGLETTERSYOUTWAT[a] == 0);

                    if((lessthan || zero )){
                        artsakh[row][col] = cilicia[a];                        //Write in board adds letter at[i]
                        
                        if (col == length - 1) {                                //If on the last col
                            if (row == length - 1) {                            //If on the bottom row
                                return true;                                    //WE GUCCI MALUCCI
                            }

                            else {                                              //If on the last col but not last row
                                if (solve(row + 1, 0)) {                    //Just Go down a row and start at first col. WAITER, NEXT SOLVE PLEASE
                                 return true;                                   //WE GUCCI MALUCCI
                                }
                            }
                        }

                        else {                                                  //If NOT on last col
                            if (solve(row, col + 1)) {                          //Just go over one. WAITER, NEXT SOLVE PLEASE
                                return true;                                    //WE GUCCI MALUCCI
                            }
                        }
                    }
                }
                LADIESANDGENTLEMENWEGOTHIM[a]--;                                //If char does NOT WORK, REMOVE THE COUNT BECAUSE IT DONT WORK MF

                Row[row].setCharAt(col, '+');                                //REPLACE x1
                Col[col].setCharAt(row, '+');                                //REPLACE x2
                artsakh[row][col] = '+';                                        //GUESS WHAT, REPLACE x3
            }
        }
        
        if (Character.isAlphabetic(armenia[row][col])) {                        //THERE IS A LETTER, GOOD LUCK SOLDIER
            if (isValid(row, col, armenia[row][col])) {                         //Bro make sure this letter actually works
                if (col == length - 1) {                                        //If on the last col
                    if (row == length - 1) {                                    //If on the bottom row
                        return true;                                            //WE GUCCI MALUCCI
                    }

                    else {                                                      //If on the last col but not last row
                        if (solve(row + 1, 0)) {                            //Just Go down a row and start at first col. WAITER, NEXT SOLVE PLEASE
                            return true;                                        //WE GUCCI MALUCCI
                        }
                    }
                }
                
                else {                                                          //If NOT on last col
                    if (solve(row, col + 1)) {                                  //Just go over one. WAITER, NEXT SOLVE PLEASE
                        return true;                                            //WE GUCCI MALUCCI
                    }
                }
            }
        }
        if (armenia[row][col] == '-') {                                         //If there is a - sign, God help us
            if (col == length - 1) {                                            //If on the last col
                if (row == length - 1) {                                        //If on the bottom row
                    return true;                                                //WE GUCCI MALUCCI
                }
                else {                                                          //If on the last col but not last row
                    if (solve(row + 1, 0)) {                                //Just Go down a row and start at first col. WAITER, NEXT SOLVE PLEASE
                        return true;                                            //WE GUCCI MALUCCI
                    }
                }
            }
            else {                                                              //If NOT on last col
                if (solve(row, col + 1)) {                                      //Just go over one. WAITER, NEXT SOLVE PLEASE
                    return true;                                                //WE GUCCI MALUCCI
                }
            }
        }
        if (Character.isDigit(armenia[row][col])) {                             //If a number WHY KHATTAB WHY
            int countLim = Character.getNumericValue(armenia[row][col]);        //Extract the into from read board[row][col]
            for (int a = 0; a < 26; a++) {                                      //Go through the alphabet in whatever order

                boolean lessthan;
                boolean zero;

                Row[row].setCharAt(col, cilicia[a]);                            //StrBldr array row = char
                Col[col].setCharAt(row, cilicia[a]);                            //StrBldr array col = char

                LADIESANDGENTLEMENWEGOTHIM[a]++;                                //Add to the # of times  letter [i] is used
                STOPPUTTINGLETTERSYOUTWAT[a] = countLim;                        //Limit array becomes current char

                lessthan =(LADIESANDGENTLEMENWEGOTHIM[a] < STOPPUTTINGLETTERSYOUTWAT[a]);
                zero = (STOPPUTTINGLETTERSYOUTWAT[a] == 0);

                    if((lessthan || zero )){
                        artsakh[row][col] = cilicia[a];                         //Write in board using char
                            if (col == length - 1) {                                //if last col
                                if (row == length - 1) {                            //and last row
                                    return true;                                    //pack up, jobs done
                                }
                                else {                                              //IF last col but not row
                                    if (solve(row + 1, 0)) {                    //Go down and to the left
                                        return true;                                //YIPEEEEEE
                                        }
                                    }
                                }
                    else {                                                      //If not last col
                        if (solve(row, col + 1)) {                              //Slide to the right
                            return true;                                        //Criss cross
                        }
                    }
                }
            
                LADIESANDGENTLEMENWEGOTHIM[a]--;                                //If char does NOT WORK, REMOVE THE COUNT BECAUSE IT DONT WORK MF
                STOPPUTTINGLETTERSYOUTWAT[a] = 0;                               //Ctrl z

                Row[row].setCharAt(col, armenia[row][col]);                     //Replace
                Col[col].setCharAt(row, armenia[row][col]);                     //Replace
                artsakh[row][col] = armenia[row][col];                          //thanos snap
            }
        }
        return false;                                                           //Bro its joever, the west has fallen
    }

    private boolean isValid(int row, int col, char letter) {                    //isValid sucks

        StringBuilder TempRow = Row[row];                                       //TemporaryStrBuilder row = strbuilder[row]
        StringBuilder TempCol = Col[col];                                       //TemporaryStrBuilder col = strbuilder[col]
        
        TempRow.setCharAt(col, letter);                                         //set TemporaryStrBuilder row to char
        TempCol.setCharAt(row, letter);                                         //set TemporaryStrBuilder col to char

        boolean across = false;                                                 //boolean for row
        boolean updown = false;                                                 //boolean for col


        boolean coledge = (col+1==length);
        boolean rowedge = (row+1==length);
        boolean nextcolminus = (col + 1 == rowfoundminus[row]);
        boolean nextrowminus = (row + 1 == colfoundminus[col]);
        boolean colpastneg = (col > rowfoundminus[row] && rowfoundminus[row] != -1);
        boolean rowpastneg = (row > colfoundminus[col] && colfoundminus[col] != -1);
        boolean colonneg =  (rowfoundminus[row] == -1);
        boolean rowbeforemine = (row + 1 < colfoundminus[col]);
        boolean rowonmine = (colfoundminus[col] == -1);
                                                                                //ALL FOR ROWS
        if (nextcolminus) {                                                     //If next is a minus sign :(
            int colTest = dict.searchPrefix(TempRow, 0, col);             //FETCH ME A WORD
            if (colTest == 3 || colTest == 2) {                                 //If a word + prefix, or just a word
                across = true;                                                  //Checks out
            }
        }
        if (colpastneg) {                                                       //If we are past negative sign
            int colTest = dict.searchPrefix(TempRow, rowfoundminus[row] + 1, col); //Search for a small(er) word
            if (coledge) {                                                      //If next is border
                if (colTest == 3 || colTest == 2) {                             //If a word + prefix, or just a word
                    across = true;                                              //Checks out
                }
            }
            else {                                                              //If not at the border
                if (colTest == 3 || colTest == 1) {                             //If a word+ prefix, or just a prefix within restraints
                    across = true;                                              //Hail citizen
                }
            }
        }
        if (nextcolminus) {
            int colTest = dict.searchPrefix(TempRow, 0, col);             //Search for a word
            if (colTest == 3 || colTest == 1) {                                 //If a word+ prefix, or just a prefix within restraints
                across = true;                                                  //Skyrim belongs to the NORDS
            }
        }
        if (colonneg) {                                                         //Landmine
            int colTest = dict.searchPrefix(TempRow, 0, col);             //Search for a word
            if (coledge) {                                                      //If at the Rio Grande
                if (colTest == 3 || colTest == 2) {                             //If a word + prefix, or just a word
                    across = true;                                              //Guccus maluccus
                }
            }
            else {                                                              //If not at border
                if (colTest == 3 || colTest == 1) {                             //If a word+ prefix, or just a prefix within restraints
                    across = true;                                              //Veni Vidi Vici
                }
            }
        }                                                                       //END OF ROWS

        //Hello Peter                                                           //START OF COLUMN
        if (nextrowminus) {                                                     //If next is neg
            int rowTest = dict.searchPrefix(TempCol, 0, row);             //Bro get me a word STAT
            if (rowTest == 3 || rowTest == 2) {                                 //If word + prefix or just word
                updown = true;                                                  //Yipakaye
            }
        }
        if (rowpastneg) {                   //If we have passed the negative
            int rowTest = dict.searchPrefix(TempCol, colfoundminus[col] + 1, row); //Chop it up into little pieces
            if (rowedge) {                                                      //If near Column border
                if (rowTest == 3 || rowTest == 2) {                             //If Word
                    updown = true;                                              //Word == good
                }
            }
            else {                                                              //If not near gated fence
                if (rowTest == 3 || rowTest == 1) {                             //If word or prefix
                    updown = true;                                              //VORWARTS
                }
            }
        }
        if (rowbeforemine) {                                                    //if Before negative
            int rowTest = dict.searchPrefix(TempCol, 0, row);             //Go up till
            if (rowTest == 3 || rowTest == 1) {                                 //IF word or prefix
                updown = true;                                                  //God Wills it
            }
        }
        if (rowonmine) {                                                        //LORD SAVE US. THE ENEMY HAVE THE UPPER HAND "Medieval Total War 2 reference, great game btw"
            int rowTest = dict.searchPrefix(TempCol, 0, row);             //Ok lets start over
            if (rowedge) {                                                      //If border
                if (rowTest == 3 || rowTest == 2) {                             //If border
                    updown = true;                                              //Masallah
                }
            }
            else {                                                              //No Border???
                if (rowTest == 3 || rowTest == 1) {                             //Prefix word?
                    updown = true;                                              //In you go
                }
            }
        }                                                                       //END OF CLOLUMN

        return (updown&&across);                                                //Good + bad = bad. But Good + Good = Good
    }


    
        

    @Override
    public boolean checkPuzzle(char[][] emptyBoard, char[][] filledBoard, DictInterface dictionary) {
        return true; //IDFK
    }
    
}

