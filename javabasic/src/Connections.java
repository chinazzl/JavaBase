import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.*;

public class Connections {
    public static void main(String[] args) {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = "jdbc:oracle:thin:@localhost:1521:xe";
            String user= "root";
            String password = "password";
            Connection connection = DriverManager.getConnection(url,user,password);
            Statement statement = connection.createStatement();
//            String sql = "insert into stu values('3',empty_blob())";
//            ResultSet query = statement.executeQuery(sql);
//            ResultSet resultSet = statement.executeQuery("select image from stu where sid  = 1 for update");
//
//            FileInputStream fis = new FileInputStream("C:\\Users\\lenovo\\Desktop\\c.log");
//            byte[] blobByte= new byte[fis.available()];
//            fis.read(blobByte);
//            fis.close();
//            if(resultSet.next()){
//                System.out.println(blobByte.length);
//                oracle.sql.BLOB blob =(oracle.sql.BLOB)resultSet.getBlob(1);
//                OutputStream binaryOutputStream = blob.getBinaryOutputStream();
//                binaryOutputStream.write(blobByte);
//               binaryOutputStream.close();
//                connection.commit();
//                connection.close();
//            }
            //读取blob
            ResultSet resultSet = statement.executeQuery("SELECT image FROM stu where sid=2");
            if (resultSet.next()){
                Blob blob = resultSet.getBlob(1);
                InputStream is = blob.getBinaryStream();
                FileOutputStream fos = new FileOutputStream("C:\\Users\\lenovo\\Desktop\\12306Bypass\\2.jpg");
                int b ;
                byte[] bytes = new byte[1024];
                while ((b=is.read(bytes))!=-1){
                    fos.write(bytes,0,b);
                }
                is.close();
                fos.close();
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
