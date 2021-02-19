package xyz.kmahyyg.eshopdemo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import xyz.kmahyyg.eshopdemo.dao.StorageService;
import xyz.kmahyyg.eshopdemo.security.imgSecurity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
public class FileUploadController {

	private final StorageService storageService;

	@Autowired
	public FileUploadController(StorageService storageService) {
		this.storageService = storageService;
	}

	@GetMapping("/show/user/home")
	public String listUploadedFiles(){
		return "userHome";
	}

	@RequestMapping(value = "/files/{filename:.+}",produces = MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	public ResponseEntity<byte[]> serveFile(@PathVariable String filename) throws IOException {
		Resource file = storageService.loadAsResource(filename);
		InputStream in = file.getInputStream();
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		byte[] buff = new byte[100];
		int rc = 0;
		while ((rc = in.read(buff, 0, 100)) > 0) {
			byteArrayOutputStream.write(buff, 0, rc);
		}
		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_PNG);
		return new ResponseEntity<byte[]>(byteArrayOutputStream.toByteArray(), headers, HttpStatus.CREATED);
}

	@PostMapping("/api/user/imgUpload")
	public String handleFileUpload(@RequestParam("file") MultipartFile file,
			RedirectAttributes redirectAttributes) throws IOException {
		boolean checkResult = imgSecurity.imgCheck(file);
		if (String.valueOf(checkResult) == "true"){
			String fileName = storageService.store(file);
			String fileURL = "/files/"+fileName;
			redirectAttributes.addFlashAttribute("message2", "You successfully uploaded !");
			redirectAttributes.addFlashAttribute("imgPath", fileURL);
		}else {
			redirectAttributes.addFlashAttribute("message2", "Upload failed !");
		}
		return "redirect:/show/user/home";
	}

}
