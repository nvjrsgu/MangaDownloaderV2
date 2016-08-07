package com.cjvnjde;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by cjvnjde on 06.08.16.
 */
public class Console {
    public void runConsole() throws IOException {
        System.out.println("Supportes sites: \n      mintmanga.com\n      readmanga.me\n      mangafox.me");
        System.out.println("Enter manga url:");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String url = br.readLine();
        System.out.println(url);
      //  br.close();
        ChooseChapParser ccp = new ChooseChapParser(url);
        LinkedHashMap<String, String> lhm2 = ccp.chapParser();
        Iterator<Map.Entry<String, String>> itr = lhm2.entrySet().iterator();
        LinkedList<String> al = new LinkedList<>();
        //System.out.println("chapters:");
        while(itr.hasNext()) {
            Map.Entry<String, String> entry = itr.next();
            al.push(entry.getKey());
            //System.out.print(entry.getKey()+", ");
        }
        System.out.println();
        System.out.println("Chapter list:");
        int i = 0;
        for(String str: al){
            if(i%4==0) {
                System.out.println(i + " = " + str+"        ");
            }else{
                System.out.print(i + " = " + str+"      ");
            }
            i++;
        }
        System.out.println();
        System.out.println("choose chaptes for download (exemple: 14-50):");

       // br = new BufferedReader(new InputStreamReader(System.in));
        String chapters = br.readLine();
        System.out.println(chapters);
       // br.close();

        String[] range = chapters.split("-");
        int finish = Integer.parseInt(range[1]);
        int start = Integer.parseInt(range[0]);
        System.out.println("Download from: "+al.get(start)+" to "+al.get(finish));

        System.out.println("Enter folder for download: ");

      //  br = new BufferedReader(new InputStreamReader(System.in));
        String folder = br.readLine();
        br.close();
        System.out.println("Folder "+folder);

        RangeDownloader rd2 = new RangeDownloader(lhm2, al.get(finish), al.get(start), folder, true);
    }
}
