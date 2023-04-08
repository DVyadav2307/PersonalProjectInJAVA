import java.util.Scanner;

public class TicTacToeV5 {
    public static void main(String[] args){
        // default user 1
        String[] userName = {"USER 1"};
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
        output(valueAtPlace,userName);

        //switching between users' turn
        for(int j=1 ; j<=10 ;j++){

            if(j>=10)
            {//checking for draw
                System.out.printf("\n\nMatch is Draw! X_X");
                System.exit(0);//program terminates here(draw situation)
            }
              
            //getting position input
            inputUser(valueAtPlace, userName);
        }
                  
    }

    //checking for possible win after printing ouput
    public static void checkresult(String[][] valueAtPlace, String[]userName){

        for(int i=0; i<=2 ;i++){

            if(valueAtPlace[i][0]==valueAtPlace[i][1]&&valueAtPlace[i][1]==valueAtPlace[i][2]//horizontal checking
            ||valueAtPlace[0][i]==valueAtPlace[1][i]&&valueAtPlace[1][i]==valueAtPlace[2][i]//verticle checking
            ||valueAtPlace[0][0]==valueAtPlace[1][1]&&valueAtPlace[1][1]==valueAtPlace[2][2]//diaglonal '\' checking
            ||valueAtPlace[0][2]==valueAtPlace[1][1]&&valueAtPlace[1][1]==valueAtPlace[2][0])// diagonal '/' chenking
            {
                if(userName[0] == "USER 1")
                    userName[0] = "USER 2";
                else 
                    userName[0] = "USER 1";

            System.out.printf("\n\t(^o^) %s wins!!",userName[0]);
            System.exit(0);//program terminates here(win situation)
            }  
        }
    }

    //position input code block
    public static void inputUser(String[][]valueAtPlace, String[]userName){

        System.out.printf("\n\n%s: Enter the position no.>>",userName[0]);

        //asking for position
        Scanner input  = new Scanner(System.in);

        int position = input.nextInt();     //e.g.  69
        int row = position / 10;            //      69/10 -> 6.9 -> 6(as int)
        int column = position % 10;         //      69 % 10 -> 9

        //avoid overwriting of already occupied
        if( (row != 0 && row != 1 && row != 2)              // 0 < row < 3 
            ||(column != 0 && column != 1 && column != 2)   // 0 < column < 3
            ||(valueAtPlace[row][column] == "X" || valueAtPlace[row][column] == "O"))
        {

            System.out.println("Wrong input, not allowed!");
            inputUser(valueAtPlace, userName);
            return;
        }

        //USER 1's turn
        if (userName[0] == "USER 1") {
            valueAtPlace[row][column] = "X";
            userName[0] = "USER 2";     //setting for user 2
        } 
        //USER 2's turn
        else {
            valueAtPlace[row][column] = "O";
            userName[0] = "USER 1";     //setting for user 1
        }
        

        //printing changes
        output(valueAtPlace, userName);
      
    }

    //prints the game board after every change
    public static void output(String[][] valueAtPlace, String[]userName){

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
