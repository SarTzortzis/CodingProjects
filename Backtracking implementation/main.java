import java.util.Scanner;

class knightTourProblem {
    int N;
    int posX;
    int posY;
    
    /* Object constructor in order to 
    know 
    -the size (N) of chessboard 
    -the starting position of Knight (X,Y) */

    public knightTourProblem(int N,int posX,int posY){
        this.N=N;
        this.posX=posX;
        this.posY=posY;
    }

    boolean run()
    {
        /*initialising our variable
          - Horizontally = valid moves in horizontal Axis
          - Vertically = valid moves in vertical Axis
          - For example, horizontally[x] and vertically[x] 
            is a valid knight's move
          - chessboard = 2 dimension Array that represents the chessboard
        */
       
        int horizontally[] = {2, 1, -1, -2, -2, -1, 1, 2 };
        int vertically[] = {1, 2, 2, 1, -1, -2, -2, -1 };
        int chessboard[][] = new int[N][N];
 
         //Chessboard initialisation 
        for (int i = 0; i<N; i++)
        {
            for (int j = 0; j < N; j++)
            {
                chessboard[i][j] = -1;
            }
        }
        //initialising the position that user selected
        chessboard[posX][posY] = 0;
        

        
        if (!solveRec(posX, posY, chessboard, 1, horizontally, vertically)) {
            System.out.println("Solution does not exist");
            return false;
        }
        else
            printChessboard(chessboard);
        return true;
    }
 
    /* SolveRec is a recursive function that tries any 
    possible move for the knight in order to fullfil the path.
    If a path isnot valid we use backtrack in order to try a different path from the 
    previous block on chessboard*/

    boolean solveRec(int x,int y,int chessboard[][],int moveNumber,int horiMoves[],int verMoves[])
    {
        int k, next_x, next_y;
        if (moveNumber == N * N)
            return true;
 
        
        for (k = 0; k < horiMoves.length; k++) {
            next_x = x + horiMoves[k];
            next_y = y + verMoves[k];

            if ( next_x >= 0 && next_y >= 0 && next_x < this.N && next_y < this.N && chessboard[next_x][next_y] == -1) {
                chessboard[next_x][next_y] = moveNumber;
                if (solveRec(next_x, next_y,chessboard,moveNumber + 1, horiMoves, verMoves)){
                    return true;
                }
             
                
                 else{ 
                    chessboard[next_x][next_y] = -1; // backtracking
                }
            }
        }
 
        return false;
    }

    void printChessboard(int chessboard[][])
    {
    for (int x = 0; x < N; x++) {
        if(N<=6)
        System.out.println("-------------------------------------");
        else if (N<=7)
        System.out.println("--------------------------------------------");
        else if (N<=8)
        System.out.println("-------------------------------------------------");
        for (int y = 0; y < N; y++) {
            if(chessboard[x][y]<=9){
                System.out.print("|  " + chessboard[x][y] + "  |");
            }
            else{
            System.out.print("| " + chessboard[x][y] + " |");
            }
        }
        System.out.println();
    }
        if(N<=6)
        System.out.println("-------------------------------------");
        else if (N<=7)
        System.out.println("--------------------------------------------");
        else if (N<=8)
        System.out.println("-------------------------------------------------");
    }
 
    
    public static void main(String args[])
    {
        System.out.println("\n\n\n\n\n\n  ~~~~~~~~~ The chessboard will be NxN ~~~~~~~~~  \n\n\n\n\n\n");

        Scanner objx = new Scanner(System.in);  
        System.out.println("Enter N");
        String x = objx.nextLine();

        Scanner objPosx = new Scanner(System.in);
        System.out.println("Enter Starting position X ");
        String posx = objPosx.nextLine();
      
        

        Scanner objPosy = new Scanner(System.in);
        System.out.println("Enter Starting position Y ");
        String posy = objPosy.nextLine();
        
        int dim=Integer.parseInt(x);
        int posX=Integer.parseInt(posx);
        int posY=Integer.parseInt(posy);

        knightTourProblem test = new knightTourProblem(dim,posX,posY);
        test.run();
    }
}