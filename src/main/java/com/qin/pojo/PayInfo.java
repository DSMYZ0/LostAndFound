package com.qin.pojo;

import java.util.Date;

public class PayInfo {

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    private Integer postId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column laf_payinfo.id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column laf_payinfo.user_id
     *
     * @mbg.generated
     */
    private Integer userId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column laf_payinfo.order_no
     *
     * @mbg.generated
     */
    private Long orderNo;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column laf_payinfo.pay_platform
     *
     * @mbg.generated
     */
    private Integer payPlatform;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column laf_payinfo.platform_number
     *
     * @mbg.generated
     */
    private String platformNumber;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column laf_payinfo.platform_status
     *
     * @mbg.generated
     */
    private Integer platformStatus;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column laf_payinfo.create_time
     *
     * @mbg.generated
     */
    private Date createTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column laf_payinfo.id
     *
     * @return the value of laf_payinfo.id
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column laf_payinfo.id
     *
     * @param id the value for laf_payinfo.id
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column laf_payinfo.user_id
     *
     * @return the value of laf_payinfo.user_id
     *
     * @mbg.generated
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column laf_payinfo.user_id
     *
     * @param userId the value for laf_payinfo.user_id
     *
     * @mbg.generated
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column laf_payinfo.order_no
     *
     * @return the value of laf_payinfo.order_no
     *
     * @mbg.generated
     */
    public Long getOrderNo() {
        return orderNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column laf_payinfo.order_no
     *
     * @param orderNo the value for laf_payinfo.order_no
     *
     * @mbg.generated
     */
    public void setOrderNo(Long orderNo) {
        this.orderNo = orderNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column laf_payinfo.pay_platform
     *
     * @return the value of laf_payinfo.pay_platform
     *
     * @mbg.generated
     */
    public Integer getPayPlatform() {
        return payPlatform;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column laf_payinfo.pay_platform
     *
     * @param payPlatform the value for laf_payinfo.pay_platform
     *
     * @mbg.generated
     */
    public void setPayPlatform(Integer payPlatform) {
        this.payPlatform = payPlatform;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column laf_payinfo.platform_number
     *
     * @return the value of laf_payinfo.platform_number
     *
     * @mbg.generated
     */
    public String getPlatformNumber() {
        return platformNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column laf_payinfo.platform_number
     *
     * @param platformNumber the value for laf_payinfo.platform_number
     *
     * @mbg.generated
     */
    public void setPlatformNumber(String platformNumber) {
        this.platformNumber = platformNumber == null ? null : platformNumber.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column laf_payinfo.platform_status
     *
     * @return the value of laf_payinfo.platform_status
     *
     * @mbg.generated
     */
    public Integer getPlatformStatus() {
        return platformStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column laf_payinfo.platform_status
     *
     * @param platformStatus the value for laf_payinfo.platform_status
     *
     * @mbg.generated
     */
    public void setPlatformStatus(Integer platformStatus) {
        this.platformStatus = platformStatus == null ? 0 : platformStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column laf_payinfo.create_time
     *
     * @return the value of laf_payinfo.create_time
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column laf_payinfo.create_time
     *
     * @param createTime the value for laf_payinfo.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}