package xyz.kmahyyg.eshopdemo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import xyz.kmahyyg.eshopdemo.utils.UserInputSanitizer;

@RestController
public class ItemRestController {
    @PostMapping("/api/search")
    void search(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String keyword = request.getParameter("q");
            //keyword = UserInputSanitizer.SanitizeUserInput(keyword);
            response.sendRedirect("/show/item/search?itemName=" + keyword);
        }catch (IOException e){
            response.sendRedirect("/show/user/login");
        }

    }
}
