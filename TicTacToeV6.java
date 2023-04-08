import java.util.Scanner;

public class TicTacToeV6 {
    public static void main(String[] args){
        // default user 1, user 2 if not in computer mode
        String[] userName = {"USER 1","USER 2"};
        String[][] valueAtPlace = {
                                    {"00","01","02"},
                                    {"10","11","12"},
                                    {"20","21","22"}
        };

        //first time printing of board
        System.out.println( "\n\n\n\t\tTIC TAC TOE"+
                                "\n\t\t===========\n"+
                            "\nHow to play?:"+
                            "\n           ~Two players play against each other on a 3x3 board."+
                            "\n           ~User 1 will use crosses 'X'"+
                            "\n           ~User 2 will use noughts 'o'"+
                            "\n           ~First player to align 3 identical symbols (horizontally, vertically, or diagonally),"+
                            "\n    wins the game.");

        //ask for playing mode
        playingMode(userName);

        //print the changes
        output(valueAtPlace,userName);

        //checking for draw
        for(int j=1 ; j<=10 ;j++){

            if(j>=10)
            {
                System.out.printf("\n\nMatch is Draw! X_X");
                System.exit(0);//program terminates here(draw situation)
            }
              
            //getting position input until draw
            inputUser(valueAtPlace, userName);
        }
                  
    }

    //ask for playing mode
   static void playingMode(String[] userName){

        System.out.println("\n\n  Choose mode:  0-->With friend"+
                             "\n                1-->against Computer");

        Scanner input = new Scanner(System.in);
        int choice = input.nextInt();

        // next arrray is USER 2 by default
        if(choice == 1){
            userName[1] = "Computer";//next array set to computer mode
        }
        //input should be 0 or 1
        else if (choice != 0 || choice != 1){
            System.out.println("Wrong Input!, Retry.");
            playingMode(userName);
            return;
        }
    }

    //checking for possible win after printing ouput
     static void checkresult(String[][] valueAtPlace, String[]userName){

        for(int i=0; i<=2 ;i++){

            if(valueAtPlace[i][0]==valueAtPlace[i][1]&&valueAtPlace[i][1]==valueAtPlace[i][2]//horizontal checking
            ||valueAtPlace[0][i]==valueAtPlace[1][i]&&valueAtPlace[1][i]==valueAtPlace[2][i]//verticle checking
            ||valueAtPlace[0][0]==valueAtPlace[1][1]&&valueAtPlace[1][1]==valueAtPlace[2][2]//diaglonal '\' checking
            ||valueAtPlace[0][2]==valueAtPlace[1][1]&&valueAtPlace[1][1]==valueAtPlace[2][0])// diagonal '/' chenking
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
    }

    //position input code block
     static void inputUser(String[][]valueAtPlace, String[]userName){

        int row, column;

        //computer's turn
        if(userName[0] == "Computer"){
            row = (int)(Math.random()*3);
            column = (int)(Math.random()*3);
            
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
            inputUser(valueAtPlace, userName);

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
