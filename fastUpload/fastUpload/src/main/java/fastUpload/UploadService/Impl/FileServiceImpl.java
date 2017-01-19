package fastUpload.UploadService.Impl;

import fastUpload.UploadService.FileService;
import fastUpload.domain.mapper.FileMapper;
import fastUpload.domain.model.File;
import fastUpload.domain.model.Perfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Created by HEHE on 2016/12/13.
 */
@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileMapper fileMapper;



    public File checkMD5FileExist(String MD5) {
        return fileMapper.selectByMD5(MD5);
    }

    @Override
    public boolean insert(File file) {
        if (fileMapper.insert(file) == 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean getFirstFileByMd5(String FileMD5) {

        if (fileMapper.getFirstFileByMd5(FileMD5).size()!=0){
            return true;
        }
        return false;
    }
}
