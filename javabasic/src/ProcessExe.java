import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zhang zhao lin
 * @Description:
 * @Date:Create：in 2020/7/23 9:47
 * @Modified By：
 *      运行可执行性文件
 */
public class ProcessExe {

//    public static void main(String[] args) throws IOException {
//        List<String> command = new ArrayList<>();
//        command.add("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");
//        command.add(null);
//        ProcessBuilder pb = new ProcessBuilder(command);
//        pb.start();
//    }
private static void openBrowse(String[] urls) {
    for (String url : urls) {
        if (java.awt.Desktop.isDesktopSupported()) {
            try {
                //创建一个URI实例,注意不是URL
                java.net.URI uri = java.net.URI.create(url);
                //获取当前系统桌面扩展
                java.awt.Desktop dp = java.awt.Desktop.getDesktop();
                //判断系统桌面是否支持要执行的功能
                if (dp.isSupported(java.awt.Desktop.Action.BROWSE)) {
                    //获取系统默认浏览器打开链接
                    dp.browse(uri);
                }
            } catch (NullPointerException | IOException e) {
                e.printStackTrace();
            }
        }
    }

}

    private static void closeBrowse() {
        try {
            Runtime.getRuntime().exec("taskkill /F /IM chrome.exe");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        String[] urls = {"https://shop136815572.taobao.com/shop/view_shop.htm?spm=a211vu.server-home.favorite.d53.627a5e16x7ttNb&mytmenu=mdianpu&user_number_id=1848993149",
                "https://item.taobao.com/item.htm?spm=a1z10.1-c-s.w4004-21735248043.2.5b065e021D00tn&id=598627677953"};
        openBrowse(urls);
        closeBrowse();
    }

}
