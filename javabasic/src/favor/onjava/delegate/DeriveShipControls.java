package favor.onjava.delegate;

/**
 * @author Julyan
 * @version V1.0
 * @Description:
 * @Date: 2022/6/23 23:08
 */
public class DeriveShipControls extends SpaceShipControls {

    @Override
    void forward(int velocity) {
        System.out.println("sub DeriveShipControls ==> " + velocity);
    }
}
