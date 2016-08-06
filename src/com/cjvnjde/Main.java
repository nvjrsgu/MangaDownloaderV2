package com.cjvnjde;

import com.cjvnjde.Parsers.MintmangaCom;
import com.cjvnjde.Parsers.ReadmangaMe;

import java.io.IOException;
import java.util.LinkedHashMap;

public class Main {
    int range;
    String url;
    String host;
    String mangaName;
    String chapters[];
    public static void main(String[] args) throws IOException {
        TakeMangaUrl tmu = new TakeMangaUrl("http://readmanga.me/one__piece/vol36/346#page=15");
        System.out.println(tmu.getMangaHost());
        ReadmangaMe rmm = new ReadmangaMe();
        MintmangaCom mmc = new MintmangaCom();
        mmc.SearchImages("http://readmanga.me/one__piece/vol84/833");
        ImageDownloader id = new ImageDownloader("http://e5.postfact.ru/auto/18/62/28/One-piece.ru_Credits.png_res.jpg", "One-piece.ru_Credits.png_res.jpg");
       // ChooseChapParser ccp = new ChooseChapParser("http://mintmanga.com/sun_ken_rock/vol25/171?mature=1");
       // ccp.chapParser();

        //ChapParser cp = ccp.chooseChapParser();
        //cp.SearchChapters("http://mintmanga.com/sun_ken_rock/vol25/171?mature=1");
    }

}
