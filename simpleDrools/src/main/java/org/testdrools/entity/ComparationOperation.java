package org.testdrools.entity;

import java.util.List;

/**
 * @author Julyan
 * @version V1.0
 * @Description:
 * @Date: 2022/3/17 15:51
 */
public class ComparationOperation {

    private String userName;

    private List<String> data;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
