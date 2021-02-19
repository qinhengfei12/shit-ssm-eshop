package xyz.kmahyyg.eshopdemo.security;

import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class imgSecurity {
    public static boolean contentTypeCheck (MultipartFile file){
        String[] contentTypeArrs = {"image/png","image/jpeg","image/gif"};
        String ContentType = file.getContentType();
        Arrays.asList(contentTypeArrs).contains(ContentType);
        return Arrays.asList(contentTypeArrs).contains(ContentType);
    }
    public static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if(hex.length() < 2){
                sb.append(0);
            }
            sb.append(hex);
        }
        return sb.toString();
    }
    public static boolean magicCheck(MultipartFile file) throws IOException {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("image/jpeg", "ffd8ff");
        map.put("image/png", "89504e");
        map.put("image/gif", "474946");
        String hexMagic = bytesToHex(file.getBytes()).substring(0, 6);
        String value = map.get(file.getContentType());
        if (hexMagic.equals(value)){
            return true;
        }
        return false;
    }
    public static boolean suffixCheck(MultipartFile file){
        String[] suffixArrs = {"png","jpg","gif"};
        String filename = file.getOriginalFilename();
        String suffix = filename.substring(filename.lastIndexOf(".")+1);
        return Arrays.asList(suffixArrs).contains(suffix);
    }
    public static boolean imageIO (MultipartFile file) throws IOException {
            Image img = ImageIO.read(file.getInputStream());
            if (img == null || img.getWidth(null) <= 0 || img.getHeight(null) <= 0) {
                return false;
            }
            return true;
    }
    public static boolean sizeCheck (MultipartFile file){
        long fileSize =  file.getSize();
        long st = 131072;
        if (fileSize <= st) {
            return true;
        }
        return false;
    }
    public static boolean imgCheck(MultipartFile file) throws IOException {
        List<Boolean> booleanList = new LinkedList<>();
        booleanList.add(sizeCheck(file));
        booleanList.add(contentTypeCheck(file));
        booleanList.add(suffixCheck(file));
        booleanList.add(imageIO(file));
        booleanList.add(magicCheck(file));
        if (booleanList.contains(false)){
            return false;
        }
        return true;
    }
}
