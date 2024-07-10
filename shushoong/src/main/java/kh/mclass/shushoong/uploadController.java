package kh.mclass.shushoong;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;


@Controller
public class uploadController {

	@Autowired
	private Cloudinary cloudinary;
	
	@GetMapping("/upload")
	public String uploadMain() {
		return "uploadPractice";
	}
	
	@PostMapping("/upload/file")
	public String uploadFile(Model model, @RequestParam("uploadfiles") MultipartFile[] files, @RequestParam("content") String content) {
		List<String> imageUrls = new ArrayList<>();
		System.out.println("이것이 content : " + content);
		for(MultipartFile file : files) {
			try {
				Map<String, Object> uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
				String imageUrl = uploadResult.get("url").toString();
				imageUrls.add(imageUrl);
				System.out.println("이미지 url : " + imageUrl);
				model.addAttribute("url", imageUrl);
			} catch (IOException e) {
				e.printStackTrace();
				model.addAttribute("url", null);
			}
		}
		return "uploadPractice";
	}
}
