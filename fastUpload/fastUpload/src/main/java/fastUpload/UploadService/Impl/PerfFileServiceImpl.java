package fastUpload.UploadService.Impl;

import fastUpload.UploadService.PerFileService;
import fastUpload.domain.mapper.PerFileMapper;
import fastUpload.domain.model.File;
import fastUpload.domain.model.Perfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by HEHE on 2016/12/16.
 */
@Service
public class PerfFileServiceImpl implements PerFileService{

    @Autowired
    private PerFileMapper perFileMapper;

    @Override
    public boolean insert(Perfile file) {
        if(perFileMapper.insert(file)==1){
        return true;
        }else{
            return false;
        }
    }

    @Override
    public List<Perfile> getPerfileList(String md5) {
        List<Perfile> perfileList=perFileMapper.getPerfileList(md5);
        return perfileList;
    }

    @Override
    public List<String> getPerfile(String FileMD5) {
        List<String> chucklist=perFileMapper.getPerfile(FileMD5);
        return chucklist;
    }

    @Override
    public void deletePerFile(String md5) {
        perFileMapper.deletePerFile(md5);
    }

    @Override
    public int isAllParts(String FileMD5) {
        return perFileMapper.isAllParts(FileMD5);
    }
}
