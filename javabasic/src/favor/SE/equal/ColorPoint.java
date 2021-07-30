package favor.SE.equal;

public class ColorPoint extends Point {

    private final Point point;
    private final Color color;

    public ColorPoint(int x, int y, Color color) {
        super(x, y);
        if(color == null){
            throw  new NullPointerException();
        }
        point = new Point(x,y);
        this.color = color;
    }

    public Point asPoint(){
        return point;
    }
    @Override
    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        if (!super.equals(o)) return false;
//        ColorPoint that = (ColorPoint) o;
//        return Objects.equals(color, that.color);

        if(!(o instanceof ColorPoint)){
            return false;
        }
        ColorPoint cp = (ColorPoint) o;
        return cp.equals(point) && cp.color.equals(color);
    }

//    @Override
//    public int hashCode() {
//
//        return Objects.hash(color);
//    }
}
