package example.com.weidushangcheng.model.bean;

import java.util.List;

public class QuanZiBean {

    /**
     * result : [{"commodityId":1,"content":"元气满满","createTime":1553266525000,"greatNum":58,"headPic":"http://172.17.8.100/images/small/head_pic/2019-03-26/20190326142936.png","id":790,"image":"http://172.17.8.100/images/small/circle_pic/2019-03-22/6731420190322095525.png","nickName":"2A_dE4I6","userId":752,"whetherGreat":2},{"commodityId":1,"content":"wkq","createTime":1553218505000,"greatNum":2784,"headPic":"http://172.17.8.100/images/small/default/user.jpg","id":789,"image":"http://172.17.8.100/images/small/circle_pic/2019-03-21/6128320190321203505.png","nickName":"HA_DM5T9","userId":11,"whetherGreat":2},{"commodityId":1,"content":"元气满满","createTime":1553216151000,"greatNum":206,"headPic":"http://172.17.8.100/images/small/head_pic/2019-03-26/20190326142936.png","id":788,"image":"http://172.17.8.100/images/small/circle_pic/2019-03-21/2380920190321195551.png","nickName":"2A_dE4I6","userId":752,"whetherGreat":2},{"commodityId":1,"content":"????","createTime":1552607214000,"greatNum":2,"headPic":"http://172.17.8.100/images/small/head_pic/2019-03-13/20190313115956.png","id":787,"image":"http://172.17.8.100/images/small/circle_pic/2019-03-14/2346920190314184654.jpg","nickName":"nanye1112","userId":1636,"whetherGreat":2},{"commodityId":1,"content":"????","createTime":1552577581000,"greatNum":4,"headPic":"http://172.17.8.100/images/small/head_pic/2019-03-13/20190313115956.png","id":786,"image":"http://172.17.8.100/images/small/circle_pic/2019-03-14/0176820190314103301.jpg","nickName":"nanye1112","userId":1636,"whetherGreat":2}]
     * message : 查询成功
     * status : 0000
     */

    private String message;
    private String status;
    private List<ResultBean> result;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * commodityId : 1
         * content : 元气满满
         * createTime : 1553266525000
         * greatNum : 58
         * headPic : http://172.17.8.100/images/small/head_pic/2019-03-26/20190326142936.png
         * id : 790
         * image : http://172.17.8.100/images/small/circle_pic/2019-03-22/6731420190322095525.png
         * nickName : 2A_dE4I6
         * userId : 752
         * whetherGreat : 2
         */

        private String commodityId;
        private String content;
        private long createTime;
        private String greatNum;
        private String headPic;
        private String id;
        private String image;
        private String nickName;
        private String userId;
        private String whetherGreat;

        public String getCommodityId() {
            return commodityId;
        }

        public void setCommodityId(String commodityId) {
            this.commodityId = commodityId;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public String getGreatNum() {
            return greatNum;
        }

        public void setGreatNum(String greatNum) {
            this.greatNum = greatNum;
        }

        public String getHeadPic() {
            return headPic;
        }

        public void setHeadPic(String headPic) {
            this.headPic = headPic;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getWhetherGreat() {
            return whetherGreat;
        }

        public void setWhetherGreat(String whetherGreat) {
            this.whetherGreat = whetherGreat;
        }
    }
}
