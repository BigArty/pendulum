import java.awt.*;
import java.awt.image.BufferedImage;

class ImgDrower extends Thread {
    ImgDrower() {
        start();
        img = new BufferedImage(Drower.f.getWidth(), Drower.f.getHeight(), BufferedImage.TYPE_INT_ARGB);
        g = (Graphics2D) img.getGraphics();
        BasicStroke pen1 = new BasicStroke(2); //толщина линии 20
        g.setStroke(pen1);
    }

    static final Object sync = new Object();
    private static int m = Drower.m;
    private static int r = Drower.r, e = Drower.e;
    static BufferedImage img;
    static Graphics2D g;

    @Override
    public void run() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException ignored) {
        }
        int b;
        int x, x1, y, y1;
        x1 = (int) (pendulum.pnt2.x * m);
        int x2 = (int) (pendulum2.pnt2.x * m);
        int y2 = (int) (pendulum2.pnt2.y * m);
        y1 = (int) (pendulum.pnt2.y * m);
        while (true) {
            try {
                Thread.sleep(0,1);
            } catch (InterruptedException ignored) {
            }
            g.setColor(Color.RED);
            synchronized (pendulum.sync) {
                b = pendulum.b;
                x = (int) (pendulum.pnt2.x * m);
                y = (int) (pendulum.pnt2.y * m);
            }
            if (b == 2) {
                synchronized (pendulum.sync) {
                    try {
                        pendulum.sync.wait(2000);
                    } catch (InterruptedException ignored) {
                    }
                }
            }
            synchronized (sync) {
                g.drawLine((x) + (r), (y) + (e), x1 + (r), y1 + (e));
            }
            x1 = x;
            y1 = y;
            synchronized (pendulum2.sync) {
                x = (int) (pendulum2.pnt2.x * m); //d
                y = (int) (pendulum2.pnt2.y * m);
            }
            g.setColor(Color.blue);
            synchronized (sync) {
                g.drawLine((x) + (r), (y) + (e), x2 + (r), y2 + (e));
            }
            x2 = x;
            y2 = y;
        }
    }
}
