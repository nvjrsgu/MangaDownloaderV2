package com.cjvnjde;

import com.cjvnjde.Parsers.MangafoxMe;
import com.cjvnjde.Parsers.MangareaderNet;
import com.cjvnjde.Parsers.MintmangaCom;
import com.cjvnjde.Parsers.ReadmangaMe;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
    MangareaderNet mrn = new MangareaderNet();
     //mfm.SearchImages("http://mangafox.me/manga/one_piece/v56/c549/1.html");
       // mrn.SearchImages("http://www.mangareader.net/fairy-tail-zero/1/1");
    //    mrn.SearchChapters("http://www.mangareader.net/fairy-tail-zero/1/1");
       Console con = new Console();
      con.runConsole();
/*
        URL url = new URL("http://i4.mangareader.net/fairy-tail-zero/12/fairy-tail-zero-5752788.jpg");
        HttpURLConnection connection =(HttpURLConnection) url.openConnection();
        connection.addRequestProperty("User-Agent", "Mozilla/4.76");
        InputStream is = connection.getInputStream();
        OutputStream os = new FileOutputStream("fairy-tail-zero-5752788.jpg");
        byte[] b = new byte[2048];
        int length;
        while ((length = is.read(b)) != -1) {
            os.write(b, 0, length);
        }
        is.close();
        os.close();
        */
     //  MintmangaCom mmc = new MintmangaCom();
     //   mmc.SearchChapters("http://mintmanga.com/love_mate/vol18/167");
      // mmc.SearchImages("http://mintmanga.com/love_mate/vol18/167");

      // MangafoxMe mfm = new MangafoxMe();
      //  mfm.SearchChapters("http://mangafox.me/manga/bleach/vTBD/c685/1.html");
      // mfm.SearchImages("http://mangafox.me/manga/bleach/vTBD/c685/1.html");

    }

}
