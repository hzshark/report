package cn.xp.hashpower.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import com.aliyun.oss.ClientConfiguration;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.auth.CredentialsProvider;
import com.aliyun.oss.common.auth.CustomSessionCredentialsProvider;
import com.aliyun.oss.model.Bucket;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;

public class OSSUnit {

	// log
	private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(OSSUnit.class);

	// 阿里云API的内或外网域名
	private static String ENDPOINT;
	// 阿里云API的密钥Access Key ID
	private static String ACCESS_KEY_ID;
	// 阿里云API的密钥Access Key Secret
	private static String ACCESS_KEY_SECRET;
	// 阿里云OSS的bucketName
	private static String BUCKET_NAME; 
	
//	public static final String OSS_DOMAIN;
	
	private static OSSClient oSSClient;

	// init static datas
	static {
		ResourceBundle bundle = PropertyResourceBundle.getBundle("oss");
		ENDPOINT = bundle.containsKey("endpoint") == false ? "" : bundle.getString("endpoint");
		ACCESS_KEY_ID = bundle.containsKey("accessKeyId") == false ? "" : bundle.getString("accessKeyId");
		ACCESS_KEY_SECRET = bundle.containsKey("accessKeySecret") == false ? "" : bundle.getString("accessKeySecret");
		//默认为测试bucket
		BUCKET_NAME = bundle.containsKey("oss_buctet_name") == false ? "huoq-test" : bundle.getString("oss_buctet_name");
		//默认为测试域名
//		OSS_DOMAIN = bundle.containsKey("oss_domain") == false ? "http://file-test.huoq.com" : bundle.getString("oss_domain");
//		SystemConfig systemConfig =(SystemConfig)ContextLoader.getCurrentWebApplicationContext().getServletContext().getAttribute("systemConfig");
//		OSS_DOMAIN = systemConfig.getHttpUrl();
	}

	/**
	 * 获取阿里云OSS客户端对象
	 */
	public static  OSSClient getOSSClient() {
/*		ClientConfiguration clientConfiguration=new ClientConfiguration();
		CredentialsProvider credsProvider=new CustomSessionCredentialsProvider(ACCESS_KEY_ID,ACCESS_KEY_SECRET);
						OSSClient(String endpoint, CredentialsProvider credsProvider, ClientConfiguration config)*/
		// 创建ClientConfiguration实例，按照您的需要修改默认参数
		ClientConfiguration conf = new ClientConfiguration();
		// 设置OSSClient使用的最大连接数，默认1024
		conf.setMaxConnections(200);
		// 设置请求超时时间，默认50秒
		conf.setSocketTimeout(10000);
		// 设置失败请求重试次数，默认3次
		conf.setMaxErrorRetry(5);

		oSSClient = new OSSClient(ENDPOINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET,conf);
		return oSSClient;
	}

	/**
	 * 新建Bucket --Bucket权限:私有
	 * 
	 * @param bucketName
	 *            bucket名称
	 * @return true 新建Bucket成功
	 */
	public static final boolean createBucket(String bucketName) {
		Bucket bucket = getOSSClient().createBucket(bucketName);
		return bucketName.equals(bucket.getName());
	}

	/**
	 * 删除Bucket
	 * 
	 * @param bucketName
	 *            bucket名称
	 */
	public static final void deleteBucket(String bucketName) {
		getOSSClient().deleteBucket(bucketName);
		log.info("删除" + bucketName + "Bucket成功");
	}

	/**
	 * 向阿里云的OSS存储中存储文件 --file也可以用InputStream替代
	 * 
	 * @param file
	 *            待上传文件的全路径
	 * @param bucketPath
	 *            上传文件的目录 --bucket下文件的路径
	 * @return String 唯一MD5数字签名
	 */
	public static final String uploadObject2OSS(File file, String bucketPath) {
		return uploadObject2OSS(file, BUCKET_NAME, bucketPath);
	}

	/**
	 * 向阿里云的OSS存储中存储文件 --file也可以用InputStream替代
	 * 
	 * @param file
	 *            待上传文件的全路径
	 * @param bucketName
	 *            bucket名称
	 * @param bucketPath
	 *            上传文件的目录 --bucket下文件的路径
	 * @return String 唯一MD5数字签名
	 */
	public static final String uploadObject2OSS(File file, String bucketName, String bucketPath) {
		String resultStr = null;
		try {
			InputStream is = new FileInputStream(file);
			String fileName = file.getName();
			Long fileSize = file.length();
			// 创建上传Object的Metadata
			ObjectMetadata metadata = new ObjectMetadata();
			metadata.setContentLength(is.available());
			metadata.setCacheControl("no-cache");
			metadata.setHeader("Pragma", "no-cache");
			metadata.setContentEncoding("utf-8");
			metadata.setContentType(getContentType(fileName));
			metadata.setContentDisposition("filename/filesize=" + fileName + "/" + fileSize + "Byte.");
			// 上传文件
			PutObjectResult putResult = getOSSClient().putObject(bucketName, bucketPath + fileName, is, metadata);
			// 解析结果
			resultStr = putResult.getETag();
		} catch (Exception e) {
			log.error("上传阿里云OSS服务器异常." + e.getMessage(), e);
		}
		return resultStr;
	}

	/**
	 * 向阿里云的OSS存储中存储文件
	 * 
	 * @param inputStream
	 *            输入流
	 * @param fileName
	 *            文件名(含后缀)
	 * @param bucketPath
	 *            上传文件的目录 --bucket下文件的路径
	 * @return String 唯一MD5数字签名
	 */
	public static final String uploadObject2OSS(InputStream inputStream, String fileName, String bucketPath) {
		return uploadObject2OSS(inputStream, fileName, BUCKET_NAME, bucketPath);
	}

	/**
	 * @param inputStream
	 *            输入流
	 * @param fileName
	 *            文件名(含后缀)
	 * @param bucketName
	 *            OSS的bucketName
	 * @param bucketPath
	 *            上传文件的目录 --bucket下文件的路径
	 * @return String 唯一MD5数字签名
	 */
	public static final String uploadObject2OSS(InputStream inputStream, String fileName,
			String bucketName, String bucketPath) {
		String resultStr = null;
		try {
			// 上传文件
			PutObjectResult putResult = getOSSClient().putObject(bucketName, bucketPath + fileName, inputStream);
			// 解析结果
			resultStr = putResult.getETag();
			log.info(bucketPath + fileName+"文件上传成功...");
		} catch (Exception e) {
			log.error("上传阿里云OSS服务器异常." + e.getMessage(), e);
		}
		return resultStr;
	}

	/**
	 * 根据key获取OSS服务器上的文件输入流
	 * 
	 * @param bucketName
	 *            bucket名称
	 * @param diskName
	 *            文件路径
	 * @param key
	 *            Bucket下的文件的路径名+文件名
	 */
	public static final InputStream getOSS2InputStream(String bucketName, String diskName,
			String key) {
		OSSObject ossObj = getOSSClient().getObject(bucketName, diskName + key);
		return ossObj.getObjectContent();
	}
	 /**
	 * @param bucketPath
	 * 		上传文件的目录 --bucket下文件的路径
	 * @return
	 */
	public static final InputStream getOSS2InputStream(String bucketPath) {
		OSSObject ossObj = getOSSClient().getObject(BUCKET_NAME, bucketPath);
		return ossObj.getObjectContent();
	}

	/**
	 * 根据key删除OSS服务器上的文件
	 * 
	 * @param bucketName
	 *            bucket名称
	 * @param diskName
	 *            文件路径
	 * @param key
	 *            文件名
	 */
	public static void deleteFile(String bucketName, String diskName, String key) {
		getOSSClient().deleteObject(bucketName, diskName + key);
		log.info("删除" + bucketName + "下的文件" + diskName + key + "成功");
	}

	/**
	 * 根据key删除OSS服务器上的文件
	 * 
	 * @param bucketPath
	 *            阿里云OSS服务上的bucket路径
	 * @param fileName
	 *            文件名
	 */
	public static void deleteFile(String bucketPath, String fileName) {
		getOSSClient().deleteObject(BUCKET_NAME, bucketPath + fileName);
		log.info("删除" + BUCKET_NAME + "下的文件" + bucketPath + fileName + "成功");
	}

	/**
	 * 通过文件名判断并获取OSS服务文件上传时文件的contentType
	 * 
	 * @param fileName
	 *            文件名
	 * @return 文件的contentType
	 */
	public static final String getContentType(String fileName) {
		int index = fileName.lastIndexOf(".");
		String fileExtension = fileName.substring(index>-1?index+1:index);
		if ("bmp".equalsIgnoreCase(fileExtension))
			return "image/bmp";
		if ("gif".equalsIgnoreCase(fileExtension))
			return "image/gif";
		if ("jpeg".equalsIgnoreCase(fileExtension) || "jpg".equalsIgnoreCase(fileExtension)
				|| "png".equalsIgnoreCase(fileExtension))
			return "image/jpeg";
		if ("html".equalsIgnoreCase(fileExtension))
			return "text/html";
		if ("txt".equalsIgnoreCase(fileExtension))
			return "text/plain";
		if ("vsd".equalsIgnoreCase(fileExtension))
			return "application/vnd.visio";
		if ("ppt".equalsIgnoreCase(fileExtension) || "pptx".equalsIgnoreCase(fileExtension))
			return "application/vnd.ms-powerpoint";
		if ("doc".equalsIgnoreCase(fileExtension) || "docx".equalsIgnoreCase(fileExtension))
			return "application/msword";
		if ("xml".equalsIgnoreCase(fileExtension))
			return "text/xml";
		return "text/html";
	}

	public static void main(String[] args) {
//		OSSClient client = new OSSClient(OSSUnit.ENDPOINT, OSSUnit.ACCESS_KEY_ID, OSSUnit.ACCESS_KEY_SECRET);
		String diskName = "test/ab/test/";
		String filePath = "C:\\Users\\Administrator\\Desktop\\日常.txt";// 本地或者服务器上文件的路径
		File file = new File(filePath);
		String result = OSSUnit.uploadObject2OSS( file, diskName);
//		OSSUnit.deleteFile("", "test.apk日常.txt");
//		System.out.println(result);
	}

}
