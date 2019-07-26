package cn.shaines.filesystem.controller;

import cn.shaines.filesystem.entity.VisitObject;
import cn.shaines.filesystem.service.VisitObjectService;
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

import java.util.Iterator;

/**
 * @author houyu
 * @createTime 2019/3/11 16:18
 */
@Controller
@RequestMapping("/visit")
public class VisitObjectController {

    @Autowired
    public VisitObjectController(VisitObjectService visitobjectService) {
        this.visitobjectService = visitobjectService;
    }

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

    private final VisitObjectService visitobjectService;

    /**
     * 分页查询文件
     */
    @GetMapping("/page")
    @ResponseBody
    public Result page(@RequestParam(defaultValue = "0") int pageIndex, @RequestParam(defaultValue = "5") int pageSize, String name) {
        Sort sort = new Sort(Sort.Direction.DESC, "date");
        Pageable pageable = PageRequest.of(pageIndex, pageSize, sort);
        Page<VisitObject> page = "".equalsIgnoreCase(name) ? visitobjectService.findAll(pageable) : visitobjectService.findAllByUriIsContainingOrParamsIsContaining(name, name, pageable);
        Iterator<VisitObject> iterable = page.iterator();
        while (iterable.hasNext()) {
            VisitObject visitObject = iterable.next();
            System.out.println(visitObject.toString());
        }

        return Result.success("请求成功", page);
    }


}
