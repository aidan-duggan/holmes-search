package com.pog.holmesdb;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.SocketOptions;
import com.google.common.primitives.Booleans;
import java.lang.RuntimeException;

public class HolmesDb{

  private static final String KEYSPACE = "create keyspace if not exists holmesdb with replication = {'class': 'SimpleStrategy', 'replication_factor' : 3}";
  private static final String MAINTABLE = "create table holmesdb.books (name text, sectionnum int, sectiontext text, PRIMARY KEY (name, sectionnum))";
  private static final String DROPEVERYTHING = "drop keyspace if exists holmesdb";

  private Cluster cluster;
  private Session session;

  public void init(String ip){
    this.cluster = buildCluster(ip);
    this.session = buildSession(cluster);
    printVersion(this.session);
  }

  public void close(){
    closeCluster(this.cluster);
  }

  public void dropKeyspace(){
    executionVoidStatement(DROPEVERYTHING);
  }

  public void createKeyspaceAndTable(){
    executionVoidStatement(KEYSPACE);
    executionVoidStatement(MAINTABLE);
  }

  public void executionVoidStatement(String statement){
    try{
      this.session.execute(statement);
    }catch(RuntimeException e){
      System.out.println(statement);
      throw e;
    }
  }

  private void printVersion(Session session){
    ResultSet rs = session.execute("select release_version from system.local");
    Row row = rs.one();
    System.out.println(row.getString("release_version"));
  }

  private Cluster buildCluster(String ip){
    return Cluster.builder()
    .addContactPoint(ip)
    .withSocketOptions(
    new SocketOptions()
    .setConnectTimeoutMillis(30000))
    .build();
  }

  private void closeCluster(Cluster cluster){
    if (cluster != null) cluster.close();
  }

  private Session buildSession(Cluster cluster){
    return cluster.connect();
  }
}
