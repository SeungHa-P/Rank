package com.rank.rank.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MainDetailModel {

    @SerializedName("companyCd")
    private int companyCd;

    @SerializedName("companyName")
    private String companyName;

    @SerializedName("mainText")
    private String mainText;

    @SerializedName("companyLogo")
    private String companyLogo;

    @SerializedName("employees")
    private int employees;

    @SerializedName("founded")
    private String founded;

    @SerializedName("classification")
    private String classification;

    @SerializedName("address")
    private String address;

    @SerializedName("homepage")
    private String homepage;

    @SerializedName("introduction")
    private String introduction;

    @SerializedName("mail")
    private String mail;

    @SerializedName("telnum")
    private String telnum;

    @SerializedName("budget")
    private String budget;

    @SerializedName("strongPoint")
    private List<String> strongPoint;

    @SerializedName("mainbussiness")
    private List<String> mainbussiness;

    @SerializedName("partner")
    private List<Partner> partner;

    @SerializedName("project")
    private List<ProjectDetailModel> project;

    public int getCompanyCd() {
        return companyCd;
    }

    public void setCompanyCd(int companyCd) {
        this.companyCd = companyCd;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getMainText() {
        return mainText;
    }

    public void setMainText(String mainText) {
        this.mainText = mainText;
    }

    public String getCompanyLogo() {
        return companyLogo;
    }

    public void setCompanyLogo(String companyLogo) {
        this.companyLogo = companyLogo;
    }

    public int getEmployees() {
        return employees;
    }

    public void setEmployees(int employees) {
        this.employees = employees;
    }

    public String getFounded() {
        return founded;
    }

    public void setFounded(String founded) {
        this.founded = founded;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getTelnum() {
        return telnum;
    }

    public void setTelnum(String telnum) {
        this.telnum = telnum;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public List<String> getStrongPoint() {
        return strongPoint;
    }

    public void setStrongPoint(List<String> strongPoint) {
        this.strongPoint = strongPoint;
    }

    public List<String> getMainbussiness() {
        return mainbussiness;
    }

    public void setMainbussiness(List<String> mainbussiness) {
        this.mainbussiness = mainbussiness;
    }

    public List<Partner> getPartner() {
        return partner;
    }

    public void setPartner(List<Partner> partner) {
        this.partner = partner;
    }

    public List<ProjectDetailModel> getProject() {
        return project;
    }

    public void setProject(List<ProjectDetailModel> project) {
        this.project = project;
    }
}
