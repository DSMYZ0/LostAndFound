package com.qin.pojo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class UserInfo{
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column laf_user.id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column laf_user.username
     *
     * @mbg.generated
     */
    private String username;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column laf_user.password
     *
     * @mbg.generated
     */
    private String password;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column laf_user.contact
     *
     * @mbg.generated
     */
    private String contact;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column laf_user.profile_url
     *
     * @mbg.generated
     */
    private String profileUrl;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column laf_user.role
     *
     * @mbg.generated
     */
    private Integer role;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column laf_user.profile
     *
     * @mbg.generated
     */
    private String profile;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column laf_user.id
     *
     * @return the value of laf_user.id
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column laf_user.id
     *
     * @param id the value for laf_user.id
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column laf_user.username
     *
     * @return the value of laf_user.username
     *
     * @mbg.generated
     */
    public String getUsername() {
        return username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column laf_user.username
     *
     * @param username the value for laf_user.username
     *
     * @mbg.generated
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column laf_user.password
     *
     * @return the value of laf_user.password
     *
     * @mbg.generated
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column laf_user.password
     *
     * @param password the value for laf_user.password
     *
     * @mbg.generated
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column laf_user.contact
     *
     * @return the value of laf_user.contact
     *
     * @mbg.generated
     */
    public String getContact() {
        return contact;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column laf_user.contact
     *
     * @param contact the value for laf_user.contact
     *
     * @mbg.generated
     */
    public void setContact(String contact) {
        this.contact = contact == null ? null : contact.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column laf_user.profile_url
     *
     * @return the value of laf_user.profile_url
     *
     * @mbg.generated
     */
    public String getProfileUrl() {
        return profileUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column laf_user.profile_url
     *
     * @param profileUrl the value for laf_user.profile_url
     *
     * @mbg.generated
     */
    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl == null ? null : profileUrl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column laf_user.role
     *
     * @return the value of laf_user.role
     *
     * @mbg.generated
     */
    public Integer getRole() {
        return role;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column laf_user.role
     *
     * @param role the value for laf_user.role
     *
     * @mbg.generated
     */
    public void setRole(Integer role) {
        this.role = role;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column laf_user.profile
     *
     * @return the value of laf_user.profile
     *
     * @mbg.generated
     */
    public String getProfile() {
        return profile;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column laf_user.profile
     *
     * @param profile the value for laf_user.profile
     *
     * @mbg.generated
     */
    public void setProfile(String profile) {
        this.profile = profile == null ? null : profile.trim();
    }
}