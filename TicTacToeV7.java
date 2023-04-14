import java.util.Scanner;

public class TicTacToeV7 {
    public static void main(String[] args){
        // default user 1, user 2 if not in computer mode
        String[] userName = {"USER 1","USER 2"};
        String[][] valueAtPlace = {
                                    {"00","01","02"},
                                    {"10","11","12"},
                                    {"20","21","22"}
        };
        int[] gameMode = {-1};//-1 = default | sets game difficulty lvl

        //first time printing of board
        System.out.println( "\n\n\n\t\tTIC TAC TOE"+
                                "\n\t\t===========\n"+
                            "\nHow to play?:"+
                            "\n           ~Two players play against each other on a 3x3 board."+
                            "\n           ~User 1 will use crosses 'X'"+
                            "\n           ~User 2/PC will use noughts 'o'"+
                            "\n           ~First player to align 3 identical symbols (horizontally, vertically, or diagonally),"+
                            "\n    wins the game.");

        //ask for playing mode
        playingMode(userName, gameMode);

        //print the changes
        output(valueAtPlace,userName);

        //exception handling of math.random -- getting input until draw
        for(int j=1 ; j<=10 ;j++){

            if(j==9){// handling exception occuring at last random selection of position
                for(int row = 0; row < 3; row++){
                    for(int column = 0; column < 3; column++){

                        //where row and col is neither x or o --- position is empty
                        if(valueAtPlace[row][column] != "X" && valueAtPlace[row][column] != "O"){
                            if(userName[0] == "USER 1"){

                                valueAtPlace[row][column] = "X";//set it as x if user 1's turn
                                userName[0] = "USER 2";         //set next turn for user 2 --to make compatible with checkresult

                                if(userName[1] == "Computer"){
                                    userName[0] = userName[1];//sets next turn for computer --only in computer mode ---make compatible with checkresult
                                }

                            }
                            else{

                                valueAtPlace[row][column] = "O";//set it as o if user's 2 turn or coputers turn

                                if(userName[0] == "Computer"){  //print only if computers turn
                                    System.out.println("\n\n\nCOMPUTER's turn: at "+ row + column);
                                }

                                userName[0] = "USER 1";// sets next turn for user1 --make compatible with checkresult
                            }
                            //printing after last change
                            output(valueAtPlace, userName);
                        }
                    }
                }
            }
            //getting position input until draw
            inputUser(valueAtPlace, userName, gameMode);
        }
                  
    }

    //ask for playing mode
   static void playingMode(String[] userName, int[] gameMode){

        System.out.println("\n\n  Choose mode:  0-->With friend"+
                             "\n                1-->against Computer");

        Scanner input = new Scanner(System.in);
        int choice = input.nextInt();//input of choice

        // remember! next arrray is USER 2 by default
        if(choice == 1){//if choice is Computer

            userName[1] = "Computer";//next array becomes computer mode--helps in user switching
            
            //randomly choose first user b/w human and computer
            int randomNumberToDecideFirstUser =  (int)(Math.random()*2);
            
            if(randomNumberToDecideFirstUser == 0){//user 1 turn if 0
                userName[0] = "USER 1";
            }
            else//computer turn if rest is 1
            {
                userName[0] = "Computer";
            }
            
            System.out.println("\n\n Choose Computer intelligence lvl:-"+
                               "\n       0-->Low"+
                               "\n       1-->Moderate"+
                               "\n       2-->High\t\t Tip-Enter wrong input to go back");
            gameMode[0] = input.nextInt();//select the intelligence lvl

            //gamemode shoud be 0, 1 or 2 -- send back to mode choice again in wrong input
            if((gameMode[0] != 0 && gameMode[0] != 1) && gameMode[0] != 2){
                System.out.println("Wrong Input!, Retry."+gameMode[0]);
                playingMode(userName, gameMode);
                return;
            }
            
        }
        //choice should be 0 or 1 --send back to mode choice again in wrong input
        else if (choice != 0 && choice != 1){
            if((gameMode[0] != 0 && gameMode[0] != 1) && gameMode[0] != 2){
                System.out.println("Wrong Input!, Retry."+gameMode[0]);
                playingMode(userName, gameMode);
                return;
            }
        }
    }

    //checking for possible win after printing ouput
     static void checkresult(String[][] valueAtPlace, String[]userName){

        for(int i=0; i<=2 ;i++){

            if(valueAtPlace[i][0]==valueAtPlace[i][1]
                &&valueAtPlace[i][1]==valueAtPlace[i][2]//horizontal checking
            ||valueAtPlace[0][i]==valueAtPlace[1][i]
                &&valueAtPlace[1][i]==valueAtPlace[2][i]//verticle checking
            ||valueAtPlace[0][0]==valueAtPlace[1][1]
                &&valueAtPlace[1][1]==valueAtPlace[2][2]//diaglonal '\' checking
            ||valueAtPlace[0][2]==valueAtPlace[1][1]
                &&valueAtPlace[1][1]==valueAtPlace[2][0])// diagonal '/' chenking
            {
                if(userName[0] == "USER 1")//means last input was of user 2 or pc
                {
                    userName[0] = "USER 2";//set winner name to user 2

                    if(userName[1] == "Computer"){
                        userName[0] = "Computer";// set winner to pc
                    }
                }
                else //means last input was of user 1
                    userName[0] = "USER 1";// sets user 1 as winner

            System.out.printf("\n\t(^o^) %s wins!!",userName[0]);
            System.exit(0);//program terminates here(win situation)
            } 
        }

        //checks for draw
        int iterations = 0;
        for(int i = 0; i<=2; i++){
            for(int j = 0; j<= 2; j++){

                //increase iterationn with evry x or o hit
                if(valueAtPlace[i][j] == "X" || valueAtPlace[i][j] == "O"){
                    iterations++;

                    //print draw on finding every box a x or o hit
                    if(iterations == 9){
                        System.out.println("\nMatch is draw X_X");
                        System.exit(0);
                    }
                }
                
            }
        }
    }


    //Computers turn  --difficult chooses according to gamemode
    static int computerChoicePosition(String[][]valueAtPlace, int[]gameMode){
       int position = -1;//defalut position null

       if(gameMode[0] == 2 || gameMode[0]==1 )//if gamemode is hard or moderate
        {   //checking for adjoining pair
            for(int i = 0; i<=2; i++)
            {
                //prefers O
                for(String previousValue = "O"; previousValue == "X" || previousValue == "O"; previousValue = "X")
                {

                    if(((valueAtPlace[i][0] == valueAtPlace[i][1]) && valueAtPlace[i][1] == previousValue) 
                    && (valueAtPlace[i][2] !="X" && valueAtPlace[i][2] != "O")){ 
                                                                        //checks for XX' '
                        position = 10*i+2;
                        break;
                    }
                    if(((valueAtPlace[i][1] == valueAtPlace[i][2]) && valueAtPlace[i][2] == previousValue) 
                    && (valueAtPlace[i][0] !="X" && valueAtPlace[i][0] != "O")){ 
                                                                        // checks for ' 'XX
                        position = 10*i+0;
                        break;
                    }
                    if(((valueAtPlace[i][0] == valueAtPlace[i][2]) && valueAtPlace[i][2] == previousValue) 
                    && (valueAtPlace[i][1] !="X" && valueAtPlace[i][1] != "O")){  
                                                                        //checks for X' 'X
                        position = 10*i+1;
                        break;  
                    }


                    if(((valueAtPlace[0][i] == valueAtPlace[1][i]) && valueAtPlace[1][i] == previousValue) 
                    && (valueAtPlace[2][i] !="X" && valueAtPlace[2][i] != "O")){ 
                                                                        //checks for  X
                        position = 10*2+i;                               //           X
                        break;                                           //          ' '
                    }
                    if(((valueAtPlace[1][i] == valueAtPlace[2][i]) && valueAtPlace[2][i] == previousValue) 
                    && (valueAtPlace[0][i] !="X" && valueAtPlace[0][i] != "O")){ 
                                                                        //checks for' '
                        position = 10*0+i;                               //           X
                        break;                                           //           X
                    }
                    if(((valueAtPlace[0][i] == valueAtPlace[2][i]) && (valueAtPlace[2][i] == previousValue)) 
                    && (valueAtPlace[1][i] !="X" && valueAtPlace[1][i] != "O")){
                                                                        //checks for  X
                        position = 10*1+i;                               //          ' '
                        break;                                           //           X  
                    }


                    if(((valueAtPlace[0][0] == valueAtPlace[1][1]) && valueAtPlace[1][1] == previousValue) 
                    && (valueAtPlace[2][2] !="X" && valueAtPlace[2][2] != "O")){  
                                                                        //checks for X
                        position = 10*2+2;                               //             X
                        break;                                           //               _
                    }
                    if(((valueAtPlace[2][2] == valueAtPlace[1][1]) && valueAtPlace[1][1] == previousValue) 
                    && (valueAtPlace[0][0] !="X" && valueAtPlace[0][0] != "O")){  
                                                                        //checks for _
                        position = 10*0+0;                               //             X
                        break;                                           //               X
                    }
                    if(((valueAtPlace[0][0] == valueAtPlace[2][2]) && valueAtPlace[2][2] == previousValue) 
                    && (valueAtPlace[1][1] !="X" && valueAtPlace[1][1] != "O")){  
                                                                        //checks for X
                        position = 10*1+1;                               //             _
                        break;                                           //               X
                    }


                    if(((valueAtPlace[0][2] == valueAtPlace[1][1]) && valueAtPlace[1][1] == previousValue) 
                        && (valueAtPlace[2][0] !="X" && valueAtPlace[2][0] != "O")){  
                                                                        //checks for     X
                        position = 10*2+0;                               //             X
                        break;                                           //           _
                    }
                    if(((valueAtPlace[2][0] == valueAtPlace[0][2]) && valueAtPlace[0][2] == previousValue) 
                    && (valueAtPlace[1][1] !="X" && valueAtPlace[1][1] != "O")){  
                                                                        //checks for     X
                        position = 10*1+1;                               //             _
                        break;                                           //           X
                    }
                    if(((valueAtPlace[1][1] == valueAtPlace[2][0]) && valueAtPlace[2][0] == previousValue) 
                    && (valueAtPlace[0][2] !="X" && valueAtPlace[0][2] != "O")){  
                                                                        //checks for     _
                        position = 10*1+1;                               //             X
                        break;                                           //           X
                    }

                    
                    //stops after o and x check
                    if(previousValue == "X"){
                        break;
                    }
                }

            
            }

            //position is still null and game mode is only in hard
            if(position == -1 && gameMode[0] != 1)
            {
                //occupies if center is empty
                if(valueAtPlace[1][1] == "11"){
                    position = 11;
                }
                //randomly occupies corner if center is not empty
                else{
                    int choice = (int)(Math.random()*4);
                    switch(choice){
                        case 0:
                            position = 00;
                            break;
                        case 1:
                            position = 02;
                            break;
                        case 2:
                            position = 20;
                            break;
                        case 3:
                            position = 22;
                            break;
                    }
                }
            }
        }
    
        if(position == -1)// computer's defauLt choice  --used in every mode
        {
            //randomly choose position
            int row = (int)(Math.random()*3);
            int column = (int)(Math.random()*3);
            position = row * 10 + column;
        }
        return position;
    }

    //position input code block
     static void inputUser(String[][]valueAtPlace, String[]userName, int[]gameMode){

        int row, column;

        //computer's turn
        if(userName[0] == "Computer"){
           int position = computerChoicePosition(valueAtPlace, gameMode);
           row = position/10;
           column = position % 10;  
        }
        //user 1 or 2's turn
        else
        {
            System.out.printf("\n\n\n%s turn: Enter the position no.>>",userName[0]);

            //asking for position
            Scanner input  = new Scanner(System.in);

            int position = input.nextInt();     //e.g.  69
             row = position / 10;            //      69/10 -> 6.9 -> 6(as int)
             column = position % 10;         //      69 % 10 -> 9
        }

        //avoid overwriting of already occupied
        if( (row != 0 && row != 1 && row != 2)              // 0 < row < 3 
            ||(column != 0 && column != 1 && column != 2)   // 0 < column < 3
            ||(valueAtPlace[row][column] == "X" || valueAtPlace[row][column] == "O"))
        {
            if(userName[0] != "Computer")//do not print if computer's turn
            {
                System.out.println("Wrong input, not allowed!");
            }
            
            //retry input
            inputUser(valueAtPlace, userName, gameMode);

            return;
        }

        //USER 1's changes
        if (userName[0] == "USER 1") {
            valueAtPlace[row][column] = "X";
            userName[0] = "USER 2";//set next user turn if not computer

            if(userName[1] == "Computer"){
                userName[0] = userName[1];// set computer next turn if next array in computer mode
            }

        } 
        //USER 2's or pc changes
        else {
            if(userName[0] == "Computer")// print only in computer's turn
            {
                System.out.println("\n\n\nCOMPUTER's turn: at "+ row + column);
            }

            valueAtPlace[row][column] = "O";
            userName[0] = "USER 1";     //set user 1 next turn
        }
        

        //printing changes
        output(valueAtPlace, userName);
      
    }

    //prints the game board after every change
     static void output(String[][] valueAtPlace, String[]userName){

        System.out.printf("\n\t\t%3c%3c%3c%3c",' ', '|', ' ', '|');

        System.out.printf("\n\t\t%3s%3c%3s%3c%3s",valueAtPlace[0][0],'|', valueAtPlace[0][1],'|', valueAtPlace[0][2]);

        System.out.printf("\n\t\t_____|_____|______");
        
        System.out.printf("\n\t\t%3c%3c%3c%3c",' ', '|', ' ', '|');

        System.out.printf("\n\t\t%3s%3c%3s%3c%3s",valueAtPlace[1][0],'|', valueAtPlace[1][1],'|', valueAtPlace[1][2]);

        System.out.printf("\n\t\t_____|_____|______");

        System.out.printf("\n\t\t%3c%3c%3c%3c",' ', '|', ' ', '|');

        System.out.printf("\n\t\t%3s%3c%3s%3c%3s",valueAtPlace[2][0],'|', valueAtPlace[2][1],'|', valueAtPlace[2][2]);

        System.out.printf("\n\t\t%3c%3c%3c%3c",' ', '|', ' ', '|');

        //check for the possible win after print
        checkresult(valueAtPlace, userName);

    }
}
