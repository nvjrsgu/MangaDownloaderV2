package com.cjvnjde;

/**
 * Created by cjvnjde on 05.08.16.
 */
public class TakeMangaUrl {
    private String url[];
    public TakeMangaUrl(String dirtyUrl){
        if(dirtyUrl.contains("http://")){
            dirtyUrl = dirtyUrl.replaceAll("http://", "");
        }
        if(dirtyUrl.contains("https://")){
            dirtyUrl = dirtyUrl.replaceAll("https://", "");
        }
        this.url = dirtyUrl.split("/");
    }
    String getMangaHost(){
        return url[0];
    }
    public String[] getUrl(){
        return url;
    }

}
