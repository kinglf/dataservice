package top.kinglf.dataservice.send.model;
import java.util.Date;

/**
 * 消息类
 * 用于在采集端和数据接收端传输数据使用
 * @author Kinglf
 */
public class KMessage {//在传输的过程中传输的是Message的JSONArray
    /**
     *    消息id
     */
    private String id;
    /**
     * projectId 竞品id
     */
    private long projectId;
    /**
     * 消息类型,车主/货主/未定义
     */
    private String type;
    /**
     * 消息创建日期
     */
    private Date crawlDate;
    /**
     * 消息主体
     */
    private String context;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getCrawlDate() {
        return crawlDate;
    }

    public void setCrawlDate(Date crawlDate) {
        this.crawlDate = crawlDate;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
}
