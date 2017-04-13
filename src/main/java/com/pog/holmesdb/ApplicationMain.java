package com.pog.holmesdb;

import com.google.common.primitives.Booleans;
import com.pog.holmesapp.HolmesTextPersistance;

public class ApplicationMain{
  public static void main(String[] args){
    boolean create = parseCreate(args);
    boolean dropFirst = parseDrop(args);

    HolmesDb db = new HolmesDb();

    try {
      db.init("127.0.0.1");

      if(dropFirst){
        System.out.println("Dropping");
        db.dropKeyspace();
      }

      if(create){
        System.out.println("Creating");
        db.createKeyspaceAndTable();
      }

    } finally {
      db.close();
    }
  }

  private static boolean parseDrop(String[] args){
    boolean drop = false;
    if(args.length > 1){
      drop = Boolean.parseBoolean(args[1]);
    }
    return drop;
  }

  private static boolean parseCreate(String[] args){
    boolean create = false;
    if(args.length > 0){
      create = Boolean.parseBoolean(args[0]);
    }
    return create;
  }
}
