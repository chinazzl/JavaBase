package designpattern.adapterpattern;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package designpattern.adapterpattern
 * @Description:
 * @Date: 2021/9/3 10:03
 */
public class PassportThirdAdapter extends MessageService implements IPassportThirdAdapter {
    @Override
    public ResultMsg loginWx(String wxName) {
        return processLogin(wxName,LoginForWxAdapter.class);
    }

    @Override
    public ResultMsg loginQQ(String id) {
        return processLogin(id,LoginForQQAdapter.class);
    }

    @Override
    public ResultMsg registerAndLogin(String username, String password) {
        super.register(username,password);
        return super.login(username, password);
    }

    private ResultMsg processLogin(String type, Class<? extends LoginAdapter> clazz) {
        try {
            LoginAdapter adapter = clazz.newInstance();
            //使用策略模式
            if (adapter.support(adapter)) {
                return adapter.login(type,adapter);
            }
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
