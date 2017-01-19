package fastUpload.domain.mapper;


import fastUpload.domain.model.File;
import fastUpload.domain.model.Perfile;

import java.util.List;

/**
 * Created by HEHE on 2016/12/13.
 */
public interface FileMapper {
    int deleteByPrimaryKey(Long fileId);

    int insert(File record);

    int insertSelective(File record);

    File selectByPrimaryKey(Long fileId);

    File selectByMD5(String MD5);

    int updateByPrimaryKeySelective(File record);

    int updateByPrimaryKey(File record);

    List<Perfile> getFirstFileByMd5(String FileMD5);
}
