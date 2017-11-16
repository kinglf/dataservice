package top.kinglf.dataservice.common.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 竞品
 * @author Kinglf
 */
@Table(name = "projects")
public class Project {
    /**
     * 竞品id
     */
    @Id
    private long id;
    /**
     * 竞品主名称(默认是网站名称)
     */
    @Column(name = "main_name")
    private String mainName;
    /**
     * 竞品名称
     */
    private String name;
    /**
     * 竞品分类 类别:1.货源2.车源3.个体货主4企业货主
     */
    private String type;
    /**
     * 竞品域名
     */
    private String domain;
    /**
     * 抓取模块? 抓取方式
     */
    @Column(name = "crawl_part")
    private String crawlPart;
    /**
     * 抓取端
     */
    private String client;
    /**
     * 竞品端状态,可用/不可用
     */
    @Column(name = "client_status")
    private String clientStatus;
    /**
     * 采集状态
     */
    @Column(name = "crawl_status")
    private String crawlStatus;
    /**
     * 采集数量
     */
    @Column(name = "crawled_count")
    private long crawledCount;
    /**
     * 有效数据量
     */
    @Column(name = "valied_count")
    private long valiedCount;
    /**
     * 备注
     */
    private String remark;
    /**
     * 抓取软件路径
     */
    @Column(name = "software_path")
    private String softwarePath;
    /**
     * 开发人
     */
    private String developer;
    /**
     * 日常维护人
     */
    private String accendant;
    /**
     * 竞品所属公司
     */
    private String company;
    /**
     * 竞品公司地址
     */
    private String address;
    /**
     * 分析信息总量
     */
    @Column(name = "info_total")
    private long infoTotal;
    /**
     * 分析信息更新量
     */
    @Column(name = "info_update")
    private long infoUpdate;
    /**
     * 分析信息更新周期
     */
    @Column(name = "info_update_cycle")
    private String infoUpdateCycle;
    /**
     * 创建日期
     */
    @Column(name = "create_date")
    private Date createDate;
    /**
     * 更新日期
     */
    @Column(name = "update_date")
    private Date updateDate;

    private String parser;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMainName() {
        return mainName;
    }

    public void setMainName(String mainName) {
        this.mainName = mainName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getCrawlPart() {
        return crawlPart;
    }

    public void setCrawlPart(String crawlPart) {
        this.crawlPart = crawlPart;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getClientStatus() {
        return clientStatus;
    }

    public void setClientStatus(String clientStatus) {
        this.clientStatus = clientStatus;
    }

    public String getCrawlStatus() {
        return crawlStatus;
    }

    public void setCrawlStatus(String crawlStatus) {
        this.crawlStatus = crawlStatus;
    }

    public long getCrawledCount() {
        return crawledCount;
    }

    public void setCrawledCount(long crawledCount) {
        this.crawledCount = crawledCount;
    }

    public long getValiedCount() {
        return valiedCount;
    }

    public void setValiedCount(long valiedCount) {
        this.valiedCount = valiedCount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSoftwarePath() {
        return softwarePath;
    }

    public void setSoftwarePath(String softwarePath) {
        this.softwarePath = softwarePath;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public String getAccendant() {
        return accendant;
    }

    public void setAccendant(String accendant) {
        this.accendant = accendant;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getInfoTotal() {
        return infoTotal;
    }

    public void setInfoTotal(long infoTotal) {
        this.infoTotal = infoTotal;
    }

    public long getInfoUpdate() {
        return infoUpdate;
    }

    public void setInfoUpdate(long infoUpdate) {
        this.infoUpdate = infoUpdate;
    }

    public String getInfoUpdateCycle() {
        return infoUpdateCycle;
    }

    public void setInfoUpdateCycle(String infoUpdateCycle) {
        this.infoUpdateCycle = infoUpdateCycle;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getParser() {
        return parser;
    }

    public void setParser(String parser) {
        this.parser = parser;
    }
}
