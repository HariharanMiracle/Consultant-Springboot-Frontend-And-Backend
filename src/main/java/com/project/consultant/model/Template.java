package com.project.consultant.model;

public class Template {
    private String logo;
    private String heading;

    private static Template template=new Template();
    private Template(){
        this.logo = "EduCan";
        this.heading = "Welcome to the portal";
    }

    public static Template getObject(){
        return template;
    }

    public String getLogo() {
        return logo;
    }

    public String getHeading() {
        return heading;
    }
}
