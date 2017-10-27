
package maze_game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;


public class Maze_Panel extends JPanel implements KeyListener{
    
    @Override
    public void paint (Graphics g){
        Maze[0][0]=1;
        g.clearRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.black);
        for(int i=0;i<11;i++)
            for(int j=0; j<10;j++)
                if(Maze_Border_V[i][j]==1)
                    g.fillRect(i*20+30, j*20+30, 2, 20);
        for(int i=0;i<10;i++)
            for(int j=0; j<11;j++)
                if(Maze_Border_H[i][j]==1)
                    g.fillRect(i*20+30, j*20+30, 20, 2);
        
        for (int i=0;i<10;i++)
            for(int j=0;j<10;j++)
            {
                g.setColor(Color.red);
                if(Maze[i][j]==1)
                    g.fill3DRect(i*20+33, j*20+33, 15, 15, true);
            }
        
        g.setFont(new Font("GIGY", Font.ITALIC, 15));
        if (Maze[9][9]== 1 && dx== 1 && dy== 0)
            g.drawString("You Win", 200, 250);
        
    }
    
    public Maze_Panel(){
        addKeyListener(this);
        setFocusable(true);
        Thread TimerRun = new Thread(Timer);
        TimerRun.start();
    }
    
    int dx , dy;
    int x = 0 , y = 0 ;
    
    public int[][] get_key(int key ,int x ,int y){
        int[][] a = new int [10][10];
        
        if ((key == 4||key == 40) && x>0 ) {
            System.out.println((x-1)+":"+y+ " : "+ Maze_Border_V[x][y]);
            if(Maze_Border_V[x][y]==0 && Maze[x-1][y]==0){
                System.out.println("Left");
                Maze[x-1][y]=1;
                dx=-1;
                dy=0;
                x--;
            }
            else if(Maze_Border_V[x][y]==0 && Maze[x-1][y]==1){
                System.out.println("Left_Back");
                Maze[x][y]=2;
                dx=-1;
                dy=0;
                x--;
            }
        }
        if ((key == 6||key == 60) && x<9) {
            System.out.println((x+1)+":"+y+ " : "+ Maze_Border_V[x+1][y]);
            if(Maze_Border_V[x+1][y]==0 && Maze[x+1][y]==0){
                System.out.println("Right");
                Maze[x+1][y]=1;
                dx=1;
                dy=0;
                x++;
            }
            else if(Maze_Border_V[x+1][y]==0 && Maze[x+1][y]==1){
                System.out.println("Right_Back");
                Maze[x][y]=2;
                dx=1;
                dy=0;
                x++;
            }
        }
        if ((key == 8||key == 80) && y>0) {
            System.out.println(x+":"+(y-1)+ " : "+ Maze_Border_H[x][y]);
            if(Maze_Border_H[x][y]==0 && Maze[x][y-1]==0){
                System.out.println("Up");
                Maze[x][y-1]=1;
                dx=0;
                dy=-1;
                y--;
            }
            else if(Maze_Border_H[x][y]==0 && Maze[x][y-1]==1){
                System.out.println("Up_Back");
                Maze[x][y]=2;
                dx=0;
                dy=-1;
                y--;
            }
        }
        if ((key == 2||key == 20) && y<9) {
            System.out.println(x+":"+(y+1)+ " : "+ Maze_Border_H[x][y+1]);
            if(Maze_Border_H[x][y+1]==0 && Maze[x][y+1]==0){
                System.out.println("Down");
                Maze[x][y+1]=1;
                dx=0;
                dy=+1;
                y++;
            }
            else if(Maze_Border_H[x][y+1]==0 && Maze[x][y+1]==1){
                System.out.println("Down_Back");
                Maze[x][y]=2;
                dx=0;
                dy=+1;
                y++;
            }
        }
        a[x][y]=1;
        return a;
    }
    
    
    private int Way (){
        int a=0;
    try{
            if(Maze_Border_H[x][y+1]==0 && Maze[x][y+1]==0 && y<9)
                return 2;
            else if (Maze_Border_V[x][y]==0 && Maze[x-1][y]==0 && x>0)
                return 4;
             else if (Maze_Border_V[x+1][y]==0 && Maze[x+1][y]==0 && x<9)
                return 6;
            else if (Maze_Border_H[x][y]==0 && Maze[x][y-1]==0 && y>0)
                return 8;
            else if (Maze_Border_V[x][y]==0 && Maze[x-1][y]==1 && x>0)
                return 40;
            else if (Maze_Border_V[x+1][y]==0 && Maze[x+1][y]==1 && x<9)
                return 60;
            else if (Maze_Border_H[x][y]==0 && Maze[x][y-1]==1 && y>0)
                return 80;
            else if(Maze_Border_H[x][y+1]==0 && Maze[x][y+1]==1 && y<9)
                return 20;
            else
                return 0;
        }
    catch (ArrayIndexOutOfBoundsException e){
            return 0;
        }
            
            
    }
        
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
//        int [][] a = new int [10][10];
//        a = get_key(e.getKeyCode(), x, y);
//            for(int i=0;i<10;i++)
//                for(int j=0;j<10;j++)
//                    if (a[i][j]==1){
//                        x=i;
//                        y=j;
//                    }
//        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    
    Runnable Timer = new Runnable() {
        public void run() {
            try {
                while (true) {
                    
                    int key = Way();
                    int [][] a = new int [10][10];
                    a = get_key(key, x, y);
                    for(int i=0;i<10;i++)
                        for(int j=0;j<10;j++)
                            if (a[i][j]==1){
                                x=i;
                                y=j;
                            }
                    repaint();
                    Thread.sleep(100L);
                    
                }
            } catch (InterruptedException iex) {}
        }
    };
    
    int [][] Maze_Border_V = 
        { {0,1,1,1,1,1,1,1,1,1},
          {1,0,0,0,0,0,0,0,1,0},
          {0,1,0,1,1,1,0,1,0,0},
          {1,1,1,1,1,0,0,0,1,1},
          {0,0,0,1,1,0,0,1,1,0},
          {0,0,1,0,1,1,1,1,1,1},
          {1,0,0,1,1,1,0,0,1,1},
          {0,0,1,1,0,1,1,0,1,0},
          {1,1,1,0,1,1,0,0,1,1},
          {0,1,0,1,0,0,1,0,1,0},
          {1,1,1,1,1,1,1,1,1,0}};
    int [][] Maze_Border_H = 
        { {1,0,1,0,1,0,1,0,1,0,1},
          {1,0,1,1,0,1,1,1,0,1,1},
          {1,0,0,0,0,0,1,1,0,0,1},
          {1,0,1,0,0,0,1,1,0,0,1},
          {1,1,0,1,0,0,0,1,0,0,1},
          {1,1,1,0,0,0,0,1,0,0,1},
          {1,1,0,0,0,1,0,1,1,0,1},
          {1,0,0,0,1,0,0,1,0,0,1},
          {1,0,1,0,1,1,0,1,0,0,1},
          {1,0,0,0,0,1,0,0,1,0,1}};
    
    int [][] Maze = new int [10][10];
    
    
    
}
