package fastUpload.UploadController;

import fastUpload.UploadService.FileService;
import fastUpload.UploadService.PerFileService;
import fastUpload.domain.model.Perfile;
import fastUpload.util.BizResponse;
import fastUpload.util.MD5Utils;
import fastUpload.util.UploadProperties;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by HEHE on 2016/12/13.
 */
@Controller
@RequestMapping(value = "/upload")
public class UploadController {

    @Autowired
    private UploadProperties uploadProperties;
    @Autowired
    private FileService fileService;
    @Autowired
    private PerFileService perFileService;

    @RequestMapping(value = "/uploadFile", method = {RequestMethod.POST})
    @ResponseBody
    public String upload(Long chunks, Long chunk, String fileMd5, String name, String type, Long size, String fileSavePath, Long lastModifiedDate,
                       @RequestParam(value = "file") MultipartFile[] files, HttpSession session) {

        System.out.println(name);
        String msg="";
        String userId = "123456";
        if (chunks == null) {
            chunks = 1L;
            chunk = 0L;
        }

        /*if (fileMd5 == null || fileMd5.equals("") || userId == null || userId.equals("")) {
            return ;
        }*/

        //用md5码校验该分片是否已经上传过
        List<String> chucklist = perFileService.getPerfile(fileMd5);
        if(chucklist.contains(String.valueOf(chunk))){
            System.out.println("第"+chunk+"片已经上传过");
             msg="第"+chunk+"片已经上传过";
            return msg;
        }



        Date getDate = new Date(lastModifiedDate);
        String fileName = fileMd5;
        try {
            for (MultipartFile mf : files) {
                if (!mf.isEmpty()) {
                    // 临时目录用来存放所有分片文件
                    String tempFileDir = getTempFilePath(getDate, fileMd5, userId) + File.separator + "temp";
                    File parentFileDir = creatFloder(tempFileDir);
                    // 分片处理时，前台会多次调用上传接口，每次都会上传文件的一部分到后台(默认每片为5M)
                    File tempPartFile = new File(parentFileDir, fileName + "_" + chunk + ".part");
                    InputStream inputStream = null;
                    try {
                        if (mf != null) {
                            inputStream = mf.getInputStream();
                            FileUtils.copyInputStreamToFile(inputStream, tempPartFile);
                        }
                    } catch (IOException e1) {
                        e1.printStackTrace();
                        return e1.toString();
                    } finally {
                        if (inputStream != null) {
                            try {
                                inputStream.close();
                                Perfile perfile = new Perfile(tempPartFile.length(),tempPartFile.getName(), chunk, tempPartFile.getAbsolutePath(), fileMd5);
                               String id= UUID.randomUUID().toString().substring(1,31);
                                perfile.setPerFileId(id);
                                perFileService.insert(perfile);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }



                    /**在多线程情况下将上传好的文件放到另一个文件夹中 以备检查完整性**/

                 /*   Long lastSize = size - (chunks - 1) * 5 * 1024 * 1024;
                    int perSize = 5 * 1024 * 1024;
                    //检查上传文件是否存在，是否符合大小，条件满足就转移
                    if ((tempPartFile.exists() && tempPartFile.length() == perSize && chunk != (chunks - 1))
                            || (tempPartFile.exists() && tempPartFile.length() == lastSize && chunk == (chunks - 1))) {
                        String cTempFileDir = getCopyFilePath(getDate, fileMd5, userId);
                        File parentFile = creatFloder(cTempFileDir);

                        File cTempPartFile = new File(parentFile, fileName + "_" + chunk + ".part");

                        FileOutputStream destTempfos1 = null;
                        try {
                            destTempfos1 = new FileOutputStream(cTempPartFile);

                            FileUtils.copyFile(tempPartFile, destTempfos1);

                        } catch (Exception e) {
                            e.printStackTrace();
                            return e.getMessage();
                        } finally {
                            if (destTempfos1 != null) {
                                try {
                                    destTempfos1.close();
                                } catch (Exception e) {
                                    e.printStackTrace();

                                }
                            }
                        }
                        Perfile perfile = new Perfile(tempPartFile.length(), tempPartFile.getName(), chunk, tempPartFile.getAbsolutePath(), fileMd5);
                        perFileService.insert(perfile);
*/

                        // 是否全部上传完成
                        // 所有分片都存在才说明整个文件上传完成
                        // 所有分片文件都上传完成
                        // 将所有分片文件合并到一个文件中

                    /**
                     * 修改后变为从分片表中读取数据，只要数量够了就整合
                     * */

                        if (perFileService.isAllParts(fileMd5) == chunks) {
                            System.out.println("上传完毕");
                            //File destTempFile = new File(getTempFilePath(getDate, fileMd5, userId), fileName);
                            //将生成的文件换一个目录
                            File destTempFile = new File(getTempFilePath(getDate, fileMd5, userId), name);
                            for (int i = 0; i < chunks; i++) {
                                File partFile = new File(parentFileDir, fileName + "_" + i + ".part");
                                FileOutputStream destTempfos = null;
                                try {
                                    destTempfos = new FileOutputStream(destTempFile, true);

                                    FileUtils.copyFile(partFile, destTempfos);

                                } catch (Exception e) {
                                    e.printStackTrace();
                                    return e.toString();
                                } finally {
                                    if (destTempfos != null) {
                                        try {
                                            destTempfos.close();
                                        } catch (Exception e) {
                                            e.printStackTrace();

                                        }
                                    }
                                }
                            }
                            // 删除临时目录中的分片文件
                            try {
                                System.out.println(destTempFile);
                                perFileService.deletePerFile(fileMd5);
                                FileUtils.deleteDirectory(parentFileDir);
                            } catch (Exception e) {
                                e.printStackTrace();
                                return e.toString();
                            } finally {

                                //FileUtils.deleteDirectory(parentFile);
                            }


                            if (MD5Utils.getFileMD5(destTempFile.getAbsolutePath()).equals(fileMd5)) {
                                // 如果数据表中没有这个文件，则添加。
                                fastUpload.domain.model.File checkMD5FileExist = fileService
                                        .checkMD5FileExist(fileMd5);
                                if (checkMD5FileExist == null) {
                                    byte temp = 0;
                                    fastUpload.domain.model.File file = new fastUpload.domain.model.File(destTempFile.getAbsolutePath(), temp, temp, fileMd5);
                                    String fileId= UUID.randomUUID().toString().substring(1,31);
                                    file.setFileId(fileId);
                                    file.setFileName( tempPartFile.getName());
                                    file.setFileSize(String.valueOf(size));
                                    fileService.insert(file);
                                    //如果出错了，要清理文件
                                    System.out.println(file);
                                    msg=file.getFileUrl();
                                } else {
                                    // 否则删除自己
                                    try {
                                        FileUtils.deleteDirectory(destTempFile);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                        return e.toString();
                                    }

                                }

                            }
                        }
                    }
                }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return msg;
    }


    @RequestMapping(value = "/checkMD5", method = {RequestMethod.POST})
    @ResponseBody
    public BizResponse checkMD5(HttpSession session, HttpServletRequest request, String firstFileMd5, @RequestParam int chunks,  @RequestParam String MD5, String savePath, String fileName) {
        BizResponse rm = null;
        String userId = "123456";


        fastUpload.domain.model.File checkMD5File = fileService.checkMD5FileExist(MD5);
        if (checkMD5File != null) {
            // 文件存在
            System.out.println("直接秒传");
            rm = new BizResponse(1000, "文件秒传", null, true);
        } else {

            List<String> chuck1list=perFileService.getPerfile(MD5);
            if(chuck1list.size()!=0){
                List<String> list=new ArrayList<String>();
            for(int i=0;i<chunks;i++){

                    if(chuck1list.contains(String.valueOf(i))){

                    }else{
                        list.add(String.valueOf(i));
                    }
            }
                String[] arr1 = list.toArray(new String[list.size()]);
                rm = new BizResponse(0, "正在恢复上传", arr1, true);
                return rm;
            }

            rm = new BizResponse(0, "文件不存在，请上传", null, true);
            System.out.println("文件不存在，请上传");


        }
        return rm;
    }

    private String getTempFilePath(Date date, String fileMd5, String userId) {
        String fileName = "";
        SimpleDateFormat df = new SimpleDateFormat("\\yyyy\\MM\\dd\\HH\\mm\\ss");
        TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
        fileName = uploadProperties.getFinalpath() + File.separator + fileMd5 + File.separator + userId;
        return fileName;
    }

    //上传完毕后转移缓存文件的目录
   /* private String getCopyFilePath(Date date, String fileMd5, String userId) {
        String fileName = "";
        SimpleDateFormat df = new SimpleDateFormat("\\yyyy\\MM\\dd\\HH\\mm\\ss");
        TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
        fileName = uploadProperties.getFinalpath() + File.separator + fileMd5 + File.separator + userId;
        return fileName;
    }*/

    //创建文件夹
    private File creatFloder(String filePath) {
        File F = new File(filePath);
        if (!F.exists()) {
            F.mkdirs();
        }
        return F;
    }

}
