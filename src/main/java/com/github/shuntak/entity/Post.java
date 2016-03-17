package com.github.shuntak.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Post {
    private String postId;
    private Long postDateTime;
    private String postUserId;
    private String postItemId;
    private Integer postItemScore;
    private String postItemState;
    private String postLikeUsers;
    private String postTags;

    @Id
    @Column(name = "postId")
    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    @Basic
    @Column(name = "postDateTime")
    public Long getPostDateTime() {
        return postDateTime;
    }

    public void setPostDateTime(Long postDateTime) {
        this.postDateTime = postDateTime;
    }

    @Basic
    @Column(name = "postUserId")
    public String getPostUserId() {
        return postUserId;
    }

    public void setPostUserId(String postUserId) {
        this.postUserId = postUserId;
    }

    @Basic
    @Column(name = "postItemId")
    public String getPostItemId() {
        return postItemId;
    }

    public void setPostItemId(String postItemId) {
        this.postItemId = postItemId;
    }

    @Basic
    @Column(name = "postItemScore")
    public Integer getPostItemScore() {
        return postItemScore;
    }

    public void setPostItemScore(Integer postItemScore) {
        this.postItemScore = postItemScore;
    }

    @Basic
    @Column(name = "postItemState")
    public String getPostItemState() {
        return postItemState;
    }

    public void setPostItemState(String postItemState) {
        this.postItemState = postItemState;
    }

    @Basic
    @Column(name = "postLikeUsers")
    public String getPostLikeUsers() {
        return postLikeUsers;
    }

    public void setPostLikeUsers(String postLikeUsers) {
        this.postLikeUsers = postLikeUsers;
    }

    @Basic
    @Column(name = "postTags")
    public String getPostTags() {
        return postTags;
    }

    public void setPostTags(String postTags) {
        this.postTags = postTags;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Post post = (Post) o;

        if (postId != null ? !postId.equals(post.postId) : post.postId != null) return false;
        if (postDateTime != null ? !postDateTime.equals(post.postDateTime) : post.postDateTime != null) return false;
        if (postUserId != null ? !postUserId.equals(post.postUserId) : post.postUserId != null) return false;
        if (postItemId != null ? !postItemId.equals(post.postItemId) : post.postItemId != null) return false;
        if (postItemScore != null ? !postItemScore.equals(post.postItemScore) : post.postItemScore != null)
            return false;
        if (postItemState != null ? !postItemState.equals(post.postItemState) : post.postItemState != null)
            return false;
        if (postLikeUsers != null ? !postLikeUsers.equals(post.postLikeUsers) : post.postLikeUsers != null)
            return false;
        if (postTags != null ? !postTags.equals(post.postTags) : post.postTags != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = postId != null ? postId.hashCode() : 0;
        result = 31 * result + (postDateTime != null ? postDateTime.hashCode() : 0);
        result = 31 * result + (postUserId != null ? postUserId.hashCode() : 0);
        result = 31 * result + (postItemId != null ? postItemId.hashCode() : 0);
        result = 31 * result + (postItemScore != null ? postItemScore.hashCode() : 0);
        result = 31 * result + (postItemState != null ? postItemState.hashCode() : 0);
        result = 31 * result + (postLikeUsers != null ? postLikeUsers.hashCode() : 0);
        result = 31 * result + (postTags != null ? postTags.hashCode() : 0);
        return result;
    }
}
