import java.util.Scanner;

import org.w3c.dom.UserDataHandler;
public class TicTacToe {
    public static void main(String[] args){
        String[] userName = new String[1];
        String[][] valueAtPlace = {{"00","01","02"},
                                   {"10","11","12"},
                                   {"20","21","22"}};

       

        output(valueAtPlace,userName);

        for(int j=1 ; j<=10 ;j++){
            if(j>=10)
               {
                System.out.printf("\nMatch is Draw!");
                System.exit(0);
               }
            if(j%2!=0)
                inputUser1(valueAtPlace, userName);
            else
                inputUser2(valueAtPlace, userName);
            
        }
                  
    }

    public static void checkresult(String[][] valueAtPlace, String[]userName){
        for(int i=0; i<=2 ;i++){
            char winner = 'n';
            if(valueAtPlace[i][0]==valueAtPlace[i][1]&&valueAtPlace[i][1]==valueAtPlace[i][2]
            ||valueAtPlace[0][i]==valueAtPlace[1][i]&&valueAtPlace[1][i]==valueAtPlace[2][i]
            ||valueAtPlace[0][0]==valueAtPlace[1][1]&&valueAtPlace[1][1]==valueAtPlace[2][2]
            ||valueAtPlace[0][2]==valueAtPlace[1][1]&&valueAtPlace[1][1]==valueAtPlace[2][0])
            {
                System.out.printf("\n%s wins!!",userName[0]);
                System.exit(0);
            }
            
        }
    }

    public static void inputUser1(String[][]valueAtPlace, String[]userName){
        userName[0] = "USER 1";
        System.out.printf("\n\n%s: Enter the position no.(with space)>>",userName[0]);
        Scanner input  = new Scanner(System.in);
        int row = input.nextInt();
        int column = input.nextInt();

        valueAtPlace[row][column] = "X";

        output(valueAtPlace, userName);
      
    }

    public static void inputUser2(String[][]valueAtPlace, String[]userName){
        userName[0] = "USER 2";
        System.out.printf("\n\n%s: Enter the position no.(with space)>>",userName[0]);
        Scanner input  = new Scanner(System.in);
        int row = input.nextInt();
        int column = input.nextInt();

        valueAtPlace[row][column] = "O";

        output(valueAtPlace, userName);
      
    }

    public static void output(String[][] valueAtPlace, String[]userName){
        System.out.printf("\n%3c%3c%3c%3c",' ', '|', ' ', '|');

        System.out.printf("\n%3s%3c%3s%3c%3s",valueAtPlace[0][0],'|', valueAtPlace[0][1],'|', valueAtPlace[0][2]);

        System.out.printf("\n_____|_____|______");
        
        System.out.printf("\n%3c%3c%3c%3c",' ', '|', ' ', '|');

        System.out.printf("\n%3s%3c%3s%3c%3s",valueAtPlace[1][0],'|', valueAtPlace[1][1],'|', valueAtPlace[1][2]);

        System.out.printf("\n_____|_____|______");

        System.out.printf("\n%3c%3c%3c%3c",' ', '|', ' ', '|');

        System.out.printf("\n%3s%3c%3s%3c%3s",valueAtPlace[2][0],'|', valueAtPlace[2][1],'|', valueAtPlace[2][2]);

        System.out.printf("\n%3c%3c%3c%3c",' ', '|', ' ', '|');

        checkresult(valueAtPlace, userName);

    }
}
