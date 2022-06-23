package favor.onjava.delegate;

/**
 * @author Julyan
 * @version V1.0
 * @Description:
 * @Date: 2022/6/23 23:09
 */
public class DeriveShipControls2 extends SpaceShipControls
{
    @Override
    void forward(int velocity) {
        System.out.println("DeriveShipControls2 space velocity ==> " + velocity);
    }
}
