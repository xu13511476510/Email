package model;

import java.io.Serializable;

/**
 * Created by Lenovo on 2018/5/22.
 */

public class User {
    private String uid;
    private String upassword;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUpassword() {
        return upassword;
    }

    public void setUpassword(String upassword) {
        this.upassword = upassword;
    }
}
