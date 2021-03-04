import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.*;

public class TestShapes {

    public static void main(String[] args) {
        TestShapes ts = new TestShapes();
        ArrayList<Shape> shapeList = ts.testShapes();

        DecimalFormat df = new DecimalFormat("##.##");

        for (Shape shape:shapeList) {
            System.out.println(shape.getClass().getSimpleName() + " area: " + df.format(shape.area()));
            System.out.println(shape.getClass().getSimpleName() + " perimeter: " + df.format(shape.perimeter()));
        }
    }

    interface Shape {
        double area();
        double perimeter();
    }

    class Circle implements Shape {
        private double radius;
        final double pi = Math.PI;

        public Circle() {
            this(1);
        }
        public Circle(double radius) {
            this.radius = radius;
        }

        @Override
        public double area() {
            double A = pi*(radius*radius);
            return A;
        }

        public double perimeter() {
            double P = 2*pi*radius;
            return P;
        }
    }

    class Rectangle implements Shape {
        private double width, length; //sides

        public Rectangle() {
            this(1,1);
        }
        public Rectangle(double width, double length) {
            this.width = width;
            this.length = length;
        }

        @Override
        public double area() {
            double A = width*length;
            return A;
        }

        @Override
        public double perimeter() {
            double P = 2*(width+length);
            return P;
        }

    }

    class Triangle implements Shape {
        private double a, b, c;

        public Triangle() {
            this(1,1,1);
        }
        public Triangle(double a, double b, double c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        @Override
        public double area() {
            double s = (a + b + c) / 2;
            double A = Math.sqrt(s * (s - a) * (s - b) * (s - c));
            return A;
        }

        @Override
        public double perimeter() {
            double P = a + b + c;
            return P;
        }
    }

    public ArrayList<Shape> testShapes() {
        ArrayList<Shape> shapes=new ArrayList<Shape>();

        Shape circle = new Circle(3);
        Shape rectangle = new Rectangle(2,4);
        Shape triangle = new Triangle(2,6,5);

        shapes.add(circle);
        shapes.add(rectangle);
        shapes.add(triangle);

        return shapes;
    }
}