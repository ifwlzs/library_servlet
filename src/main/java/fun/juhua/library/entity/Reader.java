package fun.juhua.library.entity;

/**
 * @program: library
 * @description: 读者实体类
 * @author:
 * @create: 2021-10-25 11:11
 **/
public class Reader {
    //id
    private String id;
    //名
    private String name;
    //密
    private String password;
    //性
    private String gender;
    //电
    private String telephone;
    //邮
    private String email;

    public Reader(String id, String name, String password, String gender, String telephone, String email) {

        this.id = id;
        this.name = name;
        this.password = password;
        this.gender = gender;
        this.telephone = telephone;
        this.email = email;
    }

    public Reader() {
    }


    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }


    public String getGender() {
        return gender;
    }


    public void setGender(String gender) {
        this.gender = gender;
    }


    public String getTelephone() {
        return telephone;
    }


    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Reader{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", gender='" + gender + '\'' +
                ", telephone='" + telephone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
