package favor.onjava.delegate;

/**
 * @author Julyan
 * @version V1.0
 * @Description:
 * @Date: 2022/6/23 23:01
 */
public class SpaceShipDelegate {

    private String name;

    private SpaceShipControls controls = new DeriveShipControls2();

    public SpaceShipDelegate(String name) {
        this.name = name;
    }

    public void back(int velocity) {
        controls.back(velocity);
    }

    public void down(int velocity) {
        controls.down(velocity);
    }

    public void forward(int velocity) {
        controls.forward(velocity);
    }

    public void left(int velocity) {
        controls.left(velocity);
    }

    public void right(int velocity) {
        controls.right(velocity);
    }

    public void turnboBoost() {
        controls.turboBoost();
    }

    public void up(int velocity) {
        controls.up(velocity);
    }

    public static void main(String[] args) {
        SpaceShipDelegate delegate = new SpaceShipDelegate("space ship delegate");
        delegate.forward(100);
    }
}
