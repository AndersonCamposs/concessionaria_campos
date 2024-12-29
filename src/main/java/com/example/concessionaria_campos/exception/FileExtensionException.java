package com.example.concessionaria_campos.exception;

import lombok.Getter;

import java.util.List;
import java.util.Set;

@Getter
public class FileExtensionException extends RuntimeException {
    private List<String> files;

    public FileExtensionException(String message) {
        super(message);
    }

    public FileExtensionException(String message, List<String> files) {
      super(message);
      this.files = files;
    }
}
