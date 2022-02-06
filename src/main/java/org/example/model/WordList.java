package org.example.model;

import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotEmpty;
import java.util.List;


public class WordList {

    @NotEmpty(message = "поле не должно быть пустым")
    @URL(message = "невалидный URL")
    String url;

    List<String> list;

    public WordList(){

    }

    public WordList(List<String> list) {
        this.list = list;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
