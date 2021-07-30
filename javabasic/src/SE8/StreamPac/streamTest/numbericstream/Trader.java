package SE8.StreamPac.streamTest.numbericstream;

/**
 * @Author: zhang zhao lin
 * @Description:
 * @Date:Create：in 2020/4/11 13:12
 * @Modified By：
 *  Trader
 */
public class Trader {
    private final String name;
    private final String city;
    public Trader(String n, String c){
        this.name = n;
        this.city = c;
    }
    public String getName(){
        return this.name;
    }
    public String getCity(){
        return this.city;
    }

    @Override
    public String toString(){
        return "Trader:"+this.name + " in " + this.city;
    }
}
