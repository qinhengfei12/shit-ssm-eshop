package xyz.kmahyyg.eshopdemo.security;

import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class UploadImgSecurity {
    public static boolean contentTypeCheck (MultipartFile file){
        String[] contentTypeArrs = {"image/png","image/jpeg","image/gif"};
        String ContentType = file.getContentType();
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
        HashMap<String, String> map = new HashMap<>();
        map.put("image/jpeg", "ffd8ff");
        map.put("image/png", "89504e");
        map.put("image/gif", "474946");
        String hexMagic = bytesToHex(file.getBytes()).substring(0, 6);
        String value = map.get(file.getContentType());
        return hexMagic.equals(value);
    }
    public static boolean extensionCheck(MultipartFile file){
        String[] suffixArrs = {"png","jpg","gif"};
        String filename = file.getOriginalFilename();
        String suffix = null;
        if (filename != null) {
            suffix = filename.substring(filename.lastIndexOf(".")+1);
        }
        return Arrays.asList(suffixArrs).contains(suffix);
    }
    public static boolean imageIOContentCheck (MultipartFile file) throws IOException {
            Image img = ImageIO.read(file.getInputStream());
        return img != null && img.getWidth(null) > 0 && img.getHeight(null) > 0;
    }
    public static boolean sizeCheck (MultipartFile file){
        long fileSize =  file.getSize();
        long st = 524288;
        return fileSize <= st;
    }
    public static boolean imgCheck(MultipartFile file) throws IOException {
        List<Boolean> booleanList = new LinkedList<>();
        booleanList.add(sizeCheck(file));
        booleanList.add(contentTypeCheck(file));
        booleanList.add(extensionCheck(file));
        booleanList.add(imageIOContentCheck(file));
        booleanList.add(magicCheck(file));
        return !booleanList.contains(false);
    }
}
