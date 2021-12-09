package com.feicui.mybatisplus.plus.unit;

import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

public class FastDFSClient {

  public TrackerClient trackerClient = null;
  public TrackerServer trackerServer = null;
  public StorageServer storageServer = null;
  public StorageClient1 storageClient = null;

  public FastDFSClient() {

  }
  public FastDFSClient(String conf) throws Exception {
    if (conf.contains("classpath:")) {
      conf = conf.replace("classpath:", this.getClass().getResource("/").getPath());
    }
    ClientGlobal.init(conf);
    trackerClient = new TrackerClient();
    trackerServer = trackerClient.getConnection();
    storageServer = null;
    storageClient = new StorageClient1(trackerServer, storageServer);
  }


  /**
   * 上传文件方法
   * <p>Title: uploadFile</p>
   * <p>Description: </p>
   *
   * @param fileName 文件全路径
   * @param extName  文件扩展名，不包含（.）
   * @param metas    文件扩展信息
   * @return
   * @throws Exception
   */
  public String uploadFile(String fileName, String extName, NameValuePair[] metas) throws Exception {
    String result = storageClient.upload_file1(fileName, extName, metas);
    return result;
  }

  public String uploadFile(String fileName) throws Exception {
    return uploadFile(fileName, null, null);
  }

  public String uploadFile(String fileName, String extName) throws Exception {
    return uploadFile(fileName, extName, null);
  }

  /**
   * 上传文件方法
   * <p>Title: uploadFile</p>
   * <p>Description: </p>
   *
   * @param fileContent 文件的内容，字节数组
   * @param extName     文件扩展名
   * @param metas       文件扩展信息
   * @return
   * @throws Exception
   */
  public String uploadFile(byte[] fileContent, String extName, NameValuePair[] metas) throws Exception {

    String result = storageClient.upload_file1(fileContent, extName, metas);
    return result;
  }

  public String uploadFile(byte[] fileContent) throws Exception {
    return uploadFile(fileContent, null, null);
  }

  public String uploadFile(byte[] fileContent, String extName) throws Exception {
    return uploadFile(fileContent, extName, null);
  }

  /**
   * <B>方法名称：</B>下载方法<BR>
   * <B>概要说明：</B>通过文件id进行下载<BR>
   * @param fileId 文件id
   * @return 返回InputStream
   */
  public java.io.InputStream download(String groupName, String fileId) throws Exception {
    TrackerServer trackerServer = null;
    StorageServer storageServer = null;
    StorageClient1 storageClient1 = null;
//    FastDFSClient fastDFSClient = new FastDFSClient("client.conf");
    try {
      trackerServer = trackerClient.getConnection();
//      if (trackerServer == null) {
//        logger.error("getConnection return null");
//      }
      storageServer = trackerClient.getStoreStorage(trackerServer, groupName);
      storageClient1 = new StorageClient1(trackerServer, storageServer);
      byte[] bytes = storageClient1.download_file1(fileId);
      InputStream inputStream = new ByteArrayInputStream(bytes);
      return inputStream;
    } catch (Exception ex) {
//      logger.error(ex);
      return null;
    } finally {
      if (storageServer != null){
        try {
          storageServer.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      if (trackerServer != null){
        try {
          trackerServer.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      storageClient1 = null;
    }
  }
}
