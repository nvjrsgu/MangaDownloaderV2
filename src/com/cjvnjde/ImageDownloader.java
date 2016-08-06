package com.cjvnjde;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by cjvnjde on 06.08.16.
 */
public class ImageDownloader {
    public ImageDownloader(String imUrl, String destinationFile) throws IOException {
        URL url = new URL(imUrl);
        InputStream is = url.openStream();
        OutputStream os = new FileOutputStream(destinationFile);
        byte[] b = new byte[2048];
        int length;
        while ((length = is.read(b)) != -1) {
            os.write(b, 0, length);
        }
        is.close();
        os.close();
    }
    public ImageDownloader(LinkedHashMap<String, String> lhm) throws IOException {
        Iterator<Map.Entry<String, String>> itr = lhm.entrySet().iterator();

        while(itr.hasNext()) {
            Map.Entry<String, String> entry = itr.next();
            URL url = new URL(entry.getValue());
            InputStream is = url.openStream();
            OutputStream os = new FileOutputStream(entry.getKey());
            byte[] b = new byte[2048];
            int length;
            while ((length = is.read(b)) != -1) {
                os.write(b, 0, length);
            }
            is.close();
            os.close();
        }
    }
    public ImageDownloader(LinkedHashMap<String, String> lhm, String pref) throws IOException {
        Iterator<Map.Entry<String, String>> itr = lhm.entrySet().iterator();

        while(itr.hasNext()) {
            Map.Entry<String, String> entry = itr.next();
            URL url = new URL(entry.getValue());
            InputStream is = url.openStream();
            OutputStream os = new FileOutputStream(pref+"_"+entry.getKey());
            byte[] b = new byte[2048];
            int length;
            while ((length = is.read(b)) != -1) {
                os.write(b, 0, length);
            }
            is.close();
            os.close();
        }
    }
    public ImageDownloader(LinkedHashMap<String, String> lhm, String pref, String folder) throws IOException {
        File f = new File(folder);
        if(!f.exists()){
            f.mkdirs();
        }
        Iterator<Map.Entry<String, String>> itr = lhm.entrySet().iterator();
        while(itr.hasNext()) {
            Map.Entry<String, String> entry = itr.next();
            URL url = new URL(entry.getValue());
            InputStream is = url.openStream();
            OutputStream os = new FileOutputStream(folder+"/"+pref+"_"+entry.getKey());
            byte[] b = new byte[2048];
            int length;
            while ((length = is.read(b)) != -1) {
                os.write(b, 0, length);
            }
            is.close();
            os.close();
        }
    }


}
