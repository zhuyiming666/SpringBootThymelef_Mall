package com.zym.pojo;

public class Users {

    private Integer id;
    private String email;
    private String password;
    private String salt;
    private Integer isDelete;   //-1表示已经被注销,0表示被冻结,1表示正常

    public Users() {
    }

    public Users(Integer id, String email, String password, String salt,Integer isDelete) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.salt = salt;
        this.isDelete=isDelete;
    }

    public Users(String email, String password, String salt,Integer isDelete) {
        this.email = email;
        this.password = password;
        this.salt = salt;
        this.isDelete=isDelete;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", isDelete=" + isDelete +
                '}';
    }
}
