package com.company;

import java.net.MalformedURLException;
import java.net.URL;

public class Main {

    public static void main(String[] args) throws MalformedURLException {
        FileDownloader downloaded = new FileDownloader(
                new URL("https://s3.zylowski.net/public/input/2.txt")
        );
        downloaded.save("2.txt");
        
        Loop loop = new Loop();
        loop.start();
    }
}
