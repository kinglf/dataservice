package top.kinglf.dataservice.common.model;

import top.kinglf.dataservice.common.TableField;

import javax.persistence.*;
import java.util.Date;

/**
 * 车主信息
 * @author Kinglf
 */
@Entity
@Table(name = "cars")
public class Car {
    /**
     * 车主信息ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableField(field = "ID")
    private long id;
    /**
     * 数据ID
     */
    @TableField(field = "竞品内数据ID")
    private String dataId;
    /**
     * 货物出发地,如有省份用-间隔
     */
    @TableField(field = "出发地")
    @Column(name = "start_area")
    private String startArea;
    /**
     * 货物抵达地点,如有省份用-间隔
     */
    @TableField(field = "目的地")
    @Column(name = "end_area")
    private String endArea;
    /**
     * 车类型
     */
    @TableField(field = "货车类型")
    @Column(name = "car_type")
    private String carType;
    /**
     * 厢体类型
     */
    @TableField(field = "厢体类型")
    @Column(name = "car_van")
    private String carVan;
    /**
     * 车长
     */
    @TableField(field = "车长")
    @Column(name = "car_length")
    private double carLength;
    /**
     * 载重
     */
    @TableField(field = "载重")
    @Column(name = "car_weight")
    private double carWeight;
    /**
     * 车宽
     */
    @TableField(field = "车宽")
    @Column(name = "car_width")
    private double carWidth;
    /**
     * 联系人
     */
    @TableField(field = "联系人")
    private String contact;
    /**
     * 公司
     */
    @TableField(field = "所属公司")
    private String company;
    /**
     * 标题
     */
    @TableField(field = "数据标题")
    private String title;
    /**
     * 内容主体
     */
    @TableField(field = "数据内容")
    private String content;
    /**
     * 竞品id
     */
//    @Id
    @TableField(field = "竞品ID")
    @Column(name = "project_id")
    private long projectId;
    @TableField(field = "电话1")
    private String tel1;
    @TableField(field = "电话2")
    private String tel2;
    @TableField(field = "电话3")
    private String tel3;
    @TableField(field = "电话4")
    private String tel4;
    @TableField(field = "电话5")
    private String tel5;
    /**
     * 发布日期
     */
    @TableField(field = "发布日期")
    @Column(name = "publish_date")
    private Date publishDate;
    /**
     * 采集日期
     */
    @TableField(field = "采集日期")
    @Column(name = "crawl_date")
    private Date crawlDate;
    /**
     * 工作流状态
     */
    @TableField(field = "工作状态")
    @Column(name = "work_flow_status")
    private String workFlowStatus;
    /**
     * 来源,URL / APP名称+采集任务序号
     */
    @TableField(field = "来源")
    private String source;
    /**
     * 备注
     */
    @TableField(field = "备注")
    private String remark;

    /**
     * 车牌号
     */
    @TableField(field = "车牌号")
    @Column(name = "car_card_no")
    private String carCardNo;

    public String getCarCardNo() {
        return carCardNo;
    }

    public void setCarCardNo(String carCardNo) {
        this.carCardNo = carCardNo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getCarVan() {
        return carVan;
    }

    public void setCarVan(String carVan) {
        this.carVan = carVan;
    }

    public double getCarLength() {
        return carLength;
    }

    public void setCarLength(double carLength) {
        this.carLength = carLength;
    }

    public double getCarWeight() {
        return carWeight;
    }

    public void setCarWeight(double carWeight) {
        this.carWeight = carWeight;
    }

    public double getCarWidth() {
        return carWidth;
    }

    public void setCarWidth(double carWidth) {
        this.carWidth = carWidth;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
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

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDataId() {
        return dataId;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }
}
