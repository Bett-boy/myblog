package com.ycy.myblog.controller.back;

import com.ycy.myblog.bean.MessageInfo;
import com.ycy.myblog.service.MessageInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/back/message/")
public class MessageController {
    @Autowired
    private MessageInfoService messageInfoService;

    //查询;
    @RequestMapping("list")
    public String list(MessageInfo messageInfo, Model model) {
        List<MessageInfo> messageList = messageInfoService.getMessageList(messageInfo);
        model.addAttribute("messageList",messageList);
        model.addAttribute("messageInfo",messageInfo);
        return "back/message/message_list";
    }

    //留言的显示和不显示修改;
    @RequestMapping("update")
    public String update(MessageInfo messageInfo, Model model) {
        messageInfoService.update(messageInfo);
        List<MessageInfo> messageList = messageInfoService.getMessageList(messageInfo);
        model.addAttribute("messageList",messageList);
        return "back/message/message_list";
    }

    //删除留言;
    @RequestMapping("delete/{id}")
    public String delete(@PathVariable("id")Integer messageId,Model model,MessageInfo messageInfo) {
        boolean delete = messageInfoService.delete(messageId);
        if (delete) {
            model.addAttribute("message","删除成功");
        }
        else {
            model.addAttribute("message","删除失败！");
        }
        return "back/message/message_info";
    }
}
