package com.cjvnjde;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by cjvnjde on 06.08.16.
 */
public class RangeDownloader {
    public RangeDownloader(LinkedHashMap<String, String> lhm, String start, String finish) throws IOException {
        Iterator<Map.Entry<String, String>> itr = lhm.entrySet().iterator();
        boolean key = false;
        while(itr.hasNext()) {
            Map.Entry<String, String> entry = itr.next();
            if(key || entry.getKey().compareTo(start)==0){
                key = true;
                if(entry.getKey().compareTo(finish)==0){
                    return;
                }
                ChooseImageParser cip = new ChooseImageParser(entry.getValue());

                LinkedHashMap<String, String> lhm2 = cip.imageParser();
                String strArr[] = entry.getKey().split("/");
                ImageDownloader id = new ImageDownloader(lhm2, strArr[0]+"_"+strArr[1]);
                System.out.println("Downloaded: "+entry.getKey()+" = "+entry.getValue());
            }
        }
    }

    public RangeDownloader(LinkedHashMap<String, String> lhm, String start, String finish, String folder) throws IOException {
        Iterator<Map.Entry<String, String>> itr = lhm.entrySet().iterator();
        boolean key = false;
        File f = new File(folder);
        if(!f.exists()){
            f.mkdirs();
        }


        while(itr.hasNext()) {
            Map.Entry<String, String> entry = itr.next();
            if(key || entry.getKey().compareTo(start)==0){
                key = true;
                if(entry.getKey().compareTo(finish)==0){
                    key = false;
                }
                ChooseImageParser cip = new ChooseImageParser(entry.getValue());

                LinkedHashMap<String, String> lhm2 = cip.imageParser();
                String strArr[] = entry.getKey().split("/");
                ImageDownloader id = new ImageDownloader(lhm2, strArr[0]+"_"+strArr[1], folder);
                System.out.println("Downloaded: "+entry.getKey()+" = "+entry.getValue());
            }
        }
    }
}
