package com.hikaru.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.io.Serializable;

/**
 *
 * @TableName t_blog
 */
@TableName("t_blog")
public class BlogPO implements Serializable {
    /**
     *
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 博客标题
     */
    private String title;

    /**
     * 博客内容
     */
    private String content;

    /**
     * 博客头图
     */
    private String firstPicture;

    /**
     * 博客类型
     */
    private String type;

    /**
     * 浏览次数
     */
    private Integer views;

    /**
     * 点赞量
     */
    private Integer likes;

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    /**
     * 赞赏是否开启（0：关闭 1：开启）
     */
    private Integer appreciation;

    /**
     * 转载声明是否开启（0：关闭 1：开启）
     */
    private Integer shareStatement;

    /**
     * 评论是否开启（0：关闭 1：开启）
     */
    private Integer commentAbled;

    /**
     * 是否发布（0：存为草稿 1：发布）
     */
    private Integer published;

    /**
     * 是否推荐（0：不推荐 1：推荐）
     */
    private Integer recommend;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 更新时间
     */
    private String updateTime;

    private static final long serialVersionUID = 1L;

    /**
     *
     */
    public Long getId() {
        return id;
    }

    /**
     *
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 博客标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 博客标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 博客内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 博客内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 博客头图
     */
    public String getFirstPicture() {
        return firstPicture;
    }

    /**
     * 博客头图
     */
    public void setFirstPicture(String firstPicture) {
        this.firstPicture = firstPicture;
    }

    /**
     * 博客类型
     */
    public String getType() {
        return type;
    }

    /**
     * 博客类型
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 浏览次数
     */
    public Integer getViews() {
        return views;
    }

    /**
     * 浏览次数
     */
    public void setViews(Integer views) {
        this.views = views;
    }

    /**
     * 赞赏是否开启（0：关闭 1：开启）
     */
    public Integer getAppreciation() {
        return appreciation;
    }

    /**
     * 赞赏是否开启（0：关闭 1：开启）
     */
    public void setAppreciation(Integer appreciation) {
        this.appreciation = appreciation;
    }

    /**
     * 转载声明是否开启（0：关闭 1：开启）
     */
    public Integer getShareStatement() {
        return shareStatement;
    }

    /**
     * 转载声明是否开启（0：关闭 1：开启）
     */
    public void setShareStatement(Integer shareStatement) {
        this.shareStatement = shareStatement;
    }

    /**
     * 评论是否开启（0：关闭 1：开启）
     */
    public Integer getCommentAbled() {
        return commentAbled;
    }

    /**
     * 评论是否开启（0：关闭 1：开启）
     */
    public void setCommentAbled(Integer commentAbled) {
        this.commentAbled = commentAbled;
    }

    /**
     * 是否发布（0：存为草稿 1：发布）
     */
    public Integer getPublished() {
        return published;
    }

    /**
     * 是否发布（0：存为草稿 1：发布）
     */
    public void setPublished(Integer published) {
        this.published = published;
    }

    /**
     * 是否推荐（0：不推荐 1：推荐）
     */
    public Integer getRecommend() {
        return recommend;
    }

    /**
     * 是否推荐（0：不推荐 1：推荐）
     */
    public void setRecommend(Integer recommend) {
        this.recommend = recommend;
    }

    /**
     * 创建时间
     */
    public String getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /**
     * 更新时间
     */
    public String getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间
     */
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        BlogPO other = (BlogPO) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
            && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()))
            && (this.getFirstPicture() == null ? other.getFirstPicture() == null : this.getFirstPicture().equals(other.getFirstPicture()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getViews() == null ? other.getViews() == null : this.getViews().equals(other.getViews()))
            && (this.getAppreciation() == null ? other.getAppreciation() == null : this.getAppreciation().equals(other.getAppreciation()))
            && (this.getShareStatement() == null ? other.getShareStatement() == null : this.getShareStatement().equals(other.getShareStatement()))
            && (this.getCommentAbled() == null ? other.getCommentAbled() == null : this.getCommentAbled().equals(other.getCommentAbled()))
            && (this.getPublished() == null ? other.getPublished() == null : this.getPublished().equals(other.getPublished()))
            && (this.getRecommend() == null ? other.getRecommend() == null : this.getRecommend().equals(other.getRecommend()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        result = prime * result + ((getFirstPicture() == null) ? 0 : getFirstPicture().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getViews() == null) ? 0 : getViews().hashCode());
        result = prime * result + ((getAppreciation() == null) ? 0 : getAppreciation().hashCode());
        result = prime * result + ((getShareStatement() == null) ? 0 : getShareStatement().hashCode());
        result = prime * result + ((getCommentAbled() == null) ? 0 : getCommentAbled().hashCode());
        result = prime * result + ((getPublished() == null) ? 0 : getPublished().hashCode());
        result = prime * result + ((getRecommend() == null) ? 0 : getRecommend().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", title=").append(title);
        sb.append(", content=").append(content);
        sb.append(", firstPicture=").append(firstPicture);
        sb.append(", type=").append(type);
        sb.append(", views=").append(views);
        sb.append(", appreciation=").append(appreciation);
        sb.append(", shareStatement=").append(shareStatement);
        sb.append(", commentAbled=").append(commentAbled);
        sb.append(", published=").append(published);
        sb.append(", recommend=").append(recommend);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
