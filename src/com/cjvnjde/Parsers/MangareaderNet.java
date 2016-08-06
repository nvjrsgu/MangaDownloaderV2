package com.cjvnjde.Parsers;

import com.cjvnjde.ChapParser;
import com.cjvnjde.ImageParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.LinkedHashMap;

/**
 * Created by cjvnjde on 06.08.16.
 */
public class MangareaderNet implements ChapParser, ImageParser {

    @Override
    public LinkedHashMap SearchImages(String chapUrl) throws IOException {

        String tempArr2[] = chapUrl.split("/");
        chapUrl = "http://" + tempArr2[2] + "/" + tempArr2[3] + "/" + tempArr2[4];

        URL url = new URL(chapUrl);
        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
        String line = "";
        boolean table = false;
        boolean tr = false;
        boolean td = false;
        boolean breaK = false;
        int quantity = 0;
        LinkedHashMap<String, String> lhm = new LinkedHashMap<>();
        while (line != null) {
            line = br.readLine();
            if (line != null && line.contains("<table")) {
                table = true;
            }
            if (table && line.contains("</table")) {
                table = false;
            }
            if (table && line.contains("<tr")) {
                tr = true;
            }
            if (tr && line.contains("</tr")) {
                tr = false;
            }
            if (tr && line.contains("<td")) {
                td = true;
            }
            if (td && line.contains("</td")) {
                td = false;
            }
            if (td && line.contains("<img id=")) {
                String tempArr1[] = line.split("\"");
                for (String tempStr : tempArr1) {
                    if (tempStr.contains("http")) {
                        //line = tempStr;
                        String tempArr3[] = tempStr.split("/");
                        String tempStr2 = tempArr3[tempArr3.length-1];
                       // System.out.println(tempStr2+"   "+tempStr);
                        lhm.put(tempStr2, tempStr);
                        break;
                    }
                }
                if (breaK) {
                    break;
                }
            }
            if (line != null && line.contains("</select> of ")) {
                line = line.replaceAll("</select> of ", "").replaceAll("</div>", "");
                quantity = Integer.parseInt(line);
                breaK = true;
            }
        }
        line = "";
        for (int i = 1; i <= quantity; i++) {
            url = new URL(chapUrl + "/" + i);
            br = new BufferedReader(new InputStreamReader(url.openStream()));
            while (line != null) {
                line = br.readLine();
                if (line != null && line.contains("document['pu'] = 'http")) {
                    String tempArr3[] = line.split("\'");
                    String tempStr = tempArr3[3];
                    tempArr3 = tempStr.split("/");
                    String tempStr2 = tempArr3[tempArr3.length-1];
                    lhm.put(tempStr2, tempStr);
                    //System.out.println(tempStr2+"   "+tempStr);
                    break;
                }
            }
        }
        System.out.println(lhm);
            br.close();
            return lhm;
        }

        @Override
        public LinkedHashMap SearchChapters (String mangaUrl) throws IOException {

            String tempArr1[] = mangaUrl.split("/");
            mangaUrl = "http://" + tempArr1[2] + "/" + tempArr1[3];

            URL url = new URL(mangaUrl);
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
            String line = "";

            boolean table = false;
            boolean div = false;
            boolean tr = false;
            boolean td = false;

            LinkedHashMap<String, String> lhm = new LinkedHashMap<>();

            while (line != null) {
                line = br.readLine();

                if (line != null && line.contains("<table id")) {
                    table = true;
                    // System.out.println("table+");
                }
                if (table && line.contains("</table")) {
                    table = false;
                    //  System.out.println("table-");
                }
                if (table && line.contains("<tr")) {
                    tr = true;
                    // System.out.println("tr+");
                }
                if (tr && line.contains("</tr")) {
                    tr = false;
                    //System.out.println("tr-");
                }
                if (tr && line.contains("<td")) {
                    td = true;
                    // System.out.println("td+");
                }
                if (td && line.contains("<a href")) {
                   // System.out.println(line);
                    String tempArr[] = line.split("\"");
                    line = tempArr[1];
                    tempArr = line.split("/");
                    line = tempArr[1] + "/" + tempArr[2];
                    lhm.put(line, mangaUrl + "/" + tempArr[2]);
                }
                if (td && line.contains("</td")) {
                    td = false;
                    // System.out.println("td-");
                }

            }
            System.out.println(lhm);
            return lhm;
        }
    }

