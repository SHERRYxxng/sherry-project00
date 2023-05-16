package sherry.entity;


public class HouseUser extends BaseEntity {

    private static final long serialVersionUID = 1L;

    //房源id
    private Long houseId;
    //房东姓名
    private String name;
    //手机
    private String phone;
    //性别 1:男 2：女
    private Integer sex;
    //身份证号
    private String idNo;

    public Long getHouseId() {
        return this.houseId;
    }

    public void setHouseId(Long value) {
        this.houseId = value;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String value) {
        this.name = value;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String value) {
        this.phone = value;
    }

    public Integer getSex() {
        return this.sex;
    }

    public void setSex(Integer value) {
        this.sex = value;
    }

    public String getIdNo() {
        return this.idNo;
    }

    public void setIdNo(String value) {
        this.idNo = value;
    }

}

