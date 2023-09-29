

public class Crossword implements WordPuzzleInterface{ 
	//private  int counter;
	private	 boolean PLEASE;
	private  int length;
	private  int x =0; //X CORD
	private  int y = 0; //Y CORD
    private  StringBuilder[] Col; //YKNOW 
    private  StringBuilder[] Row; //THE REST
	private  int[][] mfcounter;   //WHICH MF LETTER IS GOING TO BE ATTEMPTED IN SOLVE
    private  char[][] armenia; //READ OUT
	private  char[][] artsakh; //WRITE IN
    private static DictInterface dictionary;
	private static char[] cilicia = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };
	//private int[] idk = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0];


    

    public Crossword() {
    }

    public char[][] fillPuzzle(char[][] board, DictInterface dictionary){
        armenia = new char[board[0].length][board[0].length];
        for(int a = 0; a<board.length;a++){
            for(int b = 0; b<board.length;b++){
                armenia[a][b]=board[a][b];
				mfcounter[a][b]=0;
                

            }
        }
		length = armenia[0].length;			//NOW WE HAVE A LENGTH VARAIBLE TO USE :D
        this.solve(x,y); 					//CALL SOLVE, THE RECURSION STARTS THERE
        if(PLEASE){							//SOMETHING GOOD
            return artsakh;					//RETURN THE BOARD
        }
        else{								//BAD
            return null;					//FILL PUZZLE TELLS YOU TO FECK OFF M8
        }
        /*
         * Fill out a word puzzle defined by an empty board. 
         * 
         *  @param board is a 2-d array representing the empty board to be filled
         *  The characters in the empty board can be:
         *    '+': any letter can go here
         *    '-': no letter is allowed to go here
         *     a letter: this letter has to remain as-is at the same position in the filled puzzle
         *     a value between '1' and '9': any letter can go here and the provided value is an upper bound on the number 
         *                                  of times the letter can appear in the filled board. If a letter has multiple 
         *                                  upper bounds, the largest bound is the effective one.
         *  @param dictionary is the dictinary to be used for filling out the puzzle. Check DictInterface for
         *                    more details on the operations provided by the dictionary
         *  @return a 2-d array representing the filled out puzzle or null if the puzzle has no solution
         */
    	}

    	public void solve(int row, int col){
			char Lebanon = armenia[row][col];
			if(Character.isLetter(Lebanon)){
				if(Character.isUpperCase(Lebanon)){

				}
			}

			if(armenia[row][col]==('-')){ 
			}
			if(armenia[row][col]==('+')){				//PLEASE FOR THE LOVE OF GOSH, JUST FOR +++++++++
				for(int a = mfcounter[row][col]; a<25; a++){
					if(this.isValid(row, col,cilicia[a] )){	//CALL isValid FOR WHICHEVER LETTER, IF TRUE 
						Col[col].append(cilicia[a]);
						Row[row].append(cilicia[a]);						
						mfcounter[row][col]=a++; 		//KEEP TRACK OF WHAT NEXT LETTER IS, IN CASE OF NEEDING TO BACKTRACK
						artsakh[row][col]=cilicia[a]; 	//YO THIS SHIZ WORKS "FOR NOW", SET ROW AND COL TO A

						if(x==length-1){		//IF ALL THE WAY TO THE RIGHT
							if(y==length-1){	// AND IF ALL THE WAY ON THE BOTTOM, IE WE DONE
								PLEASE = true;	//WE GUCCI MALUCCI 
							
							}
							else{				//WE ARE NOT DONE, BUT ALL THE WAY RIGHT
								y++;
								x=0;
								this.solve(x,y); //SOLVE FROM THE FIRST COL NEXT ROW
							}	
						}
						else{					//KEEP ER GOIN
							x++;
							this.solve(x,y);
						}
					}
				}
				if(x==0&&y==0){					//BRO WE HAVE BRACKTRACKED ALL THE WAY TO THE BEGINNING.
					PLEASE = false;				//ITS JOEVER, SHUT IT DOWN
				}
				if(x==0){						//OK SO ITS NOT OVER BUT GO UP A ROW AND TO THE LAST COLUMN
					x=length-1;
					y--;
					mfcounter[row][col]=0;		//RESET SO WE CAN START FROM A AGAIN
					solve(x,y);					
				}
				else{							//GO BACK
					x--;
					mfcounter[row][col]=0;		//RESET SO WE CAN START FROM A AGAIN
					solve(x,y);
				}
			}
							//I mean if we made it this far then something had to have worked right
		}
				//for (letter)
				//if board with new letter is valid
				//1) update row_strs, col_strs, and letter count
				//2) return if done, otherwise continue solving
				//3) reset row_strs, col_strs, and letter count
					
					
				//case (0...9){
				//1) same things as previous case but now we have to
				//keep track of limit on character we place
				//on the board
				//}
				//1) is it valid given the minus	
				//2) update row_strs and col_strs	
				//3) return if done, otherwise continue solving	
    

			
    	public boolean isValid(int row, int col, char letter){
			StringBuilder updown = Col[col];
			StringBuilder leftright = Row[row];
			 
			updown.append(letter);
			leftright.append(letter);
			
			int ud = dictionary.searchPrefix(updown,0,length);
			int lr = dictionary.searchPrefix(leftright,0,length);

			boolean udworks = false;
			boolean lrworks = false;

			if(ud==0||lr==0){		//FRICK NO
				return false;
			}

			if(ud==3&&lr==3){		//FRICK YEAH
				return true;
			}

			//ALL THE CONDITIONS
			if(x==length-1){		//IF AT THE END OF THE ROW
				if(lr==1){
					lrworks = false;
				}
				if(lr==2){
					lrworks=true;
				}
			}
			else{
				if(lr==1){
					lrworks = true;
				}
			}

			if(y==length-1){		//IF ON THE BOTTOM COLLUMN
				if(ud==1){
					udworks=false;
				}
				if(ud==2){
					udworks=true;
				}
			}
			else{
				if(ud==1){
					udworks = true;
				}
			}
			/* 
			
			/* @return 0 if s is not a word or prefix within the DictInterface
			* 	                  1 if s is a prefix within the DictInterface but not a 
			*                       valid word
	    	*                    2 if s is a word within the DictInterface but not a
			*                        prefix to other words
			*                    3 if s is both a word within the DictInterface and a
			*                        prefix to other words
			*/   
			return udworks&&lrworks;
    	}
        
        public boolean checkPuzzle(char[][] emptyBoard, char[][] filledBoard, DictInterface dictionary){
			return true;
			//return false;
        }
    
        /*
         * Check if filledBoard is a correct filling for a given empty board
         * 
         * @param emptyBoard is a 2-d array representing an empty board
         * @param filledBoard is a 2-d array representing a filled out board
         * @param dictionary is the dictinary to be used for checking the filled out board
         * @return true if rules defined in fillPuzzle has been followed and 
         *  every row and column is a valid word in the dictionary. If a row
         *  or a column includes one or more '-', then each segment should be 
         *  a valid word in the dictionary; the method returns false otherwise
         */








    
}

    