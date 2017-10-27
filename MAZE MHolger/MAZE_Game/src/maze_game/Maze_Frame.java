
package maze_game;

import javax.swing.JFrame;


public class Maze_Frame extends JFrame{
    
    public Maze_Frame (){
        setTitle("Maze");
        setResizable(false);
        setVisible(true);
        setLocationRelativeTo(null);
        setSize(300, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(new Maze_Panel());
        
    }
    
    public static void main(String[] args) {
        new Maze_Frame();
    }
    
}
