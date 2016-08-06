package com.cjvnjde;

import com.cjvnjde.Parsers.MintmangaCom;
import com.cjvnjde.Parsers.ReadmangaMe;

import java.io.IOException;
import java.util.*;

public class Main {
    int range;
    String url;
    String host;
    String mangaName;
    String chapters[];
    public static void main(String[] args) throws IOException {
        /*
        //получаем все главы манги
        ChooseChapParser ccp = new ChooseChapParser("http://mintmanga.com/sun_ken_rock/vol25/171?mature=1");
        LinkedHashMap<String, String> lhm2 = ccp.chapParser();
        Iterator<Map.Entry<String, String>> itr = lhm2.entrySet().iterator();
        LinkedList<String> al = new LinkedList<>();
        System.out.println("chapters:");
        while(itr.hasNext()) {
            Map.Entry<String, String> entry = itr.next();
            al.push(entry.getKey());
            System.out.print(entry.getKey()+", ");
        }
        System.out.println();
        System.out.println("Chapter list:");
        int i = 0;
        for(String str: al){
            System.out.println(i+" = "+str);
                    i++;
        }
        //скачиваем главы
        int finish = 0;
        int start = 0;
        String folder = "mintmanga.com/sun_ken_rock";
        RangeDownloader rd2 = new RangeDownloader(lhm2, al.get(finish), al.get(start), folder);
        */
        Console con = new Console();
        con.runConsole();

    }

}
