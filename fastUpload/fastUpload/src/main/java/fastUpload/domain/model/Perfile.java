package fastUpload.domain.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by HEHE on 2016/12/16.
 */
@Table(name="perfile")
public class Perfile {

    @Id
    @Column(name="perfile_id")
    @GeneratedValue(generator = "UUID")
    private String perFileId;

    @Column(name="perfile_size")
    private Long perFileSize;

    @Column(name="perfile_name")
    private String perFileName;

    @Column(name="perfile_chuck")
    private Long perFileChuck;

    @Column(name="perfile_url")
    private String perFileUrl;

    @Column(name="perfile_md5")
    private String perFileMd5;

    public Perfile() {
    }

    public Perfile(Long perFileSize, String perFileName, Long perFileChuck, String perFileUrl, String perFileMd5) {
        this.perFileSize = perFileSize;
        this.perFileName = perFileName;
        this.perFileChuck = perFileChuck;
        this.perFileUrl = perFileUrl;
        this.perFileMd5 = perFileMd5;
    }

    public String getPerFileId() {
        return perFileId;
    }

    public void setPerFileId(String perFileId) {
        this.perFileId = perFileId;
    }

    public Long getPerFileSize() {
        return perFileSize;
    }

    public void setPerFileSize(Long perFileSize) {
        this.perFileSize = perFileSize;
    }

    public String getPerFileName() {
        return perFileName;
    }

    public void setPerFileName(String perFileName) {
        this.perFileName = perFileName;
    }

    public Long getPerFileChuck() {
        return perFileChuck;
    }

    public void setPerFileChuck(Long perFileChuck) {
        this.perFileChuck = perFileChuck;
    }

    public String getPerFileUrl() {
        return perFileUrl;
    }

    public void setPerFileUrl(String perFileUrl) {
        this.perFileUrl = perFileUrl;
    }

    public String getPerFileMd5() {
        return perFileMd5;
    }

    public void setPerFileMd5(String perFileMd5) {
        this.perFileMd5 = perFileMd5;
    }

    @Override
    public String toString() {
        return "Perfile{" +
                "perFileId='" + perFileId + '\'' +
                ", PerFileSize='" + perFileSize + '\'' +
                ", PerFileName='" + perFileName + '\'' +
                ", PerFileChuck=" + perFileChuck +
                ", PerFileUrl='" + perFileUrl + '\'' +
                ", PerFileMd5='" + perFileMd5 + '\'' +
                '}';
    }
}
