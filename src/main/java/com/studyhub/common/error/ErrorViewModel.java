package com.studyhub.common.error;

public record ErrorViewModel(
    int status,
    String title,
    String message,
    String path) { 
        
}