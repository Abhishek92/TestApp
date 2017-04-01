package com.android.testapp.network.model;

import android.databinding.BindingAdapter;
import android.text.TextUtils;
import android.widget.ImageView;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.squareup.picasso.Picasso;

/**
 * Created by hp pc on 24-03-2017.
 */

public class Organisation {

    @SerializedName("login")
    @Expose
    private String login;
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("repos_url")
    @Expose
    private String reposUrl;
    @SerializedName("events_url")
    @Expose
    private String eventsUrl;
    @SerializedName("hooks_url")
    @Expose
    private String hooksUrl;
    @SerializedName("issues_url")
    @Expose
    private String issuesUrl;
    @SerializedName("members_url")
    @Expose
    private String membersUrl;
    @SerializedName("public_members_url")
    @Expose
    private String publicMembersUrl;
    @SerializedName("avatar_url")
    @Expose
    private String avatarUrl;
    @SerializedName("description")
    @Expose
    private String description;



    private String displaylogin;

    private String displayUrl;

    private String displayreposUrl;

    private String displayeventsUrl;

    private String displayhooksUrl;

    private String displayissuesUrl;

    private String displaymembersUrl;

    private String displaypublicMembersUrl;

    private String displayavatarUrl;

    private String displaydescription;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {

        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getReposUrl() {

        return reposUrl;
    }

    public void setReposUrl(String reposUrl) {
        this.reposUrl = reposUrl;
    }

    public String getEventsUrl() {

        return eventsUrl;
    }

    public void setEventsUrl(String eventsUrl) {
        this.eventsUrl = eventsUrl;
    }

    public String getHooksUrl() {

        return hooksUrl;
    }

    public void setHooksUrl(String hooksUrl) {
        this.hooksUrl = hooksUrl;
    }

    public String getIssuesUrl() {

        return issuesUrl;
    }

    public void setIssuesUrl(String issuesUrl) {
        this.issuesUrl = issuesUrl;
    }

    public String getMembersUrl() {

        return membersUrl;
    }

    public void setMembersUrl(String membersUrl) {
        this.membersUrl = membersUrl;
    }

    public String getPublicMembersUrl() {

        return publicMembersUrl;
    }

    public void setPublicMembersUrl(String publicMembersUrl) {
        this.publicMembersUrl = publicMembersUrl;
    }

    public String getAvatarUrl() {

        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getDescription() {

        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        Picasso.with(view.getContext())
                .load(imageUrl)
                .into(view);
    }

    public String getDisplaylogin() {
        return displaylogin;
    }

    public String getDisplayUrl() {
        return displayUrl = "Url: ".concat(url);
    }

    public String getDisplayreposUrl() {
        return displayreposUrl = "Repos Url: ".concat(reposUrl);
    }

    public String getDisplayeventsUrl() {
        return displayeventsUrl = "Events Url: ".concat(eventsUrl);
    }

    public String getDisplayhooksUrl() {
        return displayhooksUrl = "Hooks Url: ".concat(hooksUrl);
    }

    public String getDisplayissuesUrl() {
        return displayissuesUrl = "Issue Url: ".concat(issuesUrl);
    }

    public String getDisplaymembersUrl() {
        return displaymembersUrl = "Members Url: ".concat(membersUrl);
    }

    public String getDisplaypublicMembersUrl() {
        return displaypublicMembersUrl = "Public Member Url: ".concat(publicMembersUrl);
    }

    public String getDisplayavatarUrl() {
        return displayavatarUrl = "Avatar Url: ".concat(avatarUrl);
    }

    public String getDisplaydescription() {
        displaydescription = TextUtils.isEmpty(description) ? "" : "Description: ".concat(description);
        return displaydescription;
    }
}
