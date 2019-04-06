package example.com.weidushangcheng.model.bean;

public class AddBuyBean {
    private int commodityId;
    private int count;

    public AddBuyBean(int commodityId, int  count) {
        this.commodityId = commodityId;
        this.count = count;
    }

    public int getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(int commodityId) {
        this.commodityId = commodityId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
