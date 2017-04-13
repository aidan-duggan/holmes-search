package com.pog.holmessearch;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Assert;

public class StringSearchCountTest {

  @Test
  public void countTest(){
    Integer count = HolmesSearch.count("ab-ab-ab-ab-ab-ab","ab");
    Assert.assertEquals(new Integer(6), count);
  }
}
