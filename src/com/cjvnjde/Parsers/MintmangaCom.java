package com.cjvnjde.Parsers;

import com.cjvnjde.ChapParser;
import com.cjvnjde.ImageParser;
import com.cjvnjde.MakeChapUrl;
import com.cjvnjde.TakeMangaUrl;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.LinkedList;

/**
 * Created by cjvnjde on 05.08.16.
 */
public class MintmangaCom implements ChapParser, ImageParser {




    public LinkedHashMap SearchChapters(String url) throws IOException {
        String chapUrl;
        String[] urlArr;

        TakeMangaUrl tmu = new TakeMangaUrl(url);
        urlArr = tmu.getUrl();
        chapUrl = "http://"+urlArr[0]+"/"+urlArr[1]+"/";

        URL mangaUrl = new URL(chapUrl);

        BufferedReader br = new BufferedReader(new InputStreamReader(mangaUrl.openStream()));
        String line = "";

        boolean table = false;
        boolean tr = false;
        boolean td = false;

        String chapUr;
        String url2[];

        LinkedHashMap<String, String> chapMap = new LinkedHashMap<>();

        while(line != null){
            line = br.readLine();
            if(line != null && line.contains("<table")){
                table = true;
            }
            if(table && line.contains("</table")){
                table = false;
            }
            if(table && line.contains("<tr")){
                tr = true;
            }
            if(table && tr && line.contains("</tr")){
                tr = false;
            }
            if(table && tr && line.contains("<td")){
                td = true;
            }
            if(table && tr && td && line.contains("</td")){
                td = false;
            }
            if(table && tr && td && line.contains("<a href=")){
                //line = line.replaceAll(urlArr[1], "");
                url2 = line.split("\"");
                line = url2[1];
                chapUr = "http://"+urlArr[0]+line+"/?mature=1";

                url2 = line.split("/");
                chapMap.put(url2[2]+"/"+url2[3],chapUr);
            }
        }
        br.close();
     //   System.out.println(chapMap);
        return chapMap;
    }

    public LinkedHashMap SearchImages(String chapterUrl) throws IOException {
        URL url = new URL(chapterUrl);
        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));

        String line = "";
        String chap1, chap2, chap3;
        chap1 = "";
        chap2 = "";
        chap3 = "";
        int div = 0;
        boolean script = false;
        LinkedHashMap<String, String> lhm = new LinkedHashMap<>();
        while(line != null){
            line = br.readLine();
            if(line != null) {
                if (line.contains("<div")) {
                    div++;
                }
                if (line.contains("</div")) {
                    div--;
                }
                if (div == 1 && line.contains("<script")) {
                    script = true;
                }
                if (div ==1 & script && line.contains("</script")) {
                    script = false;
                }
                if (script && div == 1 && line.contains("rm_h.init")){
                    line = line.replaceAll(" ", "").replaceAll("\'", "\"");
                    String linrarr1[] = line.split("\"");
                    int counter = 0;
                    for(String arr: linrarr1){
                        if(arr.contains("/")){
                            switch(counter){
                                case 0: chap2 = arr;
                                    counter++;
                                    break;
                                case 1: chap1 = arr;
                                    counter++;
                                    break;
                                case 2: chap3 = arr;
                                    String arr1[] = chap3.split("/");
                                    lhm.put(arr1[arr1.length-1], chap1+chap2+chap3);
                                    counter = 0;
                                    break;
                            }
                        }
                    }
                   // System.out.println(line);
                }

            }
        }
       // System.out.println(lhm);
        return lhm;
    }
}
