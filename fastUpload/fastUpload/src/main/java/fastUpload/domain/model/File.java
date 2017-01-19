package fastUpload.domain.model;

/**
 * Created by HEHE on 2016/12/13.
 */
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name="sfile")
public class File {

    @Id
    @Column(name = "file_id")
    @GeneratedValue(generator = "UUID")
    private String fileId;
    @Column(name = "file_name")
    private String fileName;
    @Column(name = "file_url")
    private String fileUrl;
    @Column(name = "file_type")
    private Byte fileType;
    @Column(name = "file_state")
    private Byte fileState;
    @Column(name = "file_md5")
    private String fileMD5;
    @Column(name = "create_time")
    private Date createTime;
    @Column(name = "update_time")
    private Date updateTime;
    @Column(name="firstfile_md5")
    private String firstFileMd5;
    @Column(name="file_size")
    private String fileSize;


    public File() {
    }

    public File(String fileName, String fileUrl, Byte fileType, Byte fileState, String fileMD5, String firstFileMd5, String fileSize) {
        this.fileName = fileName;
        this.fileUrl = fileUrl;
        this.fileType = fileType;
        this.fileState = fileState;
        this.fileMD5 = fileMD5;
        this.firstFileMd5 = firstFileMd5;
        this.fileSize = fileSize;
        this.createTime = new Date();
        this.updateTime = createTime;
    }

    public File(String fileUrl, Byte fileType, Byte fileState, String fileMD5) {
        super();
        this.fileUrl = fileUrl;
        this.fileType = fileType;
        this.fileState = fileState;
        this.fileMD5 = fileMD5;
        this.createTime = new Date();
        this.updateTime = createTime;

    }



    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getFirstFileMd5() {
        return firstFileMd5;
    }

    public void setFirstFileMd5(String firstFileMd5) {
        this.firstFileMd5 = firstFileMd5;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String filrName) {
        this.fileName = filrName;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public Byte getFileType() {
        return fileType;
    }

    public void setFileType(Byte fileType) {
        this.fileType = fileType;
    }

    public Byte getFileState() {
        return fileState;
    }

    public void setFileState(Byte fileState) {
        this.fileState = fileState;
    }

    public String getFileMD5() {
        return fileMD5;
    }

    public void setFileMD5(String fileMD5) {
        this.fileMD5 = fileMD5;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }




    @Override
    public String toString() {
        return "File{" +
                "fileId=" + fileId +
                ", fileName='" + fileName + '\'' +
                ", fileUrl='" + fileUrl + '\'' +
                ", fileType=" + fileType +
                ", fileState=" + fileState +
                ", fileMD5='" + fileMD5 + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", firstFileMd5='" + firstFileMd5 + '\'' +
                ", fileSize='" + fileSize + '\'' +
                '}';
    }
}