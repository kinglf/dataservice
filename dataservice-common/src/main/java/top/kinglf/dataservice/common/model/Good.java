package top.kinglf.dataservice.common.model;

import javax.persistence.*;
import java.util.Date;

/**
 * 货物信息
 * @author Kinglf
 */
@Entity
@Table(name="goods")
public class Good {
    /**
     * 货主信息ID
     */
    @Id
    @Column(name = "id")
    private String id;
    /**
     * 货物出发地,如有省份用-间隔
     */
    @Column(name = "start_area")
    private String startArea;
    /**
     * 货物抵达地点,如有省份用-间隔
     */
    @Column(name = "end_area")
    private String endArea;
    /**
     * 货物类型,哪种货
     */
    @Column(name = "good_type")
    private String goodType;
    /**
     * 货物名称,可以与货物类型同名
     */
    @Column(name = "good_name")
    private String goodName;
    /**
     * 货物重量
     */
    @Column(name = "good_weight")
    private String goodWeight;
    /**
     * 要求车的类型
     */
    @Column(name = "car_type")
    private String carType;
    /**
     * 要求车长
     */
    @Column(name = "car_length")
    private double carLength;
    /**
     * 联系人/公司名称
     */
    @Column(name = "contact_name")
    private String contactName;
    /**
     * 所属公司,包含此项为货站
     */
    @Column(name = "company")
    private String company;
    /**
     * 标题
     */
    @Column(name = "title")
    private String title;
    /**
     * 内容主体
     */
    @Column(name = "content")
    private String content;
    /**
     * 竞品Id
     */
//    @Id
    @Column(name = "project_id")
    private long projectId;
    /**
     * 电话信息
     */

    private String tel1;
    private String tel2;
    private String tel3;
    private String tel4;
    private String tel5;
    /**
     * 发布日期
     */
    @Column(name = "publish_date")
    private Date publishDate;
    /**
     * 采集日期
     */
    @Column(name = "crawl_date")
    private Date crawlDate;
    /**
     * 工作流状态
     */
    @Column(name = "work_flow_status")
    private String workFlowStatus;
    /**
     * 备注
     */
    private String remark;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStartArea() {
        return startArea;
    }

    public void setStartArea(String startArea) {
        this.startArea = startArea;
    }

    public String getEndArea() {
        return endArea;
    }

    public void setEndArea(String endArea) {
        this.endArea = endArea;
    }

    public String getGoodType() {
        return goodType;
    }

    public void setGoodType(String goodType) {
        this.goodType = goodType;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public String getGoodWeight() {
        return goodWeight;
    }

    public void setGoodWeight(String goodWeight) {
        this.goodWeight = goodWeight;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public double getCarLength() {
        return carLength;
    }

    public void setCarLength(double carLength) {
        this.carLength = carLength;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public String getTel1() {
        return tel1;
    }

    public void setTel1(String tel1) {
        this.tel1 = tel1;
    }

    public String getTel2() {
        return tel2;
    }

    public void setTel2(String tel2) {
        this.tel2 = tel2;
    }

    public String getTel3() {
        return tel3;
    }

    public void setTel3(String tel3) {
        this.tel3 = tel3;
    }

    public String getTel4() {
        return tel4;
    }

    public void setTel4(String tel4) {
        this.tel4 = tel4;
    }

    public String getTel5() {
        return tel5;
    }

    public void setTel5(String tel5) {
        this.tel5 = tel5;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public Date getCrawlDate() {
        return crawlDate;
    }

    public void setCrawlDate(Date crawlDate) {
        this.crawlDate = crawlDate;
    }

    public String getWorkFlowStatus() {
        return workFlowStatus;
    }

    public void setWorkFlowStatus(String workFlowStatus) {
        this.workFlowStatus = workFlowStatus;
    }
}
