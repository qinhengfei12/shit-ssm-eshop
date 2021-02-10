package xyz.kmahyyg.eshopdemo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.kmahyyg.eshopdemo.common.PublicResponse;
import xyz.kmahyyg.eshopdemo.enums.ErrorStatusEnum;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class GoodRestController {
    @PostMapping("/api/search")
    void search(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String keyword = request.getParameter("q");
        response.sendRedirect("/show/good/search?itemName="+keyword);
    }
}
