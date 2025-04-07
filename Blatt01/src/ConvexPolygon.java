import java.util.Arrays;


public class ConvexPolygon extends Polygon {
    // TODO
    ConvexPolygon(Vector2D[] vertices) {
        this.vertices = vertices;
    }

    @Override
    public double perimeter() {
        int eckpunkte = vertices.length;
        Vector2D side_0_n = new Vector2D(vertices[0].getX() - vertices[eckpunkte - 1].getX(), vertices[0].getY() - vertices[eckpunkte - 1].getY());
        double result = side_0_n.length();
        for (int i = 0; i < eckpunkte - 1; i++) {
            Vector2D side = new Vector2D(vertices[i].getX() - vertices[i + 1].getX(), vertices[i].getY() - vertices[i + 1].getY());
            result = result + side.length();
        }
        return result;
    }

    @Override
    public double area() {
        int eckpunkte = vertices.length;
        double area = 0;

        for (int i = 0; i < eckpunkte - 2; i++) {
            Vector2D side1 = new Vector2D(vertices[eckpunkte - 1].getX() - vertices[i].getX(), vertices[eckpunkte - 1].getY() - vertices[i].getY());
            Vector2D side2 = new Vector2D(vertices[eckpunkte - 1].getX() - vertices[i + 1].getX(), vertices[eckpunkte - 1].getY() - vertices[i + 1].getY());
            Vector2D side3 = new Vector2D(vertices[i].getX() - vertices[i + 1].getX(), vertices[i].getY() - vertices[i + 1].getY());
            double s1 = side1.length();
            double s2 = side2.length();
            double s3 = side3.length();
            double s = (s1 + s2 + s3) / 2;
            area = area + Math.sqrt(s * (s - s1) * (s - s2) * (s - s3));
        }
        return area;
    }

    @Override
    public String toString() {
        return "ConvexPolygon(" +
                Arrays.toString(vertices) +
                ")";
    }

    public static Polygon[] somePolygons() {
        Vector2D a = new Vector2D(0, 0);
        Vector2D b = new Vector2D(10, 0);
        Vector2D c = new Vector2D(5, 5);
        Vector2D vier_b = new Vector2D(10, -5);
        Vector2D vier_c = new Vector2D(12, 2);
        Vector2D vier_d = new Vector2D(3, 17);
        Polygon[] somePo = new ConvexPolygon[4];
        somePo[0] = new Triangle(a, b, c);
        somePo[1] = new Tetragon(a, vier_b, vier_c, vier_d);
        somePo[2] = new RegularPolygon(5, 1);
        somePo[3] = new RegularPolygon(6, 1);
        return somePo;
    }

    public static double totalArea(Polygon[] polygons) {
        double area = 0;
        int anzahl = polygons.length;
        for (int i = 0; i < anzahl; i++) {
            area = area + polygons[i].area();
        }
        return area;
    }

    /*
    public static void main(String[] args) {
        Polygon[] polygons = somePolygons();
        Vector2D a = new Vector2D(0, 0);
        Vector2D b = new Vector2D(10, 0);
        Vector2D c = new Vector2D(5, 5);
        Polygon polygon = new ConvexPolygon(new Vector2D[] {a, b, c});
        System.out.println(polygon);
        System.out.println("Die Totalfläche: " + (polygons[0].area() + polygons[1].area() + polygons[2].area() + polygons[3].area()));
    }

     */

}


