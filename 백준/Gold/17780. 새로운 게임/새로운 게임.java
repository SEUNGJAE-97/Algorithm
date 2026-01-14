import java.io.*;
import java.util.*;

public class Main {
	static class pieces{
        int x, y, direction;
        public pieces(int x, int y, int direction) {
            this.x = x;
            this.y = y;
            this.direction = direction;
        }
    }
	
	static int N,K;
	static int[] dx = {1, -1, 0, 0};	//좌우상하 x값 변경
    static int[] dy = {0, 0, -1, 1};
    static ArrayList<Integer>[][] board;	//체스판에 쌓인 말 정보 저장 배열
    static pieces[] chessPieces;	//말에 대한 정보 저장 배열
    static int[][] chessBoard;	
    
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        board = new ArrayList[N][N];
        chessBoard = new int[N][N];
        chessPieces = new pieces[K];
        
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++)
                chessBoard[i][j] = Integer.parseInt(st.nextToken());
        }
       
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++)
                board[i][j] = new ArrayList();
        }
        //말에 대한 정보 저장
        for(int i=0;i<K;i++){
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken())-1;
            int x = Integer.parseInt(st.nextToken())-1;
            int direction = Integer.parseInt(st.nextToken());
            chessPieces[i] = new pieces(x,y,direction-1);
            board[y][x].add(i);
        }
        int result = gameStart();	
        System.out.println(result);
	}
	
	
	private static int gameStart() {
		int turn = 0;
		
		while(true){
            turn++;
            if(turn>1000)		//턴이 1000을 넘어갔을 때
                return -1;
            for(int i=0;i<K;i++){
                int x = chessPieces[i].x;
                int y = chessPieces[i].y;
                int direction = chessPieces[i].direction;
                if(board[y][x].get(0) != i)	//해당 칸 첫 번째 말이 아닌 경우 이동 X
                    continue;
                //해당 방향으로 이동
                int tempX = x + dx[direction];
                int tempY = y + dy[direction];
                if(outBoard(tempX,tempY) || chessBoard[tempY][tempX]==2){//파란 칸일 때
                    int tempDirection = directionChange(direction);	//방향 바꾸기
                    chessPieces[i].direction = tempDirection;
                    //방향 바꾸고 한 칸 이동해보기
                    tempX = x + dx[tempDirection];
                    tempY = y + dy[tempDirection];
                    //이동한 칸이 파란색일 때, 이동 X
                    if(outBoard(tempX,tempY) || chessBoard[tempY][tempX]==2){
                        continue;
                    }else if(chessBoard[tempY][tempX] == 1){	//이동한 칸이 빨간 칸일 때
                        redMove(x,y,tempX,tempY);
                    }else		//이동한 칸이 흰 칸일 때
                        whiteMove(x,y,tempX,tempY);

                }else if(chessBoard[tempY][tempX] == 1){	//빨간칸일 때
                    redMove(x,y,tempX,tempY);
                }else{		//하얀칸일 때
                    whiteMove(x,y,tempX,tempY);
                }
                if(board[tempY][tempX].size() >= 4)	//이동한 칸에 말이 4개 이상 쌓일 때
                    return turn;	//현재 턴 반환
            }
        }
	}
	static void redMove(int x, int y, int tempX, int tempY){
        for(int i=board[y][x].size()-1;i>=0;i--){
            int temp = board[y][x].get(i);
            board[tempY][tempX].add(temp);
            chessPieces[temp].x = tempX;
            chessPieces[temp].y = tempY;
        }
        board[y][x].clear();
    }
    
    static void  whiteMove(int x, int y, int tempX, int tempY){
        for(int i=0;i<board[y][x].size();i++){
            int temp = board[y][x].get(i);
            board[tempY][tempX].add(temp);
            chessPieces[temp].x = tempX;
            chessPieces[temp].y = tempY;
        }
        board[y][x].clear();
    }
    
    static int directionChange(int direction){
        if(direction==0)	//좌
            return 1;
        else if(direction==1)	//우
            return 0;
        else if(direction==2)	//상
            return 3;
        else		//하
            return 2;
    }
    static boolean outBoard(int x, int y){
        return x < 0 || y < 0 || x >= N || y >= N;
    }
	
}
