import javax.swing.*;
import java.awt.*;
import java.awt.image.*;

class Canvas extends JComponent {
    public void update(Graphics g){
        paint(g);

    }
    private int m=Drower.m;
    private int r=Drower.r,e=Drower.e;
    private float[] scales = { 1f, 1f, 1f, 0.6f };
    private float[] offsets = new float[4];
    private RescaleOp rop = new RescaleOp(scales, offsets, null);
    public void paintComponent(Graphics a){
        setOpaque(false);
        //super.paintComponents(a);

        Graphics2D g=(Graphics2D)a;
        g.setRenderingHint ( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );
        synchronized (ImgDrower.sync) {
            g.drawImage(ImgDrower.img, rop, 0, 0);
        }
        g.setColor(Color.black);

        int x=(int) (pendulum.pnt.x*m);
        int y=(int) (pendulum.pnt.y*m);
        int x2=(int) (pendulum.pnt2.x*m);
        int y2=(int) (pendulum.pnt2.y*m);
        g.drawString(pendulum.v.x+"",20,200);
        g.drawString(""+pendulum.v.y,170,200);
        g.drawString(pendulum.v2.x+"",20,300);
        g.drawString(""+pendulum.v2.y,170,300);
        g.drawString(pendulum.a.x+"",20,400);
        g.drawString(""+pendulum.a.y,170,400);
        g.drawString(pendulum.a2.x+"",20,500);
        g.drawString(""+pendulum.a2.y,170,500);
        g.drawLine((r), (e), x+(r), y+(e));
        g.drawLine(x2+(r), y2+(e), x+(r), y+(e));
        g.fillOval(x-5+(r),y-5+(e),10,10);
        g.fillOval(x2-5+(r),y2-5+(e),10,10);
        x=(int) (pendulum2.pnt.x*m);
        y=(int) (pendulum2.pnt.y*m);
        x2=(int) (pendulum2.pnt2.x*m);
        y2=(int) (pendulum2.pnt2.y*m);
        g.drawLine((r), (e), x+(r), y+(e));
        g.drawLine(x2+(r), y2+(e), x+(r), y+(e));
        g.fillOval(x-5+(r),y-5+(e),10,10);
        g.fillOval(x2-5+(r),y2-5+(e),10,10);
        g.dispose();
    }
}

