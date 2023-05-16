package sherry.entity;


public class UserFollow extends BaseEntity {

    private static final long serialVersionUID = 1L;

    //用户id
    private Long userId;
    //房源id
    private Long houseId;

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long value) {
        this.userId = value;
    }

    public Long getHouseId() {
        return this.houseId;
    }

    public void setHouseId(Long value) {
        this.houseId = value;
    }

}

