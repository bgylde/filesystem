package cn.shaines.filesystem.controller;

import com.alibaba.fastjson.JSON;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import cn.shaines.filesystem.annotation.ChainRequired;
import cn.shaines.filesystem.treefile.FileInfo;
import cn.shaines.filesystem.treefile.FileTreeUtils;
import cn.shaines.filesystem.treefile.TreeNode;
import cn.shaines.filesystem.util.StringUtils;
import cn.shaines.filesystem.vo.Result;

/**
 * Created by wangyan on 2019-07-29
 */
@CrossOrigin(origins = "*", maxAge = 3600)                // 允许所有域名访问
@Controller
@RequestMapping("/tree")
public class FileTreeController {

    private static final String FILE_DIRECTORY = "/Users/wangyan/Desktop/chromium相关文档";

    @RequestMapping("")
    public String empty(){
        // 重定向到index
        return "redirect:/tree/index";
    }

    @RequestMapping("/index")
    public String index() {
        return "/tree/index";
    }

    /**
     * 返回json格式的文件树数据
     */
    @GetMapping("/files")
    @ResponseBody
    @ChainRequired
    public String fileTree() {
        TreeNode root = new TreeNode(FILE_DIRECTORY, null, null);
        FileTreeUtils.getFileTree(FILE_DIRECTORY, root);
        FileInfo fileInfo = new FileInfo(root);
        return JSON.toJSONString(fileInfo);
    }

    /**
     * 上传
     */
    @PostMapping("/upload")
    @ResponseBody
    @ChainRequired(ChainRequired.Type.CHECK)
    public Result upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        // 获取文件名
        String filename = file.getOriginalFilename();
        String filePath = FILE_DIRECTORY + File.separatorChar + filename;

        File tempFile = new File(filePath);
        String mapping = StringUtils.join(new String[]{
                ("".equals(request.getContextPath()) ? "" : "/" + request.getContextPath()),
                "/file/view/",
                tempFile.getName()}, "");
        Map<String, String> dataMap = new HashMap<>(1);
        dataMap.put("url", mapping);
        if (tempFile.exists()) {
            return Result.error("文件已存在，请重命名!", dataMap);
        }

        try {
            byte[] bytes = file.getBytes();

            FileTreeUtils.uploadFile(tempFile, bytes);
        } catch (IOException e) {
            return Result.success("操作成功", e);
        }

        return Result.success("操作成功", dataMap);
    }

    /**
     * 删除,根据name集合删除
     * JSON.stringify(data)
     */
    @ResponseBody
    @RequestMapping(value = "/deletes", method = RequestMethod.POST, consumes = "application/json")
    public Result delete(@RequestBody List<String> filePaths) {
        List<String> success = new ArrayList<>(filePaths.size());
        for (String path : filePaths) {
            File file = new File(path);
            if (file.exists() && file.delete()) {
                success.add(path);
            }
        }

        if (success.size() > 0) {
            return Result.success("Delete files success.", success);
        } else {
            return Result.error("Delete files fail.", filePaths);
        }
    }

    /**
     * 删除,根据名称删除
     */
    @DeleteMapping("/delete")
    @ResponseBody
    public Result delete(@RequestParam() String name) {
        File file = new File(name);
        if (file.exists() && file.delete()) {
            return Result.success("Delete file success.", name);
        } else {
            return Result.error("Delete file fail.", name);
        }
    }

    /**
     * 下载文件
     */
    @GetMapping("/download")
    @ResponseBody
    public ResponseEntity<Object> download(@RequestParam String name) {
        File file = new File(name);
        if (!file.exists()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("File cannot be found");
        }

        try {
            byte[] fileBytes = FileUtils.readFileToByteArray(file);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; fileName=" + new String(name.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1))
                    .header(HttpHeaders.CONTENT_TYPE, "application/octet-stream")
                    .header(HttpHeaders.CONTENT_LENGTH, fileBytes.length + "").header("Connection", "close")
                    .body(fileBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("File cannot be found");
    }
}
