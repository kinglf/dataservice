package top.kinglf.dataservice.common.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 需要用到的账号
 * @author Kinglf
 */
@Table(name = "account")
public class Account {
    /**
     *账户id
     */
    @Id
    private long id;
    /**
     * 登陆用户名
     */
    private String username;
    /**
     * 登陆密码
     */
    private String password;
    /**
     * 登陆类型,密码/短信
     */
    @Column(name = "login_type")
    private String loginType;
    /**
     * 绑定IP,默认为空不绑定
     */
    @Column(name = "bind_ip")
    private String bindIp;
    /**
     * 绑定手机,默认为空不绑定
     */
    @Column(name = "bind_mobile")
    private String bindMobile;
    /**
     * 注册日期
     */
    @Column(name = "register_date")
    private Date registerDate;
    /**
     * 账号状态
     */
    private String status;
    /**
     * 备注
     */
    private String remark;
    /**
     * 注册姓名
     */
    private String name;
    /**
     * 注册性别
     */
    private String sex;
    /**
     * 注册身份证号
     */
    @Column(name = "id_card_no")
    private String idCardNo;
    /**
     * 注册公司信息,以后再扩展
     */
    private String company;
    /**
     * 注册城市,省份以下逐级以-分隔
     */
    private String city;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

    public String getBindIp() {
        return bindIp;
    }

    public void setBindIp(String bindIp) {
        this.bindIp = bindIp;
    }

    public String getBindMobile() {
        return bindMobile;
    }

    public void setBindMobile(String bindMobile) {
        this.bindMobile = bindMobile;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getIdCardNo() {
        return idCardNo;
    }

    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
