package com.pog.holmessearch;

import java.io.Serializable;
import java.lang.StringBuilder;

public class SectionBean implements Serializable{
  private int sectionnum;
  private String name;
  private String sectiontext;

  public SectionBean(){
    super();
  }

  public SectionBean(int sectionnum, String name, String sectiontext){
    super();
    this.sectionnum = sectionnum;
    this.name = name;
    this.sectiontext = sectiontext;
  }

  public int getSectionNum(){
    return this.sectionnum;
  }

  public String getName(){
    return this.name;
  }

  public String getSectionText(){
    return this.sectiontext;
  }

  public void setSectionNum(int sectionnum){
    this.sectionnum = sectionnum;
  }

  public void setSectionText(String sectionText){
    this.sectiontext = sectionText;
  }

  public void setName(String name){
    this.name = name;
  }

  public String toString(){
    StringBuilder builder = new StringBuilder();
    builder.append("Name: ");
    builder.append(this.name);
    builder.append("Section: ");
    builder.append(this.sectionnum);
    builder.append("Text: ");
    builder.append(this.sectiontext);
    return builder.toString();
  }
}
