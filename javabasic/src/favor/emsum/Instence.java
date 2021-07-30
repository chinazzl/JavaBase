package favor.emsum;

public enum Instence {
    UP,
    DOWN,
    STARTING,
    OUT,
    UNKNOW;

    public static Instence toEnum(String s){
        if(s != null){
            try{
                return Instence.valueOf(s.toUpperCase());
            }catch (IllegalArgumentException e){
                e.printStackTrace();
            }
        }
        return UNKNOW;
    }

}
