package com.kzw.web;

/**
 * @author panaidong
 * @version V1.0
 * @Title:
 * @Description:
 * @date
 */
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import com.alibaba.fastjson.JSON;
import com.kzw.VO.BaseQueryVO;
import com.kzw.VO.Result;
import com.kzw.constant.SystemConstant;
import com.kzw.entity.UserEO;
import com.kzw.entity.UserFileEO;
import com.kzw.service.UserFileService;
import com.kzw.util.FileUtil;
import com.kzw.util.ImageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


/**
 * @author dalaoyang
 * @Description
 * @project springboot_learn
 * @package com.dalaoyang.Controller
 * @email yangyang@dalaoyang.cn
 * @date 2018/4/9
 */
@Controller
public class FileController {
    private static final Logger log = LoggerFactory.getLogger(FileController.class);
    private final ResourceLoader resourceLoader;
    @Autowired
    public FileController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
    @Autowired
    public UserFileService userFileService;
//    @RequestMapping(value = "/upload")
//    public String upload(HttpServletRequest request,@RequestParam("file") MultipartFile file) {
//        try {
//            if (file.isEmpty()) {
//                return "文件为空";
//            }
//
//            if (file.getSize()>20*1048576) {
//                return "文件过大超过20M";
//            }
//            UserEO user = (UserEO)request.getSession().getAttribute("user");
//            // 获取文件名
//            String fileName = file.getOriginalFilename();
//            log.info("上传的文件名为：" + fileName);
//            // 获取文件的后缀名
//            String suffixName = fileName.substring(fileName.lastIndexOf("."));
//            log.info("文件的后缀名为：" + suffixName);
//            // 设置文件存储路径
//            String filePath =getFilePath(user.getUserCode());
//            String path = filePath + fileName;
//            File dest = new File(path);
//            // 检测是否存在目录
//            if (!dest.getParentFile().exists()) {
//                dest.getParentFile().mkdirs();// 新建文件夹
//            }
//            file.transferTo(dest);// 文件写入
//            //将该文件夹下未生成缩略图的图片生成缩略图
//            ImageUtil.generateDirectoryThumbnail(filePath);
//            return "上传成功";
//        } catch (IllegalStateException e) {
//            log.error("上传失败",e);
//        } catch (IOException e) {
//            log.error("上传失败",e);
//        }
//        return "上传失败";
//    }
//
//    @PostMapping("/batch")
//    public String handleFileUpload(HttpServletRequest request) {
//        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
//        MultipartFile file = null;
//        BufferedOutputStream stream = null;
//        UserEO user = (UserEO)request.getSession().getAttribute("user");
//        String filePath =getFilePath(user.getUserCode());
//        for (int i = 0; i < files.size(); ++i) {
//            file = files.get(i);
//            if (!file.isEmpty()) {
//                if (file.getSize()>20*1048576) {
//                    return "文件过大超过20M";
//                }
//                long maxSize =FileUtil.getFileSize(filePath)+file.getSize();
//                boolean isReturn = false;
//                String result = null;
//                switch (user.getVipFlag()){
//                    case "0":
//                        if (maxSize>20*1048576) {
//                            isReturn = true;
//                        }
//                        break;
//                    case "1":
//                        if (maxSize>20*1048576) {
//                            isReturn = true;
//                        }
//                        break;
//                }
//               if ( isReturn){
//                    return "您的空间不足，不允许上传";
//               }
//                try {
//                    byte[] bytes = file.getBytes();
//                    stream = new BufferedOutputStream(new FileOutputStream(
//                            new File(filePath + file.getOriginalFilename())));//设置文件路径及名字
//                    stream.write(bytes);// 写入
//                    stream.close();
//                    //生成缩略图
//                    Thumbnails.of(filePath)
//                               .scale(0.4f)
//                               .toFile(filePath+SystemConstant.PHOTO_THUM_PATH+file.getOriginalFilename());
//                } catch (Exception e) {
//                    stream = null;
//                    return "第 " + i + " 个文件上传失败 ==> "
//                            + e.getMessage();
//                }
//            } else {
//                return "第 " + i
//                        + " 个文件上传失败因为文件为空";
//            }
//        }
//        return "上传成功";
//    }
//
//    @GetMapping("/download")
//    public String downloadFile(HttpServletRequest request, HttpServletResponse response) {
//        String fileName = "dalaoyang.jpeg";// 文件名
//        if (fileName != null) {
//            //设置文件路径
//            File file = new File("/Users/dalaoyang/Documents/dalaoyang.jpeg");
//            //File file = new File(realPath , fileName);
//            if (file.exists()) {
//                response.setContentType("application/force-download");// 设置强制下载不打开
//                response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);// 设置文件名
//                byte[] buffer = new byte[1024];
//                FileInputStream fis = null;
//                BufferedInputStream bis = null;
//                try {
//                    fis = new FileInputStream(file);
//                    bis = new BufferedInputStream(fis);
//                    OutputStream os = response.getOutputStream();
//                    int i = bis.read(buffer);
//                    while (i != -1) {
//                        os.write(buffer, 0, i);
//                        i = bis.read(buffer);
//                    }
//                    return "下载成功";
//                } catch (Exception e) {
//                    log.error("上传失败",e);
//                } finally {
//                    if (bis != null) {
//                        try {
//                            bis.close();
//                        } catch (IOException e) {
//                            log.error("上传失败",e);
//                        }
//                    }
//                    if (fis != null) {
//                        try {
//                            fis.close();
//                        } catch (IOException e) {
//                            log.error("上传失败",e);
//                        }
//                    }
//                }
//            }
//        }
//        return "下载失败";
//    }
    @RequestMapping(value = "/getFileSize")
    @ResponseBody
    public Result getFileSize(HttpServletRequest request){
        UserEO user = (UserEO)request.getSession().getAttribute("user");
        Double maxSize = new Double(FileUtil.getFileSize(getFilePath(user.getUserCode())));
        String size =null;
        int kb = 1024;
        int mb = 1024*1024;
        int gb = mb*kb;
       if(maxSize/kb<kb){
           size=maxSize/kb+"KB";
       }else if(maxSize/mb<kb){
           size=maxSize/kb+"MB";
       }else if(maxSize/gb<kb){
           size=maxSize/gb+"GB";
       }
        return Result.ok().put("size",size);
    }
    private String getFilePath(String userCode){
       return userCode+"/"+ SystemConstant.PHOTO_PATH;
    }
    @RequestMapping("/getDwFiles")
    @ResponseBody
    public Result getDwFiles(HttpServletRequest request,@RequestBody BaseQueryVO queryVo){
        int pageNum=0;
        int pageSize=10;
        if (queryVo.getPage()!=null){
            pageNum=queryVo.getPage()-1;
        }
        if (queryVo.getPageSize()!=null){
            pageSize=queryVo.getPageSize();
        }
        Sort sort = new Sort(Sort.Direction.DESC,"insertTime");
        Pageable page = new PageRequest(pageNum,pageSize,sort);
       Page<UserFileEO> pageList = userFileService.getSeeSlts(queryVo,page);
        return  Result.ok().put("files",pageList);
    }
    /**
     * show image 缩略图
     *
     * @param request
     * @return
     */
    @RequestMapping("/showImage")
    public ResponseEntity showImage(HttpServletRequest request, String fileName) {
        try {
            UserEO user = (UserEO)request.getSession().getAttribute("user");
            // 由于是读取本机的文件，file是一定要加上的， path是在application配置文件中的路径
            return ResponseEntity.ok(resourceLoader.getResource("file:" + getFilePath(user.getUserCode())+SystemConstant.PHOTO_THUM_PATH+"/" + fileName));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * show image 原图
     *
     * @param request
     * @return
     */
    @RequestMapping("/showImageReal")
    public ResponseEntity showImageReal(HttpServletRequest request, String fileName) {
        try {
            UserEO user = (UserEO)request.getSession().getAttribute("user");
            // 由于是读取本机的文件，file是一定要加上的， path是在application配置文件中的路径
            return ResponseEntity.ok(resourceLoader.getResource("file:" + getFilePath(user.getUserCode())+"/" + fileName));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }


    @RequestMapping(value = "/uploadOne", method = { RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Result uploadImage(HttpServletRequest request, @RequestParam("file")MultipartFile[] files) throws IOException {

//        MultipartHttpServletRequest req =(MultipartHttpServletRequest)request;
//        MultipartFile multipartFile =  req.getFile("file");
        try {
            for(MultipartFile multipartFile:files){


            String uniqueFlag =(String)request.getHeader("uniqueFlag");
            UserEO user =  SystemConstant.USER_MAP.get(uniqueFlag);
            if(user==null){
                return Result.error("请先登录！");
            }
            String realPath = SystemConstant.PHOTO_PATH+user.getUserCode()+"/";
            String sltPath = realPath+SystemConstant.PHOTO_THUM_PATH;
            File dir = new File(realPath);
            File dirThum = new File(sltPath);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            if (!dirThum.exists()) {
                dirThum.mkdirs();
            }
            String fileName =new Date().getTime()+".jpg";
            String fileDesc =(String)request.getParameter("fileDesc");
            String isSee =(String)request.getParameter("isSee");
            if (StringUtils.isEmpty(isSee)){
                isSee="0";
            }
            String xiangCe =(String)request.getParameter("xiangCe");
            File file  =  new File(realPath,fileName);
//            File fileThum  =  new File(sltPath,fileName);
            Result res=checkSize(multipartFile,user.getVipFlag(),realPath);
            if(res.isError()){
                return res;
            }
            multipartFile.transferTo(file);//真实图片
            //生成缩略图片
            ImageUtil.generateThumbnail2Directory(sltPath,realPath+fileName);
            UserFileEO userFile=new UserFileEO();
            userFile.setFileDesc(fileDesc);//文件描述
            userFile.setFileName(fileName);//文件名
            userFile.setSltName(fileName.substring(0,fileName.lastIndexOf("."))+"-thumbnail"+fileName.substring(fileName.lastIndexOf(".")));
            userFile.setInsertTime(new Date());
            userFile.setIsSee(isSee);//是否对外可见 1是，0否
            userFile.setXiangCe(xiangCe);//相册
            userFile.setRealPath(realPath);
            userFile.setSltPath(sltPath);
            userFile.setUserCode(user.getUserCode());
            userFileService.save(userFile);
            }
        } catch (Exception e) {
            log.error("图片上传失败",e);
            return  Result.error("图片上传失败");
        }
//        String uniqueFlag =(String)request.getHeader("uniqueFlag");
//        UserEO user =  SystemConstant.USER_MAP.get(uniqueFlag);
//        if(user==null){
//            return Result.error("请先登录！");
//        }
//        //获取文件需要上传到的路径
//        String realPath = SystemConstant.PHOTO_PATH+user.getUserCode()+"/";
//        String sltPath = realPath+SystemConstant.PHOTO_THUM_PATH;
//
//        String fileDesc =(String)request.getParameter("fileDesc");
//        String isSee =(String)request.getParameter("isSee");
//        if (StringUtils.isEmpty(isSee)){
//            isSee="0";
//        }
//        File dir = new File(realPath);
//        File dirThum = new File(sltPath);
//        if (!dir.exists()) {
//            dir.mkdirs();
//        }
//        if (!dirThum.exists()) {
//            dirThum.mkdirs();
//        }
//        String xiangCe =(String)request.getParameter("xiangCe");
//        request.setCharacterEncoding("utf-8");  //设置编码
//        //获得磁盘文件条目工厂
//        DiskFileItemFactory factory = new DiskFileItemFactory();
//
//        //如果没以下两行设置的话,上传大的文件会占用很多内存，
//        //设置暂时存放的存储室,这个存储室可以和最终存储文件的目录不同
//        /**
//         * 原理: 它是先存到暂时存储室，然后再真正写到对应目录的硬盘上，
//         * 按理来说当上传一个文件时，其实是上传了两份，第一个是以 .tem 格式的
//         * 然后再将其真正写到对应目录的硬盘上
//         */
//        factory.setRepository(dir);
//        //设置缓存的大小，当上传文件的容量超过该缓存时，直接放到暂时存储室
//        factory.setSizeThreshold(1024 * 1024);
//        //高水平的API文件上传处理
//        ServletFileUpload upload = new ServletFileUpload(factory);
//        try {
//            List<FileItem> list = upload.parseRequest(request);
//            FileItem picture = null;
//            for (FileItem item : list) {
//                //获取表单的属性名字
//                String name = item.getFieldName();
//                //如果获取的表单信息是普通的 文本 信息
//                if (item.isFormField()) {
//                    //获取用户具体输入的字符串
//                    String value = item.getString();
//                    request.setAttribute(name, value);
//                    log.info("name=" + name + ",value=" + value);
//                } else {
//                    picture = item;
//                }
//            }
//
//            //自定义上传图片的名字为userId.jpg
////            String fileName = request.getAttribute("userId") + ".jpg";
//            String fileName =new Date().getTime()+".jpg";
//            File fileThum  =  new File(sltPath,fileName);
//
//            //真正写到磁盘上
//            String destPath = sltPath + fileName;
//            File file = new File(destPath);
//            OutputStream out = new FileOutputStream(file);
//            InputStream in = picture.getInputStream();
//            int length = 0;
//            byte[] buf = new byte[1024];
//            // in.read(buf) 每次读到的数据存放在buf 数组中
//            while ((length = in.read(buf)) != -1) {
//                //在buf数组中取出数据写到（输出流）磁盘上
//                out.write(buf, 0, length);
//            }
//            in.close();
//            out.close();
//            ImageUtil.generateThumbnail2Directory(sltPath,realPath+fileName);
//
//            log.info("destPath=" + destPath);
//            UserFileEO userFile=new UserFileEO();
//            userFile.setFileDesc(fileDesc);//文件描述
//            userFile.setFileName(fileName);//文件名
//            userFile.setSltName(fileName.substring(0,fileName.lastIndexOf("."))+"-thumbnail"+fileName.substring(fileName.lastIndexOf(".")));
//            userFile.setInsertTime(new Date());
//            userFile.setIsSee(isSee);//是否对外可见 1是，0否
//            userFile.setXiangCe(xiangCe);//相册
//            userFile.setRealPath(realPath);
//            userFile.setSltPath(sltPath);
//            userFile.setUserCode(user.getUserCode());
//            userFileService.save(userFile);
//        } catch (FileUploadException e1) {
//            log.error("", e1);
//        } catch (Exception e) {
//            log.error("", e);
//        }
//
//
//        PrintWriter printWriter = response.getWriter();
//        response.setContentType("application/json");
//        response.setCharacterEncoding("utf-8");
//        HashMap<String, Object> res = new HashMap<String, Object>();
//        res.put("success", true);
//        printWriter.write(JSON.toJSONString(res));
//        printWriter.flush();
        return Result.ok("上传成功");
    }
    private Result checkSize(MultipartFile multipartFile,String vipFlag,String realPath){
    if (multipartFile.getSize()>20*1048576) {
        return Result.error("文件过大超过20M");
    }
    long maxSize =FileUtil.getFileSize(realPath)+multipartFile.getSize();
    boolean isReturn = false;
    String result = null;
    switch (vipFlag){
        case "0":
            if (maxSize>20*1048576) {
                isReturn = true;
            }
            break;
        case "1":
            if (maxSize>20*1048576) {
                isReturn = true;
            }
            break;
    }
    if ( isReturn){
        return Result.error("您的空间不足，不允许上传");
    }
    return Result.ok();
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Result delete(HttpServletRequest request,@RequestBody BaseQueryVO queryVo){
        if (StringUtils.isEmpty(queryVo.getUserCode())||StringUtils.isEmpty(queryVo.getId())){
            return Result.error("userCode或id为空");
        }
        try {
            userFileService.delete(queryVo);
        }catch (Exception e){
            log.error("删除失败",e);
            return Result.error();
        }
        return Result.ok();
    }
}
