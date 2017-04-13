package com.pog.holmesapp;

import com.datastax.driver.core.ParseUtils;
import com.pog.ErrorTuple;
import com.pog.Section;
import com.pog.holmesdb.HolmesDb;
import com.pog.holmestext.HolmesTextProvider;
import java.io.Serializable;
import java.util.UUID;
import java.util.stream.Stream;

public class HolmesTextPersistance implements Serializable{

  private static final String STATEMENT = "INSERT INTO holmesdb.books (name, sectionnum, sectiontext) VALUES (%s, %d, %s)";

  public void writeTextToDb(String cassandraIp, String url, String name){
    HolmesDb db = new HolmesDb();
    db.init(cassandraIp);
    ErrorTuple<Stream<Section>> textTuple = new HolmesTextProvider().downloadNovelFromUrl(url);
    if(textTuple.isInError()){
      return;
    }

    String escapedName = ParseUtils.quote(name);
    textTuple.getResult()
    .map(s -> String.format(STATEMENT, escapedName,s.getSectionNum(), ParseUtils.quote(s.getSectionText())))
    .forEach(db::executionVoidStatement);
  }

}
