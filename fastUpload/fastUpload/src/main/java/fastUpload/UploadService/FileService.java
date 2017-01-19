package fastUpload.UploadService;


import fastUpload.domain.model.File;
import fastUpload.domain.model.Perfile;

import java.util.List;

/**
 * Created by HEHE on 2016/12/13.
 */
public interface FileService {
    public File checkMD5FileExist(String MD5);
    public boolean insert(File file);
    Boolean getFirstFileByMd5(String FileMD5);
}
