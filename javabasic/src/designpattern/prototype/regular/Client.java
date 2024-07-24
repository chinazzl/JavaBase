package designpattern.prototype.regular;

/**********************************
 * @author zhang zhao lin
 * @date 2023年04月24日 23:22
 * @Description
 **********************************/
public class Client {

    private Prototype prototype;

    public Client(Prototype prototype) {
        this.prototype = prototype;
    }

    public Prototype startClient(Prototype concretePrototype) {
        return concretePrototype.clone();
    }
}
