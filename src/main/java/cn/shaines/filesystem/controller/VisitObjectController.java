package cn.shaines.filesystem.controller;

import cn.shaines.filesystem.entity.VisitObject;
import cn.shaines.filesystem.service.VisitobjectService;
import cn.shaines.filesystem.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author houyu
 * @createTime 2019/3/11 16:18
 */
@Controller
@RequestMapping("/visit")
public class VisitObjectController {

    // ----------------------------------------------------------- //
    // 页面跳转
    @RequestMapping("")
    public String empty(){
        // 重定向到index
        return "redirect:/visit/index";
    }

    @RequestMapping("/index")
    public String index(){
        return "/visit/index";
    }
    // ----------------------------------------------------------- //

    @Autowired
    private VisitobjectService visitobjectService;

    /**
     * 分页查询文件
     */
    @GetMapping("/page")
    @ResponseBody
    public Result page(@RequestParam(defaultValue = "0") int pageIndex, @RequestParam(defaultValue = "5") int pageSize, String name) {
        Sort sort = new Sort(Sort.Direction.DESC, "date");
        Pageable pageable = PageRequest.of(pageIndex, pageSize, sort);
        Page<VisitObject> page = "".equalsIgnoreCase(name) ? page = visitobjectService.findAll(pageable) : visitobjectService.findAllByUriIsContainingOrParamsIsContaining(name, name, pageable);
        return Result.success("请求成功", page);
    }


}
