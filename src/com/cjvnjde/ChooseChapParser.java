package com.cjvnjde;

import com.cjvnjde.Parsers.MangafoxMe;
import com.cjvnjde.Parsers.MangareaderNet;
import com.cjvnjde.Parsers.MintmangaCom;
import com.cjvnjde.Parsers.ReadmangaMe;

import java.io.IOException;
import java.util.LinkedHashMap;

/**
 * Created by cjvnjde on 05.08.16.
 */
public class ChooseChapParser {

    private String mangaHost;
    private String mangaUrl;
    ChooseChapParser(String mangaUrl){
        TakeMangaUrl tmu = new TakeMangaUrl(mangaUrl);
        String urlArr[] = tmu.getUrl();
        this.mangaHost = urlArr[0];
        this.mangaUrl = mangaUrl;
    }

    ChapParser chooseChapParser(){
        switch(mangaHost){
            case "readmanga.me":
                return new ReadmangaMe();
            case "mintmanga.com":
                return new MintmangaCom();
            case "mangafox.me":
                return new MangafoxMe();
            case "www.mangareader.net":
                return new MangareaderNet();
        }
        return null;
    }
    LinkedHashMap chapParser() throws IOException {
        ChapParser cp = chooseChapParser();
        return cp.SearchChapters(mangaUrl);
    }

}
