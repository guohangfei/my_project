package util.io;


import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 *文件增量下载
 *
 * @Author:          郭航飞
 * @CreateDate:   2018/4/27 15:24
**/
public class AddDownFile {

    /**
     * 本地文件地址
     */
    private String localFilePath;
    /**
     * 网络文件地址
     */
    private String webFilePath;
    /**
    *文件名称
    */
    private String fileName;
    /**
     * 文件后缀
     */
    private String fileSuffixType;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getLocalFilePath() {
        return localFilePath;
    }

    public void setLocalFilePath(String localFilePath) {
        this.localFilePath = localFilePath;
    }

    public String getWebFilePath() {
        return webFilePath;
    }

    public void setWebFilePath(String webFilePath) {
        this.webFilePath = webFilePath;
    }

    public String getFileSuffixType() {
        return fileSuffixType;
    }

    public void setFileSuffixType(String fileSuffixType) {
        this.fileSuffixType = fileSuffixType;
    }

    /**
     *开始执行下载
     *
     * @Author:          郭航飞
     * @CreateDate:   2018/4/27 15:43
     * @param
     * @return
    **/
    public void startDown(String webFilePath,String localFilePath,String fileName,String fileSuffixType) throws IOException {

        BufferedOutputStream bos = null;
        InputStream is = null;
        String fileCompleteName=fileName+"."+fileSuffixType;
        try {
            byte[] buff = new byte[8192];
            is = new URL(webFilePath).openStream();
            File file = new File(localFilePath, fileCompleteName);
            file.getParentFile().mkdirs();
            bos = new BufferedOutputStream(new FileOutputStream(file));
            int count = 0;
            while ( (count = is.read(buff)) != -1) {
                bos.write(buff, 0, count);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }


    }

}
