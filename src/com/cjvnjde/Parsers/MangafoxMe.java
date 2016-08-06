package com.cjvnjde.Parsers;

import com.cjvnjde.ChapParser;
import com.cjvnjde.ImageParser;
import com.cjvnjde.TakeMangaUrl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.zip.GZIPInputStream;

/**
 * Created by cjvnjde on 06.08.16.
 */
public class MangafoxMe implements ChapParser, ImageParser {

    @Override
    public LinkedHashMap SearchImages(String chapterUrl) throws IOException {
        String chapUrlArr[] = chapterUrl.split("/");

        String chapterPreUrl = "http://"+chapUrlArr[2]+"/"+chapUrlArr[3]+"/"+chapUrlArr[4]+"/"+chapUrlArr[5]+"/"+chapUrlArr[6];
        chapterUrl = chapterPreUrl+"/1.html";
        //System.out.println(chapterUrl);
        URL url = new URL(chapterUrl);

        BufferedReader br = new BufferedReader(new InputStreamReader(new GZIPInputStream(url.openStream())));

        int numberOfPages = 1;
        String line = "";
        boolean script = false;
        LinkedHashMap<String, String> lhm = new LinkedHashMap<>();
        while(line != null){
            line = br.readLine();
            if(line.contains("var total_pages=")){
                line = line.replaceAll(";","=");
                String number[] = line.split("=");
                numberOfPages = Integer.parseInt(number[1]);
                //System.out.println(numberOfPages);
                break;
            }
            //System.out.println(line);
        }
        line = "";

        for(int i = 1; i <= numberOfPages; i++) {
            chapterUrl = chapterPreUrl+"/"+i+".html";
            url = new URL(chapterUrl);
            br = new BufferedReader(new InputStreamReader(new GZIPInputStream(url.openStream())));
            int div = 0;
            while(line != null){
                line = br.readLine();
                if(line.contains("<div")){
                    div++;
                }
                if(line.contains("</div")){
                    div--;
                }
                if(div == 2 && line.contains("<img src=")){
                    String imUrl[] = line.split("\"");
                    String imName[] = imUrl[1].split("/");
                    lhm.put(imName[imName.length-1], imUrl[1]);
                   // System.out.println(line);
                    break;
                }
            }
        }
        br.close();
       // System.out.println(lhm);
        return lhm;
        }



    @Override
    public LinkedHashMap SearchChapters(String url) throws IOException {
        String chapUrl;
        String[] urlArr;

        TakeMangaUrl tmu = new TakeMangaUrl(url);
        urlArr = tmu.getUrl();
        chapUrl = "http://"+urlArr[0]+"/"+urlArr[1]+"/"+urlArr[2]+"/";
        System.out.println(chapUrl);

        URL mangaUrl = new URL(chapUrl);

        BufferedReader br = new BufferedReader(new InputStreamReader(mangaUrl.openStream()));
        String line = "";

        boolean h = false;
        boolean li = false;
        boolean ul = false;


        String chapUr;
        String url2[];

        LinkedHashMap<String, String> chapMap = new LinkedHashMap<>();

        while(line != null){

                line = br.readLine();
            if(line != null) {
                // System.out.println(line);
                if (line.contains("<ul")) {
                    ul = true;
                    //System.out.println("ul+");
                }
                if (ul && line.contains("</ul")) {
                    ul = false;
                    //System.out.println("ul-");
                }
                if (ul && line.contains("<li")) {
                    li = true;
                   // System.out.println("li+");
                }
                if (ul && li && line.contains("<li")) {
                    li = false;
                   // System.out.println("li-");
                }
                if (ul  && line.contains("<h")) {
                    h = true;
                   // System.out.println("h3+");
                }
                if (ul && h && line.contains("</h")) {
                    h = false;
                   // System.out.println("h3-");
                }

                if (h && line.contains("<a href=")) {
                 //   System.out.println(line);
                    //line = line.replaceAll(urlArr[1], "");
                    url2 = line.split("\"");
                    line = url2[1];
                    chapUr = line;
                    url2 = line.split("/");
                    chapMap.put(url2[url2.length-3]+"/"+url2[url2.length-2],chapUr);
                }
            }
        }
        br.close();
         System.out.println(chapMap);
        return chapMap;
    }
}
