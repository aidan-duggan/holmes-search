package com.pog;

import java.util.function.Supplier;

public class Section{

  private final int sectionNum;
  private final String sectionText;

  public Section(int sectionNum, String sectionText){
    this.sectionNum = sectionNum;
    this.sectionText = sectionText;
  }

  public Section(Supplier<Integer> sectionNum, String sectionText){
    this.sectionNum = sectionNum.get();
    this.sectionText = sectionText;
  }

  public int getSectionNum(){
    return this.sectionNum;
  }

  public String getSectionText(){
    return this.sectionText;
  }

}
