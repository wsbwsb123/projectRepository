package com.company;

import java.net.URL;
import java.io.FileOutputStream;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileDownloader {
    
    private final URL address;
    
    public FileDownloader(URL address) {
        this.address = address;
    }
    
    public void save(String path) {
        try {
            BufferedInputStream istream = new BufferedInputStream(address.openStream());
            FileOutputStream ostream = new FileOutputStream(path);
            
            byte data[] = new byte[1024];
            int byteContent;
        
            while ((byteContent = istream.read(data, 0, 1024)) != -1) {
                ostream.write(data, 0, byteContent);
            }
            
            ostream.close();
            istream.close();
            
        } catch (IOException ex) {
            Logger.getLogger(FileDownloader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
