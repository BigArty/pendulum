import javax.swing.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;

class Drower extends  Thread{

    Drower(){


        start();
    }

    static JFrame f = new JFrame("Pendulum");

    static int m=120;
    static int r=700,e=350;
    @Override
    public void run() {
        f.setLocation(0, 0);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setMinimumSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().height + 1, Toolkit.getDefaultToolkit().getScreenSize().height));
        f.setExtendedState(JFrame.MAXIMIZED_BOTH);
        Canvas c = new Canvas();
        f.setVisible(true);
        new ImgDrower();
        while(true){
            c.repaint();
            f.add(c);
            f.repaint();
            try {
                TimeUnit.MICROSECONDS.sleep(40000);
            } catch (InterruptedException ignored) {
            }
        }
    }
}
