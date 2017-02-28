import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

class MouseL implements MouseListener {


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1) {
            synchronized (pendulum.sync) {
                pendulum.b=(pendulum.b+1)%3;
                pendulum.sync.notify();
            }
        }
        else{
            if(e.getButton() == MouseEvent.BUTTON3) {
                synchronized (pendulum.sync) {
                    if(pendulum.b==0){pendulum.b+=3;}
                    pendulum.b--;
                    pendulum.sync.notify();
                }
            }
            else{
                if(e.getButton() == MouseEvent.BUTTON2) {
                    synchronized (ImgDrower.sync) {
                        ImgDrower.img=new BufferedImage(Drower.f.getWidth(), Drower.f.getHeight(), BufferedImage.TYPE_INT_ARGB);
                        ImgDrower.g= (Graphics2D) ImgDrower.img.getGraphics();
                        BasicStroke pen1 = new BasicStroke(2); //толщина линии 20
                        ImgDrower.g.setStroke(pen1);
                    }
                }
            }
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
