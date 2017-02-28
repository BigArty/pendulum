import java.util.concurrent.TimeUnit;

class pendulum2 extends Thread{
    static doblCoord v = new doblCoord();
    static doblCoord a = new doblCoord();
    static doblCoord v2 = new doblCoord();
    static doblCoord a2 = new doblCoord();
    static double l = 1;
    private static double l2 = 1;
    static doblCoord pnt = new doblCoord(0, -l);
    static doblCoord pnt2 = new doblCoord(0,-2*l);
    private static double g = 10;
    private static double T2 = 0;
    private static double T1 = 0;
    private static final double D = 0.00000001;
    private static final double K = 1000000;

    private static doblCoord aHelp = new doblCoord();
    static final Object sync = new Object();
    pendulum2(){
        a = A3(1, a);
        a2 = A3(2, a2);
        v.x = 0;
        v.y = 0;
        v2.x = 0.9999;
        v2.y = 0;
        start();
    }

    @Override
    public void run() {
        double t = 0.00000006;
        double t1 = t / 5;
        int b1;
        synchronized (pendulum.sync) {
            b1 = pendulum.b;
        }
        while (true) {
            if (b1 == 0) {
                T2 = -(l2 - 1) * K / l2;
                T1 = -(l - 1) * K / l;
                a = A3(1, a);
                a2 = A3(2, a2);
                mul(t, a);
                v = sum(v, aHelp);
                mul(t, a2);
                v2 = sum(v2, aHelp);
                mul(t, v);

                pnt = sum(pnt, aHelp);
                mul(t, v2);
                pnt2 = sum(pnt2, aHelp);

                l = Math.sqrt(pnt.x * pnt.x + pnt.y * pnt.y);
                l2 = Math.sqrt((pnt.x - pnt2.x) * (pnt.x - pnt2.x) + (pnt.y - pnt2.y) * (pnt.y - pnt2.y));
                synchronized (pendulum.sync) {
                    b1 = pendulum.b;
                }
            } else {
                synchronized (pendulum.sync) {
                    b1 = pendulum.b;
                }
                if (b1 == 1) {
                    T2 = -(l2 - 1) * K / l2;
                    T1 = -(l - 1) * K / l;
                    a = A3(1, a);
                    a2 = A3(2, a2);
                    mul(t1, a);
                    v = sum(v, aHelp);
                    mul(t1, a2);
                    v2 = sum(v2, aHelp);
                    mul(t1, v);
                    pnt = sum(pnt, aHelp);
                    mul(t1, v2);
                    pnt2 = sum(pnt2, aHelp);
                    l = Math.sqrt(pnt.x * pnt.x + pnt.y * pnt.y);
                    l2 = Math.sqrt((pnt.x - pnt2.x) * (pnt.x - pnt2.x) + (pnt.y - pnt2.y) * (pnt.y - pnt2.y));
                } else {
                    try {
                        TimeUnit.MICROSECONDS.sleep(2000);
                    } catch (InterruptedException ignored) {
                    }
                }
            }
        }
    }

    private static doblCoord A3(int point, doblCoord at) {
        if (point == 1) {
            at.y = T1 * pnt.y + T2 * (pnt.y - pnt2.y) + g - v.y * Math.abs(v.y) * D;
            at.x = T1 * pnt.x + T2 * (pnt.x - pnt2.x) - v.x * Math.abs(v.x) * D;
        } else {
            at.y = T2 * (-pnt.y + pnt2.y) + g - v2.y * Math.abs(v2.y) * D;
            at.x = T2 * (-pnt.x + pnt2.x) - v2.x * Math.abs(v2.x) * D;
        }
        return at;
    }

    private static void mul(double t, doblCoord v) {
        aHelp.x = v.x * t;
        aHelp.y = v.y * t;
    }

    private static doblCoord sum(doblCoord o1, doblCoord o2) {
        o1.x = o1.x + o2.x;
        o1.y = o1.y + o2.y;
        return o1;
    }
}
