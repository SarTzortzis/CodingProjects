import java.util.Scanner;

class knightTour
{
    public int N,posX,posY;
    public static final int movesX[] = {1, 2, 2, 1, -1, -2, -2, -1};
    public static final int movesY[] = {2, 1, -1, -2, -2, -1, 1, 2};
    
    public knightTour(int N,int posX,int posY){
        this.N=N;
        this.posX=posX;
        this.posY=posY;
    }
  
    /* Returns how many are the unvisited 
    tiles which are neighbours of x,y*/
    public int getNeighbours(int a[], int x, int y) 
    {
        int count = 0;
        for (int i = 0; i < 8; ++i){
            if (notVisited(a, (x + movesX[i]), (y + movesY[i]))){
                count++;
            }
        }
        return count;
    }
  
    Tile nextMove(int a[], Tile tile) 
    {

        int minPos = -1;
        int c;
        int lessMoves = (8 + 1);
        int nextX;
        int nextY;
  
        /* try all N neighbours of x,y starting from a random one */
        int start = (int)Math.floor(Math.random() * (7));

        for (int count = 0; count < 8;count++)
        {
            int i = (start + count) % 8;
            nextX = tile.x + movesX[i];
            nextY = tile.y + movesY[i];
            if ((notVisited(a, nextX, nextY)) && (c = getNeighbours(a, nextX, nextY)) < lessMoves)
            {
                minPos = i;
                lessMoves = c;
            }
        }
  
        // IF we could not find a next cell
        if (minPos == -1){
            return null;
        }
  
        // save coordinates of next point
        nextX = tile.x + movesX[minPos];
        nextY = tile.y + movesY[minPos];
  
        // next move
        a[nextY * N + nextX] = a[(tile.y)*N+(tile.x)] + 1;
  
        // update next point
        tile.x = nextX;
        tile.y = nextY;
  
        return tile;
    }
    /*This function checks if the move is valid*/  
    boolean isValid(int x, int y)
    {
        return ((x >= 0 && y >= 0) && (x < N && y < N));
    }
    /* Checks if knight can visit a tile*/
    boolean notVisited(int a[], int x, int y) 
    {
        //a[y * N +x] represents the tile in a chessboard
        //(a[y * N + x] < 0 => True if we have not visited this tile before.

        return (isValid(x, y)) && (a[y * N + x] < 0);
    }
  
    void printChessboard(int a[])
    {
        for (int i = 0; i < N; ++i) 
        {
            for (int j = 0; j < N; ++j)
                System.out.printf("%d\t", a[j * N + i]);
            System.out.printf("\n");
        }
        
    }
  
    /* checks its neighbouring sqaures */
    boolean neighbour(int x, int y, int xx, int yy){
        for (int i = 0; i < 8; ++i){
            if (((x + movesX[i]) == xx) && ((y + movesY[i]) == yy)){
                //System.out.println("Test3");
                return true;
            }
        }
        return false;
    }
  
    boolean solve(){
        // All degrees starts with -1
        int a[] = new int[N * N];
        for (int i = 0; i < N * N; ++i){
            a[i] = -1;
        }
  
        // Starting position of the knight
        int sx = this.posX;
        int sy = this.posY;
  

        Tile tile = new Tile(sx, sy);
  

        //starting tile=
        a[tile.y * N + tile.x] = 0; 
  
       
        // Warnsdorff's heuristic
        Tile newT = null;
        for (int i = 0; i < N * N - 1; i++)
        {
            newT = nextMove(a, tile);
            if (newT == null){
                return false;
            }
        }     
        printChessboard(a);
        return true;
    }
  
    
    public static void main(String[] args){
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
        // While we don't get a solution
        while (!new knightTour(dim,posY,posX).solve())
        {
            ;
        }
    }
}
// Tile helps us represent position on chessboard
class Tile {
    int x;
    int y;
  
    public Tile(int x, int y){
        this.x = x;
        this.y = y;
    }
}