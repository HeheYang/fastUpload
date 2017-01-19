package fastUpload.util;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by HEHE on 2016/12/20.
 */
@ConfigurationProperties(prefix = "upload",locations = "classpath:/config.properties")
public class UploadProperties {

    /**
     * 配置文件类
     * */

    private String tempath;
    private String finalpath;

    public String getTempath() {
        return tempath;
    }

    public void setTempath(String tempath) {
        this.tempath = tempath;
    }

    public String getFinalpath() {
        return finalpath;
    }

    public void setFinalpath(String finalpath) {
        this.finalpath = finalpath;
    }
}
