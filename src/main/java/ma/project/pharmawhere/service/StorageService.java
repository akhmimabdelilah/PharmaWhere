package ma.project.pharmawhere.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import ma.project.pharmawhere.model.Image;
import ma.project.pharmawhere.repository.ImageRepository;

@Service
public class StorageService {
	 @Autowired
	  private ImageRepository imageRepository;

	  public Image store(MultipartFile file) throws IOException {
	    String fileName = StringUtils.cleanPath(file.getOriginalFilename());
	    Image Image = new Image(fileName, file.getContentType(), file.getBytes());

	    return imageRepository.save(Image);
	  }

	  public Image getFile(int id) {
	    return imageRepository.findById(id).get();
	  }
	  

}
