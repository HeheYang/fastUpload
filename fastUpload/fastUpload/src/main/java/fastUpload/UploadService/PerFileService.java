package fastUpload.UploadService;

import fastUpload.domain.model.File;
import fastUpload.domain.model.Perfile;

import java.util.List;

/**
 * Created by HEHE on 2016/12/16.
 */
public interface PerFileService {
    public boolean insert(Perfile file);

    public List<Perfile> getPerfileList(String md5);

    public List<String> getPerfile(String FileMD5);

    public void deletePerFile(String md5);

    int isAllParts (String FileMD5);
}
