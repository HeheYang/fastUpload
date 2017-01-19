package fastUpload.domain.mapper;


import fastUpload.domain.model.Perfile;

import java.util.List;

/**
 * Created by HEHE on 2016/12/16.
 */
public interface PerFileMapper {

    int insert(Perfile record);

    List<Perfile> getPerfileList(String FileMD5);


    List<String> getPerfile(String FileMD5);

    void deletePerFile(String md5);

    List<Perfile> getFirstFile(String FileMD5);

    int isAllParts (String FileMD5);

}
