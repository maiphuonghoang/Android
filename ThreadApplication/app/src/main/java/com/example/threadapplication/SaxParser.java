package com.example.threadapplication;

import android.util.Log;

import com.example.threadapplication.model.Item;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import java.io.InputStream;
import java.util.List;

import javax.xml.parsers.SAXParserFactory;

public class SaxParser {
    public static List<Item> xmlParser(InputStream is){
        List<Item> list = null;
        try {
            XMLReader reader = SAXParserFactory.newInstance().newSAXParser().getXMLReader();
            SaxHandler handler = new SaxHandler();
            reader.setContentHandler(handler);
            reader.parse(new InputSource(is));
            list = handler.getItems();
        }catch(Exception e){
            Log.d("Error: ", e.getMessage());
        }
        return list;
    };

}
