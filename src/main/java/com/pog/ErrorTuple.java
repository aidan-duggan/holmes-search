package com.pog;

import java.util.Optional;
import java.lang.Exception;

public class ErrorTuple<T>{
  private final Optional<T> result;
  private final Optional<Exception> error;

  public ErrorTuple(T result){
    super();
    this.result = Optional.of(result);
    this.error = Optional.empty();
  }

  public ErrorTuple(Exception e){
    super();
    this.error = Optional.of(e);
    this.result = Optional.empty();
  }

  public boolean isInError(){
    return error.isPresent();
  }

  public T getResult(){
    return result.get();
  }

  public Exception getException(){
    return error.get();
  }
}
