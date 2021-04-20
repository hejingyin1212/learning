package com.ly.entity;

import java.util.Date;

public class TCase {
    private Long id;

    private Integer firstFlag;

    private Integer secondFlag;

    private String title;

    private String imgUrl;

    private String text;

    private Integer status;

    private String description;

    private Long sortFlag;

    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getFirstFlag() {
        return firstFlag;
    }

    public void setFirstFlag(Integer firstFlag) {
        this.firstFlag = firstFlag;
    }

    public Integer getSecondFlag() {
        return secondFlag;
    }

    public void setSecondFlag(Integer secondFlag) {
        this.secondFlag = secondFlag;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl == null ? null : imgUrl.trim();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text == null ? null : text.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Long getSortFlag() {
        return sortFlag;
    }

    public void setSortFlag(Long sortFlag) {
        this.sortFlag = sortFlag;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}