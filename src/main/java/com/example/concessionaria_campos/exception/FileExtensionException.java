package com.example.concessionaria_campos.exception;

import java.util.List;

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
