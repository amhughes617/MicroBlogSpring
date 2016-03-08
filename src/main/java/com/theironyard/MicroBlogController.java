package com.theironyard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by alexanderhughes on 3/7/16.
 */
@Controller
public class MicroBlogController {
    @Autowired
    UserRepository users;
    @Autowired
    MessageRepository messages;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(Model model, HttpSession session, Integer id) {
        User user = users.findByuserName((String)session.getAttribute("userName"));
        if (user != null) {
            if (id != null) {
                Message message = messages.findOne(id);
                model.addAttribute("message", message);
                return "edit";
            }
            else {
                model.addAttribute("userName", user.getName());
                model.addAttribute("messages", user.getList());
                return "home";
            }
        }
        else {
            return "home";
        }
    }
    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login(HttpSession session, String userName, String password) {
        User user = users.findByuserName(userName);
        if (user == null) {
            user = users.save(new User(userName, password));
        }
        session.setAttribute("userName", userName);
        return "redirect:/";
    }
    @RequestMapping(path = "/post-msg", method = RequestMethod.POST)
    public String post(String msg, HttpSession session) {
        User user = users.findByuserName((String)session.getAttribute("userName"));
        Message message = new Message(msg);
        message.setUser(user);
        messages.save(message);
        return "redirect:/";
    }
    @RequestMapping(path = "/delete", method = RequestMethod.POST)
    public String delete(int id) {
        messages.delete(id);
        return "redirect:/";
    }
    @RequestMapping(path = "/logout", method = RequestMethod.POST)
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
    @RequestMapping(path = "/edit", method = RequestMethod.POST)
    public String edit(String text, Integer id) {
        Message message = messages.findOne(id);
        message.setText(text);
        messages.save(message);
        return "redirect:/";
    }
}
