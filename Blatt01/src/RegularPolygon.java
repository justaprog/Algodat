// Diese Klasse implementiert nur *zentrierte* reguläre Polygone, also mit midpoint = (0, 0).
import java.lang.Math;
import java.util.Arrays;

public class RegularPolygon extends ConvexPolygon {

    // TODO
    protected int N;
    private double radius;
    public RegularPolygon(int N, double radius) {
        // TODO
        super(new Vector2D[N]);
        double winkel = (2*Math.PI)/N;
        Vector2D[] eckpunkte = new Vector2D[N];
        eckpunkte[0] = new Vector2D(radius,0);
        for (int i = 1; i < N; i++) {
            double x = Math.cos(winkel*i)*radius;
            double y = Math.sin(winkel*i)*radius;
            eckpunkte[i] = new Vector2D(x,y);
        }
        this.vertices = eckpunkte;
        this.N = N;
        this.radius = radius;
    }

    public RegularPolygon(RegularPolygon polygon) {
        // TODO
        this(polygon.N,polygon.radius);
    }

    public void resize(double newradius) {
        // TODO
        this.radius = newradius;
        double winkel = (2*Math.PI)/N;
        Vector2D[] eckpunkte = new Vector2D[N];
        eckpunkte[0] = new Vector2D(radius,0);
        for (int i = 1; i < N; i++) {
            double x = Math.cos(winkel*i)*radius;
            double y = Math.sin(winkel*i)*radius;
            eckpunkte[i] = new Vector2D(x,y);
        }
        this.vertices = eckpunkte;
    }

    @Override
    public String toString() {
        return "RegularPolygon{" +
                "N=" + N +
                ", radius=" + radius +
                '}';
    }

    public static void main(String[] args) {
        RegularPolygon pentagon = new RegularPolygon(5, 1);
        System.out.println("Der Flächeninhalt des " + pentagon + " beträgt " + pentagon.area() + " LE^2.");
//        RegularPolygon otherpentagon = pentagon;      // Dies funktioniert nicht!
        RegularPolygon otherpentagon = new RegularPolygon(pentagon);
        pentagon.resize(10);
        System.out.println("Nach Vergrößerung: " + pentagon + " mit Fläche " + pentagon.area() + " LE^2.");
        System.out.println("Die Kopie: " + otherpentagon + " mit Fläche " + otherpentagon.area() + " LE^2.");
        /*
        Die erwartete Ausgabe ist:
Der Flächeninhalt des RegularPolygon{N=5, radius=1.0} beträgt 2.377641290737883 LE^2.
Nach Vergrößerung: RegularPolygon{N=5, radius=10.0} mit Fläche 237.7641290737884 LE^2.
Die Kopie: RegularPolygon{N=5, radius=1.0} mit Fläche 2.377641290737883 LE^2.
         */
    }
}

