package com.pog.holmestext;

import com.pog.ErrorTuple;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.Integer;
import java.lang.StringBuilder;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;
import java.util.concurrent.atomic.AtomicInteger;
import com.pog.Section;

public class HolmesTextProvider {

  public ErrorTuple<Stream<Section>> downloadNovelFromUrl(String url){
    try{
      URL website = new URL(url);
      BufferedReader buf = new BufferedReader(
                            new InputStreamReader(
                                website.openStream(), "UTF-8"));
      List<String> compactText = new ArrayList<>();
      String next = buf.readLine();
      StringBuilder oneline = new StringBuilder();
      while(next != null){
        if(next.equals("")){
          addToList(compactText, oneline);
          oneline = new StringBuilder();
        }else{
          oneline.append(next);
        }
        next = buf.readLine();
      }
      addToList(compactText, oneline);
      IntSupplier count = new IntSupplier();
      return new ErrorTuple<>(compactText.stream().map(s -> new Section(count::get, s)));
    }catch(Exception e){
      return new ErrorTuple<>(e);
    }
  }

  private void addToList(List<String> lines, StringBuilder builder){
    String line = builder.toString().trim();
    if(!line.equals("")){
      lines.add(line);
    }
  }

  private class IntSupplier implements Supplier<Integer>{
    private AtomicInteger count = new AtomicInteger(0);

    public Integer get(){
      return count.getAndIncrement();
    }
  }

}
